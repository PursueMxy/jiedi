package com.icarexm.jiediuser.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icarexm.jiediuser.bean.AliPayBean;
import com.icarexm.jiediuser.bean.OrderDetailBean;
import com.icarexm.jiediuser.bean.OrderPayBean;
import com.icarexm.jiediuser.bean.SettlementBean;
import com.icarexm.jiediuser.bean.WechatPayBean;
import com.icarexm.jiediuser.contract.OrderPayContract;
import com.icarexm.jiediuser.presenter.OrderPayPresenter;
import com.icarexm.jiediuser.utils.RequstUrlUtils;
import com.icarexm.jiediuser.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.IdentityHashMap;

public class OrderPayModel implements OrderPayContract.Model {

    /*
    * 获取订单金额
    * */
    public void PostOrderPrice(OrderPayPresenter orderPayPresenter,String token,String order_id){
        OkGo.<String>post(RequstUrlUtils.URL.orderInfo)
                .params("token",token)
                .params("order_id",order_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e("订单详情",response.body());
                        Gson gson = new GsonBuilder().create();
                        OrderDetailBean orderDetailBean = gson.fromJson(response.body(), OrderDetailBean.class);
                        if (orderDetailBean.getCode()==200) {
                            OrderDetailBean.DataBean data = orderDetailBean.getData();
                            orderPayPresenter.SetOrderPrice(data);
                        }

                    }
                });
    }

    /*
    * 订单支付
    * */
    public void PostSettlement(OrderPayPresenter orderPayPresenter,String token,String order_id,String coupon_id,String pay_type){
        OkGo.<String>post(RequstUrlUtils.URL.Settlement)
                .params("token",token)
                .params("order_id",order_id)
                .params("coupon_id",coupon_id)
                .params("pay_type",pay_type)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new GsonBuilder().create();
                        if (pay_type.equals("0")) {
                            SettlementBean settlementBean = gson.fromJson(response.body(), SettlementBean.class);
                            if (settlementBean != null) {
                                orderPayPresenter.SetSettlement(settlementBean.getMsg(), settlementBean.getCode());
                            }
                        }else if (pay_type.equals("1")){
                            WechatPayBean wechatPayBean = gson.fromJson(response.body(), WechatPayBean.class);
                            orderPayPresenter.SetWechatPay(wechatPayBean);
                        }else if (pay_type.equals("2")){
                            AliPayBean aliPayBean = gson.fromJson(response.body(), AliPayBean.class);
                            orderPayPresenter.SetAliPay(aliPayBean);
                        }
                    }
                });
    }

}
