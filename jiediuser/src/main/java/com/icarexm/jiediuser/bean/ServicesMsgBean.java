package com.icarexm.jiediuser.bean;

public class ServicesMsgBean {

    /**
     * event : login
     * code : 200
     * msg : 登录成功
     * data : []
     */

    private String event;
    private int code;
    private String msg;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

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

}
