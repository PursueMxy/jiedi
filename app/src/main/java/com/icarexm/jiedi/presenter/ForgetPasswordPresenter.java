package com.icarexm.jiedi.presenter;

import com.icarexm.jiedi.contract.ForgetPasswordContract;
import com.icarexm.jiedi.contract.LoginContract;
import com.icarexm.jiedi.model.ForgetPasswordModel;
import com.icarexm.jiedi.model.LoginModel;

public class ForgetPasswordPresenter implements ForgetPasswordContract.Presenter {

    private final ForgetPasswordModel forgetPasswordModel;
    private final ForgetPasswordContract.View mView;

    public ForgetPasswordPresenter(ForgetPasswordContract.View view) {
        mView = view;
        forgetPasswordModel = new ForgetPasswordModel();
    }

    public void GetMobileCode(String mobile,String event,String type){
        forgetPasswordModel.PostMobileCode(mobile,event,type);
    }

}
