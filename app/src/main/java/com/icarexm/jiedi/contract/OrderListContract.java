package com.icarexm.jiedi.contract;

import com.icarexm.jiedi.Bean.OrderListOneBean;
import com.icarexm.jiedi.presenter.OrderListPresenter;

public interface OrderListContract {
    interface Model {
        void PostOrderList(OrderListPresenter orderListPresenter, String token, String order_type, String order_time, String limit, String page);
    }

    interface View {
        void UpdateOrderList( OrderListOneBean.DataBean data);
    }

    interface Presenter {
        void GetOrderList(String token,String order_type,String order_time,String limit,String page);
        void SetOrderList( OrderListOneBean.DataBean data);
    }
}
