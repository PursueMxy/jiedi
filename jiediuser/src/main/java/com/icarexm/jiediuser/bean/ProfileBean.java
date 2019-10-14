package com.icarexm.jiediuser.bean;

import java.util.List;

public class ProfileBean {

    /**
     * code : 100
     * msg : 身份证号有误
     * time : 1571055758
     * data : []
     */

    private int code;
    private String msg;
    private String time;
    private List<?> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
