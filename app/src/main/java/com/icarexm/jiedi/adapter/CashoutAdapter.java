package com.icarexm.jiedi.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.icarexm.jiedi.Bean.CashOutBean;
import com.icarexm.jiedi.R;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

public class CashoutAdapter extends HelperRecyclerViewAdapter<CashOutBean.DataBean> {
    public Context context;

    public CashoutAdapter(Context context) {
        super(context, R.layout.list_cashout);
        this.context=context;
    }


    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, final int position,CashOutBean.DataBean dataBean) {
        CashOutBean.DataBean data = getData(position);
       TextView tv_title = viewHolder.getView(R.id.list_cashout_tv_title);
       TextView tv_time = viewHolder.getView(R.id.list_cashout_tv_time);
       TextView tv_money = viewHolder.getView(R.id.list_cashout_tv_money);
       tv_money.setText(data.getMoney()+"");
       tv_time.setText(data.getTime());
       tv_title.setText(data.getTitle());
    }
}
