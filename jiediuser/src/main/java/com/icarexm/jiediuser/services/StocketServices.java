package com.icarexm.jiediuser.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icarexm.jiediuser.bean.GetOrderDirverBean;
import com.icarexm.jiediuser.bean.LoginDemoBean;
import com.icarexm.jiediuser.bean.OrderDirverBean;
import com.icarexm.jiediuser.bean.PlaceOrderBean;
import com.icarexm.jiediuser.bean.PositionBean;
import com.icarexm.jiediuser.bean.PositionsBean;
import com.icarexm.jiediuser.bean.RefuseOrderBean;
import com.icarexm.jiediuser.bean.ServicesMsgBean;
import com.icarexm.jiediuser.bean.pointsBean;
import com.icarexm.jiediuser.presenter.HomePresenter;
import com.icarexm.jiediuser.utils.RequstUrlUtils;
import com.icarexm.jiediuser.utils.ToastUtils;
import com.icarexm.jiediuser.view.HomeActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class StocketServices extends Service {
    private final IBinder binder = new LocalBinder(); // 服务绑定器
    private SharedPreferences sp;
    private String token="";
    private WebSocket mWebSocket;
    private String user_id;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    private AMapLocationClientOption mLocationOption;
    private double longitude;
    private double latitude;
    private String positionS;
    private float speed;
    private String city;
    private int HEART_BEAT_RATE=3000;
    private List<pointsBean> pointsList=new ArrayList<>();
    private String mobile;
    private boolean IsRecrivedOrders=false;
    private int driverId=0;
    private boolean IsCity=true;
    private  int delayMillis=200;

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    /**
     * 配置服务连接监听器类
     */
    public class LocalBinder extends Binder {
        public StocketServices getService() {
            return StocketServices.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        user_id = sp.getString("user_id", "");
        mobile = sp.getString("mobile", "");
        SetLocations();
    }

    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    //获取纬度
                    latitude = aMapLocation.getLatitude();
                    //获取经度
                    longitude = aMapLocation.getLongitude();
                    aMapLocation.getAccuracy();//获取精度信息
                    aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    aMapLocation.getCountry();//国家信息
                    aMapLocation.getProvince();//省信息
                    aMapLocation.getProvince();//城市信息
                    aMapLocation.getDistrict();//城区信息
                    aMapLocation.getStreet();//街道信息
                    aMapLocation.getStreetNum();//街道门牌号信息
                    aMapLocation.getCityCode();//城市编码
                    aMapLocation.getAdCode();//地区编码
                    aMapLocation.getAoiName();//获取当前定位点的AOI信息
                    aMapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                    aMapLocation.getFloor();//获取当前室内定位的楼层
                    aMapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
                    speed = aMapLocation.getSpeed();
                    city = aMapLocation.getCity();
                    //获取定位时间
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(aMapLocation.getTime());
                    String format = df.format(date);
                    positionS = aMapLocation.getProvince()+aMapLocation.getProvince()+aMapLocation.getDistrict()+aMapLocation.getStreetNum();
                    String locations = longitude + "," + latitude ;
                    pointsBean pointsBean = new pointsBean(locations, format, aMapLocation.getSpeed() + "", aMapLocation.getDistrict() + "", aMapLocation.getAltitude() + "", aMapLocation.getAccuracy() + "");
                    pointsList.add(pointsBean);
                    if (IsCity){
                        initSocket();
                        IsCity=false;
                    }
                }else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError","location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    };

    //定位参数配置
    private void SetLocations() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationOption = new AMapLocationClientOption();
        /**
         * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
         */
        mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.Transport);
        if(null != mLocationClient){
            mLocationClient.setLocationOption(mLocationOption);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取最近3s内精度最高的一次定位结果：
        mLocationOption.setOnceLocationLatest(true);
        //设置定位间隔,单位毫秒
        mLocationOption.setInterval(3000);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    //开启长连接
    private void initSocket() {
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(0, TimeUnit.MILLISECONDS).build();
        final Request request = new Request.Builder().url(RequstUrlUtils.URL.WEBSOCKET_HOST_AND_PORT).build();
        client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {//开启长连接成功的回调
                super.onOpen(webSocket, response);
                Log.e("BackService",city+"进来了"+token);
                mWebSocket = webSocket;
                String s = new Gson().toJson(new LoginDemoBean(token, "0", user_id,"login",new LoginDemoBean.data(city)));
                mWebSocket.send(s);
                HeartBateHandler.postDelayed(HeartBateRundbler, HEART_BEAT_RATE);
            }

            @Override
            public void onMessage(WebSocket webSocket, final String text) {//接收消息的回调
                super.onMessage(webSocket, text);
                //收到服务器端传过来的消息text
//                Log.e("BackService1",text);
                  try {
                      Gson gson = new GsonBuilder().create();
                      ServicesMsgBean servicesMsgBean = gson.fromJson(text, ServicesMsgBean.class);
                      if (servicesMsgBean!=null){
                          if (servicesMsgBean.getCode()==200){
                              String event = servicesMsgBean.getEvent();
                              if (event.equals("login")){
                                HomeActivity.GetOrderStatus("0");
                              }else if (event.equals("place_order")){
                                  HomeActivity.GetOrderStatus("0");
                              }else if (event.equals("deliver")){
                                  IsRecrivedOrders=true;
                                  delayMillis=500;
                                  HomeActivity.GetOrderStatus("1");
                              }else if (event.equals("driver_arrive")){
                                  HomeActivity.GetOrderStatus("2");
                              }else if (event.equals("passenger_boarding")){
                                  HomeActivity.GetOrderStatus("3");
                              }else if (event.equals("arrive")){
                                  HomeActivity.GetOrderStatus("4");
                              }else if (event.equals("refuse_order")){
                                  HomeActivity.CancelOrder();
                              }else if (event.equals("get_order_dirver")){
                                 HomeActivity.UpdateDervierLoaction(text);
                              }
                          }else {
                              ToastUtils.showToast(getApplicationContext(),servicesMsgBean.getMsg());
                          }
                      }
                  }catch (Exception e){
                  }

            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                super.onMessage(webSocket, bytes);
//                Log.e("BackService2",bytes.toString());
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                super.onClosing(webSocket, code, reason);
//                Log.e("BackService3",reason);
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                super.onClosed(webSocket, code, reason);
//                Log.e("BackService4",reason);
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {//长连接连接失败的回调
                super.onFailure(webSocket, t, response);
//                Log.e("BackService5","发发发");
            }
        });
        client.dispatcher().executorService().shutdown();
    }

    //实时发送心跳包
    Handler HeartBateHandler=new Handler();
    Runnable HeartBateRundbler=new Runnable() {
        @Override
        public void run() {
         position();
         HeartBateHandler.postDelayed(this, 5000);//每隔5s的时间，对长连接进行一次心跳检测
            if (IsRecrivedOrders){
               get_order_dirver();
            }
        }
    };


    //更新自己位置 /api/socketobj/position
    public void position(){
        String Receipts=new Gson().toJson(new PositionsBean(token, "0", user_id,"position",new PositionsBean.data(longitude+"",latitude+"",positionS,speed+"",city,new Gson().toJson(pointsList))));
        pointsList.clear();
        boolean isSuccess = mWebSocket.send("");
        if (!isSuccess) {//长连接已断开
            mWebSocket.cancel();//取消掉以前的长连接
            mWebSocket.send(Receipts);
        } else {//长连接处于连接状态
            mWebSocket.send(Receipts);
        }
    }


    //用户下单
    public void place_order(String startingpointE,String startingpointN,String startingpoint,String destinationE,String destinationN,
                            String destination,String estimated_mileage,String estimated_time,String budget,String service_type,
                            String city,String flightno,String estimatedeparturetime){
        String place_order = new Gson().toJson(new PlaceOrderBean(token, "0", mobile, user_id, "place_order", new PlaceOrderBean.data(startingpointE, startingpointN, startingpoint, destinationE, destinationN, destination, estimated_mileage
                , estimated_time, budget, service_type, city, flightno, estimatedeparturetime)));
        boolean isSuccess = mWebSocket.send("");
        if (!isSuccess) {//长连接已断开
            mWebSocket.cancel();//取消掉以前的长连接
            mWebSocket.send(place_order);
        } else {//长连接处于连接状态
            mWebSocket.send(place_order);
        }
    }



    //拒绝订单/取消订单
    public void refuse_order(String orderId,String reason,String remark){
        String Receipts = new Gson().toJson(new RefuseOrderBean(token, "0", user_id,"refuse_order", new RefuseOrderBean.data(orderId, reason,remark)));
        boolean isSuccess = mWebSocket.send("");
        if (!isSuccess) {//长连接已断开
            mWebSocket.cancel();//取消掉以前的长连接
            mWebSocket.send(Receipts);
        } else {//长连接处于连接状态
            mWebSocket.send(Receipts);
        }
    }

    //获取司机的位置
    public void get_order_dirver(){
        String orderBean = new Gson().toJson(new OrderDirverBean(token, "0", user_id,"get_order_dirver"));
        boolean isSuccess = mWebSocket.send("");
        if (!isSuccess) {//长连接已断开
            mWebSocket.cancel();//取消掉以前的长连接
            mWebSocket.send(orderBean);
        } else {//长连接处于连接状态
            mWebSocket.send(orderBean);
        }
    }

    //已经有订单需要更新司机位置
    public void  UpdateRecrivedOrders(){
        IsRecrivedOrders=true;
        delayMillis=500;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.stopLocation();
        mLocationClient.onDestroy();
    }
}
