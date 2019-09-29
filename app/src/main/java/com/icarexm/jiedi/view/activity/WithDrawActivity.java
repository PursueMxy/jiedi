package com.icarexm.jiedi.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.icarexm.jiedi.R;
import com.icarexm.jiedi.contract.OrderListContract;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class WithDrawActivity extends AppCompatActivity {


    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_draw);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
    }
    @OnClick({R.id.withdraw_rl_withdrawtype,R.id.with_draw_img_back,R.id.withdraw_tv_add})
    public void  onViewClick(View view){
        switch (view.getId()){
            case R.id.with_draw_img_back:
            finish();
            break;
            case R.id.withdraw_rl_withdrawtype:
                startActivity(new Intent(mContext,WithDrawTypeActivity.class));
            break;
            case R.id.withdraw_tv_add:
                startActivity(new Intent(mContext,AddWithdrawActivity.class));
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
