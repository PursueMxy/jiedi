package com.icarexm.jiedi.contract;

import com.icarexm.jiedi.Bean.AccountingRulesBean;
import com.icarexm.jiedi.presenter.AccountingRulesPresenter;

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
