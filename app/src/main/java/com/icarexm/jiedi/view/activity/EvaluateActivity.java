package com.icarexm.jiedi.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
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
import com.icarexm.jiedi.Bean.OrderDetailBean;
import com.icarexm.jiedi.Bean.OrderTypeBean;
import com.icarexm.jiedi.R;
import com.icarexm.jiedi.contract.EvaluateContract;
import com.icarexm.jiedi.presenter.EvaluatePresenter;
import com.icarexm.jiedi.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class EvaluateActivity extends AppCompatActivity implements EvaluateContract.View {

    @BindView(R.id.evaluate_map)
    MapView mMapView;
    //订单评价
    @BindView(R.id.evaluate_img_safety2)
    ImageView img_safety2;
    @BindView(R.id.evaluate_rl_safety2)
    RelativeLayout rl_safety2;
    //订单已评价
    @BindView(R.id.evaluate_img_safety3)
    ImageView img_safety3;
    @BindView(R.id.evaluate_rl_safety3)
    RelativeLayout rl_safety3;

    @BindView(R.id.evaluate_define_ratingBar1)
    RatingBar define_ratingbar;
    @BindView(R.id.evaluate_ratb_ratingBar1)
    RatingBar ratb_ratingBar1;

    @BindView(R.id.evaluate_define_tv_car_code)
    TextView tv_define_car_code;
    @BindView(R.id.evaluate_define_tv_ratingbar)
    TextView tv_define_ratingbar;
    @BindView(R.id.evaluate_define_tv_name)
    TextView tv_define_name;
    @BindView(R.id.evaluate_define_tv_money)
    TextView tv_define_money;

    @BindView(R.id.evaluate_ratb_tv_car_code)
    TextView tv_ratb_car_code;
    @BindView(R.id.evaluate_ratb_tv_name)
    TextView tv_ratb_name;
    @BindView(R.id.evaluate_ratb_tv_ratingbar)
    TextView tv_ratb_ratingbar;
    @BindView(R.id.evaluate_ratb_tv_money)
    TextView tv_ratb_money;
    private String token;
    private String user_id;
    private Context mContext;
    private AMap aMap;
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;
    private EvaluatePresenter evaluatePresenter;
    private String order_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        mContext = getApplicationContext();
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        Intent intent = getIntent();
        order_id = intent.getStringExtra("order_id");
        token = sp.getString("token", "");
        user_id = sp.getString("user_id", "");
        ButterKnife.bind(this);
        mMapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap =mMapView.getMap();
        }
        evaluatePresenter = new EvaluatePresenter(this);
        evaluatePresenter.GetOrderPrice(token, order_id);
        SetLocations();
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

    @OnClick({R.id.evaluate_define_btn_confirm,R.id.evaluate_img_back,R.id.evaluate_define_tv_money,R.id.evaluate_ratb_tv_money})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.evaluate_define_btn_confirm:
                float rating = define_ratingbar.getRating();
                if (rating!=0){
                    evaluatePresenter.GetEvaluate(token,order_id,rating+"","");
                }else {
                    ToastUtils.showToast(mContext,"评价不能为空");
                }
                break;
            case R.id.evaluate_img_back:
                finish();
                break;
            case R.id.evaluate_define_tv_money:
                Intent intent = new Intent(mContext, CostDetailActivity.class);
                intent.putExtra("order_id",order_id);
                startActivity(intent);
                finish();
                break;
            case R.id.evaluate_ratb_tv_money:
                Intent intent1 = new Intent(mContext, CostDetailActivity.class);
                intent1.putExtra("order_id",order_id);
                startActivity(intent1);
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void UpdateUI(OrderTypeBean.DataBean data){
        String user_evaluate = data.getDriver_score_order();
        String Flightno = data.getFlightno() + "";
        if (user_evaluate.equals("0")||user_evaluate.equals("")) {
            img_safety3.setVisibility(View.GONE);
            rl_safety3.setVisibility(View.GONE);
            img_safety2.setVisibility(View.VISIBLE);
            rl_safety2.setVisibility(View.VISIBLE);
            if (!Flightno.equals("")) {
                tv_define_car_code.setText(data.getFlightno() + "");
            }else {

            }
            tv_define_name.setText(data.getUserInfo().getNickname());
            tv_define_ratingbar.setText(data.getUser_evaluate());
            tv_define_money.setText("¥ "+data.getMoney());
        }else {
            img_safety2.setVisibility(View.GONE);
            rl_safety2.setVisibility(View.GONE);
            img_safety3.setVisibility(View.VISIBLE);
            rl_safety3.setVisibility(View.VISIBLE);
            String driver_evaluate = data.getDriver_evaluate();
            ratb_ratingBar1.setRating(Float.parseFloat(data.getDriver_score_order()));
            if (!Flightno.equals("")) {
                tv_ratb_car_code.setText(Flightno);
            }
            tv_ratb_name.setText(data.getUserInfo().getNickname());
            tv_ratb_car_code.setText(data.getDriverInfo().getLicenseplate());
            tv_ratb_ratingbar.setText(data.getDriverInfo().getNickname() + "  " + data.getDriverInfo().getOrder_count() + "单");
            tv_ratb_money.setText("¥ "+data.getMoney());
            tv_ratb_ratingbar.setText(driver_evaluate);
        }
    }


    //评价成功界面更新
    public void UpdateEvalute(int code,String msg){
        ToastUtils.showToast(mContext,msg);
        if (msg.equals("评价成功")){
            img_safety2.setVisibility(View.GONE);
            rl_safety2.setVisibility(View.GONE);
            rl_safety3.setVisibility(View.VISIBLE);
        }
        evaluatePresenter.GetOrderPrice(token, order_id);
    }

    private Marker markers;
    private String startingpoints;
    private double start_latitude;
    private double start_longitude;
    private String cityName;
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
                }else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", aMapLocation.getErrorCode()+"");
                }
            }
        }
    };
}
