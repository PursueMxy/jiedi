package com.icarexm.jiediuser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.view.PoiInputItemWidget;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.icarexm.jiediuser.R;
import com.icarexm.jiediuser.adapter.SearchResultAdapter;
import com.icarexm.jiediuser.contract.LoginContract;
import com.icarexm.jiediuser.custview.PopupU;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchPoiActivity extends AppCompatActivity implements TextWatcher,
        Inputtips.InputtipsListener, AdapterView.OnItemClickListener, View.OnTouchListener, PoiSearch.OnPoiSearchListener{
  @BindView(R.id.input_tips_tv_cityName)
    TextView tv_cityName;
  @BindView(R.id.search_input)
    AutoCompleteTextView mKeywordText;
  @BindView(R.id.resultList)
    ListView resultList;
  @BindView(R.id.tv_msg)
  TextView tvMsg;
  @BindView(R.id.search_loading)
  ProgressBar loadingBar;

    private int INOUT_TIPS_CODE=6699;
    private List<Tip> mCurrentTipList;
    private SearchResultAdapter resultAdapter;
    private Poi selectedPoi;
    private String city = "厦门市";
    private int pointType=-1;
    private String type;
    private int mType;
    //省
    private String selectedProvince;
    //市
    private String selectedCity;
    //区
    private String selectedArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_poi);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        city = intent.getStringExtra("city");
        type = intent.getStringExtra("type");
        resultList.setOnItemClickListener(this);
        resultList.setOnTouchListener(this);
        tvMsg.setVisibility(View.GONE);
        mKeywordText.addTextChangedListener(this);
        mKeywordText.requestFocus();
        Bundle bundle = getIntent().getExtras();
        pointType = bundle.getInt("pointType", -1);
    }
    @OnClick({R.id.input_tips_tv_cityName})
    public void onViewClick(View view){
        switch (view.getId()) {
            case R.id.input_tips_tv_cityName:
                PopupU.showRegionView(SearchPoiActivity.this, mType, selectedProvince, selectedCity, selectedArea, new PopupU.OnRegionListener() {
                    @Override
                    public void onRegionListener(String province, String citys, String area) {
                        // 选择完回调结果赋值给当前
                        selectedProvince = province;
                        selectedCity = citys;
                        selectedArea = area;
                        tv_cityName.setText(citys);
                        city = citys;
                    }
                });
                break;
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        try {
            {
                if (tvMsg.getVisibility() == View.VISIBLE) {
                    tvMsg.setVisibility(View.GONE);
                }
                String newText = s.toString().trim();
                if (!TextUtils.isEmpty(newText)) {
                    setLoadingVisible(true);
                    InputtipsQuery inputquery = new InputtipsQuery(newText, city);
                    Inputtips inputTips = new Inputtips(getApplicationContext(), inputquery);
                    inputTips.setInputtipsListener(this);
                    inputTips.requestInputtipsAsyn();
                } else {
                    resultList.setVisibility(View.GONE);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void setLoadingVisible(boolean isVisible) {
        if (isVisible) {
            loadingBar.setVisibility(View.VISIBLE);
        } else {
            loadingBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //点击提示后再次进行搜索，获取POI出入口信息
        if (mCurrentTipList != null) {
            Tip tip = (Tip) parent.getItemAtPosition(position);
            selectedPoi = new Poi(tip.getName(), new LatLng(tip.getPoint().getLatitude(), tip.getPoint().getLongitude()), tip.getPoiID());
            if (!TextUtils.isEmpty(selectedPoi.getPoiId())) {
                PoiSearch.Query query = new PoiSearch.Query(selectedPoi.getName(), "", city);
                query.setDistanceSort(false);
                query.requireSubPois(true);
                PoiSearch poiSearch = new PoiSearch(getApplicationContext(), query);
                poiSearch.setOnPoiSearchListener(this);
                poiSearch.searchPOIIdAsyn(selectedPoi.getPoiId());
            }
        }
    }

    @Override
    public void onGetInputtips(List<Tip> tipList, int rCode) {
        setLoadingVisible(false);
        try {
            if (rCode == 1000) {
                Log.e("数据长度",tipList.size()+"条");
                mCurrentTipList = new ArrayList<Tip>();
                for (Tip tip : tipList) {
                    if (null == tip.getPoint()) {
                        continue;
                    }
                    mCurrentTipList.add(tip);
                }

                if (null == mCurrentTipList || mCurrentTipList.isEmpty()) {
                    tvMsg.setText("抱歉，没有搜索到结果，请换个关键词试试");
                    tvMsg.setVisibility(View.VISIBLE);
                    resultList.setVisibility(View.GONE);
                } else {
                    resultList.setVisibility(View.VISIBLE);
                    resultAdapter = new SearchResultAdapter(getApplicationContext(), mCurrentTipList);
                    resultList.setAdapter(resultAdapter);
                    resultAdapter.notifyDataSetChanged();
                }
            } else {
                tvMsg.setText("出错了，请稍后重试");
                tvMsg.setVisibility(View.VISIBLE);
            }
        } catch (Throwable e) {
            tvMsg.setText("出错了，请稍后重试");
            tvMsg.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {

    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int errorCode) {
        try {
            LatLng latLng = null;
            int code = 0;
            if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
                if (poiItem == null) {
                    return;
                }
                LatLonPoint exitP = poiItem.getExit();
                LatLonPoint enterP = poiItem.getEnter();
                if (pointType == PoiInputItemWidget.TYPE_START) {
                    code = 100;
                    if (exitP != null) {
                        latLng = new LatLng(exitP.getLatitude(), exitP.getLongitude());
                    } else {
                        if (enterP != null) {
                            latLng = new LatLng(enterP.getLatitude(), enterP.getLongitude());
                        }
                    }
                }
                if (pointType == PoiInputItemWidget.TYPE_DEST) {
                    code = 200;
                    if (enterP != null) {
                        latLng = new LatLng(enterP.getLatitude(), enterP.getLongitude());
                    }
                }
            }
            Poi poi;
            if (latLng != null) {
                poi = new Poi(selectedPoi.getName(), latLng, selectedPoi.getPoiId());
            } else {
                poi = selectedPoi;
            }
            LatLng coordinate = poi.getCoordinate();
            Intent intent = new Intent(this,HomeActivity.class);
            intent.putExtra("tip", poi.getName());
            intent.putExtra("type",type);
            intent.putExtra("latitude",""+coordinate.latitude);
            intent.putExtra("longitude",""+coordinate.longitude);
            intent.putExtra("poiID",poi.getPoiId());
            setResult(INOUT_TIPS_CODE,intent);
            Log.e("POIID",poi.getPoiId()+"");
            finish();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
