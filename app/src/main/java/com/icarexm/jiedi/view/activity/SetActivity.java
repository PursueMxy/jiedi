package com.icarexm.jiedi.view.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageStats;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.icarexm.jiedi.Bean.ResultBean;
import com.icarexm.jiedi.R;
import com.icarexm.jiedi.utils.DataCleanManagerUtils;
import com.icarexm.jiedi.utils.RequstUrlUtils;
import com.icarexm.jiedi.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetActivity extends AppCompatActivity {

    private Context mContext;
    private View dialog_callphone;
    private TextView tv_phone_number;
    private AlertDialog alertDialog;
    private String token;
    private SharedPreferences sp;

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
        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        ButterKnife.bind(this);
    }

    @OnClick({R.id.set_img_back,R.id.set_rl_term_services,R.id.set_rl_callPhone,R.id.set_rl_about,R.id.set_rl_clear_cache,
    R.id.set_btn_logout})
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
            case R.id.set_btn_logout:
                logout();
                break;
        }
    }

    private void logout() {
        OkGo.<String>post(RequstUrlUtils.URL.logout)
                .params("token",token)
                .params("type","1")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ResultBean resultBean = new GsonBuilder().create().fromJson(response.body(), ResultBean.class);
                        if (resultBean.getCode()==202){
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("openid","");
                            editor.putString("token","");
                            editor.putString("user_id","");
                            editor.putString("nickname","");
                            editor.putString("avatar","");
                            editor.commit();//提交
                            startActivity(new Intent(mContext,LoginActivity.class));
                            finish();
                        }
                    }
                });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
