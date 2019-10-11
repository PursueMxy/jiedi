package com.icarexm.jiediuser.adapter;

import android.content.Context;

import com.icarexm.jiediuser.R;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

public class MyCouponAdapter extends HelperRecyclerViewAdapter<String> {
    public Context context;

    public MyCouponAdapter(Context context) {
        super(context, R.layout.list_my_coupon);
        this.context=context;
    }


    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, final int position, String item) {
        String data = getData(position);
    }
}
