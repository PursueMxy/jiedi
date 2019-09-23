package com.icarexm.jiedi.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.icarexm.jiedi.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CancelOrderActivity extends AppCompatActivity {

    private Context mContext;
    private int CANCELORDER_CODE=1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_order);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
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
}
