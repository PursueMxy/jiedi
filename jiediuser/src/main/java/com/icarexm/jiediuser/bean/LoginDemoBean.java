package com.icarexm.jiediuser.bean;

public class LoginDemoBean {
    public String token;
    public String type;
    public String send;
    public String event;

    public data data;

    public static class data{
        public  String city;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public data(String city) {
            this.city = city;
        }
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
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

    public LoginDemoBean.data getData() {
        return data;
    }

    public void setData(LoginDemoBean.data data) {
        this.data = data;
    }

    public LoginDemoBean(String token, String type, String send, String event, LoginDemoBean.data data) {
        this.token = token;
        this.type = type;
        this.send = send;
        this.event = event;
        this.data = data;
    }
}
