package com.icarexm.jiedi.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icarexm.jiedi.Bean.CostDetailBean;
import com.icarexm.jiedi.contract.CostDetailContract;
import com.icarexm.jiedi.presenter.CostDetailPresenter;
import com.icarexm.jiedi.utils.RequstUrlUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class CostDetailModel implements CostDetailContract.Model {
    public void PostOrderPriceInfo(CostDetailPresenter costDetailPresenter, String order_id, String token){
        OkGo.<String>post(RequstUrlUtils.URL.order_price_info)
                .params("order_id",order_id)
                .params("type","1")
                .params("token", token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Gson gson = new GsonBuilder().create();
                        CostDetailBean costDetailBean = gson.fromJson(response.body(), CostDetailBean.class);
                        if (costDetailBean.getCode()==200){
                            CostDetailBean.DataBean data = costDetailBean.getData();
                            costDetailPresenter.SetOrderDetail(data);

                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }

}
