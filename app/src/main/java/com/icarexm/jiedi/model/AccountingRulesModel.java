package com.icarexm.jiedi.model;

import android.util.Log;

import com.google.gson.GsonBuilder;
import com.icarexm.jiedi.Bean.AccountingRulesBean;
import com.icarexm.jiedi.contract.AccountingRulesContract;
import com.icarexm.jiedi.presenter.AccountingRulesPresenter;
import com.icarexm.jiedi.utils.RequstUrlUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class AccountingRulesModel implements AccountingRulesContract.Model {
    /*
     * 获取计费规则
     * */
    public  void PostAccountingRules(AccountingRulesPresenter accountingRulesPresenter){
        OkGo.<String>post(RequstUrlUtils.URL.init)
                .params("type","1")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e("信息初始化",response.body());
                        AccountingRulesBean accountingRulesBean = new GsonBuilder().create().fromJson(response.body(), AccountingRulesBean.class);
                        if (accountingRulesBean.getCode()==200){
                            AccountingRulesBean.DataBean data = accountingRulesBean.getData();
                            if (data!=null){
                                accountingRulesPresenter.SetAccountingRules(data);
                            }
                        }
                    }
                });
    }
}
