package com.icarexm.jiedi.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icarexm.jiedi.Bean.DriverArriveBean;
import com.icarexm.jiedi.Bean.OrderType1Bean;
import com.icarexm.jiedi.Bean.OrderTypeBean;
import com.icarexm.jiedi.contract.MainContract;
import com.icarexm.jiedi.presenter.MainPresenter;
import com.icarexm.jiedi.utils.RequstUrlUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class MainModel implements MainContract.Model {

    //获取订单状态
    public  void PostOrderInfo(MainPresenter mainPresenter, String token, String order_id){
        OkGo.<String>post(RequstUrlUtils.URL.orderInfo)
                .params("order_id",order_id)
                .params("type","1")
                .params("token", token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Gson gson = new GsonBuilder().create();
                        try {
                            OrderTypeBean orderTypeBean = gson.fromJson(body, OrderTypeBean.class);
                            if (orderTypeBean != null) {
                                if (orderTypeBean.getCode() == 200) {
                                    if (orderTypeBean.getData() != null) {
                                        OrderTypeBean.DataBean data = orderTypeBean.getData();
                                        mainPresenter.SetOrderInfo(data);
                                    }
                                }
                            }
                        }catch (Exception e){
                            OrderType1Bean orderType1Bean = gson.fromJson(body, OrderType1Bean.class);
                            if (orderType1Bean!=null){
                                if (orderType1Bean.getCode()==200){
                                    if (orderType1Bean.getData()!=null){
                                        OrderType1Bean.DataBean data = orderType1Bean.getData();
                                        mainPresenter.SetOrderInfo1(data);
                                    }
                                }
                            }
                        }
                    }
                });
    }

    //订单评价
    public void PostEvaluate(MainPresenter mainPresenter, String token,String order_id,String score,String comment){
        OkGo.<String>post(RequstUrlUtils.URL.evaluate)
                .params("token",token)
                .params("order_id",order_id)
                .params("score",score)
                .params("comment",comment)
                .params("type","1")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                   Log.e("评价结果",response.body());

                    }
                });

    }

}
