package com.icarexm.jiediuser.adapter;

import android.content.Context;
import android.widget.TextView;

import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.bean.BalanceDtlBean;
import com.icarexm.jiediuser.bean.LoginDemoBean;
import com.icarexm.jiediuser.bean.OrderListOneBean;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

public class BalanceDtlAdapter extends HelperRecyclerViewAdapter<BalanceDtlBean.DataBean> {
    public Context context;

    public BalanceDtlAdapter(Context context) {
        super(context, R.layout.list_my_order);
        this.context=context;
    }


    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, final int position, BalanceDtlBean.DataBean item) {
        BalanceDtlBean.DataBean data = getData(position);
        TextView order_status = viewHolder.getView(R.id.list_home_order_status);
        TextView tv_order_statusName = viewHolder.getView(R.id.list_home_order_statusName);

    }
}
