package com.icarexm.jiedi.model;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icarexm.jiedi.Bean.AutoOrderBean;
import com.icarexm.jiedi.Bean.OrderListBean;
import com.icarexm.jiedi.Bean.OrderListOneBean;
import com.icarexm.jiedi.contract.HomeContract;
import com.icarexm.jiedi.presenter.HomePresenter;
import com.icarexm.jiedi.utils.RequstUrlUtils;
import com.icarexm.jiedi.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

public class HomeModel implements HomeContract.Model {

    /*
     * 获取订单
     *
     * */
    public void  PostIndex(HomePresenter homePresenter,String token){
        OkGo.<String>post(RequstUrlUtils.URL.OrderIndex)
                .params("token",token)
                .params("type","1")
                .params("order_type","1")
                .params("order_time","")
                .params("limit","1")
                .params("page","1")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e("获取订单",response.body());
                        Gson gson = new GsonBuilder().create();
                        OrderListOneBean orderListOneBean = gson.fromJson(response.body(), OrderListOneBean.class);
                        int code = orderListOneBean.getCode();
                        if (code==200){
                            OrderListOneBean.DataBean data = orderListOneBean.getData();
                            if (data!=null){
                                List<OrderListOneBean.DataBean.OrderBean> order = data.getOrder();
                                if (order.size()>0){
                                    int i = Integer.parseInt(order.get(0).getStatus());
                                    OrderListOneBean.DataBean.OrderBean orderBean = order.get(0);
                                    String order_id = order.get(0).getId()+"";
                                    if (i>1&&i<5) {
                                        homePresenter.SetIndex(order_id);
                                    }else if (i==1){
                                        homePresenter.SetSystemOrder(orderBean);
                                    }
                                }
                            }

                        }
                    }
                });
    }

    //获取订单信息
    public void PostOrder(HomePresenter homePresenter, String token, String city, String longitude, String latitude){
        OkGo.<String>post(RequstUrlUtils.URL.driver_index)
                .params("type","1")
                .params("token", token)
                .params("city",city)
                .params("positionE",longitude+"")
                .params("positionN",latitude+"")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new GsonBuilder().create();
                        OrderListBean orderListBean = gson.fromJson(response.body(), OrderListBean.class);
                        if (orderListBean!=null){
                            if (orderListBean.getCode()==200){
                                if (orderListBean.getData()!=null){
                                    OrderListBean.DataBean data = orderListBean.getData();
                                    if (data!=null) {
                                        homePresenter.SetOrder(data);
                                    }
                                }
                            }
                        }
                    }
                });
    }

    //开启自动接单功能
    public  void PostAutoOrder(HomePresenter homePresenter, String token, String status){
        OkGo.<String>post(RequstUrlUtils.URL.auto_order)
                .params("token",token)
                .params("status",status)
                .params("type","1")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new GsonBuilder().create();
                        AutoOrderBean autoOrderBean = gson.fromJson(response.body(), AutoOrderBean.class);
                        if (autoOrderBean.getCode()==201){
                            if (status.equals("0")){
                            homePresenter.SetAutoOrder("0");
                            }else {
                             homePresenter.SetAutoOrder("1");
                            }
                        }
                    }
                });
    }
}
