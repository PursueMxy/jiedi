package com.icarexm.jiedi.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.icarexm.jiedi.Bean.OrderType1Bean;
import com.icarexm.jiedi.Bean.OrderTypeBean;
import com.icarexm.jiedi.R;
import com.icarexm.jiedi.contract.MainContract;
import com.icarexm.jiedi.presenter.MainPresenter;
import com.icarexm.jiedi.service.StocketServices;
import com.icarexm.jiedi.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.main_map)
    MapView mMapView;
    @BindView(R.id.main_tv_title)
    TextView tv_title;
    @BindView(R.id.main_tv_car_code)
    TextView tv_car_code;
    @BindView(R.id.main_tv_name)
    TextView tv_name;
    @BindView(R.id.main_tv_shape)
    TextView tv_shape;
    @BindView(R.id.main_tv_order_type)
    TextView tv_orderType;
    @BindView(R.id.main_img_safety)
    ImageView img_safety;
    @BindView(R.id.main_rl_safety)
    RelativeLayout rl_safety;
    //订单已取消
    @BindView(R.id.main_img_safety1)
    ImageView img_safety1;
    @BindView(R.id.main_rl_safety1)
    LinearLayout rl_safety1;
    @BindView(R.id.main_img_type)
    ImageView img_type;

    @BindView(R.id.main_tv_statusName)
    TextView tv_statusName;
    @BindView(R.id.main_tv_startAddress)
    TextView tv_startAddress;
    @BindView(R.id.main_tv_terminal)
    TextView tv_termianl;


    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    private AMapLocationClientOption mLocationOption;
    MyLocationStyle myLocationStyle;
    private int CANCELORDER_CODE=1001;
    public static StocketServices stocketService;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            stocketService = ((StocketServices.LocalBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private AMap aMap;
    private Context mContext;
    public static String order_id;
    public static String positionS;
    public static  String positionN;
    public static String positionE;
    private static MainPresenter mianPresenter;
    private SharedPreferences sp;
    private static String token;
    private String user_id;
    private Marker markers;
    private static String order_status;
    private View dialog_callphone;
    private TextView tv_phone_number;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        user_id = sp.getString("user_id", "");
        mianPresenter = new MainPresenter(this);
        initService();
        mContext = getApplicationContext();
        mMapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap =mMapView.getMap();
        }
        Intent intent = getIntent();
        order_id = intent.getStringExtra("order_id");
        positionE = intent.getStringExtra("positionE");
        positionN = intent.getStringExtra("positionN");
        positionS = intent.getStringExtra("position");
        order_status = intent.getStringExtra("order_status");
        //显示当前位置
        myLocation();
        SetLocations();
        mianPresenter.GetOrderInfo(token,order_id);
    }

    @OnClick({R.id.main_tv_cancel_order,R.id.main_img_back,R.id.main_tv_order_type})
    public void onViewClicked(View view){
        switch (view.getId()) {
            case R.id.main_tv_cancel_order:
                Intent intent = new Intent(mContext, CancelOrderActivity.class);
                startActivityForResult(intent,CANCELORDER_CODE);
                break;
            case R.id.main_img_safety:
                callPhoneDialog();
                break;
            case R.id.main_img_safety1:
                callPhoneDialog();
                break;
            case R.id.main_img_back:
                closeService();
                startActivity(new Intent(mContext,HomeActivity.class));
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("type","home");
                editor.commit();//提交
                finish();
                break;
            case R.id.main_tv_order_type:
                String typeName= tv_orderType.getText().toString();
                if (typeName.equals("到达上车点")){
                    AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                    alertDialog.setTitle("系统提示");
                    alertDialog.setMessage("是否已经到达上车点");
                    alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            stocketService.driver_arrive(order_id);
                        }
                    });
                    alertDialog.show();
                }else if (typeName.equals("乘客已上车")){
                    AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                    alertDialog.setTitle("系统提示");
                    alertDialog.setMessage("是否乘客已上车");
                    alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            stocketService.passenger_boarding(order_id);
                        }
                    });
                    alertDialog.show();
                }else if (typeName.equals("到达终点")){
                    AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                    alertDialog.setTitle("系统提示");
                    alertDialog.setMessage("是否到达终点");
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "取消", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

        }
    });
                    alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "确定", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            stocketService.arrive(order_id);
        }
    });
                    alertDialog.show();
}
                break;
                        }
                        }

@Override
protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CANCELORDER_CODE) {
        String type = data.getStringExtra("type");
        if (type.equals("1")) {
        String reason = data.getStringExtra("reason");
        String remark = data.getStringExtra("remark");
        stocketService.refuse_order(order_id, reason, remark);
        }
        }

        }


