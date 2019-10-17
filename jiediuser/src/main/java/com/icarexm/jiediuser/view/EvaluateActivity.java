package com.icarexm.jiediuser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
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
import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.bean.OrderDetailBean;
import com.icarexm.jiediuser.contract.EvaluateContract;
import com.icarexm.jiediuser.presenter.EvaluatePresenter;
import com.icarexm.jiediuser.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EvaluateActivity extends AppCompatActivity implements EvaluateContract.View {

    @BindView(R.id.evaluate_map)
    MapView mMapView;

    //订单评价页面
    @BindView(R.id.evaluate_rl_order_evaluate)
    RelativeLayout rl_order_evaluate;
    @BindView(R.id.evaluate_define_tv_car_code)
    TextView define_tv_car_code;
    @BindView(R.id.evaluate_define_tv_carName)
    TextView define_tv_carName;
    @BindView(R.id.evaluate_define_tv_driver_evaluate)
    TextView define_tv_driver_evaluate;
    @BindView(R.id.evaluate_define_ratingBar)
    RatingBar define_ratingBar;
    @BindView(R.id.evaluate_define_tv_moneys)
    TextView define_tv_money;
    //订单已评价页面
    @BindView(R.id.evaluate_rl_order_stop_evaluate)
    RelativeLayout rl_order_stop_evaluate;
    @BindView(R.id.evaluate_todefine_tv_car_code)
    TextView todefine_tv_carcode;
    @BindView(R.id.evaluate_todefine_tv_carName)
    TextView todefine_tv_carName;
    @BindView(R.id.evaluate_todefine_tv_ordercould)
    TextView todefine_tv_ordercould;
    @BindView(R.id.evaluate_todefine_tv_orderprice)
    TextView todefine_tv_orderprice;
    @BindView(R.id.evaluate_todefine_ratingBar)
    RatingBar todefine_ratingBar;
    private Context mContext;
    private EvaluatePresenter evaluatePresenter;
    private String token;
    private String order_id;
    private AMap aMap;
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        mContext = getApplicationContext();
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        Intent intent = getIntent();
        order_id = intent.getStringExtra("order_id");
        ButterKnife.bind(this);
        evaluatePresenter = new EvaluatePresenter(this);
        evaluatePresenter.GetOrderPrice(token, order_id);
        mMapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap =mMapView.getMap();
        }
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

    @OnClick({R.id.evaluate_img_back,R.id.evaluate_define_btn_confirm,R.id.evaluate_define_tv_moneys,R.id.evaluate_todefine_tv_orderprice})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.evaluate_img_back:
                finish();
                break;
            case R.id.evaluate_define_btn_confirm:
                float rating = define_ratingBar.getRating();
                evaluatePresenter.GetEvaluate(token,order_id,rating+"","");
                break;
            case R.id.evaluate_define_tv_moneys:
                Intent intent = new Intent(mContext, CostDetailActivity.class);
                intent.putExtra("order_id",order_id);
                startActivity(intent);
                break;
            case R.id.evaluate_todefine_tv_orderprice:
                Intent intent1 = new Intent(mContext, CostDetailActivity.class);
                intent1.putExtra("order_id",order_id);
                startActivity(intent1);
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

    public void UpdateUI(OrderDetailBean.DataBean data){
        String user_evaluate = data.getUser_score_order();
        if (user_evaluate.equals("0")) {
            rl_order_stop_evaluate.setVisibility(View.GONE);
            rl_order_evaluate.setVisibility(View.VISIBLE);
            define_tv_car_code.setText(data.getDriverInfo().getLicenseplate());
            define_tv_carName.setText(data.getDriverInfo().getNickname() + "  " + data.getDriverInfo().getOrder_count() + "单");
            define_tv_driver_evaluate.setText(data.getDriver_evaluate());
            define_tv_money.setText(data.getMoney()+"元");
        }else {
            rl_order_evaluate.setVisibility(View.GONE);
            rl_order_stop_evaluate.setVisibility(View.VISIBLE);
            String driver_evaluate = data.getUser_score_order();
            todefine_ratingBar.setRating(Float.parseFloat(data.getUser_score_order()));
            todefine_tv_carcode.setText(data.getDriverInfo().getLicenseplate());
            todefine_tv_carName.setText(data.getDriverInfo().getNickname() + "  " + data.getDriverInfo().getOrder_count() + "单");
            todefine_tv_orderprice.setText(data.getMoney()+"元");
            todefine_tv_ordercould.setText(driver_evaluate);
        }
    }

    //评价成功界面更新
    public void UpdateEvalute(int code,String msg){
        ToastUtils.showToast(mContext,msg);
        if (msg.equals("评价成功")){
        }

        evaluatePresenter.GetOrderPrice(token, order_id);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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
