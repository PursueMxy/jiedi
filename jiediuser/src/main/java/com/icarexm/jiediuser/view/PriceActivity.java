package com.icarexm.jiediuser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.bean.MemberIndexBean;
import com.icarexm.jiediuser.utils.RequstUrlUtils;
import com.icarexm.jiediuser.utils.StringUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PriceActivity extends AppCompatActivity {

    @BindView(R.id.price_tv_total_money)
    TextView tv_total_money;
    @BindView(R.id.price_tv_coupon_number)
    TextView tv_coupon_number;
    private Context mContext;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);
        mContext = getApplicationContext();
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        ButterKnife.bind(this);
        InitData();
    }

    private void InitData() {
        OkGo.<String>post(RequstUrlUtils.URL.memberindex)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new GsonBuilder().create();
                        MemberIndexBean memberIndexBean = gson.fromJson(response.body(), MemberIndexBean.class);
                        if (memberIndexBean.getCode()==200){
                            MemberIndexBean.DataBean data = memberIndexBean.getData();
                            if (data!=null){
                                String money = data.getMoney();
                                tv_total_money.setText(money);
                                tv_coupon_number.setText(data.getCoipon_count()+"张");
                            }

                        }
                    }
                });
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
