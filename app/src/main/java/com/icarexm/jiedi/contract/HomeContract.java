package com.icarexm.jiedi.contract;

import com.icarexm.jiedi.Bean.OrderListBean;
import com.icarexm.jiedi.presenter.HomePresenter;

public interface HomeContract {
    interface Model {
        void PostOrder(HomePresenter homePresenter,String token, String city, String longitude, String latitude);
        void PostAutoOrder(HomePresenter homePresenter, String token, String status);
    }

    interface View {
        void UpdateOrderList(OrderListBean.DataBean data);
        void ShowToast(String content);
    }

    interface Presenter {
        void GetOrder(String token,String city,String longitude,String latitude);
        void SetOrder(OrderListBean.DataBean data);
        void GetAutoOrder(String token, String status);
        void SetAutoOrder(String content);
    }
}
