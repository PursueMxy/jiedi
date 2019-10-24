package com.icarexm.jiediuser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TimeUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

import com.autonavi.rtbt.IFrameForRTBT;
import com.google.gson.GsonBuilder;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;
import com.icarexm.jiediuser.MyApplication;
import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.bean.LoginBean;
import com.icarexm.jiediuser.bean.ServicesMsgBean;
import com.icarexm.jiediuser.bean.WXUserInfoBean;
import com.icarexm.jiediuser.contract.LoginContract;
import com.icarexm.jiediuser.presenter.LoginPresenter;
import com.icarexm.jiediuser.utils.RequstUrlUtils;
import com.icarexm.jiediuser.utils.ToastUtils;
import com.icarexm.jiediuser.wxapi.WXEntryActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    @BindView(R.id.login_edt_mobile)
    EditText edt_mobile;
    @BindView(R.id.login_edt_verification_code)
    EditText edt_verification_code;
    @BindView(R.id.login_tv_verification_code)
    TextView tv_verification_code;
    @BindView(R.id.login_checkbox)
    CheckBox login_checkbox;
    private LoginPresenter loginPresenter;
    private Context mContext;
    private String mobile="";
    private String verification_code="";
    private SharedPreferences share;
    private String token="";
    private String openid="";
    private int Timesecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent intent = getIntent();
        //权限申请
        XXPermissions.with(this)
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {

                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {

                    }
                });
        ButterKnife.bind(this);
        share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        mContext = getApplicationContext();
        loginPresenter = new LoginPresenter(this);
        token = share.getString("token", "");
        mobile = share.getString("mobile", "");
        edt_mobile.setText(mobile);
        if (!token.equals("")){
            TokenCheck();
        }
        try{
            String type = intent.getStringExtra("type");
            Log.e("type",type);
            if (type.equals("wechat")){
                String nickname = intent.getStringExtra("nickname");
                String headimgurl = intent.getStringExtra("headimgurl");
                String openid = intent.getStringExtra("openid");
                loginPresenter.GetWeixinlogin("0",openid,nickname,headimgurl);
            }
        }catch (Exception e){

        }
    }


    @OnClick({R.id.login_tv_verification_code,R.id.login_btn_start,R.id.login_tv_wachat})
    public void onViewChick(View view){
        switch (view.getId()){
            case R.id.login_tv_verification_code:
                mobile = edt_mobile.getText().toString();
                if (!mobile.equals("")){
                    Timesecond=60;
                    timeHandler.postDelayed(timeRunnable,1000);
                    tv_verification_code.setClickable(false);
                    loginPresenter.GetMobileCode(mobile,"user_mobilelogin","0");
                }else {
                    ToastUtils.showToast(mContext,"手机号码不能为空");
                }
                break;
            case R.id.login_btn_start:
                boolean checked = login_checkbox.isChecked();
                mobile = edt_mobile.getText().toString();
                verification_code = edt_verification_code.getText().toString();
                if (checked){
                 if (!mobile.equals("")&&!verification_code.equals("")){
                 loginPresenter.GetLogin(mobile,verification_code,"user_mobilelogin");
                 }else {
                     ToastUtils.showToast(mContext,"验证码或者手机号不能为空");
                 }
                }else {
                    ToastUtils.showToast(mContext,"请阅读并同意服务条款");
                }
                break;
            case R.id.login_tv_wachat:
                WXEntryActivity.loginWeixin(LoginActivity.this, MyApplication.iwxapi);
                break;
        }
    }

    public void UpdatemobileCode(String content){
        ToastUtils.showToast(mContext,content);
    }

    public void UpdateLogin(LoginBean.DataBean.UserinfoBean userinfo){
        String token = userinfo.getToken();
        int user_id = userinfo.getUser_id();
        String nickname = userinfo.getNickname();
        String avatar = userinfo.getAvatar();
       if (userinfo.getOpenid()!=null){
           openid = userinfo.getOpenid();
       }
        if (!mobile.equals("")){

        }
        SharedPreferences.Editor editor = share.edit();
        editor.putString("mobile", mobile);
        editor.putString("openid",openid);
        editor.putString("token",token);
        editor.putString("user_id",user_id+"");
        editor.putString("nickname",nickname);
        editor.putString("avatar",avatar);
        editor.commit();//提交
        loginPresenter.GetInit();
        startActivity(new Intent(this,HomeActivity.class));

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        try {
            String userinfo = intent.getStringExtra("userinfo");
            WXUserInfoBean wxUserInfoBean = new GsonBuilder().create().fromJson(userinfo, WXUserInfoBean.class);
            loginPresenter. GetWeixinlogin("0",wxUserInfoBean.getOpenid(),wxUserInfoBean.getNickname(),wxUserInfoBean.getHeadimgurl());
        }catch (Exception e){}
        super.onNewIntent(intent);
    }

    //检查token是否过期
    public void TokenCheck(){
        OkGo.<String>get(RequstUrlUtils.URL.TokenCheck)
                .params("token",token)
                .params("type","0")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ServicesMsgBean resultBean = new GsonBuilder().create().fromJson(response.body(),  ServicesMsgBean.class);
                        if (resultBean.getCode()==200){
                            startActivity(new Intent(mContext,HomeActivity.class));
                            finish();
                        }
                    }
                });
    }

    //验证码倒计时
    Handler timeHandler=new Handler();
    Runnable timeRunnable=new Runnable() {
        @Override
        public void run() {
            if (Timesecond==0){
                timeHandler.removeCallbacks(timeRunnable);
                tv_verification_code.setClickable(true);
            }else {
                timeHandler.postDelayed(timeRunnable,1000);
            }
            tv_verification_code.setText(Timesecond+"秒后");
            Timesecond=Timesecond-1;
        }
    };
}
