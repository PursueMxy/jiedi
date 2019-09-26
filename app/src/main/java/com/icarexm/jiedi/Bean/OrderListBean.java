package com.icarexm.jiedi.Bean;

import java.util.List;

public class OrderListBean {

    /**
     * code : 200
     * msg :
     * time : 1569489381
     * data : {"order_count":1,"today_money":15.08,"message":0,"history_money":241.89,"user_evaluate":4.8,"order":[{"title":"城内出行","time_of_appointment":"09月26日 17:15","time":"17:15","startingpoint":"梧村街道厦门国贸大厦","destination":"万达广场(厦门湖里店)","flightno":"","price_info_json":null,"paymoney":"30.884","status":"0","service_type":"0","id":155}]}
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
         * order_count : 1
         * today_money : 15.08
         * message : 0
         * history_money : 241.89
         * user_evaluate : 4.8
         * order : [{"title":"城内出行","time_of_appointment":"09月26日 17:15","time":"17:15","startingpoint":"梧村街道厦门国贸大厦","destination":"万达广场(厦门湖里店)","flightno":"","price_info_json":null,"paymoney":"30.884","status":"0","service_type":"0","id":155}]
         */

        private int order_count;
        private double today_money;
        private int message;
        private double history_money;
        private double user_evaluate;
        private List<OrderBean> order;

        public int getOrder_count() {
            return order_count;
        }

        public void setOrder_count(int order_count) {
            this.order_count = order_count;
        }

        public double getToday_money() {
            return today_money;
        }

        public void setToday_money(double today_money) {
            this.today_money = today_money;
        }

        public int getMessage() {
            return message;
        }

        public void setMessage(int message) {
            this.message = message;
        }

        public double getHistory_money() {
            return history_money;
        }

        public void setHistory_money(double history_money) {
            this.history_money = history_money;
        }

        public double getUser_evaluate() {
            return user_evaluate;
        }

        public void setUser_evaluate(double user_evaluate) {
            this.user_evaluate = user_evaluate;
        }

        public List<OrderBean> getOrder() {
            return order;
        }

        public void setOrder(List<OrderBean> order) {
            this.order = order;
        }

        public static class OrderBean {
            /**
             * title : 城内出行
             * time_of_appointment : 09月26日 17:15
             * time : 17:15
             * startingpoint : 梧村街道厦门国贸大厦
             * destination : 万达广场(厦门湖里店)
             * flightno :
             * price_info_json : null
             * paymoney : 30.884
             * status : 0
             * service_type : 0
             * id : 155
             */

            private String title;
            private String time_of_appointment;
            private String time;
            private String startingpoint;
            private String destination;
            private String flightno;
            private Object price_info_json;
            private String paymoney;
            private String status;
            private String service_type;
            private int id;

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

            public Object getPrice_info_json() {
                return price_info_json;
            }

            public void setPrice_info_json(Object price_info_json) {
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
        }
    }
}
