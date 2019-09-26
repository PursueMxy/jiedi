package com.icarexm.jiedi.contract;

import com.icarexm.jiedi.Bean.OrderTypeBean;
import com.icarexm.jiedi.presenter.MainPresenter;

public interface MainContract {
    interface Model {
        void PostOrderInfo(MainPresenter mainPresenter, String token, String order_id);
        void PostEvaluate(MainPresenter mainPresenter, String token,String order_id,String score,String comment);
    }

    interface View {
        void UpdateUI(OrderTypeBean.DataBean data);
    }

    interface Presenter {
        void GetOrderInfo(String token,String order_id);
        void SetOrderInfo( OrderTypeBean.DataBean data);
        void GetEvaluate(String token,String order_id,String score,String comment);
    }
}
