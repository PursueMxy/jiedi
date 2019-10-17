package com.icarexm.jiediuser.bean;

public class MemberIndexBean {

    /**
     * code : 200
     * msg : ok
     * time : 1571126627
     * data : {"money":"967.68","coipon_count":0,"recharge_amount":"[10,100,200]"}
     */

    private int code;
    private String msg;
    private String time;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * money : 967.68
         * coipon_count : 0
         * recharge_amount : [10,100,200]
         */

        private String money;
        private int coipon_count;
        private String recharge_amount;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getCoipon_count() {
            return coipon_count;
        }

        public void setCoipon_count(int coipon_count) {
            this.coipon_count = coipon_count;
        }

        public String getRecharge_amount() {
            return recharge_amount;
        }

        public void setRecharge_amount(String recharge_amount) {
            this.recharge_amount = recharge_amount;
        }
    }
}
