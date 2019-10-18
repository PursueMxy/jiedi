package com.icarexm.jiediuser.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.adapter.MyOrderAdapter;
import com.icarexm.jiediuser.bean.OrderListOneBean;
import com.icarexm.jiediuser.contract.MyOrderContract;
import com.icarexm.jiediuser.custview.wheel.ScreenInfo;
import com.icarexm.jiediuser.custview.wheel.WheelMain;
import com.icarexm.jiediuser.presenter.MyOrderPresenter;
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

public class MyOrderActivity extends AppCompatActivity implements MyOrderContract.View {
    @BindView(R.id.myorder_radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.myorder_radiobutton_whole)
    RadioButton radiobutton_whole;
    @BindView(R.id.myorder_radiobutton_completed)
    RadioButton radiobutton_completed;
    @BindView(R.id.myorder_radiobutton_immature)
    RadioButton radiobutton_immature;
    @BindView(R.id.myorder_recyclerView)
    XRecyclerView mRecyclerView;
    @BindView(R.id.myorder_tv_time)
    TextView tv_time;
    private Context mContext;
    private LinearLayoutManager mLayoutManager;
    private SharedPreferences sp;
    private String OrderTime="";
    private String OrderType="0";
    private int page=1;
    private String limit="10";
    private MyOrderAdapter orderPerDayAdapter;
    private List<OrderListOneBean.DataBean.OrderBean> list=new ArrayList<>();
    private MyOrderPresenter myOrderPresenter;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);
        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        mContext=getApplicationContext();
        OrderTime = DateUtils.Todays();
        tv_time.setText(OrderTime);
        myOrderPresenter = new MyOrderPresenter(this);
        InitUI();

    }

    @OnClick({R.id.myorder_img_back,R.id.myorder_tv_time})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.myorder_img_back:
                finish();
                break;
            case R.id.myorder_tv_time:
                View timepickerview = LayoutInflater.from(mContext).inflate(R.layout.timepicker, null);
                final WheelMain wheelMain = new WheelMain(timepickerview,false);
                ScreenInfo screenInfo = new ScreenInfo(MyOrderActivity.this);
                wheelMain.screenheight = screenInfo.getHeight();
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month= calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                wheelMain.initDateTimePicker(year, month, day,0,0);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MyOrderActivity.this)
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
                                myOrderPresenter.GetOrderList(token,OrderType,OrderTime,limit,page+"");
                            }
                        });
                dialog.show();
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

    private void InitUI() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.myorder_radiobutton_whole:
                        radiobutton_whole.setBackgroundResource(R.drawable.myorder_choosed_color);
                        radiobutton_whole.setTextColor(getResources().getColor(R.color.ff5181fb));
                        radiobutton_immature.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_immature.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_completed.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_completed.setTextColor(getResources().getColor(R.color.black));
                        OrderType ="0";
                        page =1;
                        myOrderPresenter.GetOrderList(token,OrderType,OrderTime,limit,page+"");
                        break;
                    case R.id.myorder_radiobutton_immature:
                        radiobutton_whole.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_whole.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_immature.setBackgroundResource(R.drawable.myorder_choosed_color);
                        radiobutton_immature.setTextColor(getResources().getColor(R.color.ff5181fb));
                        radiobutton_completed.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_completed.setTextColor(getResources().getColor(R.color.black));
                        OrderType ="1";
                        page =1;
                        myOrderPresenter.GetOrderList(token,OrderType,OrderTime,limit,page+"");
                        break;
                    case R.id.myorder_radiobutton_completed:
                        radiobutton_whole.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_whole.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_immature.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_immature.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_completed.setBackgroundResource(R.drawable.myorder_choosed_color);
                        radiobutton_completed.setTextColor(getResources().getColor(R.color.ff5181fb));
                        OrderType ="2";
                        page =1;
                        myOrderPresenter.GetOrderList(token,OrderType,OrderTime,limit,page+"");
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
                myOrderPresenter.GetOrderList(token,OrderType,OrderTime,limit,page+"");
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
                if (Status==5) {
                    Intent intent = new Intent(mContext, OrderPayActivity.class);
                    intent.putExtra("order_id",list.get(position).getId()+"");
                    startActivity(intent);
                }else if (Status==6){
                    Intent intent = new Intent(mContext, EvaluateActivity.class);
                    intent.putExtra("order_id",list.get(position).getId()+"");
                    intent.putExtra("status",list.get(position).getStatus());
                    startActivity(intent);
                }
            }
        });
        myOrderPresenter.GetOrderList(token,OrderType,OrderTime,limit,page+"");
    }

    public void UpdateOrderList(List<OrderListOneBean.DataBean.OrderBean> order) {
        if (order.size()>0){
            if (page>1){
                orderPerDayAdapter .addItemsToLast(order);
                orderPerDayAdapter .notifyDataSetChanged();
            }else {
                list.clear();
                list.addAll(order);
                orderPerDayAdapter.setListAll(list);
                orderPerDayAdapter .notifyDataSetChanged();
            }
        }else {
            page=1;
            mRecyclerView.setNoMore(true);//数据加载完成
        }
        //加载更多
        mRecyclerView.loadMoreComplete();//加载动画完成
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
