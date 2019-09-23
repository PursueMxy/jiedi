package com.icarexm.jiedi.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.icarexm.jiedi.R;
import com.icarexm.jiedi.adapter.HomeAdapter;
import com.icarexm.jiedi.custView.KeepCountdownView;
import com.icarexm.jiedi.utils.MxyUtils;
import com.icarexm.jiedi.utils.ToastUtils;
import com.zhouyou.recyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.home_recyclerView)
    XRecyclerView mRecyclerView;
    @BindView(R.id.home_btn_gainorder)
    Button btn_gainorder;
    private List<String> list=new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    private Context mContext;
    private HomeAdapter homeAdapter;
    private AlertDialog alertDialog;
    private View dialog_home;
    private KeepCountdownView countDownProgressBar;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
        dialog_home = getLayoutInflater().inflate(R.layout.dialog_home, null);
        countDownProgressBar = dialog_home.findViewById(R.id.dialog_home_countDownProgress);
        countDownProgressBar.plus(10);
        countDownProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToast(mContext,"完成了");
            }
        });
        //倒计时监听
        countDownProgressBar.setCountdownListener(new KeepCountdownView.CountdownListener() {
            @Override
            public void onStart() {
                Log.e("home","开始");
            }

            @Override
            public void onEnd() {
                Log.e("home","结束");
            }
        });
        mRecyclerView.setNestedScrollingEnabled(false);
        mLayoutManager = new LinearLayoutManager(mContext);
        homeAdapter = new HomeAdapter(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setFootViewText("拼命加载中","已经全部");
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mRecyclerView.refreshComplete();//刷新动画完成
            }

            @Override
            public void onLoadMore() {
                homeAdapter .addItemsToLast(list);
                homeAdapter .notifyDataSetChanged();
                //加载更多
                mRecyclerView.loadMoreComplete();//加载动画完成
               mRecyclerView.setNoMore(true);//数据加载完成
            }
        });
        mRecyclerView.setAdapter(homeAdapter );
        homeAdapter.setListAll(list);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0
                        , 0
                        , 0
                        , MxyUtils.dpToPx(mContext, MxyUtils.getDimens(mContext, R.dimen.dp_24)));
            }
        });
    }


    @OnClick({R.id.home_btn_gainorder,R.id.home_img_order_per_day})
    public void onViewClicked(View view){
        switch (view.getId()) {
            case R.id.home_btn_gainorder:
                String btncontent = btn_gainorder.getText().toString();
                if (btncontent.equals("接单")){
                    btn_gainorder.setText("停止接单");
                    btn_gainorder.setBackground(getResources().getDrawable(R.drawable.btn_back));
                    ShowDialog();
                }else {
                    btn_gainorder.setText("接单");
                    btn_gainorder.setBackground(getResources().getDrawable(R.drawable.btn_login));
                    alertDialog.dismiss();
                }
                break;
            case R.id.home_img_order_per_day:
//                startActivity(new Intent(mContext,OrderPerDayActivity.class));
                startActivity(new Intent(mContext,MainActivity.class));
                break;
                default:
                    break;
        }
        }

        public void ShowDialog(){
            builder = new AlertDialog.Builder(HomeActivity.this);
            if (alertDialog==null) {
                alertDialog = builder.setView(dialog_home)
                        .create();
                alertDialog.show();
            }else {
                alertDialog.show();
            }
            countDownProgressBar.startCountDown();
        }



}
