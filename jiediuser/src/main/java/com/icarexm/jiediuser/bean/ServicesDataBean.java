package com.icarexm.jiediuser.bean;

public class ServicesDataBean {

    /**
     * event : place_order
     * code : 200
     * msg : 订单发布成功
     * data : {"order_id":"164"}
     */

    private String event;
    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * order_id : 164
         */

        private String order_id;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }
    }
}
