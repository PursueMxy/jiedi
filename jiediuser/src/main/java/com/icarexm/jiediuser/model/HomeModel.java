package com.icarexm.jiediuser.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icarexm.jiediuser.bean.EvaluateBean;
import com.icarexm.jiediuser.bean.LoginBean;
import com.icarexm.jiediuser.bean.OrderDetailBean;
import com.icarexm.jiediuser.bean.OrderDetailOneBean;
import com.icarexm.jiediuser.bean.OrderEstimatedPriceBean;
import com.icarexm.jiediuser.bean.OrderListOneBean;
import com.icarexm.jiediuser.contract.HomeContract;
import com.icarexm.jiediuser.presenter.HomePresenter;
import com.icarexm.jiediuser.presenter.LoginPresenter;
import com.icarexm.jiediuser.utils.RequstUrlUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.security.PublicKey;
import java.util.List;

public class HomeModel implements HomeContract.Model {

    private int status;

    /*
     * 获取订单
     *
     * */
    public void  PostIndex(HomePresenter homePresenter,String token){
        OkGo.<String>post(RequstUrlUtils.URL.OrderIndex)
                .params("token",token)
                .params("type","0")
                .params("order_type","1")
                .params("order_time","")
                .params("limit","1")
                .params("page","1")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new GsonBuilder().create();
                        OrderListOneBean orderListOneBean = gson.fromJson(response.body(), OrderListOneBean.class);
                        int code = orderListOneBean.getCode();
                        if (code==200){
                            OrderListOneBean.DataBean data = orderListOneBean.getData();
                            if (data!=null){
                                List<OrderListOneBean.DataBean.OrderBean> order = data.getOrder();
                                if (order!=null){
                                    String status = order.get(0).getStatus();
                                    int i = Integer.parseInt(status);
                                    if (i<6) {
                                        int id = order.get(0).getId();
                                        PostOrderPrice(homePresenter, token, id + "", status);
                                    }
                                }
                            }

                        }
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

    /*
     * 获取订单金额
     * */
    public void PostOrderPrice(HomePresenter homePresenter,String token,String order_id,String orderStatus){
        if (orderStatus!=null){
            status = Integer.parseInt(orderStatus);
        }
        OkGo.<String>post(RequstUrlUtils.URL.orderInfo)
                .params("token",token)
                .params("order_id",order_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new GsonBuilder().create();
                        if (status<2){
                            OrderDetailOneBean orderDetailOneBean = gson.fromJson(response.body(), OrderDetailOneBean.class);
                            if (orderDetailOneBean.getCode()==200){
                                OrderDetailOneBean.DataBean data = orderDetailOneBean.getData();
                                homePresenter.SetOrderDetailOne(data);
                            }
                        }else if (status<6){
                            OrderDetailBean orderDetailBean = gson.fromJson(response.body(), OrderDetailBean.class);
                            if (orderDetailBean.getCode() == 200) {
                                OrderDetailBean.DataBean data = orderDetailBean.getData();
                                homePresenter.SetOrderDetail(data);
                            }
                        }else if (status==6){
                            OrderDetailBean orderDetailBean = gson.fromJson(response.body(), OrderDetailBean.class);
                            if (orderDetailBean.getCode() == 200) {
                                OrderDetailBean.DataBean data = orderDetailBean.getData();
                                homePresenter.SetOrderDetail(data);
                            }
                        }
                    }
                });
    }


}
