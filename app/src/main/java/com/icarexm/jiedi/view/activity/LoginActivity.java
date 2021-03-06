package com.icarexm.jiedi.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.GsonBuilder;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;
import com.icarexm.jiedi.Bean.LoginBean;
import com.icarexm.jiedi.Bean.ResultBean;
import com.icarexm.jiedi.R;
import com.icarexm.jiedi.contract.LoginContract;
import com.icarexm.jiedi.custView.CircleImageView;
import com.icarexm.jiedi.presenter.LoginPresenter;
import com.icarexm.jiedi.utils.RequstUrlUtils;
import com.icarexm.jiedi.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
//                    _ooOoo_
//                   o8888888o
//                   88" . "88
//                   (| -_- |)
//                    O\ = /O
//                ____/`---'\____
//              .   ' \\| |// `.
//               / \\||| : |||// \
//             / _||||| -:- |||||- \
//               | | \\\ - /// | |
//             | \_| ''\---/'' | |
//              \ .-\__ `-` ___/-. /
//           ___`. .' /--.--\ `. . __
//        ."" '< `.___\_<|>_/___.' >'"".
//       | | : `- \`.;`\ _ /`;.`/ - ` : | |
//         \ \ `-. \_ __\ /__ _/ .-` / /
// ======`-.____`-.___\_____/___.-`____.-'======
//                    `=---='
// .............................................
//          佛祖保佑             永无BUG
public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    @BindView(R.id.login_edt_mobile)
    EditText edt_mobile;
    @BindView(R.id.login_edt_password)
    EditText edt_password;
    @BindView(R.id.login_img_head_portrait)
    CircleImageView img_head_portrait;
    private LoginPresenter loginPresenter;
    private LoginActivity activity;
    private String mobile="";
    private String password="";
    private SharedPreferences share;
    private String avatar="";
    private String token="";
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //权限申请
        XXPermissions.with(this)
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {

                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {

                    }
                });
        ButterKnife.bind(this);
        mContext = getApplicationContext();
        share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        mobile = share.getString("mobile", "");
        password = share.getString("password", "");
        avatar = share.getString("avatar", "");
        edt_mobile.setText(mobile+"");
        edt_password.setText(password+"");
        if (avatar!=null&&!avatar.equals("")){
            Glide.with(this).load(avatar).into(img_head_portrait);
        }
        activity = LoginActivity.this;
        loginPresenter = new LoginPresenter(this);
        if (!token.equals("")){
            TokenCheck();
        }
    }

     @OnClick ({R.id.login_btn_login,R.id.login_tv_forget_psw,R.id.login_tv_logon})
     public void onViewClicked(View view){
       switch (view.getId()){
           case R.id.login_btn_login:
               mobile = edt_mobile.getText().toString();
               password = edt_password.getText().toString();
               if (!mobile.equals("")&&!password.equals("")){
                  loginPresenter.GetUserInfo(mobile, password,"1");
               }
               break;
           case R.id.login_tv_forget_psw:
               startActivity(new Intent(this,ForgetPasswordActivity.class));
               break;
           case R.id.login_tv_logon:
               startActivity(new Intent(this,LogonActivity.class));
               break;
               default:
                   break;
       }
     }

    /**
     * 获得状态栏高度
     *
     * @return
     */
    public int getStatusBarHeight()
    {
        Class<?> c = null;
        Object obj = null;
        java.lang.reflect.Field field = null;
        int x = 0;
        int statusBarHeight = 0;
        try
        {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = getResources().getDimensionPixelSize(x);
            return statusBarHeight;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    public void LoginBack( LoginBean.DataBean.UserinfoBean userinfo)
    {
         token = userinfo.getToken();
        int user_id = userinfo.getUser_id();
        String nickname = userinfo.getNickname();
        avatar = userinfo.getAvatar();
        String licenseplate = userinfo.getLicenseplate();
        SharedPreferences.Editor editor = share.edit();
        editor.putString("type","home");
        editor.putString("mobile", mobile);
        editor.putString("password", password);
        editor.putString("token",token);
        editor.putString("user_id",user_id+"");
        editor.putString("nickname",nickname);
        editor.putString("avatar",avatar);
        editor.putString("licenseplate",licenseplate);
        editor.commit();//提交
        startActivity(new Intent(this,HomeActivity.class));
    }


    @Override
    protected void onNewIntent(Intent intent) {
        OkGo.<String>get(RequstUrlUtils.URL.tokenRrfresh)
                .params("token", this.token)
                .params("type","0")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                    }
                });
        super.onNewIntent(intent);
    }


    //检查token是否过期
    public void TokenCheck(){
        OkGo.<String>get(RequstUrlUtils.URL.TokenCheck)
                .params("token",token)
                .params("type","1")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ResultBean resultBean = new GsonBuilder().create().fromJson(response.body(), ResultBean.class);
                        if (resultBean.getCode()==200){
                            startActivity(new Intent(mContext,HomeActivity.class));
                            finish();
                        }
                    }
                });
    }
}
