package com.icarexm.jiediuser.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.bean.MessageBean;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

public class MessageCenterAdapter extends HelperRecyclerViewAdapter<MessageBean.DataBean.ListBean> {
    public Context context;

    public MessageCenterAdapter(Context context) {
        super(context, R.layout.list_message_center);
        this.context=context;
    }


    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, final int position,MessageBean.DataBean.ListBean item) {
        MessageBean.DataBean.ListBean data = getData(position);
        TextView tv_title = viewHolder.getView(R.id.list_message_center_title);
        tv_title.setText(data.getTitle());
        TextView tv_content = viewHolder.getView(R.id.list_message_center_tv_content);
        tv_content.setText(data.getContent());
        TextView tv_time = viewHolder.getView(R.id.list_message_center_tv_time);
        tv_time.setText(data.getCreatetime());
        if (data.getLink()!=null) {
            TextView tv_link = viewHolder.getView(R.id.list_message_center_tv_link);
            tv_link.setText(data.getLink() + "");
        }

    }
}
