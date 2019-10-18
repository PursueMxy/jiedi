package com.icarexm.jiediuser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.utils.DataCleanManagerUtils;
import com.icarexm.jiediuser.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetActivity extends AppCompatActivity {

    @BindView(R.id.set_tv_clean_cache)
    TextView tv_clean_cache;
    private Context mContext;
    private SharedPreferences share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        mContext = getApplicationContext();
        share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        try {
            String totalCacheSize = DataCleanManagerUtils.getTotalCacheSize(this);
            tv_clean_cache.setText(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ButterKnife.bind(this);
    }

    @OnClick({R.id.set_rl_clean_cache,R.id.set_img_back,R.id.set_rl_emergency_contact,R.id.set_rl_about,
            R.id.set_rl_agreement_explain,R.id.set_btn_logout})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.set_rl_clean_cache:
                ToastUtils.showToast(mContext,"清除成功");
                tv_clean_cache.setText("清除成功");
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
                SharedPreferences.Editor editor = share.edit();
                editor.putString("openid","");
                editor.putString("token","");
                editor.putString("user_id","");
                editor.putString("nickname","");
                editor.putString("avatar","");
                editor.commit();//提交
                startActivity(new Intent(mContext,LoginActivity.class));
                finish();
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
