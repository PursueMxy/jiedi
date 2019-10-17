package com.icarexm.jiediuser.bean;

import java.util.List;

public class BalanceDtlBean {

    /**
     * code : 200
     * msg : ok
     * time : 1571138804
     * data : [{"title":"余额充值","log_type":"1","time_of_appointment":"","time":"08:00","startingpoint":"","destination":"","content":"余额充值","flightno":"","money":null},{"title":"余额充值","log_type":"1","time_of_appointment":"","time":"08:00","startingpoint":"","destination":"","content":"余额充值","flightno":"","money":null},{"title":"余额充值","log_type":"1","time_of_appointment":"","time":"08:00","startingpoint":"","destination":"","content":"余额充值","flightno":"","money":null}]
     */

    private int code;
    private String msg;
    private String time;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * title : 余额充值
         * log_type : 1
         * time_of_appointment :
         * time : 08:00
         * startingpoint :
         * destination :
         * content : 余额充值
         * flightno :
         * money : null
         */

        private String title;
        private String log_type;
        private String time_of_appointment;
        private String time;
        private String startingpoint;
        private String destination;
        private String content;
        private String flightno;
        private Object money;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLog_type() {
            return log_type;
        }

        public void setLog_type(String log_type) {
            this.log_type = log_type;
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getFlightno() {
            return flightno;
        }

        public void setFlightno(String flightno) {
            this.flightno = flightno;
        }

        public Object getMoney() {
            return money;
        }

        public void setMoney(Object money) {
            this.money = money;
        }
    }
}
