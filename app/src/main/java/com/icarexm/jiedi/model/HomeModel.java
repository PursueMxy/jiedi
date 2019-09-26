package com.icarexm.jiedi.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icarexm.jiedi.Bean.OrderListBean;
import com.icarexm.jiedi.contract.HomeContract;
import com.icarexm.jiedi.presenter.HomePresenter;
import com.icarexm.jiedi.utils.RequstUrlUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class HomeModel implements HomeContract.Model {
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
                        Log.e("订单信息",response.body()+"");
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
}
