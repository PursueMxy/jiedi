package com.icarexm.jiedi.Bean;

public class CostDetailBean {

    /**
     * code : 200
     * msg :
     * time : 1571057044
     * data : {"id":179,"service_type":"0","createtime":1571017109,"paymoney":"14.32","price_info_json":"[{\"title\":\"\\u8d77\\u6b65\\u4ef7\",\"money\":\"9\\u5143\"},{\"title\":\"\\u91cc\\u7a0b\\u8d39(0\\u516c\\u91cc)\",\"money\":\"14.32\\u5143\",\"info\":[{\"title\":\"\\u8d77\\u6b65\\u91cc\\u7a0b(3\\u516c\\u91cc)\",\"money\":\"\"},{\"title\":\"\\u666e\\u901a\\u65f6\\u6bb5(-3\\u516c\\u91cc)\",\"money\":\"14.32\\u5143\"}]},{\"title\":\"\\u65f6\\u957f\\u8d39(60\\u5206\\u949f)\",\"money\":\"0\\u5143\",\"info\":[{\"title\":\"\\u8d77\\u6b65\\u65f6\\u957f(6\\u5206\\u949f)\",\"money\":\"\"},{\"title\":\"15:03-16:37(54\\u5206\\u949f)\",\"money\":\"0\\u5143\"}]},{\"title\":\"\\u7b49\\u5f85\\u8d39(14\\u5206\\u949f)\",\"money\":\"5.32\\u5143\",\"info\":[{\"title\":\"15:03-16:37(14\\u5206\\u949f)\",\"money\":\"5.32\\u5143\"}]}]","coupon_id":null,"coupon_money":null,"tatol_money":14.32,"time":"2019-10-14 20:44:04pm"}
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
         * id : 179
         * service_type : 0
         * createtime : 1571017109
         * paymoney : 14.32
         * price_info_json : [{"title":"\u8d77\u6b65\u4ef7","money":"9\u5143"},{"title":"\u91cc\u7a0b\u8d39(0\u516c\u91cc)","money":"14.32\u5143","info":[{"title":"\u8d77\u6b65\u91cc\u7a0b(3\u516c\u91cc)","money":""},{"title":"\u666e\u901a\u65f6\u6bb5(-3\u516c\u91cc)","money":"14.32\u5143"}]},{"title":"\u65f6\u957f\u8d39(60\u5206\u949f)","money":"0\u5143","info":[{"title":"\u8d77\u6b65\u65f6\u957f(6\u5206\u949f)","money":""},{"title":"15:03-16:37(54\u5206\u949f)","money":"0\u5143"}]},{"title":"\u7b49\u5f85\u8d39(14\u5206\u949f)","money":"5.32\u5143","info":[{"title":"15:03-16:37(14\u5206\u949f)","money":"5.32\u5143"}]}]
         * coupon_id : null
         * coupon_money : null
         * tatol_money : 14.32
         * time : 2019-10-14 20:44:04pm
         */

        private int id;
        private String service_type;
        private int createtime;
        private String paymoney;
        private String price_info_json;
        private Object coupon_id;
        private Object coupon_money;
        private double tatol_money;
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

        public double getTatol_money() {
            return tatol_money;
        }

        public void setTatol_money(double tatol_money) {
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
