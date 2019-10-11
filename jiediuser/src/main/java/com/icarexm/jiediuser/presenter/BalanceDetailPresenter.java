package com.icarexm.jiediuser.presenter;

import com.icarexm.jiediuser.contract.BalanceDetailContract;
import com.icarexm.jiediuser.model.BalanceDetailModel;

public class BalanceDetailPresenter implements BalanceDetailContract.Presenter {

    BalanceDetailModel balanceDetailModel;
    BalanceDetailContract.View mView;

    public BalanceDetailPresenter(BalanceDetailContract.View view){
            balanceDetailModel=new BalanceDetailModel();
            mView=view;
    }

    public void GetBalanceDtl(String token,String select_time,String limit,String page){
        balanceDetailModel.PostBalanceDtl(this,token,select_time,limit,page);
    }
}
