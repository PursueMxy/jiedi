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
import com.icarexm.jiedi.utils.ToastUtils;

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
    private String reason="";
    private String remark="";

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
                        reason="平台派单太远";
                        break;
                    case R.id.cancel_order_radiobtn_two:
                        reason="行程有变，暂时不需要用车";
                        break;
                    case R.id.cancel_order_radiobtn_three:
                        reason="联系不上乘客";
                        break;
                    case R.id.cancel_order_radiobtn_four:
                        reason="已超出载客人数";
                        break;
                }
            }
        });
    }

    @OnClick({R.id.cancel_order_img_back,R.id.cancel_order_btn_confirm})
    public void onViewClicked(View view){
        switch (view.getId()) {
            case R.id.cancel_order_img_back:
                Intent intent = new Intent(mContext,MainActivity.class);
                intent.putExtra("type","0");
                setResult(CANCELORDER_CODE,intent);
                finish();
                break;
            case R.id.cancel_order_btn_confirm:
                ToastUtils.showToast(mContext,"点击了"+reason);
                remark=edt_remarks.getText().toString();
                if (!reason.equals("")) {
                    Intent intent1 = new Intent(mContext,MainActivity.class);
                    intent1.putExtra("reason", reason);
                    intent1.putExtra("type", "1");
                    intent1.putExtra("remark", remark);
                    setResult(CANCELORDER_CODE, intent1);
                    finish();
                }
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(mContext,MainActivity.class);
            intent.putExtra("type","0");
            setResult(CANCELORDER_CODE,intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
