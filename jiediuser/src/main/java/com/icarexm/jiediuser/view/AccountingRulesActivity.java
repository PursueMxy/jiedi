package com.icarexm.jiediuser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.contract.AccountingRulesContract;
import com.icarexm.jiediuser.presenter.AccountingRulesPresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountingRulesActivity extends AppCompatActivity implements AccountingRulesContract.View {

    private AccountingRulesPresenter accountingRulesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounting_rules);
        ButterKnife.bind(this);
        accountingRulesPresenter = new AccountingRulesPresenter(this);
        accountingRulesPresenter.GetAccountingRules();
    }
    @OnClick({R.id.accounting_rules_img_back})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.accounting_rules_img_back:
                finish();
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
