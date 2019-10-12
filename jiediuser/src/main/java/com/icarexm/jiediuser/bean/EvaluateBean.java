package com.icarexm.jiediuser.bean;

import java.util.List;

public class EvaluateBean {

    /**
     * code : 201
     * msg : 评价成功
     * time : 1570844717
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
