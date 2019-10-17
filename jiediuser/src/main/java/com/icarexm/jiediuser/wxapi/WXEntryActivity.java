package com.icarexm.jiediuser.wxapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.icarexm.jiediuser.MyApplication;
import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.bean.WXAccessTokenInfoBean;
import com.icarexm.jiediuser.bean.WXUserInfoBean;
import com.icarexm.jiediuser.utils.AppCont;
import com.icarexm.jiediuser.utils.ToastUtils;
import com.icarexm.jiediuser.view.HomeActivity;
import com.icarexm.jiediuser.view.LoginActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private Gson mGson;
    private Context mContext;
    private String refresh_token;
    private String scope;
    private static IWXAPI wxapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);
        Intent intent = getIntent();
        mContext = getApplicationContext();
        wxapi = WXAPIFactory.createWXAPI(this, AppCont.WX_APP_ID, false);
        wxapi.registerApp(AppCont.WX_APP_ID);
        wxapi.handleIntent(getIntent(), this);
        mGson=new Gson();
    }

    public static IWXAPI InitWeiXin(Context context , @NonNull String weixin_app_id){
        if(TextUtils.isEmpty(weixin_app_id)){
//            Toast.makeText(context.getApplicationContext(),"微信appID不能为空",Toast.LENGTH_LONG).show();
        }
        wxapi = WXAPIFactory.createWXAPI(context, weixin_app_id, true);
        wxapi.registerApp(weixin_app_id);
        return wxapi;
    }

    public static  void  loginWeixin(Context context,IWXAPI api){
        if (!api.isWXAppInstalled()){
            Toast.makeText(context.getApplicationContext(),"请先安装微信客户端",Toast.LENGTH_SHORT).show();
        }
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        api.sendReq(req);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        switch (baseReq.getType()) {
            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
                break;
            default:
                break;
        }
    }

    @Override
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            // 发送成功
            case BaseResp.ErrCode.ERR_OK:
                // 获取code
                String code = ((SendAuth.Resp) resp).code;
                // 通过code获取授权口令access_token
                getAccessToken(code);
                break;
        }
    }

    public String getAccessToken(String code) {
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+ AppCont.WX_APP_ID +"&secret="+ AppCont.WX_APP_SECRET+"&code="+code+"&grant_type=authorization_code";
        String result = null;
        OkGo.<String>get(url)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        ToastUtils.showToast(mContext,body);
                        processGetAccessTokenResult(body);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        Log.e("返回用s户","失败2");
                        super.onError(response);
                    }

                });

        return result;
    }

    private void processGetAccessTokenResult(String body) {
        if (valiDateSuccess(body)){
            WXAccessTokenInfoBean tokenInfo = mGson.fromJson(body, WXAccessTokenInfoBean.class);
            refresh_token = tokenInfo.getRefresh_token();
            scope = tokenInfo.getScope();
            // 获取用户信息
            getUserInfo(tokenInfo.getAccess_token(), tokenInfo.getOpenid());
        }else {
            Log.e("返回用户","失败2");
        }
    }

    //验证是否登录成功
    private boolean valiDateSuccess(String response){
        String errFlag ="errmsg";
        Log.e("response",response);
        return errFlag.contains(response)&& "ok".equals(response)||(!"errcode".contains(response)&&!errFlag.contains(response));
    }

    //获取用户信息
    private void getUserInfo(final String access_token, final String openid) {
        String url = "https://api.weixin.qq.com/sns/userinfo?" + "access_token=" + access_token + "&openid=" + openid+"&lang=zh_CN";
        OkGo.<String>get(url)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Intent intent = new Intent(mContext, LoginActivity.class);
                        intent.putExtra("userinfo",body);
                        startActivity(intent);
//                        finish();//页面销毁
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }

                });

    }
}
