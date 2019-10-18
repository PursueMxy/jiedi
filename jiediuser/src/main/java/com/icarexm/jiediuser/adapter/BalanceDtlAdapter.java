package com.icarexm.jiediuser.adapter;

import android.content.Context;
import android.view.View;
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
        super(context, R.layout.list_balance);
        this.context=context;
    }


    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, final int position, BalanceDtlBean.DataBean item) {
        BalanceDtlBean.DataBean data = getData(position);
        String time_of_appointment = data.getTime_of_appointment();
        TextView order_status = viewHolder.getView(R.id.list_balance_status);
        TextView tv_order_statusName = viewHolder.getView(R.id.list_balance_statusName);
        TextView tv_time = viewHolder.getView(R.id.list_balance_time);
        TextView tv_startpoint=viewHolder.getView(R.id.list_balance_tv_startAddress);
        TextView tv_destination = viewHolder.getView(R.id.list_balance_tv_destination);
        TextView tv_money= viewHolder.getView(R.id.list_balance_tv_money);
        TextView tv_title = viewHolder.getView(R.id.list_balance_title);
        if (data.getLog_type().equals("0")) {
            if (!time_of_appointment.equals("")) {
                order_status.setText("预约");
                tv_order_statusName.setText(time_of_appointment);
            } else {
                order_status.setText("现在");
                tv_order_statusName.setText("现在出发");
            }
            tv_time.setText(data.getTime());
            tv_startpoint.setText(data.getStartingpoint());
            tv_destination.setText(data.getDestination());
            tv_money.setText("+"+data.getMoney() );
            tv_title.setText(data.getTitle());
        }else {

        }
    }
}
