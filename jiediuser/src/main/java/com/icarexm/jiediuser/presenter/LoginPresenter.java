package com.icarexm.jiediuser.presenter;

import com.icarexm.jiediuser.bean.LoginBean;
import com.icarexm.jiediuser.contract.LoginContract;
import com.icarexm.jiediuser.model.LoginModel;

public class LoginPresenter implements LoginContract.Presenter {
    private final LoginModel loginModel;
    LoginContract.View mView;

    public LoginPresenter(LoginContract.View view){
        mView=view;
        loginModel =new LoginModel();
    }

    @Override
    public void GetMobileCode(String mobile, String event, String type) {
       loginModel.PostMobileCode(this,mobile,event,type);
    }

    @Override
    public void SetMobuleCode(String content) {
        mView.UpdatemobileCode(content);
    }

    @Override
    public void GetLogin(String mobile, String captcha, String event) {
        loginModel.PostLogin(this,mobile,captcha,event);
    }

    public void  SetLogin(LoginBean.DataBean.UserinfoBean userinfo){
       mView.UpdateLogin(userinfo);
    }
}
