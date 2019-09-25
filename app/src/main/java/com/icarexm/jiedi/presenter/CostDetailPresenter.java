package com.icarexm.jiedi.presenter;

import com.icarexm.jiedi.contract.CostDetailContract;
import com.icarexm.jiedi.model.CostDetailModel;

public class CostDetailPresenter implements CostDetailContract.Presenter {
    public final CostDetailContract.View mView;
    public final CostDetailModel costDetailModel;

    public CostDetailPresenter(CostDetailContract.View view){
        this.mView=view;
        costDetailModel=new CostDetailModel();

    }

    public void GetOrderPriceInfo(String order_id,String token){
        costDetailModel.PostOrderPriceInfo(order_id,token);
    }
}
