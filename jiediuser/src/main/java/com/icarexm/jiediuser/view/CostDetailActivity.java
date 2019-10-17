package com.icarexm.jiediuser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.bean.CostDetailBean;
import com.icarexm.jiediuser.contract.CostDetailContract;
import com.icarexm.jiediuser.presenter.CostDetailPresenter;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CostDetailActivity extends AppCompatActivity implements CostDetailContract.View {
    @BindView(R.id.cost_dtl_service_type)
    TextView tv_service_type;
    @BindView(R.id.cost_dtl_tv_time)
    TextView tv_time;
    @BindView(R.id.cost_dtl_tv_paymoney)
    TextView tv_paymoney;
    @BindView(R.id.cost_dtl_mileage_tv_money)
    TextView mileage_tv_money;
    @BindView(R.id.cost_dtl_mileage_tv_title)
    TextView mileage_tv_title;
    @BindView(R.id.cost_dtl_start_mileage_tv_title)
    TextView start_mileage_tv_title;
    @BindView(R.id.cost_dtl_stop_mileage_tv_title)
    TextView stop_mileage_tv_title;
    @BindView(R.id.cost_dtl_stop_mileage_tv_money)
    TextView stop_mileage_tv_money;
    @BindView(R.id.cost_dtl_duration_tv_money)
    TextView duration_tv_money;
    @BindView(R.id.cost_dtl_duration_tv_title)
    TextView duration_tv_title;
    @BindView(R.id.cost_dtl_start_duration_tv_title)
    TextView start_duration_tv_title;
    @BindView(R.id.cost_dtl_stop_duration_tv_title)
    TextView stop_duration_tv_title;
    @BindView(R.id.cost_dtl_stop_duration_tv_money)
    TextView stop_duration_tv_money;
    @BindView(R.id.cost_dtl_tv_coupon_money)
    TextView tv_coupon_money;
    @BindView(R.id.cost_dtl_tv_tatol_money)
    TextView tv_tatol_money;
    @BindView(R.id.cost_dtl_tv_meet_money)
    TextView tv_meet_money;
    private CostDetailPresenter costDetailPresenter;
    private String token;
    private String order_id;
    private double coupon_mone=0.0;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_detail);
        mContext = getApplicationContext();
        Intent intent = getIntent();
        order_id = intent.getStringExtra("order_id");
        ButterKnife.bind(this);
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        costDetailPresenter = new CostDetailPresenter(this);
        costDetailPresenter.GetOrderDetail(token,order_id);
    }

    @OnClick({R.id.cost_dtl_img_back,R.id.cost_dtl_btn_accountingRule})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.cost_dtl_img_back:
                finish();
                break;
            case R.id.cost_dtl_btn_accountingRule:
                startActivity(new Intent(mContext,AccountingRulesActivity.class));
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

    public void UpdateUI(CostDetailBean.DataBean data){
        String service_type = data.getService_type();
        if (service_type.equals("0")){
            tv_service_type.setText("城内出行");
        }else if (service_type.equals("1")){
            tv_service_type.setText("城际出行");
        }else if (service_type.equals("2")){
            tv_service_type.setText("接机");
        }else if (service_type.equals("3")){
            tv_service_type.setText("送机");
        }
        String time = data.getTime();
        tv_time.setText(time);
        if (data.getCoupon_money()!=null){
            tv_coupon_money.setText(data.getCoupon_money()+"");
            coupon_mone = (double) data.getCoupon_money();
        }
        tv_tatol_money.setText("合计"+(data.getTatol_money()+coupon_mone)+"元");
        tv_meet_money.setText("支付金额"+data.getTatol_money()+"元");
        String price_info_json = data.getPrice_info_json();
        parseJSONWithJSONObject(price_info_json);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void parseJSONWithJSONObject(String jsonData){
        try{
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i=0; i< jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("title");
                String money = jsonObject.getString("money");
                if (title.indexOf("里程费")!=-1)
                {
                    JSONArray info = jsonObject.getJSONArray("info");
                    for (int a=0;a<info.length();a++) {
                        String titles = jsonObject.getString("title");
                        String moneys= jsonObject.getString("money");
                        mileage_tv_money.setText(money);
                        mileage_tv_title.setText(title);
                        if (a==0){
                            start_mileage_tv_title.setText(titles);
                        }else if (a==1){
                            stop_mileage_tv_title.setText(titles);
                            stop_mileage_tv_money.setText(moneys);
                        }
                    }
                }else if (title.indexOf("时长费")!=-1){
                    JSONArray info = jsonObject.getJSONArray("info");
                    for (int a=0;a<info.length();a++) {
                        String titles = jsonObject.getString("title");
                        String moneys= jsonObject.getString("money");
                        duration_tv_money.setText(money);
                        duration_tv_title.setText(title);
                        if (a==0){
                            start_duration_tv_title.setText(titles);
                        }else if (a==1){
                            stop_duration_tv_title.setText(titles);
                            stop_duration_tv_money.setText(moneys);
                        }
                    }
                }else if (title.indexOf("起步价")!=-1){
                    tv_paymoney.setText(money);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
