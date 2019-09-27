package com.icarexm.jiedi.presenter;

import com.icarexm.jiedi.Bean.OrderListOneBean;
import com.icarexm.jiedi.contract.OrderListContract;
import com.icarexm.jiedi.model.OrderListModel;

public class OrderListPresenter implements OrderListContract.Presenter {
    OrderListContract.View mView;
    OrderListModel orderListModel;

    public OrderListPresenter(OrderListContract.View view){
        mView=view;
        orderListModel=new OrderListModel();
    }

    @Override
    public void GetOrderList(String token, String order_type, String order_time, String limit, String page) {
        orderListModel.PostOrderList(this,token,order_type,order_time,limit,page);
    }

    public void SetOrderList( OrderListOneBean.DataBean data){
        mView.UpdateOrderList(data);
    }
}
