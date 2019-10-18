package com.icarexm.jiediuser.adapter;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.bean.CouponListBean;
import com.icarexm.jiediuser.utils.DateUtils;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

public class MyCouponAdapter extends HelperRecyclerViewAdapter<CouponListBean.DataBean.CouponBean> {
    public Context context;

    public MyCouponAdapter(Context context) {
        super(context, R.layout.list_my_coupon);
        this.context=context;
    }


    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, final int position, CouponListBean.DataBean.CouponBean item) {
        CouponListBean.DataBean.CouponBean data = getData(position);
        RelativeLayout my_coupon_rl = viewHolder.getView(R.id.list_my_coupon_rl);
        TextView tv_price = viewHolder.getView(R.id.my_coupon_tv_price);
        TextView tv_price_name = viewHolder.getView(R.id.my_coupon_tv_price_name);
        TextView tv_timeend = viewHolder.getView(R.id.my_coupon_tv_timeend);
        TextView tv_price_limit = viewHolder.getView(R.id.my_coupon_tv_price_limit);
        float aFloat = Float.valueOf(data.getMoney());
        long money=(long)aFloat;
        tv_price.setText(money+"");
        int use_start_time = data.getUse_start_time();
        int use_end_time = data.getUse_end_time();
        String start_time = DateUtils.getDateToString(use_start_time);
        String end_time = DateUtils.getDateToString(use_end_time);
        tv_timeend.setText("使用期限："+start_time+"~"+end_time);
        tv_price_limit.setText(data.getName());
        tv_price_name.setText(money+"元优惠券");
        if (data.getStatus()==0){
            my_coupon_rl.setBackgroundResource(R.mipmap.icon_coupon_blue);
        }else {
            my_coupon_rl.setBackgroundResource(R.mipmap.icon_coupon_gray);
        }


    }
}
