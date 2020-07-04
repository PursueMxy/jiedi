package com.icarexm.jiediuser.bean;

public class GetOrderDirverBean {


    /**
     * event : get_order_dirver
     * code : 200
     * msg : 获取成功
     * data : {"driver_position":{"positionE":118.118604,"positionN":24.475769,"distance":22.3,"duration":29,"speed":9.9,"time":0,"money":"获取中.."}}
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
         * driver_position : {"positionE":118.118604,"positionN":24.475769,"distance":22.3,"duration":29,"speed":9.9,"time":0,"money":"获取中.."}
         */

        private DriverPositionBean driver_position;

        public DriverPositionBean getDriver_position() {
            return driver_position;
        }

        public void setDriver_position(DriverPositionBean driver_position) {
            this.driver_position = driver_position;
        }

        public static class DriverPositionBean {
            /**
             * positionE : 118.118604
             * positionN : 24.475769
             * distance : 22.3
             * duration : 29
             * speed : 9.9
             * time : 0
             * money : 获取中..
             */

            private double positionE;
            private double positionN;
            private double distance;
            private int duration;
            private double speed;
            private int time;
            private String money;

            public double getPositionE() {
                return positionE;
            }

            public void setPositionE(double positionE) {
                this.positionE = positionE;
            }

            public double getPositionN() {
                return positionN;
            }

            public void setPositionN(double positionN) {
                this.positionN = positionN;
            }

            public double getDistance() {
                return distance;
            }

            public void setDistance(double distance) {
                this.distance = distance;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public double getSpeed() {
                return speed;
            }

            public void setSpeed(double speed) {
                this.speed = speed;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }
        }
    }
}
