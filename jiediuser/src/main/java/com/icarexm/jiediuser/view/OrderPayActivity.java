package com.icarexm.jiediuser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.bean.OrderDetailBean;
import com.icarexm.jiediuser.contract.OrderPayContract;
import com.icarexm.jiediuser.presenter.OrderPayPresenter;
import com.icarexm.jiediuser.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderPayActivity extends AppCompatActivity implements OrderPayContract.View {

    @BindView(R.id.order_pay_img_head)
    ImageView img_head;
    @BindView(R.id.order_pay_tv_account_money)
    TextView tv_account_money;
    @BindView(R.id.order_pay_tv_order_count)
    TextView tv_order_count;
    @BindView(R.id.order_pay_tv_licenseplate)
    TextView tv_licenseplate;
    @BindView(R.id.order_pay_tv_money)
    TextView tv_money;
    @BindView(R.id.order_pay_tv_carname)
    TextView tv_carname;
    @BindView(R.id.order_pay_img_balance)
    ImageView img_balance;
    @BindView(R.id.order_pay_img_ali)
    ImageView img_ali;
    @BindView(R.id.order_pay_img_wechat)
    ImageView img_wechat;


    private OrderPayPresenter orderPayPresenter;
    private String token;
    private String order_id;
    private Context mContext;
    private String pay_type="0";
    private String coupon_id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_pay);
        mContext = getApplicationContext();
        Intent intent = getIntent();
        order_id = intent.getStringExtra("order_id");
        ButterKnife.bind(this);
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        orderPayPresenter = new OrderPayPresenter(this);
        orderPayPresenter.GetOrderPrice(token,order_id);
    }

    @OnClick({R.id.order_pay_img_back,R.id.order_pay_tv_coupon,R.id.order_pay_btn_confirm,R.id.order_pay_rl_balancepay,R.id.order_pay_rl_alipay,R.id.order_pay_rl_wechatpay})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.order_pay_img_back:
                finish();
                break;
            case R.id.order_pay_tv_coupon:
                startActivity(new Intent(mContext,CouponActivity.class));
                break;
            case R.id.order_pay_btn_confirm:
                orderPayPresenter.GetSettlement(token,order_id,coupon_id,pay_type);
                break;
            case R.id.order_pay_rl_balancepay:
                pay_type="0";
                img_balance.setImageResource(R.mipmap.icon_cb_select);
                img_ali.setImageResource(R.mipmap.icon_cb_no);
                img_wechat.setImageResource(R.mipmap.icon_cb_no);
                break;
            case R.id.order_pay_rl_alipay:
                pay_type="1";
                img_ali.setImageResource(R.mipmap.icon_cb_select);
                img_balance.setImageResource(R.mipmap.icon_cb_no);
                img_wechat.setImageResource(R.mipmap.icon_cb_no);
                break;
            case R.id.order_pay_rl_wechatpay:
                pay_type="2";
                img_wechat.setImageResource(R.mipmap.icon_cb_select);
                img_balance.setImageResource(R.mipmap.icon_cb_no);
                img_ali.setImageResource(R.mipmap.icon_cb_no);
                break;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    public void UpdateUI(OrderDetailBean.DataBean data){
         tv_account_money.setText(data.getMoney());
         tv_money.setText(data.getMoney());
        OrderDetailBean.DataBean.DriverInfoBean driverInfo = data.getDriverInfo();
        tv_carname.setText(driverInfo.getNickname());
        tv_licenseplate.setText(driverInfo.getLicenseplate());
        tv_order_count.setText(driverInfo.getOrder_count()+"单");
        Glide.with(this).load(driverInfo.getAvatar()).into(img_head);
    }

    @Override
    public void UpdateToast(String msg,int codes) {
        ToastUtils.showToast(mContext,msg);
        if (codes==202){
            startActivity(new Intent(mContext,MyOrderActivity.class));
        }

    }
}
