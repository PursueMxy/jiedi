package com.icarexm.jiedi.view.activity;

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

import com.icarexm.jiedi.Bean.OrderListOneBean;
import com.icarexm.jiedi.R;
import com.icarexm.jiedi.adapter.OrderPerDayAdapter;
import com.icarexm.jiedi.contract.OrderListContract;
import com.icarexm.jiedi.custView.wheel.ScreenInfo;
import com.icarexm.jiedi.custView.wheel.WheelMain;
import com.icarexm.jiedi.presenter.OrderListPresenter;
import com.icarexm.jiedi.utils.DateUtils;
import com.icarexm.jiedi.utils.MxyUtils;
import com.zhouyou.recyclerview.XRecyclerView;
import com.zhouyou.recyclerview.adapter.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderListActivity extends AppCompatActivity implements OrderListContract.View {
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
    private OrderPerDayAdapter orderPerDayAdapter;
    private OrderListPresenter orderListPresenter;
    private SharedPreferences sp;
    private String token;
    private String user_id;
    private String OrderType="0";
    private String OrderTime="";
    private String limit="20";
    private int page=1;
    private List<OrderListOneBean.DataBean.OrderBean> list=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        ButterKnife.bind(this);
        mContext = getApplicationContext();
        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        user_id = sp.getString("user_id", "");
        OrderTime = DateUtils.Todays();
        InitUI();
        orderListPresenter = new OrderListPresenter(this);
        orderListPresenter.GetOrderList(token,OrderType,OrderTime,limit,page+"");
    }
    @OnClick({R.id.order_list_tv_time,R.id.order_list_img_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.order_list_tv_time:
                View timepickerview = LayoutInflater.from(mContext).inflate(R.layout.timepicker, null);
                final WheelMain wheelMain = new WheelMain(timepickerview,false);
                ScreenInfo screenInfo = new ScreenInfo(OrderListActivity.this);
                wheelMain.screenheight = screenInfo.getHeight();
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month= calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                wheelMain.initDateTimePicker(year, month, day,0,0);
                AlertDialog.Builder dialog = new AlertDialog.Builder(OrderListActivity.this)
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
                                orderListPresenter.GetOrderList(token,OrderType,OrderTime,limit,page+"");
                            }
                        });
                dialog.show();
                break;
            case R.id.order_list_img_back:
                finish();
                break;
            default:
                break;

        }
    }
    private void InitUI() {
        tv_time.setText(OrderTime);
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
                        OrderType="0";
                        page=1;
                        orderListPresenter.GetOrderList(token,OrderType,OrderTime,limit,page+"");
                        break;
                    case R.id.order_list_radiobutton_immature:
                        radiobutton_whole.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_whole.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_immature.setBackgroundResource(R.drawable.myorder_choosed_color);
                        radiobutton_immature.setTextColor(getResources().getColor(R.color.ff5181fb));
                        radiobutton_completed.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_completed.setTextColor(getResources().getColor(R.color.black));
                        OrderType="1";
                        page=1;
                        orderListPresenter.GetOrderList(token,OrderType,OrderTime,limit,page+"");
                        break;
                    case R.id.order_list_radiobutton_completed:
                        radiobutton_whole.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_whole.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_immature.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_immature.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_completed.setBackgroundResource(R.drawable.myorder_choosed_color);
                        radiobutton_completed.setTextColor(getResources().getColor(R.color.ff5181fb));
                        OrderType="2";
                        page=1;
                        orderListPresenter.GetOrderList(token,OrderType,OrderTime,limit,page+"");
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
                page=1;
                orderListPresenter.GetOrderList(token,OrderType,OrderTime,limit,page+"");
                mRecyclerView.refreshComplete();//刷新动画完成
            }

            @Override
            public void onLoadMore() {
                page++;
                orderListPresenter.GetOrderList(token,OrderType,OrderTime,limit,page+"");
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
                try {
                    int Status = Integer.parseInt(list.get(position).getStatus());
                    if (Status ==6) {
                        Intent intent = new Intent(mContext, EvaluateActivity.class);
                        intent.putExtra("order_id", list.get(position).getId() + "");
                        startActivity(intent);
                    }
                }catch (Exception e){}
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        orderListPresenter.GetOrderList(token,OrderType,OrderTime,limit,page+"");
        super.onNewIntent(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void UpdateOrderList(OrderListOneBean.DataBean data) {
        List<OrderListOneBean.DataBean.OrderBean>  order = data.getOrder();
        if (order.size()>0){
             if (page>1){
                 orderPerDayAdapter .addItemsToLast(list);
                 orderPerDayAdapter .notifyDataSetChanged();
             }else {
                 list.clear();
                 list.addAll(order);
                 orderPerDayAdapter.setListAll(list);
                 orderPerDayAdapter .notifyDataSetChanged();
             }
        }else {
            if (page==1){
                list.clear();
                orderPerDayAdapter.setListAll(list);
                orderPerDayAdapter .notifyDataSetChanged();
            }
            page=1;
            mRecyclerView.setNoMore(true);//数据加载完成
        }

        //加载更多
        mRecyclerView.loadMoreComplete();//加载动画完成
    }
}
