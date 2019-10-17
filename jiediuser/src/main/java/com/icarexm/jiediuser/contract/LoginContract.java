package com.icarexm.jiediuser.contract;

import com.icarexm.jiediuser.bean.LoginBean;
import com.icarexm.jiediuser.presenter.LoginPresenter;

public interface LoginContract {
    interface Model {
        void PostMobileCode(LoginPresenter loginPresenter, String mobile,String event,String type);
        void  PostLogin(LoginPresenter loginPresenter, String mobile, String captcha, String event);
        void PostInit();
        void PostWeixinlogin(LoginPresenter loginPresenter,String type,String openid,String nickname,String avatar);
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
        void GetInit();
        void GetWeixinlogin(String type,String openid,String nickname,String avatar);
    }
}
