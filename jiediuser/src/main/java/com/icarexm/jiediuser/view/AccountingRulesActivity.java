package com.icarexm.jiediuser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.bean.AccountingRulesBean;
import com.icarexm.jiediuser.contract.AccountingRulesContract;
import com.icarexm.jiediuser.presenter.AccountingRulesPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountingRulesActivity extends AppCompatActivity implements AccountingRulesContract.View {

    @BindView(R.id.accounting_rules_list)
    ListView list;
    @BindView(R.id.accounting_rules_tv_common_title)
    TextView tv_common_title;
    @BindView(R.id.accounting_rules_tv_one_connect)
    TextView tv_one_connect;
    @BindView(R.id.accounting_rules_tv_two_connect)
    TextView tv_two_connect;
    @BindView(R.id.accounting_rules_tv_three_connect)
    TextView tv_three_connect;
    @BindView(R.id.accounting_rules_tv_four_connect)
    TextView tv_four_connect;
    @BindView(R.id.accounting_rules_tv_five_connect)
    TextView tv_five_connect;
    @BindView(R.id.accounting_rules_tv_six_connect)
    TextView tv_six_connect;
    @BindView(R.id.accounting_rules_tv_mileage_title)
    TextView tv_mileage_title;
    @BindView(R.id.accounting_rules_tv_mileage_one_content)
    TextView tv_mileage_one_content;
    @BindView(R.id.accounting_rules_tv_mileage_two_content)
    TextView tv_mileage_two_content;
    @BindView(R.id.accounting_rules_tv_mileage_three_content)
    TextView tv_mileage_three_content;
    @BindView(R.id.accounting_rules_tv_duration_title)
    TextView tv_duration_title;
    @BindView(R.id.accounting_rules_tv_duration_one_content)
    TextView tv_duration_one_content;
    @BindView(R.id.accounting_rules_tv_duration_two_content)
    TextView tv_duration_two_content;
    @BindView(R.id.accounting_rules_tv_toll_title)
    TextView tv_toll_title;
    @BindView(R.id.accounting_rules_tv_toll_one_content)
    TextView tv_toll_one_content;
    @BindView(R.id.accounting_rules_tv_toll_two_content)
    TextView tv_toll_two_content;
    @BindView(R.id.accounting_rules_tv_overTime_title)
    TextView tv_overTime_title;
    @BindView(R.id.accounting_rules_tv_overTime_one_content)
    TextView tv_overTime_one_content;
    @BindView(R.id.accounting_rules_tv_overTime_two_content)
    TextView tv_overTime_two_content;


    private AccountingRulesPresenter accountingRulesPresenter;
    private List<AccountingRulesBean.DataBean.PriceRuleBean> price_rule=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounting_rules);
        ButterKnife.bind(this);
        accountingRulesPresenter = new AccountingRulesPresenter(this);
        accountingRulesPresenter.GetAccountingRules();
    }
    @OnClick({R.id.accounting_rules_img_back})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.accounting_rules_img_back:
                finish();
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

    public void UpdateUI(AccountingRulesBean.DataBean data) {
        price_rule = data.getPrice_rule();
        for (int a = 0; a < price_rule.size(); a++) {
            try {
                JSONObject jsonObject = new JSONObject(price_rule.get(a).getDesriptionjson());
                String s = jsonObject.toString();
                Log.e("第" + a+ "条", s);
                switch (a) {
                    case 0:
                        String jsonOne = jsonObject.getString("普通时段");
                        String jsonTwo = jsonObject.getString("00:00-09:30");
                        String jsonThree = jsonObject.getString("17:00-19:00");
                        String jsonFour = jsonObject.getString("23:00-00:00");
                        String jsonFive = jsonObject.getString("包含里程");
                        String jsonSix = jsonObject.getString("包含时长");
                        tv_common_title.setText(price_rule.get(a).getTitle());
                        tv_one_connect.setText(jsonOne);
                        tv_two_connect.setText(jsonTwo);
                        tv_three_connect.setText(jsonThree);
                        tv_four_connect.setText(jsonFour);
                        tv_five_connect.setText(jsonFive);
                        tv_six_connect.setText(jsonSix);
                        break;
                    case 1:
                        String mileageOne = jsonObject.getString("普通时段");
                        String mileageTwo = jsonObject.getString("00:00-05:00");
                        String mileageThree = jsonObject.getString("23:00-00:00");
                        tv_mileage_title.setText(price_rule.get(a).getTitle());
                        tv_mileage_one_content.setText(mileageOne);
                        tv_mileage_two_content.setText(mileageTwo);
                        tv_mileage_three_content.setText(mileageThree);
                        break;
                    case 2:
                        String durationOne = jsonObject.getString("普通时段");
                        String durationTwo = jsonObject.getString("05:00-09:30");
                        tv_duration_one_content.setText(durationOne);
                        tv_duration_two_content.setText(durationTwo);
                        tv_duration_title.setText(price_rule.get(a).getTitle());
                        break;
                    case 3:
                        String tollOne = jsonObject.getString("超出10公里-25公里");
                        String tollTwo = jsonObject.getString("超出25公里后");
                        tv_toll_title.setText(price_rule.get(a).getTitle());
                        tv_toll_one_content.setText(tollOne);
                        tv_toll_two_content.setText(tollTwo);
                        break;
                        case 4:
                        String overTimeOne = jsonObject.getString("普通时段");
                        String overTimeTwo = jsonObject.getString("05:00-09:30");
                        tv_overTime_title.setText(price_rule.get(a).getTitle());
                        tv_overTime_one_content.setText(overTimeOne);
                        tv_overTime_two_content.setText(overTimeTwo);
                        break;

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return price_rule.size();
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
            View inflate = getLayoutInflater().inflate(R.layout.list_accounting_rules, null);
            TextView tv_title = inflate.findViewById(R.id.list_accounting_rules_tv_title);
            TextView tv_money = inflate.findViewById(R.id.list_accounting_rules_tv_money);
            TextView tv_mileage_title = inflate.findViewById(R.id.list_accounting_rules_tv_mileage_title);
            TextView tv_mileage_money= inflate.findViewById(R.id.list_accounting_rules_tv_mileage_money);
            TextView tv_duration_title = inflate.findViewById(R.id.list_accounting_rules_tv_duration_title);
            TextView tv_duration_money= inflate.findViewById(R.id.list_accounting_rules_tv_duration_money);
            tv_title.setText(price_rule.get(i).getTitle());
            tv_mileage_title.setText(price_rule.get(i).getDesriptionjson());
            try {
                JSONObject jsonObject = new JSONObject(price_rule.get(i).getDesriptionjson());
                String s = jsonObject.toString();
                Log.e("第"+i+"条",s);
            switch (i){
                case 0:
//                  JSONObject jsonObject = new JSONObject(price_rule.get(i).getDesriptionjson());
//                  String s = jsonObject.toString();
                    String jsonOne= jsonObject.getString("普通时段");
                    String jsonTwo= jsonObject.getString("00:00-09:30");
                    String jsonThree= jsonObject.getString("17:00-19:00");
                    String jsonFour= jsonObject.getString("23:00-00:00");
                    String jsonFive= jsonObject.getString("包含里程");
                    String jsonSix= jsonObject.getString("包含时长");
                    break;
            }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return inflate;
        }
    }
}
