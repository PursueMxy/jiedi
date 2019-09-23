package com.icarexm.jiedi.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;
import com.icarexm.jiedi.R;
import com.icarexm.jiedi.service.StocketServices;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_map)
    MapView mMapView;

    MyLocationStyle myLocationStyle;
    private int CANCELORDER_CODE=1001;

    private StocketServices spservice;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            spservice = ((StocketServices.LocalBinder) service).getService();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private AMap aMap;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initService();
        ButterKnife.bind(this);
        mContext = getApplicationContext();
        mMapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap =mMapView.getMap();
        }
        //显示当前位置
        myLocation();
    }

    @OnClick({R.id.main_tv_cancel_order})
    public void onViewClicked(View view){
        switch (view.getId()) {
            case R.id.main_tv_cancel_order:
                Intent intent = new Intent(mContext, CancelOrderActivity.class);
                startActivityForResult(intent,CANCELORDER_CODE);
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

    private void myLocation() {
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
        // myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
         aMap.getUiSettings().setMyLocationButtonEnabled(false);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);//设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
    }

    //开启服务
    private void initService() {
        Intent bluetoothIntent;
        if (spservice == null) {
            bluetoothIntent = new Intent(MainActivity.this, StocketServices.class);
            bindService(bluetoothIntent, serviceConnection, BIND_AUTO_CREATE);
        }
    }

    //关闭长连接
    private void closeService() {
        if (spservice != null) {
            try {
                unbindService(serviceConnection);
                spservice = null;
            } catch (Exception e) {
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
}
