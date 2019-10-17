package com.icarexm.jiedi.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.google.gson.GsonBuilder;
import com.icarexm.jiedi.Bean.ResultBean;
import com.icarexm.jiedi.R;
import com.icarexm.jiedi.utils.RequstUrlUtils;
import com.icarexm.jiedi.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddWithdrawActivity extends AppCompatActivity {

    @BindView(R.id.add_withdraw_edt_withdrawal_account)
    EditText edt_withdrawal_account;
    @BindView(R.id.add_withdraw_edt_withdrawal_type)
    EditText edt_withdrawal_type;
    private String token;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_withdraw);
        mContext = getApplicationContext();
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        ButterKnife.bind(this);
        InitData();
    }

    private void InitData() {
        OkGo.<String>post(RequstUrlUtils.URL.getWithdrawal)
                .params("token",token)
                .params("type","1")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e("提现方式",response.body());
                    }
                });
    }


    @OnClick({R.id.with_draw_add_img_back,R.id.add_withdraw_btn_confirm})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.with_draw_add_img_back:
                finish();
                break;
            case R.id.add_withdraw_btn_confirm:
                String account = edt_withdrawal_account.getText().toString();
                String type = edt_withdrawal_type.getText().toString();
                if (!account.equals("")&&!type.equals("")){
                    OkGo.<String>post(RequstUrlUtils.URL.add_withdrawal)
                            .params("token",token)
                            .params("type","1")
                            .params("withdrawal_type",type)
                            .params("withdrawal_account",account)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    Log.e("添加提现方式",response.body());
                                    ResultBean resultBean = new GsonBuilder().create().fromJson(response.body(), ResultBean.class);
                                    ToastUtils.showToast(mContext,resultBean.getMsg());
                                    if (resultBean.getCode()==201){
                                        startActivity(new Intent(mContext,WithDrawTypeActivity.class));
                                        finish();
                                    }
                                }
                            });
                }
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==event.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
