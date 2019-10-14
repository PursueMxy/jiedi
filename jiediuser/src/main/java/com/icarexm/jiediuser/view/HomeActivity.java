package com.icarexm.jiediuser.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.DistanceItem;
import com.amap.api.services.route.DistanceResult;
import com.amap.api.services.route.DistanceSearch;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.bean.GetOrderDirverBean;
import com.icarexm.jiediuser.bean.OrderDetailBean;
import com.icarexm.jiediuser.bean.OrderDetailOneBean;
import com.icarexm.jiediuser.contract.HomeContract;
import com.icarexm.jiediuser.custview.BottomDialog;
import com.icarexm.jiediuser.custview.mywheel.MyWheelView;
import com.icarexm.jiediuser.custview.wheel.ScreenInfo;
import com.icarexm.jiediuser.custview.wheel.WheelMain;
import com.icarexm.jiediuser.model.AccountingRulesModel;
import com.icarexm.jiediuser.presenter.HomePresenter;
import com.icarexm.jiediuser.services.StocketServices;
import com.icarexm.jiediuser.utils.StringFormatUtil;
import com.icarexm.jiediuser.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.icarexm.jiediuser.utils.RequstUrlUtils.URL.price;

public class HomeActivity extends AppCompatActivity implements HomeContract.View, DistanceSearch.OnDistanceSearchListener, AMap.InfoWindowAdapter {

    private static boolean IsCancelOrder=false;
    private static boolean IsDervierLoaction=false;
    private static Double DervierPositionE;
    private static Double DervierPositionN;
    @BindView(R.id.home_map)
    MapView mMapView;
    private AMap aMap;
    @BindView(R.id.home_tv_startingpoint)
    TextView tv_startingpoint;
    @BindView(R.id.home_tv_destination)
    TextView tv_destination;
    @BindView(R.id.home_radiobutton_inside_city)
    RadioButton radioButton_inside_city;
    @BindView(R.id.home_radiobutton_intercity)
    RadioButton radioButton_interciry;
    @BindView(R.id.home_radiobutton_transfer)
    RadioButton radioButton_transfer;
    @BindView(R.id.home_radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.home_tv_original_price)
    TextView tv_original_price;
    @BindView(R.id.home_tv_estimated_price)
    TextView tv_estimated_price;
    @BindView(R.id.home_tv_estimated_time)
     TextView tv_estimated_time;
     @BindView(R.id.home_ll_lnside_city)
     LinearLayout ll_lnside_city;
     @BindView(R.id.home_ll_city_type)
     LinearLayout ll_city_type;
     @BindView(R.id.home_tv_TypeNow)
     TextView tv_typeNow;
     @BindView(R.id.home_tv_TypeMake)
     TextView tv_typeMake;
    //下单页面
    @BindView(R.id.home_rl_setorder)
    RelativeLayout rl_order_setorder;
    @BindView(R.id.home_ll_flight_transfer)
    LinearLayout ll_flight_transfer;
    @BindView(R.id.home_tv_flight_time)
    TextView tv_flight_time;
    @BindView(R.id.home_edt_flight_number)
    TextView edt_flight_number;

    //预计价格页面
    @BindView(R.id.home_rl_estimated_price)
    RelativeLayout rl_order_estimated_price;
    //取消订单页面 订单进行中
    @BindView(R.id.home_rl_cancel_order)
    RelativeLayout rl_order_cancel;
    @BindView(R.id.home_cancel_tv_cancelOrder)
    TextView cancel_tv_cancelOrder;
    @BindView(R.id.home_cancel_tv_carNumber)
    TextView cancel_tv_carNumber;
    @BindView(R.id.home_cancel_tv_carName)
    TextView cancel_tv_carName;
    //订单已取消
    @BindView(R.id.home_rl_order_cancelled)
    RelativeLayout rl_order_cancelled;
    //等待接单
    @BindView(R.id.home_rl_wait_order)
    RelativeLayout rl_wait_order;
    //接送机页面
    @BindView(R.id.home_rl_transfer)
    RelativeLayout rl_transfer;
    @BindView(R.id.home_transfer_radiocroup)
    RadioGroup transfer_radiocroup;
    @BindView(R.id.home_radiobutton_give)
    RadioButton radiobutton_give;
    @BindView(R.id.home_radiobutton_meet)
    RadioButton radiobutton_meet;



