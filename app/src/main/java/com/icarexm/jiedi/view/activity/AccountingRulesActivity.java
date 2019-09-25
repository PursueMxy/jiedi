package com.icarexm.jiedi.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.icarexm.jiedi.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountingRulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounting_rules);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.accounting_rules_img_back})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.accounting_rules_img_back:
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
}
