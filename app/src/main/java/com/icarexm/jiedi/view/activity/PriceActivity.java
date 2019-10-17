package com.icarexm.jiedi.view.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaRouter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.icarexm.jiedi.Bean.UserInfoBean;
import com.icarexm.jiedi.R;
import com.icarexm.jiedi.utils.RequstUrlUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PriceActivity extends AppCompatActivity {

    @BindView(R.id.price_tv_money)
    TextView tv_money;

    private View dialog_callphone;
    private AlertDialog alertDialog;
    private TextView tv_phone_number;
    private Context mContext;
    private String token;
    private String money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);
        ButterKnife.bind(this);
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        mContext = getApplicationContext();
        InitUI();
        InitData();
    }

    private void InitData() {
        OkGo.<String>post(RequstUrlUtils.URL.getUserInfo)
                .params("token",token)
                .params("type","1")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        UserInfoBean userInfoBean = new GsonBuilder().create().fromJson(response.body(), UserInfoBean.class);
                        if (userInfoBean.getCode()==200){
                            UserInfoBean.DataBean data = userInfoBean.getData();
                            UserInfoBean.DataBean.UserinfoBean userinfo = data.getUserinfo();
                            if (userinfo!=null) {
                                money = userinfo.getMoney();
                                tv_money.setText(userinfo.getMoney());
                            }
                        }
                    }
                });
    }

    private void InitUI() {
    }

    @OnClick({R.id.price_tv_callphone,R.id.price_rl_cashOut,R.id.price_rl_withdrawType})
    public void onViewClicked(View view){
        switch (view.getId()) {
            case R.id.price_tv_callphone:
                dialog_callphone = getLayoutInflater().inflate(R.layout.dialog_callphone, null);
                tv_phone_number = dialog_callphone.findViewById(R.id.dialog_callphone_tv_number);
                dialog_callphone.findViewById(R.id.dialog_callphone_tv_back).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                dialog_callphone.findViewById(R.id.dialog_callphone_tv_call).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String mobile = tv_phone_number.getText().toString();
                        Intent intentcall = new Intent();
                        //设置拨打电话的动作
                        intentcall.setAction(Intent.ACTION_CALL);
                        //设置拨打电话的号码
                        intentcall.setData(Uri.parse("tel:" + mobile));
                        //开启打电话的意图
                        startActivity(intentcall);
                        alertDialog.dismiss();
                    }
                });
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                alertDialog = builder.setView(dialog_callphone)
                        .create();
                alertDialog.show();
                break;
            case R.id.price_rl_cashOut:
                startActivity(new Intent(mContext,CashOutActivity.class));
                break;
            case R.id.price_rl_withdrawType:
                Intent intent = new Intent(mContext, WithDrawActivity.class);
                intent.putExtra("money",money);
                startActivity(intent);
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
