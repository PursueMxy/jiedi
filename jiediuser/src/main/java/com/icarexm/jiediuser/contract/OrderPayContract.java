package com.icarexm.jiediuser.contract;

import com.icarexm.jiediuser.bean.AliPayBean;
import com.icarexm.jiediuser.bean.OrderDetailBean;
import com.icarexm.jiediuser.bean.WechatPayBean;
import com.icarexm.jiediuser.presenter.OrderPayPresenter;

public interface OrderPayContract {
    interface Model {
        void PostOrderPrice(OrderPayPresenter orderPayPresenter, String token, String order_id);
        void PostSettlement(OrderPayPresenter orderPayPresenter,String token,String order_id,String coupon_id,String pay_type);
    }

    interface View {
        void UpdateUI(OrderDetailBean.DataBean data);
        void UpdateToast(String msg,int codes);
        void WechatPay(WechatPayBean wechatPayBean);
        void  AliPay(AliPayBean aliPayBean);

    }

    interface Presenter {
        void GetOrderPrice(String token,String order_id);
        void SetOrderPrice(OrderDetailBean.DataBean data);
        void GetSettlement(String token,String order_id,String coupon_id,String pay_type);
        void SetSettlement(String msg,int codes);

    }
}
