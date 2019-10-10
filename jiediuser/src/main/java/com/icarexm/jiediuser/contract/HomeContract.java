package com.icarexm.jiediuser.contract;

import com.icarexm.jiediuser.presenter.HomePresenter;

public interface HomeContract {
    interface Model {
      void   PostIndex(HomePresenter HomePresenter, String token);
        void PostPrice(HomePresenter HomePresenter,String kilometre,String order_id,String run_time);
    }

    interface View {
        void UpdateEstimatedPrice(String money);
    }

    interface Presenter {
        void GetIndex(String token);
        void GetPrice(String kilometre,String order_id,String run_time);
    }
}
