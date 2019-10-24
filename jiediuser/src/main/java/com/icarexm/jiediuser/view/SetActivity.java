package com.icarexm.jiediuser.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.bean.ServicesMsgBean;
import com.icarexm.jiediuser.utils.ClearCacheManager;
import com.icarexm.jiediuser.utils.DataCleanManagerUtils;
import com.icarexm.jiediuser.utils.RequstUrlUtils;
import com.icarexm.jiediuser.utils.SysUtil;
import com.icarexm.jiediuser.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetActivity extends AppCompatActivity {

    @BindView(R.id.set_tv_clean_cache)
    TextView tv_clean_cache;
    private Context mContext;
    private SharedPreferences share;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        mContext = getApplicationContext();
        share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        ButterKnife.bind(this);
        try {
            String cache = ClearCacheManager.getTotalCacheSize(SetActivity.this);
            if(SysUtil.isNotNUll(cache)){
                tv_clean_cache.setText(cache);
            }else {
                tv_clean_cache.setText("0.0B");
            }

        } catch (Exception e) {

        }
    }

    @OnClick({R.id.set_rl_clean_cache,R.id.set_img_back,R.id.set_rl_emergency_contact,R.id.set_rl_about,
            R.id.set_rl_agreement_explain,R.id.set_btn_logout})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.set_rl_clean_cache:
                ClearCacheManager.clearAllCache(SetActivity.this);
                ToastUtils.showToast(mContext,"清除成功");
                tv_clean_cache.setText("0.0B");
                break;
            case R.id.set_img_back:
                finish();
                break;
            case R.id.set_rl_emergency_contact:
                startActivity(new Intent(mContext,EmergencyContactActivity.class));
                break;
            case R.id.set_rl_about:
                startActivity(new Intent(mContext,AboutActivity.class));
                break;
            case R.id.set_rl_agreement_explain:
                startActivity(new Intent(mContext,AgreementExplainActivity.class));
                break;
            case R.id.set_btn_logout:
                logout();
                break;
        }
    }

    private void logout() {
        OkGo.<String>post(RequstUrlUtils.URL.logout)
                .params("token", token)
                .params("type","0")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                         ServicesMsgBean resultBean = new GsonBuilder().create().fromJson(response.body(),ServicesMsgBean.class);
                        if (resultBean.getCode()==202){
                            SharedPreferences.Editor editor = share.edit();
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
