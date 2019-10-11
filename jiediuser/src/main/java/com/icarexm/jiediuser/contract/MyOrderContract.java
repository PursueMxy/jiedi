package com.icarexm.jiediuser.contract;

import com.icarexm.jiediuser.bean.OrderListOneBean;
import com.icarexm.jiediuser.presenter.MyOrderPresenter;

import java.util.List;

public interface MyOrderContract {
    interface Model {
        void PostOrderList(MyOrderPresenter myOrderPresenter, String token, String order_type, String order_time, String limit, String page);
    }

    interface View {
        void UpdateOrderList(List<OrderListOneBean.DataBean.OrderBean> order);
    }

    interface Presenter {
        void GetOrderList(String token, String order_type, String order_time, String limit, String page);
        void SetOrderList(List<OrderListOneBean.DataBean.OrderBean> order);
    }
}
