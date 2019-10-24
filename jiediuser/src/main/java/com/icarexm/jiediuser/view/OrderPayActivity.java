package com.icarexm.jiediuser.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.bean.AliPayBean;
import com.icarexm.jiediuser.bean.AuthResult;
import com.icarexm.jiediuser.bean.OrderDetailBean;
import com.icarexm.jiediuser.bean.PayResult;
import com.icarexm.jiediuser.bean.WechatPayBean;
import com.icarexm.jiediuser.contract.OrderPayContract;
import com.icarexm.jiediuser.presenter.OrderPayPresenter;
import com.icarexm.jiediuser.utils.AppCont;
import com.icarexm.jiediuser.utils.ToastUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;

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
    @BindView(R.id.order_pay_tv_coupon)
    TextView tv_coupon;
    private static final int SDK_PAY_FLAG = 1;
    private static int COUPON_CODE=1111;

    private OrderPayPresenter orderPayPresenter;
    private String token;
    private String order_id;
    private Context mContext;
    private String pay_type="0";
    private String coupon_id="";
    private String coupon_name;

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
                Intent intent = new Intent(mContext, CouponActivity.class);
                intent.putExtra("order_id",order_id);
                startActivityForResult(intent,COUPON_CODE);
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

    //调用微信支付
    public void WechatPay(WechatPayBean wechatPayBean){
        if (wechatPayBean.getCode()==200) {
            WechatPayBean.DataBean wxpayBean = wechatPayBean.getData();
            IWXAPI wxapi = WXAPIFactory.createWXAPI(mContext, AppCont.WX_APP_ID, true);
            // 将该app注册到微信
            wxapi.registerApp(AppCont.WX_APP_ID);
            if (!wxapi.isWXAppInstalled()) {
                ToastUtils.showToast(mContext, "你没有安装微信");
                return;
            }
            try {
                //我们把请求到的参数全部给微信
                PayReq req = new PayReq(); //调起微信APP的对象
                req.appId = AppCont.WX_APP_ID;
                req.partnerId = wxpayBean.getPartnerid();
                req.prepayId = wxpayBean.getPrepayid();
                req.nonceStr = wxpayBean.getNoncestr();
                req.timeStamp = wxpayBean.getTimestamp();
                req.packageValue = wxpayBean.getPackageX(); //Sign=WXPay
                req.sign = wxpayBean.getSign();
                wxapi.sendReq(req);//发送调起微信的请求
            } catch (Exception e) {
                ToastUtils.showToast(mContext, e.getMessage().toString());
            }
        }
    }


    //支付宝支付
    public void  AliPay(AliPayBean aliPayBean) {
        if (aliPayBean.getCode() == 200) {
            final Runnable payRunnable = new Runnable() {

                @Override
                public void run() {
                    String orderInfo = aliPayBean.getData();
                    PayTask alipay = new PayTask(OrderPayActivity.this);
                    Map<String, String> result = alipay.payV2(orderInfo, true);
                    Log.i("msp", result.toString());

                    Message msg = new Message();
                    msg.what = SDK_PAY_FLAG;
                    msg.obj = result;
                    mHandler.sendMessage(msg);
                }
            };
            // 必须异步调用
            Thread payThread = new Thread(payRunnable);
            payThread.start();
        }
    }
    @Override
    public void UpdateToast(String msg,int codes) {
        ToastUtils.showToast(mContext,msg);
        if (codes==202){
            startActivity(new Intent(mContext,MyOrderActivity.class));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==COUPON_CODE){
            coupon_id = data.getStringExtra("coupon_id");
            coupon_name = data.getStringExtra("coupon_name");
            tv_coupon.setText(coupon_name);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    Log.e("支付宝支付","调用成功");
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        ToastUtils.showToast(mContext,"支付成功");
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Intent intent = new Intent(mContext,MyOrderActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtils.showToast(mContext,"支付失败");
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };
}
