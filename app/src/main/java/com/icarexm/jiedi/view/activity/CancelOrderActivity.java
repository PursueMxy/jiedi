package com.icarexm.jiedi.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.icarexm.jiedi.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CancelOrderActivity extends AppCompatActivity {

    @BindView(R.id.cancel_order_tv_number)
    TextView tv_number;
    @BindView(R.id.cancel_order_edt_remarks)
    EditText edt_remarks;
    @BindView(R.id.cancel_order_radiobtn_one)
    RadioButton radiobtn_one;
    @BindView(R.id.cancel_order_radiobtn_two)
    RadioButton radiobtn_two;
    @BindView(R.id.cancel_order_radiobtn_three)
    RadioButton radiobtn_three;
    @BindView(R.id.cancel_order_radiobtn_four)
    RadioButton radiobtn_four;
    @BindView(R.id.cancel_order_radiogroup)
    RadioGroup cancel_order_radiogroup;

    private Context mContext;
    private int CANCELORDER_CODE=1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_order);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
        InitUI();
    }

    private void InitUI() {
        cancel_order_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.cancel_order_radiobtn_one:
                        break;
                    case R.id.cancel_order_radiobtn_two:
                        break;
                    case R.id.cancel_order_radiobtn_three:
                        break;
                    case R.id.cancel_order_radiobtn_four:
                        break;
                }
            }
        });
    }

    @OnClick({R.id.cancel_order_img_back})
    public void onViewClicked(View view){
        switch (view.getId()) {
            case R.id.cancel_order_img_back:
                Intent intent = new Intent(mContext, CancelOrderActivity.class);
                setResult(CANCELORDER_CODE,intent);
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
