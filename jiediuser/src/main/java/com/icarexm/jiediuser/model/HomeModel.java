package com.icarexm.jiediuser.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icarexm.jiediuser.bean.LoginBean;
import com.icarexm.jiediuser.bean.OrderEstimatedPriceBean;
import com.icarexm.jiediuser.contract.HomeContract;
import com.icarexm.jiediuser.presenter.HomePresenter;
import com.icarexm.jiediuser.presenter.LoginPresenter;
import com.icarexm.jiediuser.utils.RequstUrlUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class HomeModel implements HomeContract.Model {
    /*
     * 获取订单
     *
     * */
    public void  PostIndex(HomePresenter HomePresenter,String token){
        OkGo.<String>post(RequstUrlUtils.URL.OrderIndex)
                .params("token",token)
                .params("type","0")
                .params("order_type","1")
                .params("order_time","2019-10-10")
                .params("limit","10")
                .params("page","1")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e("获取订单列表",response.body());
                    }
                });
    }

     /*
     * 获取订单价格
     */
     public void PostPrice(HomePresenter homePresenter,String kilometre,String order_id,String run_time){
         OkGo.<String>post(RequstUrlUtils.URL.price)
                 .params("kilometre",kilometre)
                 .params("order_id",order_id)
                 .params("run_time",run_time)
                 .execute(new StringCallback() {
                     @Override
                     public void onSuccess(Response<String> response) {
                         Log.e("订单价格",response.body());
                         Gson gson = new GsonBuilder().create();
                         OrderEstimatedPriceBean orderEstimatedPriceBean = gson.fromJson(response.body(), OrderEstimatedPriceBean.class);
                         if (orderEstimatedPriceBean.getCode()==200){
                             OrderEstimatedPriceBean.DataBean data = orderEstimatedPriceBean.getData();
                             double money = data.getMoney();
                             homePresenter.SetPrice(money+"");
                         }
                     }
                 });
     }
}
