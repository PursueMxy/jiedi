package com.icarexm.jiedi.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.icarexm.jiedi.Bean.OrderListBean;
import com.icarexm.jiedi.R;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

public class HomeAdapter extends HelperRecyclerViewAdapter<OrderListBean.DataBean.OrderBean> {
    public Context context;

    public HomeAdapter(Context context) {
        super(context, R.layout.list_home);
        this.context=context;
    }


    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, final int position, OrderListBean.DataBean.OrderBean  item) {
        OrderListBean.DataBean.OrderBean data = getData(position);
        int id = data.getId();
        TextView tv_order_type = viewHolder.getView(R.id.list_home_order_type);
        tv_order_type.setText(data.getTitle());
        TextView tv_order_time = viewHolder.getView(R.id.list_home_order_time);
        tv_order_time.setText(data.getTime());
         TextView tv_startingpoint= viewHolder.getView(R.id.list_home_order_startingpoint);
         tv_startingpoint.setText(data.getStartingpoint());
         TextView tv_destination = viewHolder.getView(R.id.list_home_order_destination);
         tv_destination.setText(data.getDestination());
        TextView tv_paymoney = viewHolder.getView(R.id.list_home_order_paymoney);
        tv_paymoney.setText(data.getPaymoney()+"");


    }
}
