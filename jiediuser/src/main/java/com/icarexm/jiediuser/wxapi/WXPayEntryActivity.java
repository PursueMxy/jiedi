package com.icarexm.jiediuser.wxapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.utils.AppCont;
import com.icarexm.jiediuser.view.PriceActivity;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private Context mContext;
    private IWXAPI msgApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxpay_entry);
        mContext = getApplicationContext();
        msgApi = WXAPIFactory.createWXAPI(this, AppCont.WX_APP_ID);
        msgApi.registerApp(AppCont.WX_APP_ID);
        msgApi.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            int code = baseResp.errCode;
            switch (code) {
                case 0:
                    Log.e("微信支付", "支付成功");
                    Intent intent = new Intent(WXPayEntryActivity.this, PriceActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case -1:
                    finish();
                    // 支付失败 可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等
                    Log.e("微信支付", "支付失败");
                    Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();
                    break;
                case -2:
//                    finish();
                    Log.e("微信支付", "支付取消");
                    Toast.makeText(mContext, "支付取消", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
