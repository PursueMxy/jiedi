package com.icarexm.jiediuser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.icarexm.jiediuser.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecommendActivity extends AppCompatActivity {


    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
    }

    @OnClick({R.id.recommend_tv_invite})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.recommend_tv_invite:
                startActivity(new Intent(mContext,InviteActivity.class));
                break;
        }
    }
}
