package com.icarexm.jiediuser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.bean.ContactBean;
import com.icarexm.jiediuser.utils.RequstUrlUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmergencyContactActivity extends AppCompatActivity {
    @BindView(R.id.emergency_contact_list)
    ListView listView;
    private Context mContext;
    private String token;
    private List<ContactBean.DataBean> data=new ArrayList<>();
    private MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact);
        mContext = getApplicationContext();
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        ButterKnife.bind(this);
        InitUI();
        InitData();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        InitData();
        super.onNewIntent(intent);
    }

    private void InitData() {
        OkGo.<String>post(RequstUrlUtils.URL.emergencycontactIndex)
                .params("token",token)
                .params("type","0")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ContactBean contactBean = new GsonBuilder().create().fromJson(response.body(), ContactBean.class);
                        if (contactBean.getCode()==200){
                            data = contactBean.getData();
                            listView.setAdapter(myAdapter);
                        }
                    }
                });
    }

    private void InitUI() {
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==data.size()){
                 startActivity(new Intent(mContext,AddContactActivity.class));
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

    public class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return data.size()+1;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = getLayoutInflater().inflate(R.layout.list_emergency_contact, null);
            TextView tv_mobile= inflate.findViewById(R.id.list_emergency_contact_tv_mobile);
            TextView tv_manage = inflate.findViewById(R.id.list_emergency_contact_tv_manage);
            if (i==data.size()){
             tv_mobile.setText("添加紧急联系人");
                Drawable drawableLeft = tv_mobile.
                        getResources().getDrawable(
                        R.mipmap.icon_add);
                tv_mobile.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                        null, null, null);
                tv_mobile.setTextColor(getResources().getColor(R.color.ff707070));
                tv_manage.setVisibility(View.GONE);
            }else {
                tv_mobile.setText(data.get(i).getMobile());
            }
            tv_manage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, DeleteContactActivity.class);
                    intent.putExtra("contact_id",data.get(i).getId()+"");
                    intent.putExtra("contact_mobile",data.get(i).getMobile());
                    intent.putExtra("contact_name",data.get(i).getName());
                    startActivity(intent);
                }
            });
            return inflate;
        }
    }

}
