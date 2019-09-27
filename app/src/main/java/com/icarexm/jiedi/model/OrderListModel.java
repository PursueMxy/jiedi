package com.icarexm.jiedi.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icarexm.jiedi.Bean.OrderListOneBean;
import com.icarexm.jiedi.contract.OrderListContract;
import com.icarexm.jiedi.presenter.OrderListPresenter;
import com.icarexm.jiedi.utils.RequstUrlUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class OrderListModel implements OrderListContract.Model {

    /*
    * @order_type 订单类型
    * @order_time 订单时间
    * @limit  每页长度
    * @page 页数
    * */
    public void PostOrderList(OrderListPresenter orderListPresenter,String token,String order_type,String order_time,String limit,String page){
        OkGo.<String>post(RequstUrlUtils.URL.OrderIndex)
                .params("token",token)
                .params("type",1)
                .params("order_type",order_type)
                .params("order_time",order_time)
                .params("limit",limit)
                .params("page",page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e("订单列表",response.body());
                        Gson gson = new GsonBuilder().create();
                        OrderListOneBean orderListOneBean = gson.fromJson(response.body(), OrderListOneBean.class);
                        if (orderListOneBean!=null){
                            if (orderListOneBean.getCode()==200){
                                OrderListOneBean.DataBean data = orderListOneBean.getData();
                                orderListPresenter.SetOrderList(data);
                            }
                        }
                    }
                });
    }
}
