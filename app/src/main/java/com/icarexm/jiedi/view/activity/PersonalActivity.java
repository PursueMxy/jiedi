package com.icarexm.jiedi.view.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.icarexm.jiedi.R;
import com.icarexm.jiedi.custView.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalActivity extends AppCompatActivity {

    @BindView(R.id.personal_tv_name)
    TextView tv_name;
    @BindView(R.id.personal_img_head_portrait)
    CircleImageView img_head_portrait;
    @BindView(R.id.personal_tv_phone)
    TextView tv_phone;
    @BindView(R.id.personal_tv_licenseplate)
    TextView tv_licenseplate;
    private Context mContext;
    private SharedPreferences share;
    private String mobile;
    private String nickname;
    private String avatar;
    private String licenseplate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
        share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        mobile = share.getString("mobile", "");
        nickname = share.getString("nickname", "");
        avatar = share.getString("avatar", "");
        licenseplate = share.getString("licenseplate", "");
        tv_name.setText(nickname);
        tv_phone.setText(mobile);
        tv_licenseplate.setText(licenseplate);
        Glide.with(this).load(avatar).into(img_head_portrait);
    }

    @OnClick({R.id.personal_img_back,R.id.personal_rl_order,R.id.personal_rl_price,R.id.personal_rl_message,R.id.personal_rl_set})
    public void onViewClicked(View view){
        switch (view.getId()) {
            case R.id.personal_img_back:
              finish();
                break;
            case R.id.personal_rl_order:
                startActivity(new Intent(mContext,OrderListActivity.class));
                break;
            case R.id.personal_rl_price:
                startActivity(new Intent(mContext,PriceActivity.class));
                break;
            case R.id.personal_rl_message:
                startActivity(new Intent(mContext,MessageCenterActivity.class));
                break;
            case R.id.personal_rl_set:
                startActivity(new Intent(mContext,SetActivity.class));
                break;
            default:
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

    public void UpdateUI(){

    }

}
