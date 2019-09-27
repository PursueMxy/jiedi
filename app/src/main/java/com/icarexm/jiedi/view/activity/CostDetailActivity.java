package com.icarexm.jiedi.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.icarexm.jiedi.R;
import com.icarexm.jiedi.contract.CostDetailContract;
import com.icarexm.jiedi.presenter.CostDetailPresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CostDetailActivity extends AppCompatActivity implements CostDetailContract.View {



    private CostDetailPresenter costDetailPresenter;
    private String token;
    private String order_id;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_detail);
        ButterKnife.bind(this);
        mContext = getApplicationContext();
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        Intent intent = getIntent();
        order_id = intent.getStringExtra("order_id");
        costDetailPresenter = new CostDetailPresenter(this);
        costDetailPresenter.GetOrderPriceInfo(order_id,token);

    }

    @OnClick({R.id.cost_dtl_btn_accountingRule})
    public void  onViewClicked(View view){
        switch (view.getId()){
            case R.id.cost_dtl_btn_accountingRule:
                startActivity(new Intent(mContext,AccountingRulesActivity.class));
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

    public void OrderPriceInfo(){

    }
}
