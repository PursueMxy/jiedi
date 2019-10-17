package com.icarexm.jiediuser.contract;

import com.icarexm.jiediuser.bean.AccountingRulesBean;
import com.icarexm.jiediuser.presenter.AccountingRulesPresenter;

public interface AccountingRulesContract {
    interface Model {
        void PostAccountingRules(AccountingRulesPresenter accountingRulesPresenter);
    }

    interface View {
        void UpdateUI(AccountingRulesBean.DataBean data);
    }

    interface Presenter {
        void GetAccountingRules();
        void SetAccountingRules(AccountingRulesBean.DataBean data);
    }
}
