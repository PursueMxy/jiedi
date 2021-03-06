package com.icarexm.jiedi.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icarexm.jiedi.Bean.EvaluateBean;
import com.icarexm.jiedi.Bean.OrderDetailBean;
import com.icarexm.jiedi.Bean.OrderTypeBean;
import com.icarexm.jiedi.contract.EvaluateContract;
import com.icarexm.jiedi.presenter.EvaluatePresenter;
import com.icarexm.jiedi.utils.RequstUrlUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class EvaluateModel implements EvaluateContract.Model {

    /*
     * 获取订单金额
     * */
    public void PostOrderPrice(EvaluatePresenter evaluatePresenter, String token, String order_id){
        OkGo.<String>post(RequstUrlUtils.URL.orderInfo)
                .params("token",token)
                .params("type","1")
                .params("order_id",order_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new GsonBuilder().create();
                        OrderTypeBean orderDetailBean = gson.fromJson(response.body(), OrderTypeBean.class);
                        if (orderDetailBean.getCode() == 200) {
                            OrderTypeBean.DataBean data = orderDetailBean.getData();
                            evaluatePresenter.SetOrderDetail(data);
                        }
                    }
                });
    }

    /*
     * 订单评价
     * */
    public void PostEvaluate(EvaluatePresenter evaluatePresenter, String token, String order_id, String score, String comment){
        OkGo.<String>post(RequstUrlUtils.URL.evaluate)
                .params("token",token)
                .params("order_id",order_id)
                .params("type","1")
                .params("score",score)
                .params("comment",comment)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e("评价订单",response.body());
                        Gson gson = new GsonBuilder().create();
                        EvaluateBean evaluateBean = gson.fromJson(response.body(), EvaluateBean.class);
                        evaluatePresenter.SetEvaLuate(evaluateBean.getCode(),evaluateBean.getMsg());
                    }
                });
    }
}
