package com.icarexm.jiediuser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.Marker;
import com.icarexm.jiediuser.R;

public class StartInfoWindow implements AMap.InfoWindowAdapter {

    private Context context;

    public StartInfoWindow(Context context) {
        this.context = context;
    }
    @Override
    public View getInfoWindow(Marker marker) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.evaluate_infowindow, null);
        return inflate;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