    private int CANCEL_ORDER_CODE=6698;
    private int INOUT_TIPS_CODE=6699;
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
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;
    private static Context mContext;
    private String cityName;
    private static int ORDER_TYPE=0;
    private static HomePresenter homePresenter;
    private SharedPreferences sp;
    private static String token;
    private DistanceSearch distanceSearch;
    private double start_longitude;
    private double start_latitude;
    private DistanceSearch.DistanceQuery distanceQuery;

    //起始经度 纬度 位置
    private String startingpointE;
    private String startingpointN;
    private String startingpoint;
    //目的地经度纬度 位置
    private String destinationE;
    private String destinationN;
    private String destination;

    //预计里程 时间 价格
    private  String estimated_mileage;
    private String estimated_time;
    private String budget;

    //服务类型  城市 	航班号 预约出发时间,预约时填写
    private  String service_type;
     private   String city;
    private  String flightno;
    private  String estimatedeparturetime;
    private String startingpoints;
    private static final String[] PLANETS = new String[]{"今天", "明天","后天"};
    private static final String[] HOURS_TYPE = new String[]{"00","01", "02","03","04","05","06","07","08","09","10","11","12"
    ,"13","14","15","16","17","18","19","20","21","22","23"};
    private static final String[] min = new String[]{"00", "10","20","30","40","50","60"};

