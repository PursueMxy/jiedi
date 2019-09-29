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

import com.icarexm.jiedi.R;
import com.icarexm.jiedi.adapter.CashoutAdapter;
import com.icarexm.jiedi.adapter.WithDrawTypeAdapter;
import com.icarexm.jiedi.utils.MxyUtils;
import com.zhouyou.recyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WithDrawTypeActivity extends AppCompatActivity {
    @BindView(R.id.with_draw_type_recyclerView)
    XRecyclerView mRecyclerView;

    private List<String> list=new ArrayList<>();
    private Context mContext;
    private WithDrawTypeAdapter withDrawTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_draw_type);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
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
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        withDrawTypeAdapter = new WithDrawTypeAdapter(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setFootViewText("拼命加载中","已经全部");
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mRecyclerView.refreshComplete();//刷新动画完成
            }

            @Override
            public void onLoadMore() {
                withDrawTypeAdapter .addItemsToLast(list);
                withDrawTypeAdapter .notifyDataSetChanged();
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
    }

    @OnClick({R.id.with_draw_type_img_back,R.id.with_draw_type_tv_add})
    public void  onViewClick(View view){
        switch (view.getId()){
            case R.id.with_draw_type_img_back:
                finish();
                break;
            case R.id.with_draw_type_tv_add:
                startActivity(new Intent(mContext,AddWithdrawActivity.class));
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
