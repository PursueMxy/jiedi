package com.icarexm.jiediuser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.icarexm.jiediuser.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AgreementExplainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement_explain);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.agreement_explain_img_back})
    public void onView(View view){
        switch (view.getId()){
            case R.id.agreement_explain_img_back:
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
}
