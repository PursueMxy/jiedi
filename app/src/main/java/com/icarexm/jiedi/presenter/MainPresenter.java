package com.icarexm.jiedi.presenter;

import android.util.Log;

import com.icarexm.jiedi.Bean.OrderType1Bean;
import com.icarexm.jiedi.Bean.OrderTypeBean;
import com.icarexm.jiedi.contract.MainContract;
import com.icarexm.jiedi.model.MainModel;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private MainModel mianModel;

    public MainPresenter(MainContract.View view){
        mView=view;
        mianModel=new MainModel();
    }

   public void GetOrderInfo(String token,String order_id){
        //获取订单详情
        mianModel.PostOrderInfo(this,token,order_id);
   }

    @Override
    public void SetOrderInfo( OrderTypeBean.DataBean data) {
        mView.UpdateUI(data);

    }

    public void SetOrderInfo1( OrderType1Bean.DataBean data) {
        mView.UpdateUI1(data);

    }

    @Override
    public void GetEvaluate(String token, String order_id, String score, String comment) {
        mianModel.PostEvaluate(this,token,order_id,score,comment);
    }




}
