package com.icarexm.jiediuser.presenter;

import com.icarexm.jiediuser.bean.OrderListOneBean;
import com.icarexm.jiediuser.contract.MyOrderContract;
import com.icarexm.jiediuser.model.MyOrderModel;

import java.util.List;

public class MyOrderPresenter implements MyOrderContract.Presenter {

    private final MyOrderModel myOrderModel;
    MyOrderContract.View mView;

    public MyOrderPresenter(MyOrderContract.View view)
    {
        mView=view;
        myOrderModel = new MyOrderModel();
    }

    @Override
    public void GetOrderList(String token, String order_type, String order_time, String limit, String page) {
    myOrderModel.PostOrderList(this,token,order_type,order_time,limit,page);
    }

    public void SetOrderList(List<OrderListOneBean.DataBean.OrderBean> order){
      mView.UpdateOrderList(order);
    }

}
