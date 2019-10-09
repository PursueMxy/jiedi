package com.icarexm.jiediuser.contract;

import com.icarexm.jiediuser.presenter.HomePresenter;

public interface HomeContract {
    interface Model {
      void   PostDriverIndex(HomePresenter HomePresenter, String token, String city, String positionE, String positionN);
    }

    interface View {
    }

    interface Presenter {
        void GetDriverIndex(String token,String city,String positionE, String positionN);
    }
}
