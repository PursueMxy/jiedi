package com.icarexm.jiediuser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.google.gson.GsonBuilder;
import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.bean.AddContactBean;
import com.icarexm.jiediuser.utils.RequstUrlUtils;
import com.icarexm.jiediuser.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddContactActivity extends AppCompatActivity {

    @BindView(R.id.add_contact_edt_mobile)
    EditText edt_mobile;
    @BindView(R.id.add_contact_edt_name)
    EditText edt_name;
    private String token;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        mContext = getApplicationContext();
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        ButterKnife.bind(this);
    }

    @OnClick({R.id.add_contact_btn_add,R.id.add_contact_img_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.add_contact_img_back:
                finish();
                break;
            case R.id.add_contact_btn_add:
                String mobile = edt_mobile.getText().toString();
                String name = edt_name.getText().toString();
                if (!mobile.equals("")){
                 if (!name.equals("")){
                     OkGo.<String>post(RequstUrlUtils.URL.emergencycontactAdd)
                             .params("token",token)
                             .params("type","0")
                             .params("mobile",mobile)
                             .params("name",name)
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

                 }else {
                     ToastUtils.showToast(mContext,"名字不能为空");
                 }
                }else {
                    ToastUtils.showToast(mContext,"手机号不能为空");
                }
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
