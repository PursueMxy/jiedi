package com.icarexm.jiediuser.view;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.adapter.MyCouponAdapter;
import com.icarexm.jiediuser.bean.CouponListBean;
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
import butterknife.OnClick;

public class MyCouponActivity extends AppCompatActivity {

    @BindView(R.id.my_coupon_radiobutton_whole)
    RadioButton radiobutton_whole;
    @BindView(R.id.my_coupon_radiobutton_out_used)
    RadioButton radiobutton_out_used;
    @BindView(R.id.my_coupon_radiobutton_already_used)
    RadioButton radiobutton_already_used;
    @BindView(R.id.my_coupon_radiobutton_expired)
    RadioButton radiobutton_expired;
    @BindView(R.id.my_coupon_radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.my_coupon_recyclerView)
    XRecyclerView mRecyclerView;

    private List<CouponListBean.DataBean.CouponBean> list=new ArrayList<>();
    private Context mContext;
    private MyCouponAdapter myCouponAdapter;
    private String token;
    private String coupon_type="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coupon);
        ButterKnife.bind(this);
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        mContext = getApplicationContext();
        InitUI();
        InitData();
    }

    private void InitData() {
        OkGo.<String>post(RequstUrlUtils.URL.couponindex)
                .params("token",token)
                .params("type","0")
                .params("coupon_type",coupon_type)
                .params("limit","50")
                .params("page","1")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new GsonBuilder().create();
                        CouponListBean couponListBean = gson.fromJson(response.body(), CouponListBean.class);
                        if (couponListBean.getCode()==200){
                            CouponListBean.DataBean data = couponListBean.getData();
                            List<CouponListBean.DataBean.CouponBean> coupon = data.getCoupon();
                            if (coupon.size()>0) {
                                list.clear();
                                list.addAll(coupon);
                                myCouponAdapter.setListAll(list);
                                myCouponAdapter.notifyDataSetChanged();
                            }else {
                                list.clear();
                                myCouponAdapter.setListAll(list);
                                myCouponAdapter.notifyDataSetChanged();
                            }

                        }
                    }
                });
    }

    private void InitUI() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.my_coupon_radiobutton_whole:
                        coupon_type="0";
                        radiobutton_whole.setTextColor(getResources().getColor(R.color.ff5181fb));
                        radiobutton_whole.setBackgroundResource(R.drawable.myorder_choosed_color);
                        radiobutton_out_used.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_out_used.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_already_used.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_already_used.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_expired.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_expired.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        InitData();
                        break;
                    case R.id.my_coupon_radiobutton_out_used:
                        coupon_type="1";
                        radiobutton_whole.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_whole.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_out_used.setTextColor(getResources().getColor(R.color.ff5181fb));
                        radiobutton_out_used.setBackgroundResource(R.drawable.myorder_choosed_color);
                        radiobutton_already_used.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_already_used.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_expired.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_expired.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        InitData();
                        break;
                    case R.id.my_coupon_radiobutton_already_used:
                        coupon_type="2";
                        radiobutton_whole.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_whole.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_out_used.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_out_used.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_already_used.setTextColor(getResources().getColor(R.color.ff5181fb));
                        radiobutton_already_used.setBackgroundResource(R.drawable.myorder_choosed_color);
                        radiobutton_expired.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_expired.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        InitData();
                        break;
                    case R.id.my_coupon_radiobutton_expired:
                        coupon_type="3";
                        radiobutton_whole.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_whole.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_out_used.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_out_used.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_already_used.setTextColor(getResources().getColor(R.color.black));
                        radiobutton_already_used.setBackgroundResource(R.drawable.myorder_nochoosed_color);
                        radiobutton_expired.setTextColor(getResources().getColor(R.color.ff5181fb));
                        radiobutton_expired.setBackgroundResource(R.drawable.myorder_choosed_color);
                        InitData();
                        break;
                }
            }
        });
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        myCouponAdapter = new MyCouponAdapter(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setFootViewText("拼命加载中","已经全部");
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mRecyclerView.refreshComplete();//刷新动画完成
            }

            @Override
            public void onLoadMore() {
                myCouponAdapter .notifyDataSetChanged();
                //加载更多
                mRecyclerView.loadMoreComplete();//加载动画完成
                mRecyclerView.setNoMore(true);//数据加载完成
            }
        });
        mRecyclerView.setAdapter(myCouponAdapter);
        myCouponAdapter.setListAll(list);
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

    @OnClick({R.id.my_coupon_img_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.my_coupon_img_back:
                finish();
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
}
