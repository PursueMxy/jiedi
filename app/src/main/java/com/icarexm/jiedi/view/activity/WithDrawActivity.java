package com.icarexm.jiedi.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.icarexm.jiedi.Bean.ResultBean;
import com.icarexm.jiedi.Bean.WithdrawalDefaultBean;
import com.icarexm.jiedi.R;
import com.icarexm.jiedi.contract.OrderListContract;
import com.icarexm.jiedi.utils.RequstUrlUtils;
import com.icarexm.jiedi.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.icarexm.jiedi.view.activity.WithDrawTypeActivity.InitData;

public class WithDrawActivity extends AppCompatActivity {

    @BindView(R.id.with_draw_tv_balance_money)
    TextView tv_balance_money;
    @BindView(R.id.with_draw_tv_money)
    TextView tv_money;
    @BindView(R.id.withdraw_tv_account)
    TextView tv_accout;
    @BindView(R.id.withdraw_tv_type)
    TextView tv_type;
    private Context mContext;
    private String money;
    private String token;
    private String withdrawal_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_draw);
        mContext = getApplicationContext();
        Intent intent = getIntent();
        money = intent.getStringExtra("money");
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        ButterKnife.bind(this);
        tv_balance_money.setText("余额："+money+"元");
        tv_money.setText(money);
        InitData();

    }

    private void InitData() {
        OkGo.<String>post(RequstUrlUtils.URL.get_withdrawal_default)
                .params("token",token)
                .params("type","1")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        WithdrawalDefaultBean withdrawalDefaultBean = new GsonBuilder().create().fromJson(response.body(), WithdrawalDefaultBean.class);
                        if (withdrawalDefaultBean.getCode()==200){
                            WithdrawalDefaultBean.DataBean data = withdrawalDefaultBean.getData();
                            tv_accout.setText(data.getWithdrawal().getWithdrawal_account());
                            tv_type.setText(data.getWithdrawal().getWithdrawal_type());
                            withdrawal_id = data.getWithdrawal().getId()+"";
                        }
                    }
                });
    }

    @OnClick({R.id.withdraw_rl_withdrawtype,R.id.with_draw_img_back,R.id.withdraw_tv_add,R.id.withdraw_btn_confirm})
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
            case R.id.withdraw_btn_confirm:
                OkGo.<String>post(RequstUrlUtils.URL.withdraw)
                        .params("token",token)
                        .params("type","1")
                        .params("money",money)
                        .params("withdrawal_id", withdrawal_id)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                ResultBean resultBean = new GsonBuilder().create().fromJson(response.body(), ResultBean.class);
                                if (resultBean.getCode()==202){
                                    ToastUtils.showToast(mContext,resultBean.getMsg());
                                    InitData();
                                }
                            }
                        });
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
