package com.icarexm.jiediuser.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icarexm.jiediuser.bean.LoginBean;
import com.icarexm.jiediuser.bean.MobileCodeBean;
import com.icarexm.jiediuser.contract.LoginContract;
import com.icarexm.jiediuser.presenter.LoginPresenter;
import com.icarexm.jiediuser.utils.RequstUrlUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class LoginModel implements LoginContract.Model {
    /*
    * 获取验证码
    * @mobile 手机号
    * event 验证码类型
    * type 用户类型
    *
    * */
    public void PostMobileCode(LoginPresenter loginPresenter, String mobile, String event, String type){
        OkGo.<String>post(RequstUrlUtils.URL.MobileCode)
                .params("mobile",mobile)
                .params("event",event)
                .params("type",type)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Gson gson = new GsonBuilder().create();
//                        MobileCodeBean mobileCodeBean = gson.fromJson(response.body(), MobileCodeBean.class);
//                        if (mobileCodeBean!=null){
//                            loginPresenter.SetMobuleCode(mobileCodeBean.getMsg());
//                        }
                    }
                });

    }
   /*
   * 验证码登录
   *
   * */
    public void  PostLogin(LoginPresenter loginPresenter, String mobile, String captcha, String event){
        OkGo.<String>post(RequstUrlUtils.URL.mobileLogin)
                .params("mobile",mobile)
                .params("captcha",captcha)
                .params("event",event)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Gson gson = new GsonBuilder().create();
                        LoginBean loginBean = gson.fromJson(body, LoginBean.class);
                        if (loginBean!=null){
                            if (loginBean.getCode()==200){
                                LoginBean.DataBean data = loginBean.getData();
                                if (data!=null) {
                                    LoginBean.DataBean.UserinfoBean userinfo = data.getUserinfo();
                                    loginPresenter.SetLogin(userinfo);
                                }

                            }else {
                                loginPresenter.SetMobuleCode(loginBean.getMsg());
                            }
                        }
                        Log.e("返回数据",body);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });

    }

/*
*  数据初始化
*/
    public void PostInit(){
        OkGo.<String>post(RequstUrlUtils.URL.init)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e("初始化",response.body());
                    }
                });
    }
}
