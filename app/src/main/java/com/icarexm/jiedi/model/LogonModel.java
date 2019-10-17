package com.icarexm.jiedi.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icarexm.jiedi.contract.LogonContract;
import com.icarexm.jiedi.presenter.LogonPresenter;
import com.icarexm.jiedi.utils.RequstUrlUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class LogonModel implements LogonContract.Model {

    //获取验证码
    /** 获取验证码
     * @mobile 手机号
     * event 验证码类型
     * type 用户类型
     *
     * */
    public void PostMobileCode(LogonPresenter logonPresenter, String mobile, String event, String type){
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

    //申请账号

}
