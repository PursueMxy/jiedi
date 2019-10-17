package com.icarexm.jiediuser.contract;

import com.icarexm.jiediuser.bean.CostDetailBean;
import com.icarexm.jiediuser.presenter.CostDetailPresenter;

public interface CostDetailContract {
    interface Model {
        void PostOrderDetail(CostDetailPresenter costDetailPresenter, String token, String order_id);
    }

    interface View {
        void UpdateUI(CostDetailBean.DataBean data);
    }

    interface Presenter {
        void GetOrderDetail(String token, String order_id);
        void SetOrderDetail(CostDetailBean.DataBean data);
    }
}
