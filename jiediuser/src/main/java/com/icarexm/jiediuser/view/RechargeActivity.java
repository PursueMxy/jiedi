package com.icarexm.jiediuser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.bean.AliPayBean;
import com.icarexm.jiediuser.bean.AuthResult;
import com.icarexm.jiediuser.bean.MemberIndexBean;
import com.icarexm.jiediuser.bean.PayResult;
import com.icarexm.jiediuser.bean.WechatPayBean;
import com.icarexm.jiediuser.utils.AppCont;
import com.icarexm.jiediuser.utils.RequstUrlUtils;
import com.icarexm.jiediuser.utils.StringUtil;
import com.icarexm.jiediuser.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RechargeActivity extends AppCompatActivity {
    @BindView(R.id.recharge_gridview)
    GridView recharge_gridview;

    private List<Map<String, Object>> dataList;
    private GridAdapter gridAdapter;
    private int SLT_TYPE=0;
    private String SLT_PRICE="10";
    private String token;
    private String[] strings=new String[]{};
    private Context mContext;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        mContext = getApplicationContext();
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        ButterKnife.bind(this);
        InitData();
        InitUI();
    }

    @OnClick({R.id.recharge_btn_wechatPay,R.id.recharge_btn_aliPay,R.id.recharge_img_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.recharge_img_back:
                finish();
                break;
            case R.id.recharge_btn_wechatPay:
                OkGo.<String>post(RequstUrlUtils.URL.memberrecharge)
                        .params("token",token)
                        .params("money","0.01")
                        .params("pay_type","wechat")
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Gson gson = new GsonBuilder().create();
                                WechatPayBean wechatPayBean = gson.fromJson(response.body(), WechatPayBean.class);
                                if (wechatPayBean.getCode()==200){
                                    WechatPayBean.DataBean wxpayBean= wechatPayBean.getData();
                                    IWXAPI wxapi = WXAPIFactory.createWXAPI(mContext, AppCont.WX_APP_ID, true);
                                    // 将该app注册到微信
                                    wxapi.registerApp(AppCont.WX_APP_ID);
                                    if (!wxapi.isWXAppInstalled()) {
                                      ToastUtils.showToast(mContext,"你没有安装微信");
                                        return;
                                    }
                                    try {
                                        //我们把请求到的参数全部给微信
                                        PayReq req = new PayReq(); //调起微信APP的对象
                                        req.appId = AppCont.WX_APP_ID;
                                        req.partnerId =wxpayBean.getPartnerid();
                                        req.prepayId = wxpayBean.getPrepayid();
                                        req.nonceStr = wxpayBean.getNoncestr();
                                        req.timeStamp = wxpayBean.getTimestamp();
                                        req.packageValue = wxpayBean.getPackageX(); //Sign=WXPay
                                        req.sign =wxpayBean.getSign();
                                        wxapi.sendReq(req);//发送调起微信的请求
                                    }catch (Exception e){
                                   ToastUtils.showToast(mContext,e.getMessage().toString());
                                    }
                                }
                            }
                        });
                break;
            case R.id.recharge_btn_aliPay:
                OkGo.<String>post(RequstUrlUtils.URL.memberrecharge)
                        .params("token",token)
                        .params("money","0.01")
                        .params("pay_type","alipay")
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Gson gson = new GsonBuilder().create();
                                AliPayBean aliPayBean = gson.fromJson(response.body(), AliPayBean.class);
                                if (aliPayBean.getCode()==200) {
                                    final Runnable payRunnable = new Runnable() {

                                        @Override
                                        public void run() {
                                            String orderInfo = aliPayBean.getData();
                                            PayTask alipay = new PayTask(RechargeActivity.this);
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
                        });
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

    private void InitData() {
        OkGo.<String>post(RequstUrlUtils.URL.memberindex)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new GsonBuilder().create();
                        MemberIndexBean memberIndexBean = gson.fromJson(response.body(), MemberIndexBean.class);
                        if (memberIndexBean.getCode()==200){
                            MemberIndexBean.DataBean data = memberIndexBean.getData();
                            if (data!=null){
                                String recharge_amount = data.getRecharge_amount();
                                String substring = recharge_amount.substring(1, recharge_amount.length() - 1);
                                strings = StringUtil.splitString(substring, ",");
                                gridAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
    }

    private void InitUI() {
        String[] from={"text"};
        int[] to={R.id.text};
        gridAdapter = new GridAdapter();
        recharge_gridview.setAdapter( gridAdapter);
        recharge_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SLT_TYPE=i;
                SLT_PRICE=strings[i];
                gridAdapter.notifyDataSetChanged();
            }
        });
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
                        Intent intent = new Intent(mContext, PriceActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                       ToastUtils.showToast(mContext,"支付失败");
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();
                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        showAlert(RechargeActivity.this, getString(R.string.auth_success) + authResult);
                    } else {
                        // 其他状态值则为授权失败
                        showAlert(RechargeActivity.this, getString(R.string.auth_failed) + authResult);
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };


    private static void showAlert(Context ctx, String info) {
        showAlert(ctx, info, null);
    }

    private static void showAlert(Context ctx, String info, DialogInterface.OnDismissListener onDismiss) {
        new AlertDialog.Builder(ctx)
                .setMessage(info)
                .setPositiveButton(R.string.confirm, null)
                .setOnDismissListener(onDismiss)
                .show();
    }

    public class GridAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return strings.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = getLayoutInflater().inflate(R.layout.recharge_gridview, null);
            TextView tv_name = inflate.findViewById(R.id.recharge_gridview_tv_name);
            tv_name.setText(strings[i]+"元");
            if (i==SLT_TYPE){
                tv_name.setTextColor(getResources().getColor(R.color.white));
                tv_name.setBackgroundResource(R.drawable.bg_blue);
            }else {
                tv_name.setTextColor(getResources().getColor(R.color.ced6e2));
                tv_name.setBackgroundResource(R.drawable.bg_gary);
            }
            return inflate;
        }
    }
}
