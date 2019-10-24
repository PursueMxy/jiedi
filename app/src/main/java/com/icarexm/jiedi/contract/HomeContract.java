package com.icarexm.jiedi.contract;

import com.icarexm.jiedi.Bean.DeliverBean;
import com.icarexm.jiedi.Bean.OrderListBean;
import com.icarexm.jiedi.Bean.OrderListOneBean;
import com.icarexm.jiedi.presenter.HomePresenter;

public interface HomeContract {
    interface Model {
        void PostOrder(HomePresenter homePresenter,String token, String city, String longitude, String latitude);
        void PostAutoOrder(HomePresenter homePresenter, String token, String status);
        void  PostIndex(HomePresenter homePresenter,String token);
    }

    interface View {
        void UpdateOrderList(OrderListBean.DataBean data);
        void ShowToast(String content);
        void UpdateOrder(String order_id);
        void ShowDialog(DeliverBean.DataBean.OrderBean order);
        void UpSyatemOrder(OrderListOneBean.DataBean.OrderBean orderBean);
        void Logout();
    }

    interface Presenter {
        void GetOrder(String token,String city,String longitude,String latitude);
        void SetOrder(OrderListBean.DataBean data);
        void GetAutoOrder(String token, String status);
        void SetAutoOrder(String content);
        void GetIndex(String token);
        void SetIndex(String order);
        void SetOrderUpload(DeliverBean.DataBean.OrderBean order);
        void SetSystemOrder(OrderListOneBean.DataBean.OrderBean orderBean);
        void SetLogout();
    }
}
