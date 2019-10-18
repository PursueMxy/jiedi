package com.icarexm.jiediuser.contract;

import com.icarexm.jiediuser.bean.OrderDetailBean;
import com.icarexm.jiediuser.bean.OrderDetailOneBean;
import com.icarexm.jiediuser.presenter.HomePresenter;

public interface HomeContract {
    interface Model {
      void   PostIndex(HomePresenter HomePresenter, String token);
        void PostPrice(HomePresenter HomePresenter,String kilometre,String order_id,String run_time);
        void PostOrderPrice(HomePresenter homePresenter,String token,String order_id,String orderStatus);
    }

    interface View {
        void UpdateEstimatedPrice(String money);
        void UpdateOrderDtl(OrderDetailBean.DataBean data);
        void UpdateOrderDtlOne(OrderDetailOneBean.DataBean data);
        void UpdateCancelOrder();
    }

    interface Presenter {
        void GetIndex(String token);
        void GetPrice(String kilometre,String order_id,String run_time);
        void GetOrderPrice(String token,String order_id,String orderStatus);
        void SetOrderDetail(OrderDetailBean.DataBean data);
        void GetCancelOrder();
    }
}
