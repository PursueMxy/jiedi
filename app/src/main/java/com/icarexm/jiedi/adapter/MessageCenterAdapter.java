package com.icarexm.jiedi.adapter;

import android.content.Context;

import com.icarexm.jiedi.R;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

public class MessageCenterAdapter extends HelperRecyclerViewAdapter<String> {
    public Context context;

    public MessageCenterAdapter(Context context) {
        super(context, R.layout.list_message_center);
        this.context=context;
    }


    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, final int position, String item) {
        String data = getData(position);
    }
}
