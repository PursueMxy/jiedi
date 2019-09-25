package com.icarexm.jiedi.contract;

public interface CostDetailContract {
    interface Model {
        void PostOrderPriceInfo(String order_id,String token);
    }

    interface View {

    }

    interface Presenter {
        void GetOrderPriceInfo(String order_id,String token);
    }
}
