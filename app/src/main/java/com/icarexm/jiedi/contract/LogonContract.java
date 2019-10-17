package com.icarexm.jiedi.contract;

import com.icarexm.jiedi.presenter.LogonPresenter;

public interface LogonContract {
    interface Model {
        void PostMobileCode(LogonPresenter logonPresenter, String mobile, String event, String type);
    }

    interface View {
    }

    interface Presenter {
        void GetMobileCode(String mobile, String event, String type);
    }
}
