package com.icarexm.jiedi.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icarexm.jiedi.Bean.DeliverBean;
import com.icarexm.jiedi.Bean.DriverArriveBean;
import com.icarexm.jiedi.Bean.LoginDemoBean;
import com.icarexm.jiedi.Bean.OrderListBean;
import com.icarexm.jiedi.Bean.OrderListOneBean;
import com.icarexm.jiedi.Bean.PositionsBean;
import com.icarexm.jiedi.Bean.ReceiptBean;
import com.icarexm.jiedi.Bean.RefuseOrderBean;
import com.icarexm.jiedi.Bean.ServicesMsgBean;
import com.icarexm.jiedi.Bean.UserToDriverBean;
import com.icarexm.jiedi.Bean.pointsBean;
import com.icarexm.jiedi.R;
import com.icarexm.jiedi.adapter.HomeAdapter;
import com.icarexm.jiedi.contract.HomeContract;
import com.icarexm.jiedi.custView.KeepCountdownView;
import com.icarexm.jiedi.presenter.HomePresenter;
import com.icarexm.jiedi.service.StocketServices;
import com.icarexm.jiedi.utils.MxyUtils;
import com.icarexm.jiedi.utils.RequstUrlUtils;
import com.icarexm.jiedi.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.zhouyou.recyclerview.XRecyclerView;
import com.zhouyou.recyclerview.adapter.BaseRecyclerViewAdapter;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    @BindView(R.id.home_recyclerView)
    XRecyclerView mRecyclerView;
    @BindView(R.id.home_btn_gainorder)
    Button btn_gainorder;
    @BindView(R.id.home_tv_receipt_number)
    TextView tv_receipt_number;
    @BindView(R.id.home_tv_receipt_price)
    TextView tv_receipt_price;
    @BindView(R.id.home_tv_receipt_evaluate)
    TextView tv_receipt_evaluate;


    private List<OrderListBean.DataBean.OrderBean> list=new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    private static Context mContext;
    private HomeAdapter homeAdapter;
    private static AlertDialog alertDialog;
    private static View dialog_home;
    private static KeepCountdownView countDownProgressBar;
    private static AlertDialog.Builder builder;
    private SharedPreferences sp;
    private String token;
    private String user_id;
    //声明AMapLocationClient类对象
    public static AMapLocationClient mLocationClient = null;
    private AMapLocationClientOption mLocationOption;
    private static String city="";
    private static String longitude="0";
    private static String latitude="0";
    private static HomePresenter homePresenter;

    private TextView dialog_home_tv_time;
    private TextView dialog_home_tv_distance;
    private TextView home_dialog_tv_startingpoint;
    private TextView home_dialog_tv_destination;
    private String order_id;
    private static String automaticOrder;
    private static boolean isAutomatic=false;
    private View dialog_callphone;
    private TextView tv_phone_number;
    private WebSocket mWebSocket;

    private int HEART_BEAT_RATE=3000;
    private List<pointsBean> pointsList=new ArrayList<>();
    private float speed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mContext = getApplicationContext();
        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        user_id = sp.getString("user_id", "");
        ButterKnife.bind(this);
        SetLocations();
        InitUI();
        homePresenter = new HomePresenter(this);
        homePresenter.GetIndex(token);
        orderHandler.postDelayed(orderRunnable,200);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        homePresenter.GetIndex(token);
        orderHandler.postDelayed(orderRunnable,200);
        super.onNewIntent(intent);
    }


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

    private void InitUI() {
        dialog_home = getLayoutInflater().inflate(R.layout.dialog_home, null);
        dialog_home_tv_time = dialog_home.findViewById(R.id.dialog_home_tv_time);
        dialog_home_tv_distance = dialog_home.findViewById(R.id.dialog_home_tv_distance);
        home_dialog_tv_startingpoint = dialog_home.findViewById(R.id.home_dialog_tv_startingpoint);
        home_dialog_tv_destination = dialog_home.findViewById(R.id.home_dialog_tv_destination);
        countDownProgressBar = dialog_home.findViewById(R.id.dialog_home_countDownProgress);
        countDownProgressBar.plus(10);
        countDownProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                alertDialog=null;
                homePresenter.GetAutoOrder(token,"0");
                btn_gainorder.setText("接单");
                btn_gainorder.setBackground(getResources().getDrawable(R.drawable.btn_login));
                orderHandler.removeCallbacks(orderRunnable);
                if (!longitude.equals("0")) {
                    Receipt(order_id, longitude + "", latitude + "", city);
                }
            }
        });
        //倒计时监听
        countDownProgressBar.setCountdownListener(new KeepCountdownView.CountdownListener() {
            @Override
            public void onStart() {
                Log.e("home","开始");
            }

            @Override
            public void onEnd() {
                alertDialog.dismiss();
                alertDialog=null;
            }
        });
        mRecyclerView.setNestedScrollingEnabled(false);
        mLayoutManager = new LinearLayoutManager(mContext);
        homeAdapter = new HomeAdapter(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setFootViewText("拼命加载中","已经全部");
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                mRecyclerView.refreshComplete();//刷新动画完成
            }

            @Override
            public void onLoadMore() {
                homeAdapter .addItemsToLast(list);
                homeAdapter .notifyDataSetChanged();
                //加载更多
                mRecyclerView.loadMoreComplete();//加载动画完成
                mRecyclerView.setNoMore(true);//数据加载完成
            }
        });
        mRecyclerView.setAdapter(homeAdapter );
        homeAdapter.setListAll(list);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0
                        , 0
                        , 0
                        , MxyUtils.dpToPx(mContext, MxyUtils.getDimens(mContext, R.dimen.dp_24)));
            }
        });
        homeAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object item, int position) {
                orderHandler.removeCallbacks(orderRunnable);
                order_id=list.get(position).getId()+"";
                Receipt(list.get(position).getId()+"",longitude+"",latitude+"",city);
            }
        });
    }

    //开启和关闭接单功能
    public void ShowToast(String content){
        if (content.equals("1")){
            ToastUtils.showToast(mContext,"开启自动接收订单成功");
            btn_gainorder.setText("停止接单");
            btn_gainorder.setBackground(getResources().getDrawable(R.drawable.btn_back));
        }else {
            ToastUtils.showToast(mContext,"关闭自动接收订单成功");
            btn_gainorder.setText("接单");
            btn_gainorder.setBackground(getResources().getDrawable(R.drawable.btn_login));
        }
    }

    @OnClick({R.id.home_btn_gainorder,R.id.home_img_personal,R.id.home_img_message})
    public void onViewClicked(View view){
        switch (view.getId()) {
            case R.id.home_btn_gainorder:
                String btncontent = btn_gainorder.getText().toString();
                if (btncontent.equals("接单")){
                    homePresenter.GetAutoOrder(token,"1");
                }else {
                    homePresenter.GetAutoOrder(token,"0");
                }
                break;
            case R.id.home_img_personal:
                startActivity(new Intent(mContext,PersonalActivity.class));
                break;
            case R.id.home_img_message:
                startActivity(new Intent(mContext,MessageCenterActivity.class));
                break;
                default:
                    break;
        }
        }

    //刷新订单列表页面
    public void UpdateOrderList(OrderListBean.DataBean data){
        if (data!=null||!data.equals("")) {
            tv_receipt_number.setText(data.getOrder_count()+"");
            tv_receipt_price.setText(data.getToday_money()+"元");
            tv_receipt_evaluate.setText(data.getUser_evaluate()+"");
            list.clear();
            List<OrderListBean.DataBean.OrderBean> order = data.getOrder();
            list.addAll(order);
            homeAdapter.setListAll(list);
            homeAdapter.notifyDataSetChanged();
        }
    }


    //自动接单dialog
    public  void ShowDialog(DeliverBean.DataBean.OrderBean order){
        order_id = order.getId()+"";
             home_dialog_tv_destination.setText(order.getDestination());
             home_dialog_tv_startingpoint.setText(order.getStartingpoint());
             dialog_home_tv_time.setText(order.getTime());
             dialog_home_tv_distance.setText(order.getDistance()+"");
              builder = new AlertDialog.Builder(this);
        builder = new AlertDialog.Builder(this);
        if (alertDialog == null) {
            alertDialog = builder.setView(dialog_home).create();
            alertDialog.show();
        } else {
            alertDialog.show();
        }
        countDownProgressBar.startCountDown();
             countDownProgressBar.startCountDown();
    }

    //系统自动接单接到新订单了
    public  void automatic(String orders){
        Gson gson = new GsonBuilder().create();
        DeliverBean deliverBean = gson.fromJson(orders, DeliverBean.class);
        DeliverBean.DataBean data = deliverBean.getData();
        DeliverBean.DataBean.OrderBean order = data.getOrder();
        automaticOrder = orders;
        isAutomatic = true;
        ShowDialog(order);
    }

    //获取到系统派单
    public  void UpSyatemOrder(OrderListOneBean.DataBean.OrderBean orderBean){
        order_id = orderBean.getId()+"";
        home_dialog_tv_destination.setText( orderBean.getDestination());
        home_dialog_tv_startingpoint.setText( orderBean.getStartingpoint());
        dialog_home_tv_time.setText( orderBean.getTime());
        dialog_home_tv_distance.setText("");
        try {
            builder = new AlertDialog.Builder(this);
            if (alertDialog == null) {
                alertDialog = builder.setView(dialog_home).create();
                alertDialog.show();
            } else {
                alertDialog.show();
            }
            countDownProgressBar.startCountDown();
        }catch (Exception e){

        }
    }

     //刷新订单列表handler
    private Handler orderHandler=new Handler();
    Runnable orderRunnable=new Runnable() {
                    @Override
                    public void run() {
                        if (!longitude.equals("")&&!latitude.equals("")&&!city.equals("")) {
                            homePresenter.GetOrder(token, city, longitude + "", latitude + "");
                            orderHandler.postDelayed(orderRunnable,10000);
                        }else {
                            orderHandler.postDelayed(orderRunnable,200);
                        }
        }
    };

