package com.icarexm.jiediuser.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.bean.ProfileBean;
import com.icarexm.jiediuser.bean.UploadBean;
import com.icarexm.jiediuser.custview.BottomDialog;
import com.icarexm.jiediuser.custview.CircleImageView;
import com.icarexm.jiediuser.custview.mywheel.MyWheelView;
import com.icarexm.jiediuser.utils.RequstUrlUtils;
import com.icarexm.jiediuser.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.winfo.photoselector.PhotoSelector;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EdtMaterialsActivity extends AppCompatActivity {

    @BindView(R.id.edt_materials_tv_gender)
    TextView tv_gender;
    @BindView(R.id.edt_materials_tv_age_group)
    TextView tv_age_group;
    @BindView(R.id.edt_materials_tv_certification)
    TextView tv_certification;
    @BindView(R.id.edt_materials_edt_bio)
    EditText edt_bio;
    @BindView(R.id.edt_materials_edt_occupation)
    EditText edt_occupation;
    @BindView(R.id.edt_materials_edt_company)
    EditText edt_company;
    @BindView(R.id.edt_materials_img_head_portrait)
    CircleImageView img_head_portrait;
    @BindView(R.id.edt_materials_edt_nickname)
    EditText edt_nickname;

    private Context mContext;
    private static final String[] PLANETS = new String[]{"男", "女"};
    private static final String[] AGEGROUP = new String[]{"20后","30后","40后","50后", "60后","70后","80后","90后","00后","10后"};
    private String genderString="男";
    private String ageGroupString="90后";
    private static final int START_CODE=10086;
    public static final int VERSO_CODE=1001;
    private String identity_card;
    private String username;
    private String uploadurl;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edt_materials);
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        ButterKnife.bind(this);
        mContext = getApplicationContext();
    }
    @OnClick({R.id.edt_materials_back,R.id.edt_materials_tv_certification,R.id.edt_materials_tv_gender
            ,R.id.edt_materials_tv_age_group,R.id.edt_materials_btn_confirm,R.id.edt_materials_img_head_portrait})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.edt_materials_back:
                finish();
                break;
            case R.id.edt_materials_tv_certification:
                Intent intent = new Intent(mContext, CertificationActivity.class);
                startActivityForResult(intent, START_CODE);
                break;
            case R.id.edt_materials_btn_confirm:
                String bio = edt_bio.getText().toString();
                String company = edt_company.getText().toString();
                String occupation = edt_occupation.getText().toString();
                String gender = tv_gender.getText().toString();
                String age_group = tv_age_group.getText().toString();
                String certification = tv_certification.getText().toString();
                String nickname = edt_nickname.getText().toString();
                if (!bio.equals("")&&!company.equals("")&&!occupation.equals("")&&!gender.equals("")&&!age_group.equals("")
                &&!uploadurl.equals("")&&!username.equals("")&&!identity_card.equals("")&&!nickname.equals("")){
                       OkGo.<String>post(RequstUrlUtils.URL.profile)
                               .params("token",token)
                               .params("avatar",uploadurl)
                               .params("nickname",nickname)
                               .params("gender",gender)
                               .params("age_group",age_group)
                               .params("company",company)
                               .params("occupation",occupation)
                               .params("bio",bio)
                               .params("username",username)
                               .params("identity_card",identity_card)
                               .execute(new StringCallback() {
                                   @Override
                                   public void onSuccess(Response<String> response) {
                                       Gson gson = new GsonBuilder().create();
                                       ProfileBean profileBean = gson.fromJson(response.body(), ProfileBean.class);
                                       if (profileBean.getCode()==200){
                                           ToastUtils.showToast(mContext,profileBean.getMsg());
                                        finish();
                                       }else {
                                           ToastUtils.showToast(mContext,profileBean.getMsg());
                                       }
                                   }
                               });


                }else {
                    ToastUtils.showToast(mContext,"请把数据填充完整");
                }
                break;
            case R.id.edt_materials_tv_gender:
                final BottomDialog bottomDialog = new BottomDialog(this, R.style.ActionSheetDialogStyle);
                View inflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_gender, null);
                MyWheelView wva = inflate.findViewById(R.id.dialog_gender_wheel_one);
                wva.setItems(Arrays.asList(PLANETS),0);
                wva.setOnItemSelectedListener(new MyWheelView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(int selectedIndex, String item) {
                        genderString=item;
                    }
                });
                inflate.findViewById(R.id.dialog_reserce_time_back).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomDialog.dismiss();
                    }
                });
                inflate.findViewById(R.id.dialog_reserce_time_confirm).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tv_gender.setText(genderString);
                        bottomDialog.dismiss();
                    }
                });
                //防止弹出两个窗口
                if (bottomDialog !=null && bottomDialog.isShowing()) {
                    return;
                }

                bottomDialog.setContentView(inflate);
                bottomDialog.show();
                break;

            case R.id.edt_materials_tv_age_group:
                final BottomDialog age_groupDialog = new BottomDialog(this, R.style.ActionSheetDialogStyle);
                View age_groupinflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_gender, null);
                MyWheelView age_groupwva = age_groupinflate.findViewById(R.id.dialog_gender_wheel_one);
                age_groupwva.setItems(Arrays.asList(AGEGROUP ),7);
                age_groupwva.setOnItemSelectedListener(new MyWheelView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(int selectedIndex, String item) {
                        ageGroupString=item;
                    }
                });
                age_groupinflate.findViewById(R.id.dialog_reserce_time_back).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        age_groupDialog.dismiss();
                    }
                });
                age_groupinflate.findViewById(R.id.dialog_reserce_time_confirm).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tv_age_group.setText(ageGroupString);
                        age_groupDialog.dismiss();
                    }
                });
                //防止弹出两个窗口
                if (age_groupDialog !=null && age_groupDialog.isShowing()) {
                    return;
                }

                age_groupDialog.setContentView(age_groupinflate);
                age_groupDialog.show();
                break;
            case R.id.edt_materials_img_head_portrait:
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {

            case VERSO_CODE:
                //获取到裁剪后的图片的Uri进行处理
                Uri resultUri1 = PhotoSelector.getCropImageUri(data);
                Glide.with(this).load(resultUri1).into(img_head_portrait);
                File file = new File(resultUri1.getPath());//实例化数据库文件
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
                                        uploadurl = uploaddata.getUrl();
                                    }
                                }catch (Exception e){

                                }
                            }
                        });
                break;
            case START_CODE:
                try{
                    identity_card = data.getStringExtra("identity_card");
                    username = data.getStringExtra("username");
                    tv_certification.setText("已完成认证");
                }catch (Exception e){

                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
