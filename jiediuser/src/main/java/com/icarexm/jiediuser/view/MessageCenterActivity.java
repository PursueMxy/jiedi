package com.icarexm.jiediuser.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.adapter.MessageCenterAdapter;
import com.icarexm.jiediuser.utils.MxyUtils;
import com.icarexm.jiediuser.utils.RequstUrlUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhouyou.recyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageCenterActivity extends AppCompatActivity {
    @BindView(R.id.message_center_recyclerView)
    XRecyclerView mRecyclerView;
    private List<String> list=new ArrayList<>();
    private Context mContext;
    private MessageCenterAdapter messageCenterAdapter;
    private String limit="20";
    private int page=1;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_center);
        mContext = getApplicationContext();
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        ButterKnife.bind(this);
        InitUI();
        InitData();
    }

    private void InitData() {
        OkGo.<String>post(RequstUrlUtils.URL.messageindex)
                .params("token",token)
                .params("type","0")
                .params("limit",limit)
                .params("page",page+"")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e("response",response.body());
                    }
                });
    }

    private void InitUI() {
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        messageCenterAdapter = new MessageCenterAdapter(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setFootViewText("拼命加载中","已经全部");
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mRecyclerView.refreshComplete();//刷新动画完成
            }

            @Override
            public void onLoadMore() {
                messageCenterAdapter.addItemsToLast(list);
                messageCenterAdapter .notifyDataSetChanged();
                //加载更多
                mRecyclerView.loadMoreComplete();//加载动画完成
                mRecyclerView.setNoMore(true);//数据加载完成
            }
        });
        mRecyclerView.setAdapter(messageCenterAdapter);
        messageCenterAdapter.setListAll(list);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(MxyUtils.dpToPx(mContext, MxyUtils.getDimens(mContext, R.dimen.dp_15))
                        , 0
                        ,MxyUtils.dpToPx(mContext, MxyUtils.getDimens(mContext, R.dimen.dp_15))
                        , MxyUtils.dpToPx(mContext, MxyUtils.getDimens(mContext, R.dimen.dp_20)));
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
