package com.icarexm.jiedi.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.google.gson.GsonBuilder;
import com.icarexm.jiedi.Bean.ResultBean;
import com.icarexm.jiedi.Bean.WithDrawTypeBean;
import com.icarexm.jiedi.R;
import com.icarexm.jiedi.adapter.CashoutAdapter;
import com.icarexm.jiedi.adapter.WithDrawTypeAdapter;
import com.icarexm.jiedi.utils.MxyUtils;
import com.icarexm.jiedi.utils.RequstUrlUtils;
import com.icarexm.jiedi.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhouyou.recyclerview.XRecyclerView;
import com.zhouyou.recyclerview.adapter.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WithDrawTypeActivity extends AppCompatActivity {
    @BindView(R.id.with_draw_type_recyclerView)
    XRecyclerView mRecyclerView;

    private static List<WithDrawTypeBean.DataBean> list=new ArrayList<>();
    private Context mContext;
    private static WithDrawTypeAdapter withDrawTypeAdapter;
    private static String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_draw_type);
        mContext = getApplicationContext();
        list.clear();
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        ButterKnife.bind(this);
        InitUI();
        InitData();
    }

    public static void InitData() {
        OkGo.<String>post(RequstUrlUtils.URL.getWithdrawal)
                .params("token",token)
                .params("type","1")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        WithDrawTypeBean withDrawTypeBean = new GsonBuilder().create().fromJson(response.body(), WithDrawTypeBean.class);
                        if (withDrawTypeBean.getCode()==200){
                            List<WithDrawTypeBean.DataBean> data = withDrawTypeBean.getData();
                            list.clear();
                            list.addAll(data);
                            withDrawTypeAdapter.setListAll(list);
                            withDrawTypeAdapter .notifyDataSetChanged();
                        }
                    }
                });
    }

    private void InitUI() {
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        withDrawTypeAdapter = new WithDrawTypeAdapter(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setFootViewText("拼命加载中","已经全部");
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list.clear();
                InitData();
                mRecyclerView.refreshComplete();//刷新动画完成
            }

            @Override
            public void onLoadMore() {
//                withDrawTypeAdapter .addItemsToLast(list);
//                withDrawTypeAdapter .notifyDataSetChanged();
                //加载更多
                mRecyclerView.loadMoreComplete();//加载动画完成
                mRecyclerView.setNoMore(true);//数据加载完成
            }
        });
        mRecyclerView.setAdapter(withDrawTypeAdapter);
        withDrawTypeAdapter.setListAll(list);
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
        withDrawTypeAdapter.setOnItemLongClickListener(new BaseRecyclerViewAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, Object item, int position) {
                OkGo.<String>post(RequstUrlUtils.URL.set_withdrawal)
                        .params("token",token)
                        .params("type","1")
                        .params("id",list.get(position).getId())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                ResultBean resultBean = new GsonBuilder().create().fromJson(response.body(), ResultBean.class);
                                if (resultBean.getCode()==201){
                                    ToastUtils.showToast(mContext,resultBean.getMsg());
                                }
                            }
                        });
            }
        });
    }

    @OnClick({R.id.with_draw_type_img_back,R.id.with_draw_type_tv_add})
    public void  onViewClick(View view){
        switch (view.getId()){
            case R.id.with_draw_type_img_back:
                finish();
                break;
            case R.id.with_draw_type_tv_add:
                startActivity(new Intent(mContext,AddWithdrawActivity.class));
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==event.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
