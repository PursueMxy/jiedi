package com.icarexm.jiediuser.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icarexm.jiediuser.bean.OrderListOneBean;
import com.icarexm.jiediuser.contract.MyOrderContract;
import com.icarexm.jiediuser.presenter.MyOrderPresenter;
import com.icarexm.jiediuser.utils.RequstUrlUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

public class MyOrderModel implements MyOrderContract.Model {

    /*
    * 获取订单列表
    * */
    public void PostOrderList(MyOrderPresenter myOrderPresenter,String token,String order_type,String order_time,String limit,String page){
        OkGo.<String>post(RequstUrlUtils.URL.OrderIndex)
                .params("token",token)
                .params("type","0")
                .params("order_type",order_type)
                .params("order_time",order_time)
                .params("limit",limit)
                .params("page",page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new GsonBuilder().create();
                        OrderListOneBean orderListOneBean = gson.fromJson(response.body(), OrderListOneBean.class);
                        if (orderListOneBean!=null){
                            if (orderListOneBean.getCode()==200){
                                OrderListOneBean.DataBean data = orderListOneBean.getData();
                                List<OrderListOneBean.DataBean.OrderBean> order = data.getOrder();
                                myOrderPresenter.SetOrderList(order);
                            }
                        }
                    }
                });
    }
}
