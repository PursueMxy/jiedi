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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icarexm.jiedi.Bean.RegisterBean;
import com.icarexm.jiedi.Bean.UploadBean;
import com.icarexm.jiedi.custView.CircleImageView;
import com.icarexm.jiedi.R;
import com.icarexm.jiedi.contract.LogonContract;
import com.icarexm.jiedi.custView.wheel.ScreenInfo;
import com.icarexm.jiedi.custView.wheel.WheelMain;
import com.icarexm.jiedi.presenter.LogonPresenter;
import com.icarexm.jiedi.utils.RequstUrlUtils;
import com.icarexm.jiedi.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.winfo.photoselector.PhotoSelector;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
    private String mobile="";
    private String IDcarimageurl="";
    private String IDcablackrimageurl="";
    private String driverimageurl="";
    private String avatarurl="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon);
        ButterKnife.bind(this);
        mContext = getApplicationContext();
        logonPresenter = new LogonPresenter(this);
    }


    @OnClick({R.id.logon_btn_back,R.id.logon_btn_gain_verification_code,R.id.logon_btn_submit,R.id.logon_img_back,R.id.logon_img_add_front,
    R.id.logon_img_add_verso,R.id.logon_img_add_driver_licence,R.id.logon_tv_login,R.id.logon_tv_certification_time,R.id.logon_img_head_portrait
    })
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.logon_btn_back:
                finish();
                break;
            case R.id.logon_btn_gain_verification_code:
                mobile = edt_mobile.getText().toString();
                if (mobile.length()==11){
                    logonPresenter.GetMobileCode(mobile,"driver_register","1");
                }
                break;
            case R.id.logon_btn_submit:
                mobile = edt_mobile.getText().toString();
                String password = edt_password.getText().toString();
                String verification_code = edt_verification_code.getText().toString();
                String username = edt_username.getText().toString();
                String carNumber = edt_caeNumber.getText().toString();
                String certification_time = tv_certification_time.getText().toString();
                 if (!password.equals("")&&!verification_code.equals("")&&!username.equals("")&&!carNumber.equals("")
                 &&!certification_time.equals("")&&!driverimageurl.equals("")&&!mobile.equals("")&&!avatarurl.equals("")
                 &&!IDcarimageurl.equals("")&&!IDcablackrimageurl.equals("")){
                     ToastUtils.showToast(mContext,"数据上传中");
                OkGo.<String>post(RequstUrlUtils.URL.register)
                        .params("type","1")
                        .params("mobile",mobile)
                        .params("password",password)
                        .params("event","driver_register")
                        .params("captcha",verification_code)
                        .params("nickname",username)
                        .params("licenseplate",carNumber)
                        .params("avatar",avatarurl)
                        .params("gender","1")
                        .params("driverimage",driverimageurl)
                        .params("IDcarimage",IDcarimageurl)
                        .params("IDcablackrimage",IDcablackrimageurl)
                        .params("driving_license_time",certification_time)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Log.e("注册事件",response.body());
                                Gson gson = new GsonBuilder().create();
                                RegisterBean registerBean = gson.fromJson(response.body(), RegisterBean.class);
                            }
                        });
            }else {
                     ToastUtils.showToast(mContext,"数据不全");
            }

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
                    File file = new File(resultUri.getPath());//实例化数据库文件
                    List<File> fileList = new ArrayList<File>();//添加到arraylist里面
                    fileList.add(file);
                    OkGo.<String>post(RequstUrlUtils.URL.upload)
                            .addFileParams("file",fileList)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    Gson gson = new GsonBuilder().create();
                                    try {

                                        UploadBean uploadBean = gson.fromJson(response.body(), UploadBean.class);
                                        if (uploadBean.getCode() == 200) {
                                            UploadBean.DataBean uploaddata = uploadBean.getData();
                                            IDcarimageurl = uploaddata.getUrl();
                                        }
                                    }catch (Exception e){

                                    }
                                }
                            });
                    break;
                case VERSO_CODE:
                    //获取到裁剪后的图片的Uri进行处理
                    Uri resultUri1 = PhotoSelector.getCropImageUri(data);
                    Glide.with(this).load(resultUri1).into(img_add_verso);
                    File file1 = new File(resultUri1.getPath());//实例化数据库文件
                    List<File> fileList1 = new ArrayList<File>();//添加到arraylist里面
                    fileList1.add(file1);
                    OkGo.<String>post(RequstUrlUtils.URL.upload)
                            .addFileParams("file",fileList1)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    Gson gson = new GsonBuilder().create();
                                    try {

                                        UploadBean uploadBean = gson.fromJson(response.body(), UploadBean.class);
                                        if (uploadBean.getCode() == 200) {
                                            UploadBean.DataBean uploaddata = uploadBean.getData();
                                            IDcablackrimageurl = uploaddata.getUrl();
                                        }
                                    }catch (Exception e){

                                    }
                                }
                            });
                    break;
                case LICENCE_CODE:
                    //获取到裁剪后的图片的Uri进行处理
                    Uri resultUri2 = PhotoSelector.getCropImageUri(data);
                    Glide.with(this).load(resultUri2).into(img_add_driver_licence);
                    File file2 = new File(resultUri2.getPath());//实例化数据库文件
                    List<File> fileList2= new ArrayList<File>();//添加到arraylist里面
                    fileList2.add(file2);
                    OkGo.<String>post(RequstUrlUtils.URL.upload)
                            .addFileParams("file",fileList2)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    Gson gson = new GsonBuilder().create();
                                    try {

                                        UploadBean uploadBean = gson.fromJson(response.body(), UploadBean.class);
                                        if (uploadBean.getCode() == 200) {
                                            UploadBean.DataBean uploaddata = uploadBean.getData();
                                            driverimageurl = uploaddata.getUrl();
                                        }
                                    }catch (Exception e){

                                    }
                                }
                            });
                    break;
                case HEAD_CODE:
                    //获取到裁剪后的图片的Uri进行处理
                    Uri resultUri3 = PhotoSelector.getCropImageUri(data);
                    Glide.with(this).load(resultUri3.getPath()).into(img_head_portrait);
                    File file3 = new File(resultUri3.getPath());//实例化数据库文件
                    List<File> fileList3= new ArrayList<File>();//添加到arraylist里面
                    fileList3.add(file3);
                    OkGo.<String>post(RequstUrlUtils.URL.upload)
                            .addFileParams("file",fileList3)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    Gson gson = new GsonBuilder().create();
                                    try {

                                        UploadBean uploadBean = gson.fromJson(response.body(), UploadBean.class);
                                        if (uploadBean.getCode() == 200) {
                                            UploadBean.DataBean uploaddata = uploadBean.getData();
                                            avatarurl = uploaddata.getUrl();
                                        }
                                    }catch (Exception e){

                                    }
                                }
                            });
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
