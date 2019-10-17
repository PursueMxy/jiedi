package com.icarexm.jiedi.presenter;

import com.icarexm.jiedi.Bean.AccountingRulesBean;
import com.icarexm.jiedi.contract.AccountingRulesContract;
import com.icarexm.jiedi.model.AccountingRulesModel;

public class AccountingRulesPresenter implements AccountingRulesContract.Presenter {
    AccountingRulesContract.View mView;
    AccountingRulesModel accountingRulesModel;

    public AccountingRulesPresenter(AccountingRulesContract.View view){
        mView=view;
        accountingRulesModel=new AccountingRulesModel();
    }

    public void GetAccountingRules() {
        accountingRulesModel.PostAccountingRules(this);
    }

    public void SetAccountingRules(AccountingRulesBean.DataBean data){
        mView.UpdateUI(data);
    }
}
