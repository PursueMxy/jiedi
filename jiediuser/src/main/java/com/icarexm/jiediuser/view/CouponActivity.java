package com.icarexm.jiediuser.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.adapter.MyCouponAdapter;
import com.icarexm.jiediuser.adapter.MyOrderAdapter;
import com.icarexm.jiediuser.bean.CouponListBean;
import com.icarexm.jiediuser.bean.ServicesMsgBean;
import com.icarexm.jiediuser.utils.MxyUtils;
import com.icarexm.jiediuser.utils.RequstUrlUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhouyou.recyclerview.XRecyclerView;
import com.zhouyou.recyclerview.adapter.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CouponActivity extends AppCompatActivity {

    @BindView(R.id.coupon_recyclerView)
    XRecyclerView mRecyclerView;
    private String order_id;
    private String token;
    private Context mContext;
    private MyCouponAdapter myCouponAdapter;
    private List<CouponListBean.DataBean.CouponBean> list=new ArrayList<>();
    private static int COUPON_CODE=1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        mContext = getApplicationContext();
        Intent intent = getIntent();
        order_id = intent.getStringExtra("order_id");
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        ButterKnife.bind(this);
        InitUI();
        InitData();
    }

    private void InitUI() {
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        myCouponAdapter = new MyCouponAdapter(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setFootViewText("拼命加载中","已经全部");
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mRecyclerView.loadMoreComplete();//加载动画完成
            }

            @Override
            public void onLoadMore() {
                mRecyclerView.loadMoreComplete();//加载动画完成
                mRecyclerView.setNoMore(true);//数据加载完成
            }
        });
        mRecyclerView.setAdapter( myCouponAdapter);
        myCouponAdapter.setListAll(list);
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

        myCouponAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object item, int position) {
                int id = list.get(position).getId();
                String name = list.get(position).getName();
                Intent intent = new Intent(mContext, OrderPayActivity.class);
                intent.putExtra("coupon_id",id+"");
                intent.putExtra("coupon_name",name);
                setResult(COUPON_CODE,intent);
                finish();
            }
        });
    }

    private void InitData() {
        OkGo.<String>post(RequstUrlUtils.URL.order_coupon)
                .params("token",token)
                .params("type","0")
                .params("order_id",order_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new GsonBuilder().create();
                        ServicesMsgBean servicesMsgBean = gson.fromJson(response.body(), ServicesMsgBean.class);
                        if (servicesMsgBean.getCode()==200){
                            CouponListBean couponListBean = gson.fromJson(response.body(), CouponListBean.class);
                            List<CouponListBean.DataBean.CouponBean> coupon = couponListBean.getData().getCoupon();
                            list.clear();
                            if (coupon.size()>0){
                                list.addAll(coupon);
                            }
                            myCouponAdapter.setListAll(list);
                            myCouponAdapter.notifyDataSetChanged();
                        }
                    }
                });

    }
}
