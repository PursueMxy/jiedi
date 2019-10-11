package com.icarexm.jiediuser.model;

import android.util.Log;

import com.icarexm.jiediuser.contract.AccountingRulesContract;
import com.icarexm.jiediuser.utils.RequstUrlUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class AccountingRulesModel implements AccountingRulesContract.Model {
    /*
    * 获取计费规则
    * */
   public  void PostAccountingRules(){
       OkGo.<String>post(RequstUrlUtils.URL.init)
               .execute(new StringCallback() {
                   @Override
                   public void onSuccess(Response<String> response) {
                       Log.e("信息初始化",response.body());
                   }
               });
   }

}
