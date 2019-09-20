package com.icarexm.jiedi.contract;

public interface ForgetPasswordContract {
    interface Model {
        void PostMobileCode(String mobile,String event,String type);
    }

    interface View {
    }

    interface Presenter {
       void GetMobileCode(String mobile,String event,String type);
    }
}
