package com.icarexm.jiedi.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.google.gson.GsonBuilder;
import com.icarexm.jiedi.Bean.CashOutBean;
import com.icarexm.jiedi.R;
import com.icarexm.jiedi.adapter.CashoutAdapter;
import com.icarexm.jiedi.utils.MxyUtils;
import com.icarexm.jiedi.utils.RequstUrlUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhouyou.recyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CashOutActivity extends AppCompatActivity {

    @BindView(R.id.cashout_recyclerView)
    XRecyclerView mRecyclerView;

    private List<CashOutBean.DataBean> list=new ArrayList<>();
    private Context mContext;
    private CashoutAdapter cashoutAdapter;
    private String token;
    private String limit="50";
    private int page=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_out);
        mContext = getApplicationContext();
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        ButterKnife.bind(this);
        InitUI();
        InitData();
    }
    @OnClick({R.id.cashout_img_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.cashout_img_back:
                finish();
                break;
        }
    }

    private void InitData() {
        OkGo.<String>post(RequstUrlUtils.URL.money_log)
                .params("token", token)
                .params("type","1")
                .params("select_time","")
                .params("limit",limit)
                .params("page",page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        CashOutBean balanceDtlBean = new GsonBuilder().create().fromJson(response.body(), CashOutBean.class);
                        if (balanceDtlBean.getCode()==200){
                            List<CashOutBean.DataBean> data = balanceDtlBean.getData();
                            cashoutAdapter .addItemsToLast(data);
                            cashoutAdapter .notifyDataSetChanged();
                        }
                    }
                });
    }

    private void InitUI() {
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager   mLayoutManager = new LinearLayoutManager(mContext);
        cashoutAdapter = new CashoutAdapter(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setFootViewText("拼命加载中","已经全部");
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mRecyclerView.refreshComplete();//刷新动画完成
            }

            @Override
            public void onLoadMore() {
                cashoutAdapter .addItemsToLast(list);
                cashoutAdapter .notifyDataSetChanged();
                //加载更多
                mRecyclerView.loadMoreComplete();//加载动画完成
                mRecyclerView.setNoMore(true);//数据加载完成
            }
        });
        mRecyclerView.setAdapter(cashoutAdapter);
        cashoutAdapter.setListAll(list);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0
                        , 0
                        , 0
                        , MxyUtils.dpToPx(mContext, MxyUtils.getDimens(mContext, R.dimen.dp_10)));
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
