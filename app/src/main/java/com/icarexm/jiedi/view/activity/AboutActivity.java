package com.icarexm.jiedi.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.icarexm.jiedi.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends AppCompatActivity {
   @BindView(R.id.about_tv_address)
    TextView tv_address;
   @BindView(R.id.about_tv_phoneNumber)
   TextView tv_phoneNumber;
   @BindView(R.id.about_tv_version)
   TextView tv_version;
   @BindView(R.id.about_img_logo)
   TextView img_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.about_img_back})
    public void  onViewClicked(View view){
        switch (view.getId()){
            case R.id.about_img_back:
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
