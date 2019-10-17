package com.icarexm.jiedi.presenter;

import com.icarexm.jiedi.Bean.OrderDetailBean;
import com.icarexm.jiedi.Bean.OrderTypeBean;
import com.icarexm.jiedi.contract.EvaluateContract;
import com.icarexm.jiedi.model.EvaluateModel;

public class EvaluatePresenter implements EvaluateContract.Presenter {
    private EvaluateContract.View mView;
    private EvaluateModel evaluateModel;
    public EvaluatePresenter(EvaluateContract.View view){
        mView=view;
        evaluateModel=new EvaluateModel();
    }


    public void SetOrderDetail(OrderTypeBean.DataBean data){
        mView. UpdateUI(data);
    }

    @Override
    public void GetOrderPrice(String token, String order_id) {
        evaluateModel.PostOrderPrice(this,token,order_id);
    }

    public void GetEvaluate(String token, String order_id, String score, String comment) {
        evaluateModel.PostEvaluate(this,token,order_id,score,comment);
    }

    public void SetEvaLuate(int code,String msg){
        mView.UpdateEvalute(code,msg);

    }
}
