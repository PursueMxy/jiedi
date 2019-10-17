package com.icarexm.jiediuser.utils;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chen.yingjie on 2019/3/23
 */
public class Config {

    public static final int TYPE_ADD = 0;
    public static final int TYPE_EDIT = 1;


    /**
     * 普通时段 : 9元
     * 00:00-09:30 : 10元
     * 17:00-19:00 : 10元
     * 23:00-00:00 : 10元
     * 包含里程 : 3公里
     * 包含时长 : 6分钟
     */

    private String 普通时段;
    @SerializedName("00:00-09:30")
    private String _$_000009300; // FIXME check this code
    @SerializedName("17:00-19:00")
    private String _$_1700190084; // FIXME check this code
    @SerializedName("23:00-00:00")
    private String _$_23000000151; // FIXME check this code
    private String 包含里程;
    private String 包含时长;

    public String get普通时段() {
        return 普通时段;
    }

    public void set普通时段(String 普通时段) {
        this.普通时段 = 普通时段;
    }

    public String get_$_000009300() {
        return _$_000009300;
    }

    public void set_$_000009300(String _$_000009300) {
        this._$_000009300 = _$_000009300;
    }

    public String get_$_1700190084() {
        return _$_1700190084;
    }

    public void set_$_1700190084(String _$_1700190084) {
        this._$_1700190084 = _$_1700190084;
    }

    public String get_$_23000000151() {
        return _$_23000000151;
    }

    public void set_$_23000000151(String _$_23000000151) {
        this._$_23000000151 = _$_23000000151;
    }

    public String get包含里程() {
        return 包含里程;
    }

    public void set包含里程(String 包含里程) {
        this.包含里程 = 包含里程;
    }

    public String get包含时长() {
        return 包含时长;
    }

    public void set包含时长(String 包含时长) {
        this.包含时长 = 包含时长;
    }
}
