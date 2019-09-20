package com.icarexm.jiedi.Bean;

public class LoginDemoBean {
    public String token;
    public String type;
    public String send;
    public String event;



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

    public LoginDemoBean(String token, String type, String send, String event) {
        this.token = token;
        this.type = type;
        this.send = send;
        this.event = event;
    }
}
