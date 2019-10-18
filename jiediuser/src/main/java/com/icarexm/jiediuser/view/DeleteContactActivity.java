package com.icarexm.jiediuser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.bean.AddContactBean;
import com.icarexm.jiediuser.utils.RequstUrlUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeleteContactActivity extends AppCompatActivity {

    @BindView(R.id.dlt_contact_tv_mobile)
    TextView tv_mobile;
    @BindView(R.id.dlt_contact_tv_name)
    TextView tv_name;

    private String contact_id;
    private String contact_mobile;
    private String contact_name;
    private String token;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_contact);
        mContext = getApplicationContext();
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        Intent intent = getIntent();
        contact_id = intent.getStringExtra("contact_id");
        contact_mobile = intent.getStringExtra("contact_mobile");
        contact_name = intent.getStringExtra("contact_name");
        ButterKnife.bind(this);
        tv_mobile.setText(contact_mobile);
        tv_name.setText(contact_name);
    }

    @OnClick({R.id.delete_contact_img_back,R.id.delete_contact_btn_dlt})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.delete_contact_btn_dlt:
                DltData();
                break;
            case R.id.delete_contact_img_back:
                finish();
                break;
        }
    }

    private void DltData() {
        OkGo.<String>post(RequstUrlUtils.URL.emergencycontactDelete)
                .params("token",token)
                .params("type","0")
                .params("id",contact_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        AddContactBean addContactBean = new GsonBuilder().create().fromJson(response.body(), AddContactBean.class);
                        if (addContactBean.getCode()==201){
                            startActivity(new Intent(mContext,EmergencyContactActivity.class));
                            finish();
                        }
                    }
                });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
