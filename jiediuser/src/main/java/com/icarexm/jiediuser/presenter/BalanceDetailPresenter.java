package com.icarexm.jiediuser.presenter;

import com.icarexm.jiediuser.bean.BalanceDtlBean;
import com.icarexm.jiediuser.contract.BalanceDetailContract;
import com.icarexm.jiediuser.model.BalanceDetailModel;

import java.util.List;

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

    public void SetBalanceDtl(List<BalanceDtlBean.DataBean> data){
      mView.UpdateList(data);
    }
}
