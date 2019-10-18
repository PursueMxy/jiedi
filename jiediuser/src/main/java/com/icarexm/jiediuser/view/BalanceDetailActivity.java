package com.icarexm.jiediuser.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.adapter.BalanceDtlAdapter;
import com.icarexm.jiediuser.bean.BalanceDtlBean;
import com.icarexm.jiediuser.bean.OrderListOneBean;
import com.icarexm.jiediuser.contract.BalanceDetailContract;
import com.icarexm.jiediuser.custview.wheel.ScreenInfo;
import com.icarexm.jiediuser.custview.wheel.WheelMain;
import com.icarexm.jiediuser.presenter.BalanceDetailPresenter;
import com.icarexm.jiediuser.utils.DateUtils;
import com.icarexm.jiediuser.utils.MxyUtils;
import com.zhouyou.recyclerview.XRecyclerView;
import com.zhouyou.recyclerview.adapter.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BalanceDetailActivity extends AppCompatActivity implements BalanceDetailContract.View {

    @BindView(R.id.balance_dtl_tv_time)
    TextView tv_time;
    @BindView(R.id.balance_dtl_recyclerView)
    XRecyclerView mRecyclerView;
    private Context mContext;
    private LinearLayoutManager mLayoutManager;
    private BalanceDtlAdapter balanceDtlAdapter;
    private List<BalanceDtlBean.DataBean> list=new ArrayList<>();
    private BalanceDetailPresenter balanceDetailPresenter;
    private String token;
    private String OrderTime;
    private String limit="50";
    private int page=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_detail);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        balanceDetailPresenter = new BalanceDetailPresenter(this);
        OrderTime = DateUtils.Todays();
        tv_time.setText(OrderTime);
        InitUI();
        balanceDetailPresenter.GetBalanceDtl(token,OrderTime,limit,page+"");
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
                balanceDetailPresenter.GetBalanceDtl(token,OrderTime,limit,page+"");
                mRecyclerView.refreshComplete();//刷新动画完成
            }

            @Override
            public void onLoadMore() {
                mRecyclerView.refreshComplete();//刷新动画完成
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
            }
        });
    }

    @OnClick({R.id.balance_dtl_img_back,R.id.balance_dtl_tv_time})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.balance_dtl_img_back:
                finish();
                break;
            case R.id.balance_dtl_tv_time:
                View timepickerview = LayoutInflater.from(mContext).inflate(R.layout.timepicker, null);
                final WheelMain wheelMain = new WheelMain(timepickerview,false);
                ScreenInfo screenInfo = new ScreenInfo(BalanceDetailActivity.this);
                wheelMain.screenheight = screenInfo.getHeight();
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month= calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                wheelMain.initDateTimePicker(year, month, day,0,0);
                AlertDialog.Builder dialog = new AlertDialog.Builder(BalanceDetailActivity.this)
                        .setTitle("请选择日期")
                        .setView(timepickerview)
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String time = wheelMain.getDate();
                                tv_time.setText(time);
                                OrderTime=time;
                                page=1;
                                balanceDetailPresenter.GetBalanceDtl(token,OrderTime,limit,page+"");
                            }
                        });
                dialog.show();
                break;
        }
    }

    public void  UpdateList(List<BalanceDtlBean.DataBean> data){
          list.addAll(data);
        if (data!=null){
            if (page>1){
                balanceDtlAdapter.addItemsToLast(data);
                balanceDtlAdapter.notifyDataSetChanged();
            }else {
                list.clear();
                list.addAll(data);
                balanceDtlAdapter.setListAll(list);
                balanceDtlAdapter.notifyDataSetChanged();
            }
        }else {
            page=1;
            list.clear();
            balanceDtlAdapter.setListAll(list);
            balanceDtlAdapter.notifyDataSetChanged();
            mRecyclerView.setNoMore(true);//数据加载完成
        }
        //加载更多
        mRecyclerView.loadMoreComplete();//加载动画完成
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
