package com.icarexm.jiedi.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;
import com.icarexm.jiedi.R;
import com.icarexm.jiedi.service.StocketServices;

import java.util.List;

public class MainActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initService();
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
}