    private String DayString="今天";
    private String HoursString="00";
    private String MinString="00";
    private static String order_id;
    private String reason;
    private String remark;
    private String TRANSFER_TYPE="0";
    private static int CITY_TYPE=1;
    private Calendar calendar;
    private Marker derviermarker;
    private static double dervierDistance;
    private static int dervierDuration;
   private static String OrderStatus;
    private static String dervierMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mContext = getApplicationContext();
        calendar = Calendar.getInstance();
        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        homePresenter = new HomePresenter(this);
        mMapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap =mMapView.getMap();
        }
        aMap.setInfoWindowAdapter(this);
        InitUI();
        initService();
        SetLocations();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        order_id = intent.getStringExtra("order_id");
        String status = intent.getStringExtra("status");
        if (status.equals("6")){
            homePresenter.GetOrderPrice(token, order_id,status);
        }
        super.onNewIntent(intent);
        // 跳转首页或者其他操作
    }

    private void InitUI() {
        tv_original_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
        //路线参数初始化
        distanceSearch = new DistanceSearch(this);
        distanceQuery = new DistanceSearch.DistanceQuery();
        distanceSearch.setDistanceSearchListener(this);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.home_radiobutton_inside_city:
                        rl_transfer.setVisibility(View.GONE);
                        ll_flight_transfer.setVisibility(View.GONE);
                        ll_city_type.setVisibility(View.VISIBLE);
                        ORDER_TYPE=0;
                        if (CITY_TYPE==2){
                            tv_estimated_time.setVisibility(View.VISIBLE);
                        }else {
                            tv_estimated_time.setVisibility(View.GONE);
                        }
                        radioButton_inside_city.setBackgroundResource(R.drawable.myorder_choosed_color);
                        radioButton_inside_city.setTextColor(getResources().getColor(R.color.ff5181fb));
                        radioButton_interciry.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radioButton_interciry.setTextColor(getResources().getColor(R.color.black));
                        radioButton_transfer.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radioButton_transfer.setTextColor(getResources().getColor(R.color.black));
                        break;
                    case R.id.home_radiobutton_intercity:
                        rl_transfer.setVisibility(View.GONE);
                        ll_flight_transfer.setVisibility(View.GONE);
                        ll_city_type.setVisibility(View.VISIBLE);
                        ORDER_TYPE=1;
                        if (CITY_TYPE==2){
                            tv_estimated_time.setVisibility(View.VISIBLE);
                        }else {
                            tv_estimated_time.setVisibility(View.GONE);
                        }
                        radioButton_inside_city.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radioButton_inside_city.setTextColor(getResources().getColor(R.color.black));
                        radioButton_interciry.setBackgroundResource(R.drawable.myorder_choosed_color);
                        radioButton_interciry.setTextColor(getResources().getColor(R.color.ff5181fb));
                        radioButton_transfer.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radioButton_transfer.setTextColor(getResources().getColor(R.color.black));
                        break;
                    case R.id.home_radiobutton_transfer:
                        ORDER_TYPE=2;
                        radiobutton_give.setBackgroundResource(R.drawable.nochoosed_color);
                        radiobutton_meet.setBackgroundResource(R.drawable.choosed_color);
                        rl_transfer.setVisibility(View.VISIBLE);
                        ll_flight_transfer.setVisibility(View.VISIBLE);
                        ll_city_type.setVisibility(View.GONE);
                        tv_estimated_time.setVisibility(View.GONE);
                        radioButton_inside_city.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radioButton_inside_city.setTextColor(getResources().getColor(R.color.black));
                        radioButton_interciry.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radioButton_interciry.setTextColor(getResources().getColor(R.color.black));
                        radioButton_transfer.setBackgroundResource(R.drawable.myorder_choosed_color);
                        radioButton_transfer.setTextColor(getResources().getColor(R.color.ff5181fb));
                        break;
                        default:
                            break;
                }
            }
        });

        transfer_radiocroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.home_radiobutton_give:
                        ORDER_TYPE=3;
                        radiobutton_give.setBackgroundResource(R.drawable.choosed_color);
                        radiobutton_meet.setBackgroundResource(R.drawable.nochoosed_color);
                        break;
                    case R.id.home_radiobutton_meet:
                        ORDER_TYPE=2;
                        radiobutton_give.setBackgroundResource(R.drawable.nochoosed_color);
                        radiobutton_meet.setBackgroundResource(R.drawable.choosed_color);
                        break;
                }
            }
        });
    }


    @OnClick({R.id.home_tv_destination,R.id.home_top_img_left,R.id.home_tv_edt_materials,R.id.home_tv_myorder,R.id.home_tv_price
    ,R.id.home_tv_set,R.id.home_tv_message_center,R.id.home_tv_recommend,R.id.home_btn_confirm_order,R.id.home_tv_TypeNow,
            R.id.home_tv_TypeMake,R.id.home_tv_estimated_time,R.id.home_tv_accounting_rules,
    R.id.home_top_img_message,R.id.home_cancel_tv_cancelOrder,R.id.home_tv_flight_time})
    public void  onViewClick(View view){
        switch (view.getId()){
            case R.id.home_tv_destination:
                Intent intent = new Intent(mContext, SearchPoiActivity.class);
                intent.putExtra("city", cityName);
                intent.putExtra("type", "1");
                startActivityForResult(intent, INOUT_TIPS_CODE);
                break;
            case R.id.home_top_img_left:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.home_tv_edt_materials:
                startActivity(new Intent(mContext,EdtMaterialsActivity.class));
                drawerLayout.closeDrawers();
                break;
            case R.id.home_tv_myorder:
                startActivity(new Intent(mContext,MyOrderActivity.class));
                drawerLayout.closeDrawers();
                break;
            case R.id.home_tv_price:
                startActivity(new Intent(mContext,PriceActivity.class));
                drawerLayout.closeDrawers();
                break;
            case R.id.home_tv_set:
                startActivity(new Intent(mContext,SetActivity.class));
                drawerLayout.closeDrawers();
                break;
            case R.id.home_tv_message_center:
                startActivity(new Intent(mContext,MessageCenterActivity.class));
                drawerLayout.closeDrawers();
                break;
            case R.id.home_tv_recommend:
                startActivity(new Intent(mContext,RecommendActivity.class));
                drawerLayout.closeDrawers();
                break;
            case R.id.home_btn_confirm_order:
                if(ORDER_TYPE>1){
                    estimatedeparturetime = tv_flight_time.getText().toString();
                    flightno = edt_flight_number.getText().toString();
                    if (!estimatedeparturetime.equals("")&&!flightno.equals("")){
                        stocketService.place_order(startingpointE,startingpointN,startingpoint,destinationE,destinationN,destination,
                                estimated_mileage,estimated_time,budget,ORDER_TYPE+"",cityName,flightno,estimatedeparturetime);
                    }else {
                        ToastUtils.showToast(mContext,"航班号或者航班时间不能为空");
                    }
                }else{
                    if (CITY_TYPE==1){
                        estimatedeparturetime = tv_estimated_time.getText().toString();
                        if (!estimatedeparturetime.equals("")){
                            stocketService.place_order(startingpointE,startingpointN,startingpoint,destinationE,destinationN,destination,
                                    estimated_mileage,estimated_time,budget,ORDER_TYPE+"",cityName,flightno,estimatedeparturetime);
                        }else {
                            ToastUtils.showToast(mContext,"预定时间不能为空");
                        }
                    }else {
                        stocketService.place_order(startingpointE,startingpointN,startingpoint,destinationE,destinationN,destination,
                                estimated_mileage,estimated_time,budget,ORDER_TYPE+"",cityName,flightno,estimatedeparturetime);
                    }
                }
                break;
            case R.id.home_tv_TypeNow:
                CITY_TYPE=1;
                tv_estimated_time.setVisibility(View.GONE);
                int HOUR = calendar.get(Calendar.HOUR_OF_DAY)+1;
                if (HOUR<10){
                    tv_estimated_time.setText("今天 0"+HOUR+":00");
                }else {
                    tv_estimated_time.setText("今天 "+HOUR+":00");
                }
                ll_city_type.setBackgroundResource(R.mipmap.icon_bluewhite);
                tv_typeNow.setTextColor(getResources().getColor(R.color.white));
                tv_typeMake.setTextColor(getResources().getColor(R.color.black));
                break;
            case R.id.home_tv_TypeMake:
                int HOURs = calendar.get(Calendar.HOUR_OF_DAY)+1;
                if (HOURs<10){
                    tv_estimated_time.setText("今天 0"+HOURs+":00");
                }else {
                    tv_estimated_time.setText("今天 "+HOURs+":00");
                }
                CITY_TYPE=2;
                tv_estimated_time.setVisibility(View.VISIBLE);
                ll_city_type.setBackgroundResource(R.mipmap.icon_whiteblue);
                tv_typeNow.setTextColor(getResources().getColor(R.color.black));
                tv_typeMake.setTextColor(getResources().getColor(R.color.white));
                break;
            case R.id.home_tv_estimated_time:
                AppointmentDialog();
                break;
            case R.id.home_tv_accounting_rules:
                startActivity(new Intent(mContext, AccountingRulesActivity.class));
                drawerLayout.closeDrawers();
                break;
            case R.id.home_top_img_message:
                startActivity(new Intent(mContext,MessageCenterActivity.class));
                break;
            case R.id.home_cancel_tv_cancelOrder:
                Intent intents = new Intent(mContext, CancelOrderActivity.class);
                intents.putExtra("order_id", order_id);
                startActivityForResult(intents, CANCEL_ORDER_CODE);
                break;
            case R.id.home_tv_flight_time:
                View timepickerview = LayoutInflater.from(mContext).inflate(R.layout.timepicker, null);
                final WheelMain wheelMain = new WheelMain(timepickerview,true);
                ScreenInfo screenInfo = new ScreenInfo(HomeActivity.this);
                wheelMain.screenheight = screenInfo.getHeight();
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month= calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                wheelMain.initDateTimePicker(year, month, day,hour,minute);
                AlertDialog.Builder dialog = new AlertDialog.Builder(HomeActivity.this)
                        .setTitle("请选择日期")
                        .setView(timepickerview)
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String time = wheelMain.getDate();
                                tv_flight_time.setText(time);
                                Log.e("当前时间",time);

                            }
                        });
                dialog.show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
        if (requestCode==INOUT_TIPS_CODE){
                String type = data.getStringExtra("type");
                String tip = data.getStringExtra("tip");
                String latitude = data.getStringExtra("latitude");
                String longitude = data.getStringExtra("longitude");
                String poiID = data.getStringExtra("poiID");
                tv_destination.setText(tip);
                LatLonPoint start = new LatLonPoint(start_latitude, start_longitude);
                List<LatLonPoint> latLonPoints = new ArrayList<LatLonPoint>();
                latLonPoints.add(start);
                LatLonPoint dest = new LatLonPoint(Double.valueOf(latitude), Double.valueOf(longitude));
                distanceQuery.setOrigins(latLonPoints);
                distanceQuery.setDestination(dest);
                //设置测量方式，支持直线和驾车
                distanceQuery.setType(DistanceSearch.TYPE_DRIVING_DISTANCE);
                distanceSearch.calculateRouteDistanceAsyn(distanceQuery);
                startingpointN = start_latitude + "";
                startingpointE = start_longitude + "";
                startingpoint = startingpoints;
                destinationN = latitude;
                destinationE = longitude;
                destination = tip;
        }else if (requestCode==CANCEL_ORDER_CODE){
           order_id = data.getStringExtra("order_id");
            reason = data.getStringExtra("reason");
            remark = data.getStringExtra("remark");
            stocketService.refuse_order(order_id,reason,remark);
        }
        }catch (Exception e){}
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void SetLocations() {
        CameraUpdate mCameraUpdate = CameraUpdateFactory.zoomTo(16);
        aMap.moveCamera(mCameraUpdate);
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

    private Marker markers;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    //获取纬度
                    start_latitude = aMapLocation.getLatitude();
                    //获取经度
                    start_longitude = aMapLocation.getLongitude();
                    aMapLocation.getAccuracy();//获取精度信息
                    aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    aMapLocation.getCountry();//国家信息
                    aMapLocation.getProvince();//省信息
                    //城市信息
                    cityName = aMapLocation.getCity();
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
                            .decodeResource(getResources(),R.mipmap.icon_my_location)));
                    // 将Marker设置为贴地显示，可以双指下拉地图查看效果
                    markerOption.setFlat(false);//设置marker平贴地图效果
                    if (markers !=null){
                        markers.remove();
                        markers =null;
                    }
                    markers = aMap.addMarker(markerOption);
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude()));
                    aMap.moveCamera(cameraUpdate);
                    startingpoints = aMapLocation.getCity()+aMapLocation.getDistrict()+aMapLocation.getStreet()+aMapLocation.getAoiName()+aMapLocation.getStreetNum();
                    tv_startingpoint.setText(aMapLocation.getCity()+aMapLocation.getDistrict()+aMapLocation.getStreet()+aMapLocation.getAoiName()+aMapLocation.getStreetNum());
                    if (IsCancelOrder){
                        IsCancelOrder=false;
                        rl_transfer.setVisibility(View.GONE);
                        ll_flight_transfer.setVisibility(View.GONE);
                        tv_estimated_time.setVisibility(View.GONE);
                        ll_city_type.setVisibility(View.VISIBLE);
                        radioButton_inside_city.setBackgroundResource(R.drawable.myorder_choosed_color);
                        radioButton_inside_city.setTextColor(getResources().getColor(R.color.ff5181fb));
                        radioButton_interciry.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radioButton_interciry.setTextColor(getResources().getColor(R.color.black));
                        radioButton_transfer.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radioButton_transfer.setTextColor(getResources().getColor(R.color.black));
                    }
                    if (IsDervierLoaction){
                        if (derviermarker!=null){
                            derviermarker.remove();
                            derviermarker=null;
                        }
                        MarkerOptions DervierOption = new MarkerOptions();
                        DervierOption.snippet("距离你"+dervierDistance+"公里,"+dervierDuration+"分钟");
                        DervierOption.position(new LatLng(DervierPositionN,DervierPositionE));
                        DervierOption.draggable(false);//设置Marker可拖动
                        DervierOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                .decodeResource(getResources(),R.mipmap.icon_car_top)));
                        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
                        DervierOption.setFlat(true);//设置marker平贴地图效果
                        derviermarker = aMap.addMarker(DervierOption);
                        getInfoWindow(derviermarker);
                        derviermarker.showInfoWindow();
                    }
                }else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", aMapLocation.getErrorCode()+"");
                }
            }
        }
    };

    //开启服务
    private void initService() {
        Intent bluetoothIntent;
        if (stocketService == null) {
            bluetoothIntent = new Intent(HomeActivity.this, StocketServices.class);
            bindService(bluetoothIntent, serviceConnection, BIND_AUTO_CREATE);
        }
    }

    //关闭服务
    private void closeService() {
        if (stocketService != null) {
            try {
                unbindService(serviceConnection);
                stocketService = null;
            } catch (Exception e) {
            }
        }
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

    //路程计算返回参数
    @Override
    public void onDistanceSearched(DistanceResult distanceResult, int i) {
        if (i==1000){
            List<DistanceItem> distanceResults = distanceResult.getDistanceResults();
            float duration = distanceResults.get(0).getDuration()/60;//大约时间
            float distance = distanceResults.get(0).getDistance()/1000;//长度(公里）
            estimated_mileage=distance+"";
            estimated_time=duration+"";
            homePresenter.GetPrice(estimated_mileage,"0",estimated_time);
        }
    }

    //显示路程预计价格
    public void UpdateEstimatedPrice(String money){
        rl_order_setorder.setVisibility(View.GONE);
        rl_order_estimated_price.setVisibility(View.VISIBLE);
        tv_estimated_price.setText(money);
        tv_original_price.setText(money);
        budget=money;
    }

    //选择时间dialog
    public void  AppointmentDialog(){
        int HOUR = calendar.get(Calendar.HOUR_OF_DAY)+1;
        int DAY=0;
        if (HOUR>23){
            HOUR=0;
            DAY=1;
        }
        if (HOUR<10){
            HoursString="0"+HOUR;
        }else {
            HoursString=HOUR+"";
        }
        final BottomDialog bottomDialog = new BottomDialog(this, R.style.ActionSheetDialogStyle);
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_reserce_time, null);
        MyWheelView wva = inflate.findViewById(R.id.dialog_reserce_time_wheel_one);
        wva.setItems(Arrays.asList(PLANETS),DAY);//init selected position is 1 初始选中位置为1
        MyWheelView wva_two = inflate.findViewById(R.id.dialog_reserce_time_wheel_two);
        wva_two.setItems(Arrays.asList(HOURS_TYPE),HOUR);//init selected position is 1 初始选中位置为1
        MyWheelView wva_three = inflate.findViewById(R.id.dialog_reserce_time_wheel_three);
        wva_three.setItems(Arrays.asList(min),0);
        wva.setOnItemSelectedListener(new MyWheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                DayString=item;
            }
        });
        wva_two.setOnItemSelectedListener(new MyWheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                HoursString=item;
            }
        });
        wva_three.setOnItemSelectedListener(new MyWheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                MinString=item;
            }
        });
        inflate.findViewById(R.id.dialog_reserce_time_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomDialog.dismiss();
            }
        });
        inflate.findViewById(R.id.dialog_reserce_time_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                String getDay=""+day;
                if (DayString.equals("00")){
                    DayString="今天";
                    getDay=""+day;
                }else if (DayString.equals("今天")){
                    getDay=""+day;
                }else if (DayString.equals("明天")){
                    getDay=""+(day+1);
                }else if (DayString.equals("后天")){
                    getDay=""+(day+2);
                }
                estimatedeparturetime=year+"-"+month+"-"+getDay+"  "+HoursString+":"+MinString;
                tv_estimated_time.setText(DayString+" "+HoursString+":"+MinString);
                bottomDialog.dismiss();
            }
        });
        //防止弹出两个窗口
        if (bottomDialog !=null && bottomDialog.isShowing()) {
            return;
        }

        bottomDialog.setContentView(inflate);
        bottomDialog.show();
    }

    //获取订单详情
    public void UpdateOrderDtl(OrderDetailBean.DataBean data){
       order_id = data.getId()+"";
        if (data!=null) {
            String status = data.getStatus();
            if (status.equals("6")) {
                Intent intent=new Intent(mContext,EvaluateActivity.class);
                intent.putExtra("order_id",order_id);
                startActivity(intent);
            }else if (status.equals("2")){
                rl_order_setorder.setVisibility(View.GONE);
                rl_wait_order.setVisibility(View.GONE);
                cancel_tv_cancelOrder.setVisibility(View.VISIBLE);
                rl_order_cancel.setVisibility(View.VISIBLE);
                cancel_tv_carNumber.setText(data.getDriverInfo().getLicenseplate());
                cancel_tv_carName.setText(data.getDriverInfo().getNickname() + "  " + data.getDriverInfo().getOrder_count() + "单");
                stocketService.UpdateRecrivedOrders();
            }else if (status.equals("3")){
                rl_order_setorder.setVisibility(View.GONE);
                rl_wait_order.setVisibility(View.GONE);
                cancel_tv_cancelOrder.setVisibility(View.VISIBLE);
                rl_order_cancel.setVisibility(View.VISIBLE);
                cancel_tv_carNumber.setText(data.getDriverInfo().getLicenseplate());
                cancel_tv_carName.setText(data.getDriverInfo().getNickname() + "  " + data.getDriverInfo().getOrder_count() + "单");
                stocketService.UpdateRecrivedOrders();
            }else if (status.equals("4")){
                rl_order_setorder.setVisibility(View.GONE);
                rl_wait_order.setVisibility(View.GONE);
                cancel_tv_cancelOrder.setVisibility(View.GONE);
                rl_order_cancel.setVisibility(View.VISIBLE);
                cancel_tv_carNumber.setText(data.getDriverInfo().getLicenseplate());
                cancel_tv_carName.setText(data.getDriverInfo().getNickname() + "  " + data.getDriverInfo().getOrder_count() + "单");
                stocketService.UpdateRecrivedOrders();
            }else if (status.equals("5")){
                Intent intent = new Intent(mContext, OrderPayActivity.class);
                intent.putExtra("order_id",order_id);
                startActivity(intent);
            }
            OrderStatus=status;


        }
    }

    //获取订单详情
    public void UpdateOrderDtlOne(OrderDetailOneBean.DataBean data){
        if (data!=null) {
            order_id = data.getId()+"";
            String status = data.getStatus();
             if (status.equals("0")){
                 rl_order_setorder.setVisibility(View.GONE);
                 rl_order_estimated_price.setVisibility(View.GONE);
                 cancel_tv_cancelOrder.setVisibility(View.VISIBLE);
                 rl_wait_order.setVisibility(View.VISIBLE);
                 rl_order_cancel.setVisibility(View.VISIBLE);
                 cancel_tv_carNumber.setText("等待司机接单");
                 cancel_tv_carName.setText("");
            }
        }
    }

    //获取订单详情
    public static void GetOrderStatus(String orderStatus){
        OrderStatus=orderStatus;
        Log.e("OrderStatus",orderStatus);
        //获取订单状态
       homePresenter.GetIndex(token);
    }

    //取消订单成功
    public static void  CancelOrder(){
    ToastUtils.showToast(mContext,"订单取消成功");
    order_id="";
        ORDER_TYPE=0;
        CITY_TYPE=1;
        IsCancelOrder=true;

    }

    //显示司机位置
    public static void UpdateDervierLoaction(String text){
        Gson DervierLoactiongson = new GsonBuilder().create();
        GetOrderDirverBean getOrderDirverBean = DervierLoactiongson.fromJson(text, GetOrderDirverBean.class);
        if (getOrderDirverBean.getData()!=null){
            GetOrderDirverBean.DataBean data = getOrderDirverBean.getData();
            GetOrderDirverBean.DataBean.DriverPositionBean driver_position = data.getDriver_position();
            if (driver_position!=null){
                DervierPositionE = Double.valueOf(driver_position.getPositionE());
                DervierPositionN = Double.valueOf(driver_position.getPositionN());
                dervierDistance = driver_position.getDistance();
                dervierDuration = driver_position.getDuration();
                dervierMoney = driver_position.getMoney();
            }
            IsDervierLoaction=true;
        }

    }

    @Override
    public View getInfoWindow(Marker marker) {
        View infoWindow = getLayoutInflater().inflate(R.layout.map_infowindow, null);//display为自定义layout文件
        TextView tv_content = infoWindow.findViewById(R.id.infowindow_tv_content);
        TextView tv_distance = infoWindow.findViewById(R.id.infowindow_tv_distance);
        TextView tv_duration = infoWindow.findViewById(R.id.infowindow_tv_duration);
        TextView tv_price = infoWindow.findViewById(R.id.infowindow_tv_price);
        if (OrderStatus.equals("2")) {
            tv_distance.setVisibility(View.GONE);
            tv_duration.setVisibility(View.GONE);
            tv_price.setVisibility(View.GONE);
            tv_content.setVisibility(View.VISIBLE);
            StringFormatUtil spanStr = new StringFormatUtil(mContext, "距离您" + dervierDistance + "公里," + dervierDuration + "分钟", "距离您", R.color.black).fillColor();
            tv_content.setText(spanStr.getResult());
        }else if (OrderStatus.equals("3")){
            tv_distance.setVisibility(View.GONE);
            tv_duration.setVisibility(View.GONE);
            tv_price.setVisibility(View.GONE);
            tv_content.setVisibility(View.VISIBLE);
            tv_content.setText("司机点击了到达，您可与司机确认上车地点");
            tv_content.setTextColor(getResources().getColor(R.color.black));
        }else if (OrderStatus.equals("4")){
            tv_content.setVisibility(View.GONE);
            tv_distance.setVisibility(View.VISIBLE);
            tv_duration.setVisibility(View.VISIBLE);
            tv_price.setVisibility(View.VISIBLE);
            StringFormatUtil spanStr = new StringFormatUtil(mContext, "距离终点" + dervierDistance + "公里", "距离终点", R.color.black).fillColor();
            tv_distance.setText(spanStr.getResult());
            StringFormatUtil spanStr1 = new StringFormatUtil(mContext, "预计还需" + dervierDuration + "分钟", "预计还需", R.color.black).fillColor();
            tv_duration.setText(spanStr1.getResult());
            tv_price.setText(dervierMoney);
        }
        return infoWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

}
