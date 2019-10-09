package com.icarexm.jiediuser.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icarexm.jiediuser.bean.LoginBean;
import com.icarexm.jiediuser.contract.HomeContract;
import com.icarexm.jiediuser.presenter.HomePresenter;
import com.icarexm.jiediuser.presenter.LoginPresenter;
import com.icarexm.jiediuser.utils.RequstUrlUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class HomeModel implements HomeContract.Model {
    /*
     * 发送用户位置
     *
     * */
    public void  PostDriverIndex(HomePresenter HomePresenter,String token, String city, String positionE, String positionN){
//
    }
}
