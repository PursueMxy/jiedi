package com.icarexm.jiediuser.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.adapter.MyOrderAdapter;
import com.icarexm.jiediuser.bean.OrderListOneBean;
import com.icarexm.jiediuser.utils.MxyUtils;
import com.zhouyou.recyclerview.XRecyclerView;
import com.zhouyou.recyclerview.adapter.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrderActivity extends AppCompatActivity {
    @BindView(R.id.order_list_radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.order_list_radiobutton_whole)
    RadioButton radiobutton_whole;
    @BindView(R.id.order_list_radiobutton_completed)
    RadioButton radiobutton_completed;
    @BindView(R.id.order_list_radiobutton_immature)
    RadioButton radiobutton_immature;
    @BindView(R.id.order_list_recyclerView)
    XRecyclerView mRecyclerView;
    @BindView(R.id.order_list_tv_time)
    TextView tv_time;
    private Context mContext;
    private LinearLayoutManager mLayoutManager;
    private SharedPreferences sp;
    private String OrderType;
    private int page;
    private MyOrderAdapter orderPerDayAdapter;
    private List<OrderListOneBean.DataBean.OrderBean> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);
        mContext=getApplicationContext();
        InitUI();
    }

    private void InitUI() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.order_list_radiobutton_whole:
                        radiobutton_whole.setBackgroundResource(R.drawable.myorder_choosed_color);
                        radiobutton_whole.setTextColor(getResources().getColor(R.color.ff5181fb));
                        radiobutton_immature.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_immature.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_completed.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_completed.setTextColor(getResources().getColor(R.color.black));
                        OrderType ="0";
                        page =1;
                        break;
                    case R.id.order_list_radiobutton_immature:
                        radiobutton_whole.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_whole.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_immature.setBackgroundResource(R.drawable.myorder_choosed_color);
                        radiobutton_immature.setTextColor(getResources().getColor(R.color.ff5181fb));
                        radiobutton_completed.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_completed.setTextColor(getResources().getColor(R.color.black));
                        OrderType ="1";
                        page =1;
                        break;
                    case R.id.order_list_radiobutton_completed:
                        radiobutton_whole.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_whole.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_immature.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_immature.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_completed.setBackgroundResource(R.drawable.myorder_choosed_color);
                        radiobutton_completed.setTextColor(getResources().getColor(R.color.ff5181fb));
                        OrderType ="2";
                        page =1;
                        break;
                }
            }
        });
        mRecyclerView.setNestedScrollingEnabled(false);
        mLayoutManager = new LinearLayoutManager(mContext);
        orderPerDayAdapter = new MyOrderAdapter(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setFootViewText("拼命加载中","已经全部");
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page =1;
                mRecyclerView.refreshComplete();//刷新动画完成
            }

            @Override
            public void onLoadMore() {
                page++;
            }
        });
        mRecyclerView.setAdapter(orderPerDayAdapter);
        orderPerDayAdapter.setListAll(list);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0
                        , MxyUtils.dpToPx(mContext, MxyUtils.getDimens(mContext, R.dimen.dp_10))
                        , 0
                        , 0);
            }
        });
        orderPerDayAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object item, int position) {
                int Status = Integer.parseInt(list.get(position).getStatus());
                if (Status>5) {
//                    Intent intent = new Intent(mContext, .class);
//                    intent.putExtra("order_id",list.get(position).getId()+"");
//                    startActivity(intent);
                }
            }
        });
    }
}
