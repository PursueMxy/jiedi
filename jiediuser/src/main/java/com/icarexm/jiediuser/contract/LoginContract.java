package com.icarexm.jiediuser.contract;

import com.icarexm.jiediuser.bean.LoginBean;
import com.icarexm.jiediuser.presenter.LoginPresenter;

public interface LoginContract {
    interface Model {
        void PostMobileCode(LoginPresenter loginPresenter, String mobile,String event,String type);
        void  PostLogin(LoginPresenter loginPresenter, String mobile, String captcha, String event);
    }

    interface View {
        void UpdatemobileCode(String content);
        void UpdateLogin(LoginBean.DataBean.UserinfoBean userinfo);
    }

    interface Presenter {
        void GetMobileCode(String mobile, String event, String type);
        void SetMobuleCode(String content);
        void GetLogin(String mobile, String captcha, String event);
        void  SetLogin(LoginBean.DataBean.UserinfoBean userinfo);
    }
}
