package com.icarexm.jiediuser.contract;

import com.icarexm.jiediuser.bean.BalanceDtlBean;
import com.icarexm.jiediuser.presenter.BalanceDetailPresenter;

import java.util.List;

public interface BalanceDetailContract {
    interface Model {
        void PostBalanceDtl(BalanceDetailPresenter balanceDetailPresenter, String token, String select_time, String limit, String page);
    }

    interface View {
        void  UpdateList(List<BalanceDtlBean.DataBean> data);
    }

    interface Presenter {
        void GetBalanceDtl(String token,String select_time,String limit,String page);
        void SetBalanceDtl(List<BalanceDtlBean.DataBean> data);
    }
}
