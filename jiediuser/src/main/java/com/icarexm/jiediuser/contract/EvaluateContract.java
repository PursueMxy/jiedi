package com.icarexm.jiediuser.contract;

import com.icarexm.jiediuser.bean.OrderDetailBean;
import com.icarexm.jiediuser.model.EvaluateModel;
import com.icarexm.jiediuser.presenter.EvaluatePresenter;

public interface EvaluateContract {
    interface Model {
        void PostOrderPrice(EvaluatePresenter evaluatePresente, String token, String order_id);
    }

    interface View {
        void UpdateUI(OrderDetailBean.DataBean data);
        void UpdateEvalute(int code,String msg);
    }

    interface Presenter {
        void SetOrderDetail(OrderDetailBean.DataBean data);
        void GetOrderPrice(String token, String order_id);
        void GetEvaluate(String token, String order_id, String score, String comment);
        void SetEvaLuate(int code,String msg);
    }
}