@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
        closeService();
        startActivity(new Intent(mContext,HomeActivity.class));
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("type","home");
        editor.commit();//提交
        finish();
        }
        return super.onKeyDown(keyCode, event);
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

    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    //获取纬度
                     aMapLocation.getLatitude();
                    //获取经度
                    aMapLocation.getLongitude();
                    aMapLocation.getAccuracy();//获取精度信息
                    aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    aMapLocation.getCountry();//国家信息
                    aMapLocation.getProvince();//省信息
                    //城市信息
                     aMapLocation.getCity();
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
                    MarkerOptions markerOption = new MarkerOptions();
                    markerOption.position(new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude()));
                    markerOption.title(aMapLocation.getStreetNum()).snippet(aMapLocation.getStreet()+aMapLocation.getStreetNum());
                    markerOption.draggable(false);//设置Marker可拖动
                    markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                            .decodeResource(getResources(),R.mipmap.icon_driver_car)));
                    // 将Marker设置为贴地显示，可以双指下拉地图查看效果
                    markerOption.setFlat(false);//设置marker平贴地图效果
                    if (markers!=null){
                        markers.remove();
                        markers=null;
                    }
                    markers = aMap.addMarker(markerOption);
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude()));
                    aMap.moveCamera(cameraUpdate);
                }else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError","location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    };

    private void myLocation() {
//        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
//        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
//        myLocationStyle.strokeColor(R.color.ff000000);
//        myLocationStyle.radiusFillColor(R.color.ff000000);
//        myLocationStyle.strokeWidth((float) 0.1);
//        // myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
//        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
//        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
//        aMap.setMyLocationEnabled(false);//设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
        //设置希望展示的地图缩放级别
        CameraUpdate mCameraUpdate = CameraUpdateFactory.zoomTo(16);
        aMap.moveCamera(mCameraUpdate);

    }

    //联系客服
    public void callPhoneDialog(){
        dialog_callphone = getLayoutInflater().inflate(R.layout.dialog_callphone, null);
        tv_phone_number = dialog_callphone.findViewById(R.id.dialog_callphone_tv_number);
        dialog_callphone.findViewById(R.id.dialog_callphone_tv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        dialog_callphone.findViewById(R.id.dialog_callphone_tv_call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile = tv_phone_number.getText().toString();
                Intent intentcall = new Intent();
                //设置拨打电话的动作
                intentcall.setAction(Intent.ACTION_CALL);
                //设置拨打电话的号码
                intentcall.setData(Uri.parse("tel:" + mobile));
                //开启打电话的意图
                startActivity(intentcall);
                alertDialog.dismiss();
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        alertDialog = builder.setView(dialog_callphone)
                .create();
        alertDialog.show();
    }

    //开启服务
    private void initService() {
        Intent bluetoothIntent;
        if (stocketService == null) {
            bluetoothIntent = new Intent(MainActivity.this, StocketServices.class);
            bindService(bluetoothIntent, serviceConnection, BIND_AUTO_CREATE);
        }
    }

    //根据订单状态修改UI
    public void UpdateUI(OrderTypeBean.DataBean data){
        OrderTypeBean.DataBean.UserInfoBean userInfo = data.getUserInfo();
        tv_name.setText(userInfo.getNickname());
        tv_shape.setText(data.getUser_evaluate());
        //订单状态:0=发起订单,1=订单分配中,2=司机接单,3=已到达,4=乘客已上车,5=到达目的地,6=订单完成,7=订单异常,8=取消订单,9=已评价,10=半途取消
        String status = data.getStatus();
        //服务类型 0-城内出行 1-城际出行 2-接机 3-送机
        String service_type = data.getService_type();
        if (service_type.equals("0")||service_type.equals("1")){
            tv_car_code.setVisibility(View.INVISIBLE);
            tv_title.setText("捷滴出行");
            img_type.setImageResource(R.mipmap.icon_car1);
            if (service_type.equals("1")){
                Log.e("service_type",service_type);
                tv_statusName.setText("预约订单");
            }else if (service_type.equals("0")){
                Log.e("service_type",service_type);
                tv_statusName.setText("现在出行");
            }
        }else {
            tv_car_code.setText(data.getFlightno());
            tv_title.setText("捷滴接机");
            img_type.setImageResource(R.mipmap.icon_plane1);
            if (service_type.equals("3")){
                tv_statusName.setText("接机");
            }else if (service_type.equals("4")){
                tv_statusName.setText("送机");
            }
        }
        if (status.equals("0")){
            MainActivity.PlaceOrders();
        }else if (status.equals("2")){
            img_safety1.setVisibility(View.GONE);
            rl_safety1.setVisibility(View.GONE);
            img_safety.setVisibility(View.VISIBLE);
            rl_safety.setVisibility(View.VISIBLE);
            tv_orderType.setText("到达上车点");
            MarkerOptions markerOption = new MarkerOptions();
            markerOption.position(new LatLng(Double.valueOf(data.getStartingpointN()),Double.valueOf(data.getStartingpointE())));
            markerOption.snippet(data.getStartingpoint());
            markerOption.draggable(false);//设置Marker可拖动
            markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                    .decodeResource(getResources(),R.mipmap.icon_startposition)));
            // 将Marker设置为贴地显示，可以双指下拉地图查看效果
            markerOption.setFlat(false);//设置marker平贴地图效果
            aMap.addMarker(markerOption);
        }
        else if (status.equals("3")){
            img_safety1.setVisibility(View.GONE);
            rl_safety1.setVisibility(View.GONE);
            img_safety.setVisibility(View.VISIBLE);
            rl_safety.setVisibility(View.VISIBLE);
            tv_orderType.setText("乘客已上车");
            MarkerOptions markerOption = new MarkerOptions();
            markerOption.position(new LatLng(Double.valueOf(data.getStartingpointN()),Double.valueOf(data.getStartingpointE())));
            markerOption.snippet(data.getStartingpoint());
            markerOption.draggable(false);//设置Marker可拖动
            markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                    .decodeResource(getResources(),R.mipmap.icon_startposition)));
            // 将Marker设置为贴地显示，可以双指下拉地图查看效果
            markerOption.setFlat(false);//设置marker平贴地图效果
            aMap.addMarker(markerOption);
        }
        else  if (status.equals("4")){
            img_safety1.setVisibility(View.GONE);
            rl_safety1.setVisibility(View.GONE);
            img_safety.setVisibility(View.VISIBLE);
            rl_safety.setVisibility(View.VISIBLE);
            tv_orderType.setText("到达终点");
            MarkerOptions markerOption = new MarkerOptions();
            markerOption.position(new LatLng(Double.valueOf(data.getStartingpointN()),Double.valueOf(data.getStartingpointE())));
            markerOption.snippet(data.getStartingpoint());
            markerOption.draggable(false);//设置Marker可拖动
            markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                    .decodeResource(getResources(),R.mipmap.icon_startposition)));
            // 将Marker设置为贴地显示，可以双指下拉地图查看效果
            markerOption.setFlat(false);//设置marker平贴地图效果
            aMap.addMarker(markerOption);

            MarkerOptions markerOption1 = new MarkerOptions();
            markerOption1.position(new LatLng(Double.valueOf(data.getDestinationN()),Double.valueOf(data.getDestinationE())));
            markerOption1.snippet(data.getDestination());
            markerOption1.draggable(false);//设置Marker可拖动
            markerOption1.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                    .decodeResource(getResources(),R.mipmap.icon_startposition)));
            // 将Marker设置为贴地显示，可以双指下拉地图查看效果
            markerOption1.setFlat(false);//设置marker平贴地图效果
            aMap.addMarker(markerOption1);
        }
        else if (status.equals("5")){
            closeService();
            finish();
        }
        else if (status.equals("6")){
           String score = data.getScore();
            if (score.equals("0")) {
                img_safety.setVisibility(View.GONE);
                rl_safety.setVisibility(View.GONE);
                img_safety1.setVisibility(View.GONE);
                rl_safety1.setVisibility(View.GONE);
            }else {
                img_safety.setVisibility(View.GONE);
                rl_safety.setVisibility(View.GONE);
                img_safety1.setVisibility(View.GONE);
                rl_safety1.setVisibility(View.GONE);
            }
        }
        else if (status.equals("8")){
            img_safety.setVisibility(View.GONE);
            rl_safety.setVisibility(View.GONE);
            img_safety1.setVisibility(View.VISIBLE);
            rl_safety1.setVisibility(View.VISIBLE);
            tv_title.setText("订单结束");
            tv_startAddress.setText(data.getStartingpoint());
            tv_termianl.setText(data.getDestination());
        }
    }

    //根据订单状态修改UI
    public void UpdateUI1(OrderType1Bean.DataBean data){
        OrderType1Bean.DataBean.UserInfoBean userInfo = data.getUserInfo();
        //订单状态:0=发起订单,1=订单分配中,2=司机接单,3=已到达,4=乘客已上车,5=到达目的地,6=订单完成,7=订单异常,8=取消订单,9=已评价,10=半途取消
        String status = data.getStatus();
        tv_name.setText(userInfo.getNickname());
        tv_shape.setText(data.getUser_evaluate());

        //服务类型 0-城内出行 1-城际出行 2-接机 3-送机
        String service_type = data.getService_type();
        if (service_type.equals("0")&&service_type.equals("1")){
            tv_car_code.setVisibility(View.GONE);
            tv_title.setText("捷滴出行");
            img_type.setImageResource(R.mipmap.icon_car1);
            if (service_type.equals("0")){
                tv_statusName.setText("预约订单");
            }else if (service_type.equals("1")){
                tv_statusName.setText("现在出行");
            }
        }
        else {
            tv_car_code.setText(data.getFlightno());
            tv_title.setText("捷滴接机");
            img_type.setImageResource(R.mipmap.icon_plane1);
            if (service_type.equals("3")){
                tv_statusName.setText("接机");
            }else if (service_type.equals("4")){
                tv_statusName.setText("送机");
            }
        }
        if (status.equals("0")){
            MainActivity.PlaceOrders();
        } else if (status.equals("2")){
            MarkerOptions markerOption = new MarkerOptions();
            markerOption.position(new LatLng(Double.valueOf(data.getStartingpointN()),Double.valueOf(data.getStartingpointE())));
            markerOption.snippet(data.getStartingpoint());
            markerOption.draggable(false);//设置Marker可拖动
            markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                    .decodeResource(getResources(),R.mipmap.icon_startposition)));
            // 将Marker设置为贴地显示，可以双指下拉地图查看效果
            markerOption.setFlat(false);//设置marker平贴地图效果
            aMap.addMarker(markerOption);
            img_safety1.setVisibility(View.GONE);
            rl_safety1.setVisibility(View.GONE);
            img_safety.setVisibility(View.VISIBLE);
            rl_safety.setVisibility(View.VISIBLE);
            tv_orderType.setText("到达上车点");
        }
        else if (status.equals("3")){
            MarkerOptions markerOption = new MarkerOptions();
            markerOption.position(new LatLng(Double.valueOf(data.getStartingpointN()),Double.valueOf(data.getStartingpointE())));
            markerOption.snippet(data.getStartingpoint());
            markerOption.draggable(false);//设置Marker可拖动
            markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                    .decodeResource(getResources(),R.mipmap.icon_startposition)));
            // 将Marker设置为贴地显示，可以双指下拉地图查看效果
            markerOption.setFlat(false);//设置marker平贴地图效果
            aMap.addMarker(markerOption);
            img_safety1.setVisibility(View.GONE);
            rl_safety1.setVisibility(View.GONE);
            img_safety.setVisibility(View.VISIBLE);
            rl_safety.setVisibility(View.VISIBLE);
            tv_orderType.setText("乘客已上车");
        }
        else  if (status.equals("4")){
            MarkerOptions markerOption = new MarkerOptions();
            markerOption.position(new LatLng(Double.valueOf(data.getStartingpointN()),Double.valueOf(data.getStartingpointE())));
            markerOption.snippet(data.getStartingpoint());
            markerOption.draggable(false);//设置Marker可拖动
            markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                    .decodeResource(getResources(),R.mipmap.icon_startposition)));
            // 将Marker设置为贴地显示，可以双指下拉地图查看效果
            markerOption.setFlat(false);//设置marker平贴地图效果
            aMap.addMarker(markerOption);
            img_safety1.setVisibility(View.GONE);
            rl_safety1.setVisibility(View.GONE);
            img_safety.setVisibility(View.VISIBLE);
            rl_safety.setVisibility(View.VISIBLE);
            tv_orderType.setText("到达终点");
        }else if (status.equals("5")){
            closeService();
            finish();
        }else if (status.equals("6")){
            String score = data.getScore();
            if (score.equals("0")) {
                img_safety.setVisibility(View.GONE);
                rl_safety.setVisibility(View.GONE);
                img_safety1.setVisibility(View.GONE);
                rl_safety1.setVisibility(View.GONE);
            }else {
                img_safety.setVisibility(View.GONE);
                rl_safety.setVisibility(View.GONE);
                img_safety1.setVisibility(View.GONE);
                rl_safety1.setVisibility(View.GONE);
            }
        }
        else if (status.equals("8")){
            img_safety.setVisibility(View.GONE);
            rl_safety.setVisibility(View.GONE);
            img_safety1.setVisibility(View.VISIBLE);
            rl_safety1.setVisibility(View.VISIBLE);
            tv_title.setText("订单结束");
            tv_startAddress.setText(data.getStartingpoint());
            tv_termianl.setText(data.getDestination());
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // 跳转首页或者其他操作
    }

    //关闭长连接
    private void closeService() {
        if (stocketService != null) {
            try {
                unbindService(serviceConnection);
                stocketService = null;
            } catch (Exception e) {
            }
        }
    }

    //完成接单
    public static void PlaceOrders(){
        Log.e("获取订单状态","获取订单状态"+order_id);
        stocketService.Receipt(order_id,positionE,positionN,positionS);
    }

    //获取订单状态
    public static void GetOrderStatus(){
        //获取订单状态
        mianPresenter.GetOrderInfo(token,order_id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeService();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
