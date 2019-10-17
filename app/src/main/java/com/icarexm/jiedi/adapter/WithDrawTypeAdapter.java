package com.icarexm.jiedi.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.icarexm.jiedi.Bean.ResultBean;
import com.icarexm.jiedi.Bean.WithDrawTypeBean;
import com.icarexm.jiedi.R;
import com.icarexm.jiedi.utils.RequstUrlUtils;
import com.icarexm.jiedi.view.activity.WithDrawTypeActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

public class WithDrawTypeAdapter extends HelperRecyclerViewAdapter<WithDrawTypeBean.DataBean> {
    private final String token;
    public Context context;

    public WithDrawTypeAdapter(Context context) {
        super(context, R.layout.list_with_draw_type);
        SharedPreferences sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        this.context=context;
    }


    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, final int position, WithDrawTypeBean.DataBean item) {
        WithDrawTypeBean.DataBean data = getData(position);
       ImageView ima_type = viewHolder.getView(R.id.list_withdraw_type_img);
        TextView tv_type = viewHolder.getView(R.id.list_withdraw_type_tv_type);
        TextView tv_account = viewHolder.getView(R.id.list_withdraw_type_tv_account);
        viewHolder.getView(R.id.list_withdraw_type_btn_dlt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkGo.<String>post(RequstUrlUtils.URL.del_witgdrawal)
                        .params("token",token)
                        .params("type","1")
                        .params("id",data.getId())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                ResultBean resultBean = new GsonBuilder().create().fromJson(response.body(), ResultBean.class);
                                if (resultBean.getCode()==201){
                                    WithDrawTypeActivity.InitData();
                                }
                            }
                        });
            }
        });
        tv_account.setText(data.getWithdrawal_account());
        tv_type.setText(data.getWithdrawal_type());
        if (data.getWithdrawal_type().equals("微信")){
            ima_type.setImageResource(R.mipmap.icon_wechatpay);
        }else if (data.getWithdrawal_type().equals("支付宝")){
            ima_type.setImageResource(R.mipmap.icon_alipay);
        }else {
            ima_type.setImageResource(R.mipmap.icon_yinlian);
        }

    }
}
