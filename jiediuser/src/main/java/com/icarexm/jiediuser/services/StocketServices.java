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

import java.text.DecimalFormat;
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
    private String longitude;
    private String latitude;
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
    private com.icarexm.jiediuser.bean.pointsBean pointsBean;

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
    }


}
