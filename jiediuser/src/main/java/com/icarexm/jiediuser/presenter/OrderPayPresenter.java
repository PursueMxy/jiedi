package com.icarexm.jiediuser.presenter;

import com.icarexm.jiediuser.bean.OrderDetailBean;
import com.icarexm.jiediuser.contract.OrderPayContract;
import com.icarexm.jiediuser.model.OrderPayModel;

public class OrderPayPresenter implements OrderPayContract.Presenter {

    OrderPayModel orderPayModel;
    OrderPayContract.View mView;

    public OrderPayPresenter(OrderPayContract.View view){
        mView=view;
        orderPayModel=new OrderPayModel();
    }

    @Override
    public void GetOrderPrice(String token, String order_id) {
       orderPayModel.PostOrderPrice(this,token,order_id);
    }

    public void SetOrderPrice(OrderDetailBean.DataBean data){
     mView.UpdateUI(data);
    }

    public void GetSettlement(String token,String order_id,String coupon_id,String pay_type){
        orderPayModel.PostSettlement(this,token,order_id,coupon_id,pay_type);
    }

    @Override
    public void SetSettlement(String msg, int codes) {
      mView.UpdateToast(msg,codes);
    }

}
