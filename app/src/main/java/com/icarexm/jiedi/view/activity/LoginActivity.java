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
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;
import com.icarexm.jiedi.Bean.LoginBean;
import com.icarexm.jiedi.R;
import com.icarexm.jiedi.contract.LoginContract;
import com.icarexm.jiedi.presenter.LoginPresenter;
import com.icarexm.jiedi.utils.ToastUtils;

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
    ImageView img_head_portrait;
    private LoginPresenter loginPresenter;
    private LoginActivity activity;
    private String mobile="";
    private String password="";
    private SharedPreferences share;
    private String avatar="";


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
        share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
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
    public void LoginBack( LoginBean.DataBean.UserinfoBean userinfo){
        String token = userinfo.getToken();
        int user_id = userinfo.getUser_id();
        String nickname = userinfo.getNickname();
        String avatar = userinfo.getAvatar();
        SharedPreferences.Editor editor = share.edit();
        editor.putString("mobile", mobile);
        editor.putString("password", password);
        editor.putString("token",token);
        editor.putString("user_id",user_id+"");
        editor.putString("nickname",nickname);
        editor.putString("avatar",avatar);
        editor.commit();//提交
        startActivity(new Intent(this,HomeActivity.class));
    }

}
