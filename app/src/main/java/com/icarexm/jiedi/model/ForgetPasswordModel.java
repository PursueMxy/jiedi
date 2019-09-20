package com.icarexm.jiedi.model;

import android.util.Log;

import com.icarexm.jiedi.contract.ForgetPasswordContract;
import com.icarexm.jiedi.utils.RequstUrlUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class ForgetPasswordModel implements ForgetPasswordContract.Model {
    public void PostMobileCode(String mobile,String event,String type){
        OkGo.<String>post(RequstUrlUtils.URL.MobileCode)
                .params("mobile",mobile)
                .params("event",event)
                .params("type",type)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Log.e("返回",body);
                    }
                });

    }
}
