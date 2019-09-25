package com.icarexm.jiedi.view.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.icarexm.jiedi.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
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

}
