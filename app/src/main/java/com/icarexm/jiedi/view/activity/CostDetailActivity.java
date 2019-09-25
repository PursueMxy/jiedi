package com.icarexm.jiedi.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;

import com.icarexm.jiedi.R;
import com.icarexm.jiedi.contract.CostDetailContract;
import com.icarexm.jiedi.presenter.CostDetailPresenter;

public class CostDetailActivity extends AppCompatActivity implements CostDetailContract.View {

    private CostDetailPresenter costDetailPresenter;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_detail);
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        costDetailPresenter = new CostDetailPresenter(this);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void OrderPriceInfo(){

    }
}
