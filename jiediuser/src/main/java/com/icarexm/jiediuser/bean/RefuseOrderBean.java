package com.icarexm.jiediuser.bean;

public class RefuseOrderBean {
    public String token;
    public String type;
    public String send;
    public String event;
    public RefuseOrderBean.data data;

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

    public RefuseOrderBean.data getData() {
        return data;
    }

    public void setData(RefuseOrderBean.data data) {
        this.data = data;
    }

    public static class  data {

    public String order_id;

    public String reason;

    public String remark;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }



        public data(String order_id, String reason, String remark) {
            this.order_id = order_id;
            this.reason = reason;
            this.remark = remark;
        }
    }


    public RefuseOrderBean(String token, String type, String send, String event, RefuseOrderBean.data data) {
        this.token = token;
        this.type = type;
        this.send = send;
        this.event = event;
        this.data = data;
    }
}
