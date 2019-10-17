package com.icarexm.jiedi.presenter;

import com.icarexm.jiedi.contract.LogonContract;
import com.icarexm.jiedi.model.LogonModel;

public class LogonPresenter implements LogonContract.Presenter {

    private final LogonModel loginModel;
    LogonContract.View mView;

    public LogonPresenter(LogonContract.View view){
        mView=view;
        loginModel =new LogonModel();
    }

    public void GetMobileCode(String mobile, String event, String type) {
        loginModel.PostMobileCode(this,mobile,event,type);
    }
}
