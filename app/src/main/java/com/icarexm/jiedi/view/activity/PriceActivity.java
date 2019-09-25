package com.icarexm.jiedi.view.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.icarexm.jiedi.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PriceActivity extends AppCompatActivity {

    private View dialog_callphone;
    private AlertDialog alertDialog;
    private TextView tv_phone_number;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);
        ButterKnife.bind(this);
        mContext = getApplicationContext();
        InitUI();
    }

    private void InitUI() {
        dialog_callphone = getLayoutInflater().inflate(R.layout.dialog_callphone, null);
        tv_phone_number = dialog_callphone.findViewById(R.id.dialog_callphone_tv_number);
        dialog_callphone.findViewById(R.id.dialog_callphone_tv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        dialog_callphone.findViewById(R.id.dialog_callphone_tv_call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

    @OnClick({R.id.price_tv_callphone,R.id.price_rl_cashout})
    public void onViewClicked(View view){
        switch (view.getId()) {
            case R.id.price_tv_callphone:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                alertDialog = builder.setView(dialog_callphone)
                        .create();
                alertDialog.show();
                break;
            case R.id.price_rl_cashout:
                startActivity(new Intent(mContext,CashOutActivity.class));
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
