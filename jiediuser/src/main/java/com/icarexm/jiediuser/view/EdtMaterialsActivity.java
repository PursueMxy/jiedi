package com.icarexm.jiediuser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.icarexm.jiediuser.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class EdtMaterialsActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edt_materials);
        ButterKnife.bind(this);
        mContext = getApplicationContext();
    }
    @OnClick({R.id.edt_materials_back,R.id.edt_materials_tv_certification})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.edt_materials_back:
                finish();
                break;
            case R.id.edt_materials_tv_certification:
                startActivity(new Intent(mContext,CertificationActivity.class));
                break;
        }
    }
}