//    有订单跳转到main
    public  void UpdateOrder(String order_id){
        Intent intent = new Intent(mContext, MainActivity.class);
        intent.putExtra("order_id",order_id);
        intent.putExtra("positionE",longitude+"");
        intent.putExtra("positionN",latitude+"");
        intent.putExtra("position",city);
        intent.putExtra("order_status","2");
        startActivity(intent);
        mLocationClient.stopLocation();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
//            closeService();
//            finish();
            // 创建退出对话框
            AlertDialog isExit = new AlertDialog.Builder(this).create();
            isExit.setTitle("系统提示");
            isExit.setMessage("是否退出应用");
            isExit.setButton(DialogInterface.BUTTON_POSITIVE, "取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            isExit.setButton(DialogInterface.BUTTON_NEUTRAL, "确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    orderHandler.removeCallbacks(orderRunnable);
                    System.exit(0);
                    finish();
                }
            });
            isExit.show();


        }
        return super.onKeyDown(keyCode, event);
    }

    /**监听对话框里面的button点击事件*/

    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener(){

        public void onClick(DialogInterface dialog, int which)

        {

            switch (which)

            {

                case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序

                    finish();

                    break;

                case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框

                    break;

                default:

                    break;

            }

        }

    };



     //退出登录
    public void Logout(){
        finish();
    }
    private String positionS;
    private pointsBean pointsBean;
    private boolean IsCity=true;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    //获取纬度
                    latitude = new DecimalFormat("0.000000").format(aMapLocation.getLatitude());
                    //获取经度
                    longitude = new DecimalFormat("0.000000").format(aMapLocation.getLongitude());
                    aMapLocation.getAccuracy();//获取精度信息
                    aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    aMapLocation.getCountry();//国家信息
                    aMapLocation.getProvince();//省信息
                    //城市信息
                    city = aMapLocation.getCity();
                    speed = aMapLocation.getSpeed();
                    aMapLocation.getDistrict();//城区信息
                    aMapLocation.getStreet();//街道信息
                    aMapLocation.getStreetNum();//街道门牌号信息
                    aMapLocation.getCityCode();//城市编码
                    aMapLocation.getAdCode();//地区编码
                    aMapLocation.getAoiName();//获取当前定位点的AOI信息
                    aMapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                    aMapLocation.getFloor();//获取当前室内定位的楼层
                    aMapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
                    //获取定位时间
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(aMapLocation.getTime());
                    String format = df.format(date);
                    long time = aMapLocation.getTime()/1000;
                    positionS = aMapLocation.getProvince()+aMapLocation.getProvince()+aMapLocation.getDistrict()+aMapLocation.getStreetNum();
                    String locations = longitude + "," + latitude ;
                    pointsBean = new pointsBean(locations, time+"", aMapLocation.getSpeed() + "", aMapLocation.getBearing()+ "", aMapLocation.getAltitude() + "", aMapLocation.getAccuracy() + "");
                    pointsList.add(pointsBean);
                    if (IsCity){
                        initSocket();
                        IsCity =false;
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        orderHandler.removeCallbacks(orderRunnable);
        HeartBateHandler.removeCallbacks(HeartBateRundbler);
        super.onDestroy();
    }

    //开启长连接
    private void initSocket() {
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(0, TimeUnit.MILLISECONDS).build();
        final Request request = new Request.Builder().url(RequstUrlUtils.URL.WEBSOCKET_HOST_AND_PORT).build();
        client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, okhttp3.Response response) {//开启长连接成功的回调
                super.onOpen(webSocket, response);
                Log.e("BackService","进来了"+city);
                mWebSocket = webSocket;
                String s = new Gson().toJson(new LoginDemoBean(token, "1", user_id,"login",new LoginDemoBean.data(city)));
                mWebSocket.send(s);
                HeartBateHandler.postDelayed(HeartBateRundbler, HEART_BEAT_RATE);
            }

            @Override
            public void onMessage(WebSocket webSocket, final String text) {//接收消息的回调
                super.onMessage(webSocket, text);
                //收到服务器端传过来的消息text
                Log.e("BackService1",text);
                try {
                    Gson gson = new GsonBuilder().create();
                    ServicesMsgBean servicesMsgBean = gson.fromJson(text, ServicesMsgBean.class);
                    if (servicesMsgBean!=null){
                        if (servicesMsgBean.getCode()==200){
                            String event = servicesMsgBean.getEvent();
                            if (event.equals("login")){
                            }else if (event.equals("deliver")){
                               automatic(text);
                            }else if (event.equals("receipt")){
                                Intent intent = new Intent(mContext, MainActivity.class);
                                intent.putExtra("order_id",order_id);
                                intent.putExtra("positionE",longitude+"");
                                intent.putExtra("positionN",latitude+"");
                                intent.putExtra("position",city);
                                intent.putExtra("order_status","2");
                                startActivity(intent);
                                mLocationClient.stopLocation();
                            }
                        }else if (servicesMsgBean.getCode()==401){
                            if (servicesMsgBean.getEvent().equals("login")){
                                ToastUtils.showToast(getApplicationContext(),servicesMsgBean.getMsg());
                                startActivity(new Intent(getApplicationContext(), LogonActivity.class));
                                new HomeActivity().finish();
                            }
                        }
                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                super.onMessage(webSocket, bytes);
                Log.e("BackService2",bytes.toString());
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                super.onClosing(webSocket, code, reason);
                Log.e("BackService3",reason);
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                super.onClosed(webSocket, code, reason);
                Log.e("BackService4",reason);
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {//长连接连接失败的回调
                super.onFailure(webSocket, t, response);
                Log.e("BackService5","发发发");
                logout();
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
            HeartBateHandler.postDelayed(this,10000);//每隔一定的时间，对长连接进行一次心跳检测
        }
    };

    //司机接单/抢单
    public void  Receipt(String orderId,String positionE,String positionN,String position) {
        Log.e("订单信息",orderId+"和"+positionE+"和"+positionN+"和"+position);
        String Receipts = new Gson().toJson(new ReceiptBean(token, "1", user_id,"receipt", new ReceiptBean.data(orderId, positionE, positionN, position)));
        boolean isSuccess = mWebSocket.send("");
        if (!isSuccess) {//长连接已断开
            mWebSocket.cancel();//取消掉以前的长连接
            mWebSocket.send(Receipts);
        } else {//长连接处于连接状态
            mWebSocket.send(Receipts);
        }
    }

    //更新自己位置 /api/socketobj/position
    public void position(){
        String s = new Gson().toJson(pointsList);
        Log.e("轨迹点",s.substring(1,s.length()-1));
        String Receipts=new Gson().toJson(new PositionsBean(token, "1", user_id,"position","android",new PositionsBean.data(longitude+"",latitude+"", positionS,speed+"",city,new Gson().toJson(pointsList))));
        boolean isSuccess = mWebSocket.send("");
        pointsList.clear();
        if (!isSuccess) {//长连接已断开
            mWebSocket.cancel();//取消掉以前的长连接
            mWebSocket.send(Receipts);
        } else {//长连接处于连接状态
            mWebSocket.send(Receipts);
        }
    }
    //根据定位实时更新自己的位置
    public void LocationPosition(){
        String s = new Gson().toJson(pointsBean);
        Log.e("轨迹上传",s);
        String Receipts=new Gson().toJson(new PositionsBean(token, "1", user_id,"position","android",new PositionsBean.data(longitude+"",latitude+"", positionS,speed+"",city,new Gson().toJson(pointsBean))));
        pointsList.clear();
        boolean isSuccess = mWebSocket.send("");
        if (!isSuccess) {//长连接已断开
            mWebSocket.cancel();//取消掉以前的长连接
            mWebSocket.send(Receipts);
        } else {//长连接处于连接状态
            mWebSocket.send(Receipts);
        }
    }

    //退出登录
    private void logout() {
        OkGo.<String>post(RequstUrlUtils.URL.logout)
                .params("token", token)
                .params("type","0")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        ServicesMsgBean resultBean = new GsonBuilder().create().fromJson(response.body(),ServicesMsgBean.class);
                        if (resultBean.getCode()==202){
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("openid","");
                            editor.putString("token","");
                            editor.putString("user_id","");
                            editor.putString("nickname","");
                            editor.putString("avatar","");
                            editor.commit();//提交
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });

    }



}
