package com.icarexm.jiediuser.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.adapter.InviteAdapter;
import com.icarexm.jiediuser.adapter.MessageCenterAdapter;
import com.icarexm.jiediuser.utils.MxyUtils;
import com.zhouyou.recyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InviteActivity extends AppCompatActivity {
    @BindView(R.id.invite_radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.invite_radiobutton_one)
    RadioButton radiobutton_one;
    @BindView(R.id.invite_radiobutton_two)
    RadioButton radiobutton_two;
    @BindView(R.id.invite_radiobutton_three)
    RadioButton radiobutton_three;
    @BindView(R.id.invite_listview)
    XRecyclerView mRecyclerView;
    private Context mContext;
    private List<String> list=new ArrayList<>();
    private InviteAdapter inviteAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
        InitUI();
    }

    private void InitUI() {

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.invite_radiobutton_one:
                        radiobutton_one.setBackgroundResource(R.drawable.myorder_choosed_color);
                        radiobutton_one.setTextColor(getResources().getColor(R.color.ff5181fb));
                        radiobutton_two.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_two.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_three.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_three.setTextColor(getResources().getColor(R.color.black));
                        break;
                    case R.id.invite_radiobutton_two:
                        radiobutton_one.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_one.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_two.setBackgroundResource(R.drawable.myorder_choosed_color);
                        radiobutton_two.setTextColor(getResources().getColor(R.color.ff5181fb));
                        radiobutton_three.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_three.setTextColor(getResources().getColor(R.color.black));
                        break;
                    case R.id.invite_radiobutton_three:
                        radiobutton_one.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_one.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_two.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_two.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_three.setBackgroundResource(R.drawable.myorder_choosed_color);
                        radiobutton_three.setTextColor(getResources().getColor(R.color.ff5181fb));
                        break;
                }
            }
        });
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
        inviteAdapter = new InviteAdapter(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setFootViewText("拼命加载中","已经全部");
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mRecyclerView.refreshComplete();//刷新动画完成
            }

            @Override
            public void onLoadMore() {
                inviteAdapter.addItemsToLast(list);
                inviteAdapter .notifyDataSetChanged();
                //加载更多
                mRecyclerView.loadMoreComplete();//加载动画完成
                mRecyclerView.setNoMore(true);//数据加载完成
            }
        });
        mRecyclerView.setAdapter(inviteAdapter);
        inviteAdapter.setListAll(list);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0
                        , 0
                        ,0
                        , MxyUtils.dpToPx(mContext, MxyUtils.getDimens(mContext, R.dimen.dp_2)));
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
