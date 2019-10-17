package com.icarexm.jiedi.model;

import android.util.Log;

import androidx.constraintlayout.solver.GoalRow;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icarexm.jiedi.Bean.LoginBean;
import com.icarexm.jiedi.Bean.ResultBean;
import com.icarexm.jiedi.contract.LoginContract;
import com.icarexm.jiedi.presenter.LoginPresenter;
import com.icarexm.jiedi.utils.RequstUrlUtils;
import com.icarexm.jiedi.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;



public class LoginModel implements LoginContract.Model {

    public void  PostLogin(LoginPresenter loginPresenter, String account, String password, String type){
        OkGo.<String>post(RequstUrlUtils.URL.Login)
                .params("account",account)
                .params("password",password)
                .params("type",type)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Gson gson = new GsonBuilder().create();
                        ResultBean resultBean = gson.fromJson(body, ResultBean.class);
                        if (resultBean.getCode()==200) {
                            LoginBean loginBean = gson.fromJson(body, LoginBean.class);
                            if (loginBean != null) {
                                if (loginBean.getCode() == 200) {
                                    if (loginBean.getData() != null) {
                                        LoginBean.DataBean data = loginBean.getData();
                                        LoginBean.DataBean.UserinfoBean userinfo = data.getUserinfo();
                                        loginPresenter.SetUserInfo(userinfo);
                                    }
                                }
                            }
                        }else {

                        }
                        Log.e("返回数据",body);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });

    }

}
