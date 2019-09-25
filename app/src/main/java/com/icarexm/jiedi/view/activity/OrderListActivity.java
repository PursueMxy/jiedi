package com.icarexm.jiedi.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.icarexm.jiedi.R;
import com.icarexm.jiedi.adapter.OrderPerDayAdapter;
import com.icarexm.jiedi.utils.MxyUtils;
import com.zhouyou.recyclerview.XRecyclerView;
import com.zhouyou.recyclerview.adapter.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderListActivity extends AppCompatActivity {
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
    private List<String> list=new ArrayList<>();
    private Context mContext;
    private LinearLayoutManager mLayoutManager;
    private OrderPerDayAdapter orderPerDayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        ButterKnife.bind(this);
        mContext = getApplicationContext();
        InitUI();
    }

    private void InitUI() {
        list.add("102");
        list.add("112");
        list.add("122");
        list.add("132");
        list.add("142");
        list.add("152");
        list.add("162");
        list.add("172");
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
                        break;
                    case R.id.order_list_radiobutton_immature:
                        radiobutton_whole.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_whole.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_immature.setBackgroundResource(R.drawable.myorder_choosed_color);
                        radiobutton_immature.setTextColor(getResources().getColor(R.color.ff5181fb));
                        radiobutton_completed.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_completed.setTextColor(getResources().getColor(R.color.black));
                        break;
                    case R.id.order_list_radiobutton_completed:
                        radiobutton_whole.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_whole.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_immature.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_immature.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_completed.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_completed.setTextColor(getResources().getColor(R.color.ff5181fb));
                        break;
                }
            }
        });
        mRecyclerView.setNestedScrollingEnabled(false);
        mLayoutManager = new LinearLayoutManager(mContext);
        orderPerDayAdapter = new OrderPerDayAdapter(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setFootViewText("拼命加载中","已经全部");
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mRecyclerView.refreshComplete();//刷新动画完成
            }

            @Override
            public void onLoadMore() {
                orderPerDayAdapter .addItemsToLast(list);
                orderPerDayAdapter .notifyDataSetChanged();
                //加载更多
                mRecyclerView.loadMoreComplete();//加载动画完成
                mRecyclerView.setNoMore(true);//数据加载完成
            }
        });
        mRecyclerView.setAdapter(orderPerDayAdapter );
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
                startActivity(new Intent(mContext,CostDetailActivity.class));
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
