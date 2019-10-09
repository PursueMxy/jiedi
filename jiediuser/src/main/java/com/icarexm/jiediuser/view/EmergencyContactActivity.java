package com.icarexm.jiediuser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.icarexm.jiediuser.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmergencyContactActivity extends AppCompatActivity {
    @BindView(R.id.emergency_contact_list)
    ListView listView;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
        InitUI();
    }

    private void InitUI() {
        MyAdapter myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==22){
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
            return 23;
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
            if (i==22){
             tv_mobile.setText("添加紧急联系人");
                Drawable drawableLeft = tv_mobile.
                        getResources().getDrawable(
                        R.mipmap.icon_add);
                tv_mobile.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                        null, null, null);
                tv_mobile.setTextColor(getResources().getColor(R.color.ff707070));
                tv_manage.setVisibility(View.GONE);
            }else {

            }
            tv_manage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(mContext,DeleteContactActivity.class));
                }
            });
            return inflate;
        }
    }

}
