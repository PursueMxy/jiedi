package com.icarexm.jiediuser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.contract.CostDetailContract;
import com.icarexm.jiediuser.presenter.CostDetailPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CostDetailActivity extends AppCompatActivity implements CostDetailContract.View {
    @BindView(R.id.cost_dtl_service_type)
    TextView tv_service_type;
    @BindView(R.id.cost_dtl_tv_time)
    TextView tv_time;
    @BindView(R.id.cost_dtl_tv_paymoney)
    TextView tv_paymoney;
    private CostDetailPresenter costDetailPresenter;
    private String token;
    private String order_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_detail);
        Intent intent = getIntent();
        order_id = intent.getStringExtra("order_id");
        ButterKnife.bind(this);
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        costDetailPresenter = new CostDetailPresenter(this);
        costDetailPresenter.GetOrderDetail(token,order_id);
    }

    @OnClick({R.id.cost_dtl_img_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.cost_dtl_img_back:
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

    public void UpdateUI(){

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
