package com.icarexm.jiedi.presenter;

import android.util.Log;

import com.icarexm.jiedi.Bean.DeliverBean;
import com.icarexm.jiedi.Bean.OrderListBean;
import com.icarexm.jiedi.Bean.OrderListOneBean;
import com.icarexm.jiedi.contract.HomeContract;
import com.icarexm.jiedi.contract.LoginContract;
import com.icarexm.jiedi.model.HomeModel;
import com.icarexm.jiedi.model.LoginModel;

public class HomePresenter implements HomeContract.Presenter {

    private final  HomeContract.View mView;
    private final HomeModel  HomeModel;

    public HomePresenter( HomeContract.View view) {
        mView = view;
        HomeModel = new  HomeModel();
    }
    public  void GetOrder(String token,String city,String longitude,String latitude){
       HomeModel.PostOrder(this,token,city,longitude,latitude);
    }

    public  void SetOrder(OrderListBean.DataBean data){
        mView.UpdateOrderList(data);
    }

    //已经退出登录
    public void SetLogout(){
        mView.Logout();
    }

    @Override
    public void GetAutoOrder(String token, String status) {
        HomeModel.PostAutoOrder(this,token,status);
    }


    public  void SetAutoOrder(String content){
        mView.ShowToast(content);
    }

    public void GetIndex(String token) {
        HomeModel.PostIndex(this,token);
    }

    //系统派单等待接收
    public void SetSystemOrder(OrderListOneBean.DataBean.OrderBean orderBean){
       mView.UpSyatemOrder(orderBean);
    }

    //有订单还未完成
    public void SetIndex(String order){
      mView.UpdateOrder(order);
    }




    //自动接单更新
    public void SetOrderUpload(DeliverBean.DataBean.OrderBean order){
     mView.ShowDialog(order);
    }

}
