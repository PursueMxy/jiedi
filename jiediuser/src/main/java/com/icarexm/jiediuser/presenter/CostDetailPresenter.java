package com.icarexm.jiediuser.presenter;

import com.icarexm.jiediuser.contract.CostDetailContract;
import com.icarexm.jiediuser.model.CostDetailModel;

public class CostDetailPresenter implements CostDetailContract.Presenter {

    CostDetailContract.View mView;
    CostDetailModel costDetailModel;

    public CostDetailPresenter(CostDetailContract.View view){
        mView=view;
        costDetailModel=new CostDetailModel();
    }
    @Override
    public void GetOrderDetail(String token, String order_id) {
       costDetailModel.PostOrderDetail(this,token,order_id);
    }
    public void setOrderDetail(){

    }
}
