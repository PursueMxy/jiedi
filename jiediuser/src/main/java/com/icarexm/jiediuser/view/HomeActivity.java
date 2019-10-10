package com.icarexm.jiediuser.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ComponentName;
import android.content.Context;
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
import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.contract.HomeContract;
import com.icarexm.jiediuser.custview.BottomDialog;
import com.icarexm.jiediuser.custview.mywheel.MyWheelView;
import com.icarexm.jiediuser.presenter.HomePresenter;
import com.icarexm.jiediuser.services.StocketServices;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity implements HomeContract.View, DistanceSearch.OnDistanceSearchListener {

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
    @BindView(R.id.home_rl_setorder)
    RelativeLayout rl_setorder;
    @BindView(R.id.home_tv_original_price)
    TextView tv_original_price;
    @BindView(R.id.home_tv_estimated_price)
    TextView tv_estimated_price;
    @BindView(R.id.home_rl_estimated_price)
    RelativeLayout rl_estimated_price;
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
    private Context mContext;
    private String cityName;
    private int ORDER_TYPE=0;
    private HomePresenter homePresenter;
    private SharedPreferences sp;
    private String token;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mContext = getApplicationContext();
        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        homePresenter = new HomePresenter(this);
        mMapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap =mMapView.getMap();
        }
        InitUI();
        initService();
        SetLocations();
        homePresenter.GetIndex(token);
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
                        ORDER_TYPE=0;
                        radioButton_inside_city.setBackgroundResource(R.drawable.myorder_choosed_color);
                        radioButton_inside_city.setTextColor(getResources().getColor(R.color.ff5181fb));
                        radioButton_interciry.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radioButton_interciry.setTextColor(getResources().getColor(R.color.black));
                        radioButton_transfer.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radioButton_transfer.setTextColor(getResources().getColor(R.color.black));
                        break;
                    case R.id.home_radiobutton_intercity:
                        ORDER_TYPE=1;
                        radioButton_inside_city.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radioButton_inside_city.setTextColor(getResources().getColor(R.color.black));
                        radioButton_interciry.setBackgroundResource(R.drawable.myorder_choosed_color);
                        radioButton_interciry.setTextColor(getResources().getColor(R.color.ff5181fb));
                        radioButton_transfer.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radioButton_transfer.setTextColor(getResources().getColor(R.color.black));
                        break;
                    case R.id.home_radiobutton_transfer:
                        ORDER_TYPE=2;
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

    }


    @OnClick({R.id.home_tv_destination,R.id.home_top_img_left,R.id.home_tv_edt_materials,R.id.home_tv_myorder,R.id.home_tv_price
    ,R.id.home_tv_set,R.id.home_tv_message_center,R.id.home_tv_recommend,R.id.home_btn_confirm_order,R.id.home_tv_TypeNow,
            R.id.home_tv_TypeMake,R.id.home_tv_estimated_time})
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
                break;
            case R.id.home_tv_myorder:
                startActivity(new Intent(mContext,MyOrderActivity.class));
                break;
            case R.id.home_tv_price:
                startActivity(new Intent(mContext,PriceActivity.class));
                break;
            case R.id.home_tv_set:
                startActivity(new Intent(mContext,SetActivity.class));
                break;
            case R.id.home_tv_message_center:
                startActivity(new Intent(mContext,MessageCenterActivity.class));
                break;
            case R.id.home_tv_recommend:
                startActivity(new Intent(mContext,RecommendActivity.class));
                break;
            case R.id.home_btn_confirm_order:
                stocketService.place_order(startingpointE,startingpointN,startingpoint,destinationE,destinationN,destination,
                        estimated_mileage,estimated_time,budget,ORDER_TYPE+"",cityName,flightno,estimatedeparturetime);
                break;
            case R.id.home_tv_TypeNow:
                tv_estimated_time.setVisibility(View.GONE);
                ll_city_type.setBackgroundResource(R.mipmap.icon_bluewhite);
                tv_typeNow.setTextColor(getResources().getColor(R.color.white));
                tv_typeMake.setTextColor(getResources().getColor(R.color.black));
                break;
            case R.id.home_tv_TypeMake:
                tv_estimated_time.setVisibility(View.VISIBLE);
                ll_city_type.setBackgroundResource(R.mipmap.icon_whiteblue);
                tv_typeNow.setTextColor(getResources().getColor(R.color.black));
                tv_typeMake.setTextColor(getResources().getColor(R.color.white));
                break;
            case R.id.home_tv_estimated_time:
                AppointmentDialog();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==INOUT_TIPS_CODE){
            try {
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
            }catch (Exception e){}
        }
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
        rl_setorder.setVisibility(View.GONE);
        rl_estimated_price.setVisibility(View.VISIBLE);
        tv_estimated_price.setText(money);
        tv_original_price.setText(money);
        budget=money;
    }

    //选择时间dialog
    public void  AppointmentDialog(){
        final BottomDialog bottomDialog = new BottomDialog(this, R.style.ActionSheetDialogStyle);
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_reserce_time, null);
        MyWheelView wva = inflate.findViewById(R.id.dialog_reserce_time_wheel_one);
        wva.setItems(Arrays.asList(PLANETS),0);//init selected position is 1 初始选中位置为1
        MyWheelView wva_two = inflate.findViewById(R.id.dialog_reserce_time_wheel_two);
        wva_two.setItems(Arrays.asList(HOURS_TYPE),0);//init selected position is 1 初始选中位置为1
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
                Calendar calendar = Calendar.getInstance();
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
}
