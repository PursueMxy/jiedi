package com.icarexm.jiedi.view.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageStats;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.icarexm.jiedi.R;
import com.icarexm.jiedi.utils.DataCleanManagerUtils;
import com.icarexm.jiedi.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetActivity extends AppCompatActivity {

    private Context mContext;
    private View dialog_callphone;
    private TextView tv_phone_number;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        mContext = getApplicationContext();
        try {
            String totalCacheSize = DataCleanManagerUtils.getTotalCacheSize(mContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ButterKnife.bind(this);
    }

    @OnClick({R.id.set_img_back,R.id.set_rl_term_services,R.id.set_rl_callPhone,R.id.set_rl_about,R.id.set_rl_clear_cache})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.set_img_back:
                finish();
                break;
            case R.id.set_rl_about:
                startActivity(new Intent(mContext,AboutActivity.class));
                break;
            case R.id.set_rl_term_services:
                startActivity(new Intent(mContext,TermServicesActivity.class));
                break;
            case R.id.set_rl_callPhone:
                dialog_callphone = getLayoutInflater().inflate(R.layout.dialog_callphone, null);
                tv_phone_number = dialog_callphone.findViewById(R.id.dialog_callphone_tv_number);
                dialog_callphone.findViewById(R.id.dialog_callphone_tv_back).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                dialog_callphone.findViewById(R.id.dialog_callphone_tv_call).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intentcall = new Intent();
                        //设置拨打电话的动作
                        intentcall.setAction(Intent.ACTION_CALL);
                        //设置拨打电话的号码
                        intentcall.setData(Uri.parse("tel:" + "059188888888"));
                        //开启打电话的意图
                        startActivity(intentcall);
                        alertDialog.dismiss();

                    }
                });
                AlertDialog.Builder builder = new AlertDialog.Builder(this).setView(dialog_callphone);
                alertDialog = builder.create();
                alertDialog.show();
                break;
            case R.id.set_rl_clear_cache:
                DataCleanManagerUtils.cleanInternalCache(mContext);
                ToastUtils.showToast(mContext,"清除缓存成功");
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

}
