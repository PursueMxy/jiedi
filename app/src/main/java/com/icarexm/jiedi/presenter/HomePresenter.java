package com.icarexm.jiedi.presenter;

import com.icarexm.jiedi.Bean.OrderListBean;
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

    @Override
    public void GetAutoOrder(String token, String status) {
        HomeModel.PostAutoOrder(this,token,status);
    }


    public  void SetAutoOrder(String content){
        mView.ShowToast(content);
    }
}
