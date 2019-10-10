package com.icarexm.jiedi.presenter;

import com.icarexm.jiedi.Bean.LoginBean;
import com.icarexm.jiedi.contract.LoginContract;
import com.icarexm.jiedi.model.LoginModel;

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mView;
    private final LoginModel loginModel;

    public LoginPresenter(LoginContract.View view) {
        mView = view;
        loginModel = new LoginModel();
    }

    public void GetUserInfo(String account,String password,String type){
        loginModel.PostLogin(this,account,password,type);
    }

    public void SetUserInfo(LoginBean.DataBean.UserinfoBean userinfo){
        mView.LoginBack(userinfo);
    }

    public void GetInit(){
    }

}
