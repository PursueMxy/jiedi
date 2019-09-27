package com.icarexm.jiedi.model;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icarexm.jiedi.Bean.AutoOrderBean;
import com.icarexm.jiedi.Bean.OrderListBean;
import com.icarexm.jiedi.contract.HomeContract;
import com.icarexm.jiedi.presenter.HomePresenter;
import com.icarexm.jiedi.utils.RequstUrlUtils;
import com.icarexm.jiedi.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class HomeModel implements HomeContract.Model {

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
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e("接单功能",response.body());
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
