package com.icarexm.jiedi.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.icarexm.jiedi.custView.CircleImageView;
import com.icarexm.jiedi.R;
import com.icarexm.jiedi.contract.LogonContract;
import com.icarexm.jiedi.custView.wheel.ScreenInfo;
import com.icarexm.jiedi.custView.wheel.WheelMain;
import com.icarexm.jiedi.presenter.LogonPresenter;
import com.winfo.photoselector.PhotoSelector;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogonActivity extends AppCompatActivity implements LogonContract.View {

    @BindView(R.id.logon_cb_ok)
    CheckBox cb_ok;
    @BindView(R.id.logon_edt_mobile)
    EditText edt_mobile;
    @BindView(R.id.logon_edt_password)
    EditText edt_password;
    @BindView(R.id.logon_edt_carNumber)
    EditText edt_caeNumber;
    @BindView(R.id.logon_edt_username)
    EditText edt_username;
    @BindView(R.id.logon_edt_verification_code)
    EditText edt_verification_code;
    @BindView(R.id.logon_tv_certification_time)
    TextView tv_certification_time;
    @BindView(R.id.logon_img_add_front)
    ImageView img_add_front;
    @BindView(R.id.logon_img_add_verso)
    ImageView img_add_verso;
    @BindView(R.id.logon_img_add_driver_licence)
    ImageView img_add_driver_licence;
    @BindView(R.id.logon_img_head_portrait)
    CircleImageView img_head_portrait;

    private LogonPresenter logonPresenter;
    public static final int FRONT_CODE=1001;
    public static final int VERSO_CODE=1002;
    public static final int LICENCE_CODE=1003;
    public static final int HEAD_CODE=1004;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon);
        ButterKnife.bind(this);
        mContext = getApplicationContext();
        logonPresenter = new LogonPresenter();
    }


    @OnClick({R.id.logon_btn_back,R.id.logon_btn_gain_verification_code,R.id.logon_btn_submit,R.id.logon_img_back,R.id.logon_img_add_front,
    R.id.logon_img_add_verso,R.id.logon_img_add_driver_licence,R.id.logon_tv_login,R.id.logon_tv_certification_time,R.id.logon_img_head_portrait})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.logon_btn_back:
                finish();
                break;
            case R.id.logon_btn_gain_verification_code:
                break;
            case R.id.logon_btn_submit:
                break;
            case R.id.logon_img_back:
                finish();
                break;
            case R.id.logon_img_add_front:
                //单选后剪裁 裁剪的话都是针对一张图片所以要设置setSingle(true)
                PhotoSelector.builder()
                        .setShowCamera(true)//显示拍照
                        .setSingle(true)//单选，裁剪都是单选
                        .setCrop(true)//是否裁剪
                        .setCropMode(PhotoSelector.CROP_RECTANG)//设置裁剪模式 矩形还是圆形
                        .setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent))
                        .setToolBarColor(ContextCompat.getColor(this, R.color.colorAccent))
                        .setBottomBarColor(ContextCompat.getColor(this, R.color.colorAccent))
                        .setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent))
                        .start(this,FRONT_CODE);
                break;
            case R.id.logon_img_add_verso:
                //单选后剪裁 裁剪的话都是针对一张图片所以要设置setSingle(true)
                PhotoSelector.builder()
                        .setShowCamera(true)//显示拍照
                        .setSingle(true)//单选，裁剪都是单选
                        .setCrop(true)//是否裁剪
                        .setCropMode(PhotoSelector.CROP_RECTANG)//设置裁剪模式 矩形还是圆形
                        .setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent))
                        .setToolBarColor(ContextCompat.getColor(this, R.color.colorAccent))
                        .setBottomBarColor(ContextCompat.getColor(this, R.color.colorAccent))
                        .setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent))
                        .start(this,VERSO_CODE);
                break;
            case R.id.logon_img_add_driver_licence:
                //单选后剪裁 裁剪的话都是针对一张图片所以要设置setSingle(true)
                PhotoSelector.builder()
                        .setShowCamera(true)//显示拍照
                        .setSingle(true)//单选，裁剪都是单选
                        .setCrop(true)//是否裁剪
                        .setCropMode(PhotoSelector.CROP_RECTANG)//设置裁剪模式 矩形还是圆形
                        .setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent))
                        .setToolBarColor(ContextCompat.getColor(this, R.color.colorAccent))
                        .setBottomBarColor(ContextCompat.getColor(this, R.color.colorAccent))
                        .setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent))
                        .start(this,LICENCE_CODE);
                break;
            case R.id.logon_tv_login:
                finish();
                break;
            case R.id.logon_tv_certification_time:
                View timepickerview = LayoutInflater.from(mContext).inflate(R.layout.timepicker, null);
                final WheelMain wheelMain = new WheelMain(timepickerview,false);
                ScreenInfo screenInfo = new ScreenInfo(LogonActivity.this);
                wheelMain.screenheight = screenInfo.getHeight();
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month= calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                wheelMain.initDateTimePicker(year, month, day,0,0);
                AlertDialog.Builder dialog = new AlertDialog.Builder(LogonActivity.this)
                        .setTitle("请选择日期")
                        .setView(timepickerview)
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String time = wheelMain.getDate();
                                tv_certification_time.setText(time);
                                Log.e("当前时间",time);

                            }
                        });
                dialog.show();
                break;
            case R.id.logon_img_head_portrait:
                PhotoSelector.builder()
                        .setShowCamera(true)//显示拍照
                        .setSingle(true)//单选，裁剪都是单选
                        .setCrop(true)//是否裁剪
                        .setCropMode(PhotoSelector.CROP_CIRCLE)//设置裁剪模式 矩形还是圆形
                        .setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent))
                        .setToolBarColor(ContextCompat.getColor(this, R.color.colorAccent))
                        .setBottomBarColor(ContextCompat.getColor(this, R.color.colorAccent))
                        .setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent))
                        .start(this,HEAD_CODE);
                break;
                default:
                    break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==-1){
            switch (requestCode){
                case FRONT_CODE:
                    //获取到裁剪后的图片的Uri进行处理
                    Uri resultUri = PhotoSelector.getCropImageUri(data);
                    Glide.with(this).load(resultUri).into(img_add_front);
                    break;
                case VERSO_CODE:
                    //获取到裁剪后的图片的Uri进行处理
                    Uri resultUri1 = PhotoSelector.getCropImageUri(data);
                    Glide.with(this).load(resultUri1).into(img_add_verso);
                    break;
                case LICENCE_CODE:
                    //获取到裁剪后的图片的Uri进行处理
                    Uri resultUri2 = PhotoSelector.getCropImageUri(data);
                    Glide.with(this).load(resultUri2).into(img_add_driver_licence);
                    break;
                case HEAD_CODE:
                    //获取到裁剪后的图片的Uri进行处理
                    Uri resultUri3 = PhotoSelector.getCropImageUri(data);
                    Glide.with(this).load(resultUri3.getPath()).into(img_head_portrait);
                    break;
                default:
                    break;
            }
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
