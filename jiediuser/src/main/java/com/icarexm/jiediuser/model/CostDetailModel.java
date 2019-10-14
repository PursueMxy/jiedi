package com.icarexm.jiediuser.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icarexm.jiediuser.bean.CostDetailBean;
import com.icarexm.jiediuser.contract.CostDetailContract;
import com.icarexm.jiediuser.presenter.CostDetailPresenter;
import com.icarexm.jiediuser.utils.RequstUrlUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class CostDetailModel implements CostDetailContract.Model {
    /*
    * 获取订单详情
    * */
    public void PostOrderDetail(CostDetailPresenter costDetailPresenter,String token, String order_id){
        OkGo.<String>post(RequstUrlUtils.URL.order_price_info)
                .params("token",token)
                .params("order_id",order_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e("订单详情",response.body());
                        Gson gson = new GsonBuilder().create();
                        CostDetailBean costDetailBean = gson.fromJson(response.body(), CostDetailBean.class);
                        if (costDetailBean.getCode()==200){
                            CostDetailBean.DataBean data = costDetailBean.getData();
                        }
                    }
                });
    }
}
