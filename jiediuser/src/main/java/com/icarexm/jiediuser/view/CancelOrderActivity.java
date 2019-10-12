package com.icarexm.jiediuser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.icarexm.jiediuser.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CancelOrderActivity extends AppCompatActivity {
    @BindView(R.id.cancel_order_radiogroup)
    RadioGroup radioGroup;
    @BindView(R.id.cancel_order_radiobtn_one)
    RadioButton radiobtn_one;
    @BindView(R.id.cancel_order_radiobtn_two)
    RadioButton radiobtn_two;
    @BindView(R.id.cancel_order_radiobtn_three)
    RadioButton radiobtn_three;
    @BindView(R.id.cancel_order_radiobtn_four)
    RadioButton radiobtn_four;
    @BindView(R.id.cancel_order_edt_remarks)
    EditText edt_remarks;
    private String order_id;
    private String reason="点错了";
    private String remark="";
    private Context mContext;
    private int CANCEL_ORDER_CODE=6698;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_order);
        mContext = getApplicationContext();
        Intent intent = getIntent();
        order_id = intent.getStringExtra("order_id");
        ButterKnife.bind(this);
        InitUI();
    }

    private void InitUI() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.cancel_order_radiobtn_one:
                        reason="点错了";
                        break;
                    case R.id.cancel_order_radiobtn_two:
                        reason="地址信息填错了";
                        break;
                    case R.id.cancel_order_radiobtn_three:
                        reason="司机迟到";
                        break;
                    case R.id.cancel_order_radiobtn_four:
                        reason="乘客无法联系到司机";
                        break;
                }
            }
        });
    }

    @OnClick({R.id.cancel_order_img_back,R.id.cancel_order_btn_confirm})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.cancel_order_img_back:
                finish();
                break;
            case R.id.cancel_order_btn_confirm:
                remark = edt_remarks.getText().toString();
                Intent intent=new Intent(mContext,HomeActivity.class);
                intent.putExtra("order_id",order_id);
                intent.putExtra("reason",reason);
                intent.putExtra("remark",remark);
                setResult(CANCEL_ORDER_CODE,intent);
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
