package com.icarexm.jiediuser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CertificationActivity extends AppCompatActivity {

    @BindView(R.id.certification_edt_identity_card)
    EditText edt_identity_card;
    @BindView(R.id.certification_edt_username)
    EditText edt_username;
    private int START_CODE=10086;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
    }

    @OnClick({R.id.certification_img_back,R.id.certification_btn_confirm})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.certification_img_back:
                finish();
                break;
            case R.id.certification_btn_confirm:
                String identity_card= edt_identity_card.getText().toString();
                String username = edt_username.getText().toString();
                if (!identity_card.equals("")&&!username.equals("")) {
                    Intent intent = new Intent(mContext, EdtMaterialsActivity.class);
                    intent.putExtra("identity_card",identity_card);
                    intent.putExtra("username",username);
                    setResult(START_CODE, intent);
                    finish();
                }else {
                    ToastUtils.showToast(mContext,"姓名或身份证号不能为空");
                }
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
