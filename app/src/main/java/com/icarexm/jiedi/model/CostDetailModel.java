package com.icarexm.jiedi.model;

import android.util.Log;

import com.icarexm.jiedi.contract.CostDetailContract;
import com.icarexm.jiedi.utils.RequstUrlUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class CostDetailModel implements CostDetailContract.Model {
    public void PostOrderPriceInfo(String order_id,String token){
        OkGo.<String>post(RequstUrlUtils.URL.order_price_info)
                .params("order_id",order_id)
                .params("type","1")
                .params("token", token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Log.e("订单详情",body);

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }

}
