package com.icarexm.jiediuser.model;

import android.util.Log;

import com.google.gson.GsonBuilder;
import com.icarexm.jiediuser.bean.AccountingRulesBean;
import com.icarexm.jiediuser.contract.AccountingRulesContract;
import com.icarexm.jiediuser.presenter.AccountingRulesPresenter;
import com.icarexm.jiediuser.utils.RequstUrlUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class AccountingRulesModel implements AccountingRulesContract.Model {
    /*
    * 获取计费规则
    * */
   public  void PostAccountingRules(AccountingRulesPresenter accountingRulesPresenter){
       OkGo.<String>post(RequstUrlUtils.URL.init)
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
