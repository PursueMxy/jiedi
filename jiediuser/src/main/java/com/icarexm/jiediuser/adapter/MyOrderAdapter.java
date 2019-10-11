package com.icarexm.jiediuser.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.bean.OrderListOneBean;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

public class MyOrderAdapter extends HelperRecyclerViewAdapter<OrderListOneBean.DataBean.OrderBean> {
    public Context context;

    public MyOrderAdapter(Context context) {
        super(context, R.layout.list_my_order);
        this.context=context;
    }


    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, final int position, OrderListOneBean.DataBean.OrderBean item) {
        OrderListOneBean.DataBean.OrderBean data = getData(position);
        TextView order_status = viewHolder.getView(R.id.list_home_order_status);
        TextView tv_order_statusName = viewHolder.getView(R.id.list_home_order_statusName);
        TextView tv_time = viewHolder.getView(R.id.list_home_order_time);
        tv_time.setText(data.getTime());
        if (data.getService_type().equals("0")||data.getService_type().equals("1")) {
            if (!data.getTime_of_appointment().equals("")) {
                tv_order_statusName.setText(data.getTime_of_appointment());
                order_status.setText("预约");
            } else {
                order_status.setText("现在");
                tv_order_statusName.setText("现在出发");
            }
        }else {
                tv_order_statusName.setText(data.getTime_of_appointment());
                order_status.setText("预约");
        }
        TextView tv_orderType = viewHolder.getView(R.id.list_home_order_type);
        tv_orderType.setText(data.getTitle());
        TextView tv_startAddress = viewHolder.getView(R.id.list_home_order_tv_startAddress);
        tv_startAddress.setText(data.getStartingpoint());
        TextView tv_destination = viewHolder.getView(R.id.list_home_order_tv_destination);
        tv_destination.setText(data.getDestination()+"");
        TextView tv_status = viewHolder.getView(R.id.list_home_order_tv_status);
        if (data.getStatus().equals("2")){
            tv_status.setText("司机接单");
        }else if (data.getStatus().equals("3")){
            tv_status.setText("司机到达");
        }else if (data.getStatus().equals("4")){
            tv_status.setText("乘客已上车");
        }else if (data.getStatus().equals("5")){
            tv_status.setText("到达目的地");
        }else if (data.getStatus().equals("6")){
            tv_status.setText("等待评价");
        }else if (data.getStatus().equals("7")){
            tv_status.setText("订单异常");
        }else if (data.getStatus().equals("8")){
            tv_status.setText("取消订单");
        }else if (data.getStatus().equals("9")){
            tv_status.setText("已评价");
        }else if (data.getStatus().equals("10")){
            tv_status.setText("半途取消");
        }

    }
}
