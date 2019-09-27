package com.icarexm.jiedi.Bean;

import java.util.List;

public class OrderListOneBean {

    /**
     * code : 200
     * msg : ok
     * time : 1569566362
     * data : {"order":[{"title":"城内出行","time_of_appointment":"09月27日 11:47","time":"11:47","startingpoint":"梧村街道厦门国贸大厦","destination":"厦门大桥","flightno":"","price_info_json":"[{\"title\":\"\\u8d77\\u6b65\\u4ef7\",\"money\":\"9\\u5143\"},{\"title\":\"\\u91cc\\u7a0b\\u8d39(0\\u516c\\u91cc)\",\"money\":\"9\\u5143\",\"info\":[{\"title\":\"\\u8d77\\u6b65\\u91cc\\u7a0b(3\\u516c\\u91cc)\",\"money\":\"\"},{\"title\":\"\\u666e\\u901a\\u65f6\\u6bb5(-3\\u516c\\u91cc)\",\"money\":\"9\\u5143\"}]}]","paymoney":"9.00","status":"6","service_type":"0","id":160,"driver_score_order":"0","user_score_order":""},{"title":"城内出行","time_of_appointment":"09月27日 10:43","time":"10:43","startingpoint":"梧村街道厦门国贸大厦","destination":"厦门大学思明校区","flightno":"","price_info_json":null,"paymoney":null,"status":"8","service_type":"0","id":159,"driver_score_order":"0","user_score_order":""},{"title":"城内出行","time_of_appointment":"09月27日 10:04","time":"10:05","startingpoint":"梧村街道厦门国贸大厦","destination":"五缘湾","flightno":"","price_info_json":"[{\"title\":\"\\u8d77\\u6b65\\u4ef7\",\"money\":\"9\\u5143\"},{\"title\":\"\\u91cc\\u7a0b\\u8d39(0\\u516c\\u91cc)\",\"money\":\"9\\u5143\",\"info\":[{\"title\":\"\\u8d77\\u6b65\\u91cc\\u7a0b(3\\u516c\\u91cc)\",\"money\":\"\"},{\"title\":\"\\u666e\\u901a\\u65f6\\u6bb5(-3\\u516c\\u91cc)\",\"money\":\"9\\u5143\"}]}]","paymoney":"9.00","status":"6","service_type":"0","id":157,"driver_score_order":"0","user_score_order":"3"}]}
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
        private List<OrderBean> order;

        public List<OrderBean> getOrder() {
            return order;
        }

        public void setOrder(List<OrderBean> order) {
            this.order = order;
        }

        public static class OrderBean {
            /**
             * title : 城内出行
             * time_of_appointment : 09月27日 11:47
             * time : 11:47
             * startingpoint : 梧村街道厦门国贸大厦
             * destination : 厦门大桥
             * flightno :
             * price_info_json : [{"title":"\u8d77\u6b65\u4ef7","money":"9\u5143"},{"title":"\u91cc\u7a0b\u8d39(0\u516c\u91cc)","money":"9\u5143","info":[{"title":"\u8d77\u6b65\u91cc\u7a0b(3\u516c\u91cc)","money":""},{"title":"\u666e\u901a\u65f6\u6bb5(-3\u516c\u91cc)","money":"9\u5143"}]}]
             * paymoney : 9.00
             * status : 6
             * service_type : 0
             * id : 160
             * driver_score_order : 0
             * user_score_order :
             */

            private String title;
            private String time_of_appointment;
            private String time;
            private String startingpoint;
            private String destination;
            private String flightno;
            private String price_info_json;
            private String paymoney;
            private String status;
            private String service_type;
            private int id;
            private String driver_score_order;
            private String user_score_order;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTime_of_appointment() {
                return time_of_appointment;
            }

            public void setTime_of_appointment(String time_of_appointment) {
                this.time_of_appointment = time_of_appointment;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getStartingpoint() {
                return startingpoint;
            }

            public void setStartingpoint(String startingpoint) {
                this.startingpoint = startingpoint;
            }

            public String getDestination() {
                return destination;
            }

            public void setDestination(String destination) {
                this.destination = destination;
            }

            public String getFlightno() {
                return flightno;
            }

            public void setFlightno(String flightno) {
                this.flightno = flightno;
            }

            public String getPrice_info_json() {
                return price_info_json;
            }

            public void setPrice_info_json(String price_info_json) {
                this.price_info_json = price_info_json;
            }

            public String getPaymoney() {
                return paymoney;
            }

            public void setPaymoney(String paymoney) {
                this.paymoney = paymoney;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getService_type() {
                return service_type;
            }

            public void setService_type(String service_type) {
                this.service_type = service_type;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getDriver_score_order() {
                return driver_score_order;
            }

            public void setDriver_score_order(String driver_score_order) {
                this.driver_score_order = driver_score_order;
            }

            public String getUser_score_order() {
                return user_score_order;
            }

            public void setUser_score_order(String user_score_order) {
                this.user_score_order = user_score_order;
            }
        }
    }
}
