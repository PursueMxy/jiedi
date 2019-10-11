package com.icarexm.jiediuser.contract;

import com.icarexm.jiediuser.presenter.CostDetailPresenter;

public interface CostDetailContract {
    interface Model {
        void PostOrderDetail(CostDetailPresenter costDetailPresenter, String token, String order_id);
    }

    interface View {
    }

    interface Presenter {
        void GetOrderDetail(String token, String order_id);
    }
}
