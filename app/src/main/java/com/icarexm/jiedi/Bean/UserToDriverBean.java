package com.icarexm.jiedi.Bean;

public class UserToDriverBean {
    public String token;
    public String type;
    public String send;
    public String event;

    public data data;
    public static class data{
        public String order_id;
        public String msg;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public data(String order_id,String msg) {
            this.order_id = order_id;
            this.msg = msg;
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public UserToDriverBean.data getData() {
        return data;
    }

    public void setData(UserToDriverBean.data data) {
        this.data = data;
    }

    public UserToDriverBean(String token, String type, String send, String event, UserToDriverBean.data data) {
        this.token = token;
        this.type = type;
        this.send = send;
        this.event = event;
        this.data = data;
    }
}
