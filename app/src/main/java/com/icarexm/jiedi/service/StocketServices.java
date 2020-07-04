package com.icarexm.jiedi.service;

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
import com.icarexm.jiedi.Bean.DeliverBean;
import com.icarexm.jiedi.Bean.DriverArriveBean;
import com.icarexm.jiedi.Bean.LoginDemoBean;
import com.icarexm.jiedi.Bean.PositionsBean;
import com.icarexm.jiedi.Bean.ReceiptBean;
import com.icarexm.jiedi.Bean.RefuseOrderBean;
import com.icarexm.jiedi.Bean.ServicesMsgBean;
import com.icarexm.jiedi.Bean.UserToDriverBean;
import com.icarexm.jiedi.Bean.pointsBean;
import com.icarexm.jiedi.model.MainModel;
import com.icarexm.jiedi.presenter.MainPresenter;
import com.icarexm.jiedi.utils.RequstUrlUtils;
import com.icarexm.jiedi.utils.ToastUtils;
import com.icarexm.jiedi.view.activity.HomeActivity;
import com.icarexm.jiedi.view.activity.LoginActivity;
import com.icarexm.jiedi.view.activity.LogonActivity;
import com.icarexm.jiedi.view.activity.MainActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.IOException;
import java.security.PublicKey;
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

import static com.lzy.okgo.utils.HttpUtils.runOnUiThread;

public class StocketServices extends Service {
    private final IBinder binder = new LocalBinder(); // 服务绑定器
    private SharedPreferences sp;
    private String token="";
    private String user_id;


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
    }



}
