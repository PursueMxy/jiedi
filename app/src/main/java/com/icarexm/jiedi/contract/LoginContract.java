package com.icarexm.jiedi.contract;

import com.icarexm.jiedi.Bean.LoginBean;
import com.icarexm.jiedi.presenter.LoginPresenter;

public interface LoginContract {
    interface Model {
        void PostLogin(LoginPresenter loginPresenter, String account, String password, String type);
    }

    interface View {
        void LoginBack( LoginBean.DataBean.UserinfoBean userinfo);
    }

    interface Presenter {
        void GetUserInfo(String account, String password, String type);
        void SetUserInfo(LoginBean.DataBean.UserinfoBean userinfo);
    }
}
