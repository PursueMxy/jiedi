package com.icarexm.jiediuser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.icarexm.jiediuser.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RechargeActivity extends AppCompatActivity {
    @BindView(R.id.recharge_gridview)
    GridView recharge_gridview;

    private List<Map<String, Object>> dataList;
    private int[] name;
    private GridAdapter gridAdapter;
    private int SLT_TYPE=0;
    private int SLT_PRICE=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        ButterKnife.bind(this);
        InitData();
        InitUI();
    }

    private void InitData() {
        //图标下的文字
        name = new int[]{10,20,30,50,100,150,200,300};
    }

    private void InitUI() {
        String[] from={"text"};
        int[] to={R.id.text};
        gridAdapter = new GridAdapter();
        recharge_gridview.setAdapter( gridAdapter);
        recharge_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SLT_TYPE=i;
                SLT_PRICE=name[i];
                gridAdapter.notifyDataSetChanged();
            }
        });
    }

    public class GridAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return name.length;
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
            View inflate = getLayoutInflater().inflate(R.layout.recharge_gridview, null);
            TextView tv_name = inflate.findViewById(R.id.recharge_gridview_tv_name);
            tv_name.setText(name[i]+"元");
            if (i==SLT_TYPE){
                tv_name.setTextColor(getResources().getColor(R.color.white));
                tv_name.setBackgroundResource(R.drawable.bg_blue);
            }else {
                tv_name.setTextColor(getResources().getColor(R.color.ced6e2));
                tv_name.setBackgroundResource(R.drawable.bg_gary);
            }
            return inflate;
        }
    }
}
