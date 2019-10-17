package com.icarexm.jiediuser.presenter;

import com.icarexm.jiediuser.bean.AccountingRulesBean;
import com.icarexm.jiediuser.contract.AccountingRulesContract;
import com.icarexm.jiediuser.model.AccountingRulesModel;

public class AccountingRulesPresenter implements AccountingRulesContract.Presenter {

    AccountingRulesContract.View mView;
    AccountingRulesModel accountingRulesModel;

    public AccountingRulesPresenter(AccountingRulesContract.View view){
        mView=view;
        accountingRulesModel=new AccountingRulesModel();
    }

    @Override
    public void GetAccountingRules() {
     accountingRulesModel.PostAccountingRules(this);
    }

    public void SetAccountingRules(AccountingRulesBean.DataBean data){
     mView.UpdateUI(data);
    }
}
