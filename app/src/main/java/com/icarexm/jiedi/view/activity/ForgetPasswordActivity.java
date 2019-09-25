package com.icarexm.jiedi.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.icarexm.jiedi.R;
import com.icarexm.jiedi.contract.ForgetPasswordContract;
import com.icarexm.jiedi.presenter.ForgetPasswordPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPasswordActivity extends AppCompatActivity implements ForgetPasswordContract.View{

    @BindView(R.id.forget_psw_edt_mobile)
    EditText edt_mobile;
    @BindView(R.id.forget_psw_edt_new_password)
    EditText edt_password;
    @BindView(R.id.forget_psw_edt_verification_code)
    EditText edt_verification_code;
    private ForgetPasswordPresenter forgetPasswordPresenter;
    private String mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
        forgetPasswordPresenter = new ForgetPasswordPresenter(this);
    }

    @OnClick({R.id.forget_psw_btn_confirm_update,R.id.forget_psw_btn_gain_verification_code,R.id.forget_psw_img_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.forget_psw_btn_confirm_update:
                break;
            case R.id.forget_psw_btn_gain_verification_code:
                mobile = edt_mobile.getText().toString();
                forgetPasswordPresenter.GetMobileCode(mobile,"driver_register","1");
                break;
            case R.id.forget_psw_img_back:
                finish();
                break;
                default:
                    break;

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }


    public void affirmUpdate(){}
}
