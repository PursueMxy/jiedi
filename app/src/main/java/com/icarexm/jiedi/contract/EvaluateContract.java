package com.icarexm.jiedi.contract;

import com.icarexm.jiedi.Bean.OrderDetailBean;
import com.icarexm.jiedi.Bean.OrderTypeBean;
import com.icarexm.jiedi.presenter.EvaluatePresenter;

public interface EvaluateContract {
    interface Model {
        void PostOrderPrice(EvaluatePresenter evaluatePresente, String token, String order_id);
    }

    interface View {
        void UpdateUI(OrderTypeBean.DataBean data);
        void UpdateEvalute(int code,String msg);
    }

    interface Presenter {
        void SetOrderDetail(OrderTypeBean.DataBean data);
        void GetOrderPrice(String token, String order_id);
        void GetEvaluate(String token, String order_id, String score, String comment);
        void SetEvaLuate(int code,String msg);
    }
}
