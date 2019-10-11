package com.icarexm.jiediuser.model;

import android.util.Log;

import com.icarexm.jiediuser.contract.BalanceDetailContract;
import com.icarexm.jiediuser.presenter.BalanceDetailPresenter;
import com.icarexm.jiediuser.utils.RequstUrlUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class BalanceDetailModel implements BalanceDetailContract.Model {
    /*
    * 获取余额明细
    * */

    public void PostBalanceDtl(BalanceDetailPresenter balanceDetailPresenter,String token,String select_time,String limit,String page){
        OkGo.<String>post(RequstUrlUtils.URL.money_log)
                .params("token",token)
                .params("select_time",select_time)
                .params("limit",limit)
                .params("page",page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e("余额明细",response.body());
                    }
                });

    }
}
