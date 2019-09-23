package com.icarexm.jiedi.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.icarexm.jiedi.R;
import com.icarexm.jiedi.adapter.HomeAdapter;
import com.icarexm.jiedi.adapter.OrderPerDayAdapter;
import com.icarexm.jiedi.custView.wheel.ScreenInfo;
import com.icarexm.jiedi.custView.wheel.WheelMain;
import com.icarexm.jiedi.utils.MxyUtils;
import com.zhouyou.recyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderPerDayActivity extends AppCompatActivity {

    @BindView(R.id.order_per_day_recyclerView)
    XRecyclerView mRecyclerView;
    @BindView(R.id.order_per_day_tv_time)
    TextView tv_time;
    private List<String> list=new ArrayList<>();
    private Context mContext;
    private LinearLayoutManager mLayoutManager;
    private OrderPerDayAdapter orderPerDayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_per_day);
        ButterKnife.bind(this);
        mContext = getApplicationContext();
        InitUI();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
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
    }

    @OnClick({R.id.order_per_day_tv_time,R.id.order_per_day_img_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.order_per_day_tv_time:
                View timepickerview = LayoutInflater.from(mContext).inflate(R.layout.timepicker, null);
                final WheelMain wheelMain = new WheelMain(timepickerview,false);
                ScreenInfo screenInfo = new ScreenInfo(OrderPerDayActivity.this);
                wheelMain.screenheight = screenInfo.getHeight();
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month= calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                wheelMain.initDateTimePicker(year, month, day,0,0);
                AlertDialog.Builder dialog = new AlertDialog.Builder(OrderPerDayActivity.this)
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
                                Log.e("当前时间",time);

                            }
                        });
                dialog.show();
                break;
            case R.id.order_per_day_img_back:
                finish();
                break;
                default:
                    break;

        }
    }
}
