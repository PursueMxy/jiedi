package com.icarexm.jiediuser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.icarexm.jiediuser.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeleteContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_contact);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.delete_contact_img_back,R.id.delete_contact_btn_dlt})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.delete_contact_btn_dlt:
                break;
            case R.id.delete_contact_img_back:
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
