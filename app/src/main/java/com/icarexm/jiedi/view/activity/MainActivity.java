package com.icarexm.jiedi.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
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

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.icarexm.jiedi.Bean.OrderTypeBean;
import com.icarexm.jiedi.R;
import com.icarexm.jiedi.contract.MainContract;
import com.icarexm.jiedi.presenter.MainPresenter;
import com.icarexm.jiedi.service.StocketServices;
import com.icarexm.jiedi.utils.ToastUtils;

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
    @BindView(R.id.main_define_tv_car_code)
    TextView tv_define_car_code;
    @BindView(R.id. main_ratb_tv_car_code)
    TextView tv_ratb_car_code;
    @BindView(R.id.main_tv_name)
    TextView tv_name;
    @BindView(R.id.main_define_tv_name)
    TextView tv_define_name;
    @BindView(R.id.main_ratb_tv_name)
    TextView tv_ratb_name;
    @BindView(R.id.main_tv_shape)
    TextView tv_shape;
    @BindView(R.id.main_define_tv_ratingbar)
    TextView tv_define_ratingbar;
    @BindView(R.id.main_ratb_tv_ratingbar)
    TextView tv_ratb_ratingbar;
    @BindView(R.id.main_ratb_tv_money)
    TextView tv_ratb_money;
    @BindView(R.id.main_define_tv_money)
    TextView tv_define_money;
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
    //订单评价
    @BindView(R.id.main_img_safety2)
    ImageView img_safety2;
    @BindView(R.id.main_rl_safety2)
    RelativeLayout rl_safety2;
    //订单已评价
    @BindView(R.id.main_img_safety3)
    ImageView img_safety3;
    @BindView(R.id.main_rl_safety3)
    RelativeLayout rl_safety3;

    @BindView(R.id.main_define_ratingBar1)
    RatingBar define_ratingbar;
    @BindView(R.id.main_ratb_ratingBar1)
    RatingBar ratb__ratingBar1;



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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        user_id = sp.getString("user_id", "");
        mianPresenter = new MainPresenter(this);
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
        //显示当前位置
        myLocation();
        initService();
        mianPresenter.GetOrderInfo(token,order_id);
    }

    @OnClick({R.id.main_tv_cancel_order,R.id.main_img_back,R.id.main_tv_order_type,R.id.main_define_btn_confirm})
    public void onViewClicked(View view){
        switch (view.getId()) {
            case R.id.main_tv_cancel_order:
                Intent intent = new Intent(mContext, CancelOrderActivity.class);
                startActivityForResult(intent,CANCELORDER_CODE);
                break;
            case R.id.main_img_back:
                closeService();
                finish();
                break;
            case R.id.main_tv_order_type:
                String typeName= tv_orderType.getText().toString();
                if (typeName.equals("到达上车点")){
                    stocketService.driver_arrive(order_id);
                }else if (typeName.equals("乘客已上车")){
                    stocketService.passenger_boarding(order_id);
                }else if (typeName.equals("到达终点")){
                    stocketService.arrive(order_id);
                }
                break;
            case R.id.main_define_btn_confirm:
                float rating = define_ratingbar.getRating();
                if (rating!=0){
                    mianPresenter.GetEvaluate(token,order_id,rating+"","");
                }else {
                    ToastUtils.showToast(mContext,"评价不能为空");
                }
                break;
        }
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CANCELORDER_CODE) {

            }

        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            closeService();
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }


    private void myLocation() {
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
        // myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
         aMap.getUiSettings().setMyLocationButtonEnabled(false);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);//设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
        //设置希望展示的地图缩放级别
        CameraUpdate mCameraUpdate = CameraUpdateFactory.zoomTo(16);
        aMap.moveCamera(mCameraUpdate);

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
        Log.e("UI更新","哦看看");
        OrderTypeBean.DataBean.UserInfoBean userInfo = data.getUserInfo();
        tv_name.setText(userInfo.getNickname());
        tv_ratb_name.setText(userInfo.getNickname());
        tv_define_name.setText(userInfo.getNickname());
        tv_shape.setText(data.getUser_evaluate());
        tv_define_ratingbar.setText(data.getUser_evaluate());
        tv_ratb_ratingbar.setText(data.getUser_evaluate());
        tv_define_money.setText("¥ "+data.getBudget()+"");
        tv_ratb_money.setText("¥ "+data.getBudget()+"");
        //订单状态:0=发起订单,1=订单分配中,2=司机接单,3=已到达,4=乘客已上车,5=到达目的地,6=订单完成,7=订单异常,8=取消订单,9=已评价,10=半途取消
        String status = data.getStatus();
        //服务类型 0-城内出行 1-城际出行 2-接机 3-送机
        String service_type = data.getService_type();
        if (service_type.equals("0")&&service_type.equals("1")){
            tv_car_code.setVisibility(View.INVISIBLE);
            tv_define_car_code.setVisibility(View.INVISIBLE);
            tv_ratb_car_code.setVisibility(View.INVISIBLE);
        }else {
            tv_car_code.setText(data.getFlightno());
            tv_define_car_code.setText(data.getFlightno());
            tv_ratb_car_code.setText(data.getFlightno());
        }
        if (status.equals("0")){
            MainActivity.PlaceOrders();
        }
        else if (status.equals("2")){
            img_safety1.setVisibility(View.GONE);
            img_safety2.setVisibility(View.GONE);
            img_safety3.setVisibility(View.GONE);
            rl_safety1.setVisibility(View.GONE);
            rl_safety2.setVisibility(View.GONE);
            rl_safety3.setVisibility(View.GONE);
            img_safety.setVisibility(View.VISIBLE);
            rl_safety.setVisibility(View.VISIBLE);
            tv_orderType.setText("到达上车点");
        }
        else if (status.equals("3")){
            img_safety1.setVisibility(View.GONE);
            img_safety2.setVisibility(View.GONE);
            img_safety3.setVisibility(View.GONE);
            rl_safety1.setVisibility(View.GONE);
            rl_safety2.setVisibility(View.GONE);
            rl_safety3.setVisibility(View.GONE);
            img_safety.setVisibility(View.VISIBLE);
            rl_safety.setVisibility(View.VISIBLE);
            tv_orderType.setText("乘客已上车");
        }
        else  if (status.equals("4")){
            img_safety1.setVisibility(View.GONE);
            img_safety2.setVisibility(View.GONE);
            img_safety3.setVisibility(View.GONE);
            rl_safety1.setVisibility(View.GONE);
            rl_safety2.setVisibility(View.GONE);
            rl_safety3.setVisibility(View.GONE);
            img_safety.setVisibility(View.VISIBLE);
            rl_safety.setVisibility(View.VISIBLE);
            tv_orderType.setText("到达终点");
        }else if (status.equals("5")){
            closeService();
            finish();
        }else if (status.equals("6")){
            int score = data.getScore();
            if (score>0) {
                img_safety.setVisibility(View.GONE);
                rl_safety.setVisibility(View.GONE);
                img_safety1.setVisibility(View.GONE);
                rl_safety1.setVisibility(View.GONE);
                img_safety2.setVisibility(View.GONE);
                rl_safety2.setVisibility(View.GONE);
                img_safety3.setVisibility(View.VISIBLE);
                rl_safety3.setVisibility(View.VISIBLE);
            }else {
                img_safety.setVisibility(View.GONE);
                rl_safety.setVisibility(View.GONE);
                img_safety1.setVisibility(View.GONE);
                rl_safety1.setVisibility(View.GONE);
                img_safety3.setVisibility(View.GONE);
                rl_safety3.setVisibility(View.GONE);
                img_safety2.setVisibility(View.VISIBLE);
                rl_safety2.setVisibility(View.VISIBLE);
            }
        }
        else if (status.equals("8")){
            img_safety.setVisibility(View.GONE);
            rl_safety.setVisibility(View.GONE);
            img_safety2.setVisibility(View.GONE);
            rl_safety2.setVisibility(View.GONE);
            img_safety3.setVisibility(View.GONE);
            rl_safety3.setVisibility(View.GONE);
            img_safety1.setVisibility(View.VISIBLE);
            rl_safety1.setVisibility(View.VISIBLE);
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
