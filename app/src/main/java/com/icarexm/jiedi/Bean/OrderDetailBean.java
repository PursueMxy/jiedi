package com.icarexm.jiedi.Bean;

public class OrderDetailBean {


    /**
     * code : 200
     * msg :
     * time : 1569572131
     * data : {"id":107,"service_type":"0","createtime":1566986210,"paymoney":"10.00","price_info_json":"[{\"title\":\"\\u8d77\\u6b65\\u4ef7\",\"money\":\"10\\u5143\"},{\"title\":\"\\u91cc\\u7a0b\\u8d39(0.023\\u516c\\u91cc)\",\"money\":\"10\\u5143\",\"info\":[{\"title\":\"\\u8d77\\u6b65\\u91cc\\u7a0b(3\\u516c\\u91cc)\",\"money\":\"\"},{\"title\":\"\\u666e\\u901a\\u65f6\\u6bb5(-2.977\\u516c\\u91cc)\",\"money\":\"10\\u5143\"}]}]","coupon_id":null,"coupon_money":null,"tatol_money":10,"time":"2019-09-27 16:15:31pm"}
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
         * id : 107
         * service_type : 0
         * createtime : 1566986210
         * paymoney : 10.00
         * price_info_json : [{"title":"\u8d77\u6b65\u4ef7","money":"10\u5143"},{"title":"\u91cc\u7a0b\u8d39(0.023\u516c\u91cc)","money":"10\u5143","info":[{"title":"\u8d77\u6b65\u91cc\u7a0b(3\u516c\u91cc)","money":""},{"title":"\u666e\u901a\u65f6\u6bb5(-2.977\u516c\u91cc)","money":"10\u5143"}]}]
         * coupon_id : null
         * coupon_money : null
         * tatol_money : 10
         * time : 2019-09-27 16:15:31pm
         */

        private int id;
        private String service_type;
        private int createtime;
        private String paymoney;
        private String price_info_json;
        private Object coupon_id;
        private Object coupon_money;
        private int tatol_money;
        private String time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getService_type() {
            return service_type;
        }

        public void setService_type(String service_type) {
            this.service_type = service_type;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public String getPaymoney() {
            return paymoney;
        }

        public void setPaymoney(String paymoney) {
            this.paymoney = paymoney;
        }

        public String getPrice_info_json() {
            return price_info_json;
        }

        public void setPrice_info_json(String price_info_json) {
            this.price_info_json = price_info_json;
        }

        public Object getCoupon_id() {
            return coupon_id;
        }

        public void setCoupon_id(Object coupon_id) {
            this.coupon_id = coupon_id;
        }

        public Object getCoupon_money() {
            return coupon_money;
        }

        public void setCoupon_money(Object coupon_money) {
            this.coupon_money = coupon_money;
        }

        public int getTatol_money() {
            return tatol_money;
        }

        public void setTatol_money(int tatol_money) {
            this.tatol_money = tatol_money;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
