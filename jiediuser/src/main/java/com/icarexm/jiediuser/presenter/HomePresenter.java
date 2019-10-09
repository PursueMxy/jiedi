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

    public void GetDriverIndex(String token,String city,String positionE, String positionN){
        homeModel.PostDriverIndex(this,token,city,positionE,positionN);
    }
}
