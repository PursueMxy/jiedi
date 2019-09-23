package com.icarexm.jiedi.adapter;

import android.content.Context;

import com.icarexm.jiedi.R;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

public class OrderPerDayAdapter extends HelperRecyclerViewAdapter<String> {
    public Context context;

    public OrderPerDayAdapter(Context context) {
        super(context, R.layout.list_order_per_day);
        this.context=context;
    }


    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, final int position, String item) {
        String data = getData(position);
    }
}
