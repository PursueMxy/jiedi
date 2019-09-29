package com.icarexm.jiedi.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.icarexm.jiedi.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddWithdrawActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_withdraw);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.with_draw_add_img_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.with_draw_add_img_back:
                finish();
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==event.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
