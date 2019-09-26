package com.icarexm.jiedi.contract;

import com.icarexm.jiedi.Bean.OrderListBean;
import com.icarexm.jiedi.presenter.HomePresenter;

public interface HomeContract {
    interface Model {
        void PostOrder(HomePresenter homePresenter,String token, String city, String longitude, String latitude);
    }

    interface View {
        void UpdateOrderList(OrderListBean.DataBean data);
    }

    interface Presenter {
        void GetOrder(String token,String city,String longitude,String latitude);
        void SetOrder(OrderListBean.DataBean data);
    }
}
