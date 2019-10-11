package com.icarexm.jiediuser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.icarexm.jiediuser.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PriceActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
    }
    @OnClick({R.id.price_btn_recharge,R.id.price_img_back,R.id.price_rl_balance_dtl,R.id.price_rl_coupon})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.price_btn_recharge:
                startActivity(new Intent(mContext,RechargeActivity.class));
                break;
            case R.id.price_img_back:
                finish();
                break;
            case R.id.price_rl_balance_dtl:
                startActivity(new Intent(mContext,BalanceDetailActivity.class));
                break;
            case R.id.price_rl_coupon:
                startActivity(new Intent(mContext,MyCouponActivity.class));
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
