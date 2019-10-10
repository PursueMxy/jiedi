package com.icarexm.jiediuser.presenter;

import com.icarexm.jiediuser.contract.HomeContract;
import com.icarexm.jiediuser.model.HomeModel;

public class HomePresenter implements HomeContract.Presenter {
    private final HomeModel homeModel;
    HomeContract.View mView;

    public HomePresenter(HomeContract.View view){
        mView=view;
        homeModel =new HomeModel();
    }


    @Override
    public void GetIndex(String token) {
        homeModel.PostIndex(this,token);
    }

    @Override
    public void GetPrice(String kilometre, String order_id, String run_time) {
        homeModel.PostPrice(this,kilometre,order_id,run_time);
    }

    public void SetPrice(String money){
        mView.UpdateEstimatedPrice(money);
    }
}
