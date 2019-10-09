package com.icarexm.jiediuser.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.adapter.BalanceDtlAdapter;
import com.icarexm.jiediuser.bean.OrderListOneBean;
import com.icarexm.jiediuser.utils.MxyUtils;
import com.zhouyou.recyclerview.XRecyclerView;
import com.zhouyou.recyclerview.adapter.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BalanceDetailActivity extends AppCompatActivity {

    @BindView(R.id.balance_dtl_tv_time)
    TextView tv_time;
    @BindView(R.id.balance_dtl_recyclerView)
    XRecyclerView mRecyclerView;
    private Context mContext;
    private LinearLayoutManager mLayoutManager;
    private BalanceDtlAdapter balanceDtlAdapter;
    private List<OrderListOneBean.DataBean.OrderBean> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_detail);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
        InitUI();
    }

    private void InitUI() {
        mRecyclerView.setNestedScrollingEnabled(false);
        mLayoutManager = new LinearLayoutManager(mContext);
        balanceDtlAdapter = new BalanceDtlAdapter(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setFootViewText("拼命加载中","已经全部");
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mRecyclerView.refreshComplete();//刷新动画完成
            }

            @Override
            public void onLoadMore() {
            }
        });
        mRecyclerView.setAdapter(balanceDtlAdapter);
        balanceDtlAdapter.setListAll(list);
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
        balanceDtlAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
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
