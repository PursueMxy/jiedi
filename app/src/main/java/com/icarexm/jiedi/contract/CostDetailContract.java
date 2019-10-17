package com.icarexm.jiedi.contract;

import com.icarexm.jiedi.Bean.CostDetailBean;
import com.icarexm.jiedi.presenter.CostDetailPresenter;

public interface CostDetailContract {
    interface Model {
        void PostOrderPriceInfo(CostDetailPresenter costDetailPresenter, String order_id, String token);
    }

    interface View {
        void UpdateUI(CostDetailBean.DataBean data);
    }

    interface Presenter {
        void GetOrderPriceInfo(String order_id,String token);
        void SetOrderDetail(CostDetailBean.DataBean data);
    }
}
