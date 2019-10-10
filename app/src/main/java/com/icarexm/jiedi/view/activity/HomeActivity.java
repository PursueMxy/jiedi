package com.icarexm.jiedi.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Rect;
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
import com.autonavi.rtbt.IFrameForRTBT;
import com.icarexm.jiedi.Bean.OrderListBean;
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
import com.lzy.okgo.model.Response;
import com.zhouyou.recyclerview.XRecyclerView;
import com.zhouyou.recyclerview.adapter.BaseRecyclerViewAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private Context mContext;
    private HomeAdapter homeAdapter;
    private AlertDialog alertDialog;
    private View dialog_home;
    private KeepCountdownView countDownProgressBar;
    private AlertDialog.Builder builder;
    private SharedPreferences sp;
    private String token;
    private String user_id;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    private AMapLocationClientOption mLocationOption;
    private String city="";
    private double longitude=0;
    private double latitude=0;
    private HomePresenter homePresenter;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mContext = getApplicationContext();
        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        user_id = sp.getString("user_id", "");
        ButterKnife.bind(this);
        InitUI();
        homePresenter = new HomePresenter(this);
        SetLocations();
        orderHandler.postDelayed(orderRunnable,200);
        initService();
    }

    //开启服务
    private void initService() {
        Intent bluetoothIntent;
        if (stocketService == null) {
            bluetoothIntent = new Intent(HomeActivity.this, StocketServices.class);
            bindService(bluetoothIntent, serviceConnection, BIND_AUTO_CREATE);
        }
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
        countDownProgressBar = dialog_home.findViewById(R.id.dialog_home_countDownProgress);
        countDownProgressBar.plus(10);
        countDownProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                homePresenter.GetAutoOrder(token,"0");
                btn_gainorder.setText("接单");
                btn_gainorder.setBackground(getResources().getDrawable(R.drawable.btn_login));
                orderHandler.removeCallbacks(orderRunnable);
                Intent intent = new Intent(mContext, MainActivity.class);
                intent.putExtra("order_id","162");
                intent.putExtra("positionE",longitude+"");
                intent.putExtra("positionN",latitude+"");
                intent.putExtra("position",city);
                startActivity(intent);
                mLocationClient.stopLocation();
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
                Log.e("home","结束");
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
                Intent intent = new Intent(mContext, MainActivity.class);
                intent.putExtra("order_id",list.get(position).getId()+"");
                intent.putExtra("positionE",longitude+"");
                intent.putExtra("positionN",latitude+"");
                intent.putExtra("position",city);
                startActivity(intent);
                mLocationClient.stopLocation();
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
//                    ShowDialog();
                }else {
                    homePresenter.GetAutoOrder(token,"0");
//                    alertDialog.dismiss();
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
        if (data!=null) {
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
    public void ShowDialog(){
            builder = new AlertDialog.Builder(HomeActivity.this);
            if (alertDialog==null) {
                alertDialog = builder.setView(dialog_home)
                        .create();
                alertDialog.show();
            }else {
                alertDialog.show();
            }
            countDownProgressBar.startCountDown();
        }

   //刷新订单列表handler
    private Handler orderHandler=new Handler();
    Runnable orderRunnable=new Runnable() {
        @Override
        public void run() {
            if (longitude!=0&&latitude!=0&&!city.equals("")) {
                homePresenter.GetOrder(token, city, longitude + "", latitude + "");
                orderHandler.postDelayed(orderRunnable,10000);
            }else {
                orderHandler.postDelayed(orderRunnable,200);
            }
        }
    };


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            closeService();
            finish();
        }
        return super.onKeyDown(keyCode, event);
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
                    //城市信息
                    city = aMapLocation.getCity();
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
}
