package com.icarexm.jiediuser.bean;

import java.util.List;

public class OrderDetailOneBean {


    /**
     * code : 200
     * msg : OK
     * time : 1570848766
     * data : {"id":173,"type":"0","driver_id":null,"user_id":12,"startingpointE":"118.118813","startingpointN":"24.475469","startingpoint":"厦门市思明区湖明南路裕发广场338号","destinationE":"118.09347","destinationN":"24.437154","destination":"厦门大学思明校区(西门)","status":"0","createtime":1570848766,"estimatedeparturetime":null,"acceptance_time":null,"driver_arrivaltime":null,"departuretime":null,"arrivaltime":null,"endtime":null,"updatetime":1570848766,"meal_id":null,"money":null,"start_money":null,"mileage_money":null,"timecost":null,"longdistanceexpenses":null,"wait_money":null,"free_kilometre":null,"free_time":null,"travel_time":0,"travel_interval_time":null,"wait_time":0,"mileage":null,"city":"厦门市","estimated_mileage":8.229,"coupon_id":null,"paymoney":null,"mobile":"15306987579","order_trip_id":1100,"budget":"26.177999999999997","service_type":"0","createddatetime":"10:52:46","pay_type":"","commission_platform":"0.00","reason":"","estimated_time":20,"refuse_user":null,"arrive_position":null,"arrive_positionN":null,"arrive_positionE":null,"price_info_json":null,"out_trade_no":null,"flightno":"","refuse_drver_id":null,"odometerperiod":null,"trafficViolation":null,"coupon_money":"0.00","driver_money":null,"driverInfo":[],"time_of_appointment":"","userInfo":{"nickname":"15306987579","mobile":"15306987579","avatar":"http://jdcx.icarefz.cn/uploads/20190719/b25e11841e370737ac4dd3f0d4f2eaae.jpg","id":12},"user_evaluate":"5","user_score_order":"0","info_submited":0,"score":""}
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
         * id : 173
         * type : 0
         * driver_id : null
         * user_id : 12
         * startingpointE : 118.118813
         * startingpointN : 24.475469
         * startingpoint : 厦门市思明区湖明南路裕发广场338号
         * destinationE : 118.09347
         * destinationN : 24.437154
         * destination : 厦门大学思明校区(西门)
         * status : 0
         * createtime : 1570848766
         * estimatedeparturetime : null
         * acceptance_time : null
         * driver_arrivaltime : null
         * departuretime : null
         * arrivaltime : null
         * endtime : null
         * updatetime : 1570848766
         * meal_id : null
         * money : null
         * start_money : null
         * mileage_money : null
         * timecost : null
         * longdistanceexpenses : null
         * wait_money : null
         * free_kilometre : null
         * free_time : null
         * travel_time : 0
         * travel_interval_time : null
         * wait_time : 0
         * mileage : null
         * city : 厦门市
         * estimated_mileage : 8.229
         * coupon_id : null
         * paymoney : null
         * mobile : 15306987579
         * order_trip_id : 1100
         * budget : 26.177999999999997
         * service_type : 0
         * createddatetime : 10:52:46
         * pay_type :
         * commission_platform : 0.00
         * reason :
         * estimated_time : 20
         * refuse_user : null
         * arrive_position : null
         * arrive_positionN : null
         * arrive_positionE : null
         * price_info_json : null
         * out_trade_no : null
         * flightno :
         * refuse_drver_id : null
         * odometerperiod : null
         * trafficViolation : null
         * coupon_money : 0.00
         * driver_money : null
         * driverInfo : []
         * time_of_appointment :
         * userInfo : {"nickname":"15306987579","mobile":"15306987579","avatar":"http://jdcx.icarefz.cn/uploads/20190719/b25e11841e370737ac4dd3f0d4f2eaae.jpg","id":12}
         * user_evaluate : 5
         * user_score_order : 0
         * info_submited : 0
         * score :
         */

        private int id;
        private String type;
        private Object driver_id;
        private int user_id;
        private String startingpointE;
        private String startingpointN;
        private String startingpoint;
        private String destinationE;
        private String destinationN;
        private String destination;
        private String status;
        private int createtime;
        private Object estimatedeparturetime;
        private Object acceptance_time;
        private Object driver_arrivaltime;
        private Object departuretime;
        private Object arrivaltime;
        private Object endtime;
        private int updatetime;
        private Object meal_id;
        private Object money;
        private Object start_money;
        private Object mileage_money;
        private Object timecost;
        private Object longdistanceexpenses;
        private Object wait_money;
        private Object free_kilometre;
        private Object free_time;
        private int travel_time;
        private Object travel_interval_time;
        private int wait_time;
        private Object mileage;
        private String city;
        private double estimated_mileage;
        private Object coupon_id;
        private Object paymoney;
        private String mobile;
        private int order_trip_id;
        private String budget;
        private String service_type;
        private String createddatetime;
        private String pay_type;
        private String commission_platform;
        private String reason;
        private int estimated_time;
        private Object refuse_user;
        private Object arrive_position;
        private Object arrive_positionN;
        private Object arrive_positionE;
        private Object price_info_json;
        private Object out_trade_no;
        private String flightno;
        private Object refuse_drver_id;
        private Object odometerperiod;
        private Object trafficViolation;
        private String coupon_money;
        private Object driver_money;
        private String time_of_appointment;
        private UserInfoBean userInfo;
        private String user_evaluate;
        private String user_score_order;
        private int info_submited;
        private String score;
        private Object driverInfo;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getDriver_id() {
            return driver_id;
        }

        public void setDriver_id(Object driver_id) {
            this.driver_id = driver_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getStartingpointE() {
            return startingpointE;
        }

        public void setStartingpointE(String startingpointE) {
            this.startingpointE = startingpointE;
        }

        public String getStartingpointN() {
            return startingpointN;
        }

        public void setStartingpointN(String startingpointN) {
            this.startingpointN = startingpointN;
        }

        public String getStartingpoint() {
            return startingpoint;
        }

        public void setStartingpoint(String startingpoint) {
            this.startingpoint = startingpoint;
        }

        public String getDestinationE() {
            return destinationE;
        }

        public void setDestinationE(String destinationE) {
            this.destinationE = destinationE;
        }

        public String getDestinationN() {
            return destinationN;
        }

        public void setDestinationN(String destinationN) {
            this.destinationN = destinationN;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public Object getEstimatedeparturetime() {
            return estimatedeparturetime;
        }

        public void setEstimatedeparturetime(Object estimatedeparturetime) {
            this.estimatedeparturetime = estimatedeparturetime;
        }

        public Object getAcceptance_time() {
            return acceptance_time;
        }

        public void setAcceptance_time(Object acceptance_time) {
            this.acceptance_time = acceptance_time;
        }

        public Object getDriver_arrivaltime() {
            return driver_arrivaltime;
        }

        public void setDriver_arrivaltime(Object driver_arrivaltime) {
            this.driver_arrivaltime = driver_arrivaltime;
        }

        public Object getDeparturetime() {
            return departuretime;
        }

        public void setDeparturetime(Object departuretime) {
            this.departuretime = departuretime;
        }

        public Object getArrivaltime() {
            return arrivaltime;
        }

        public void setArrivaltime(Object arrivaltime) {
            this.arrivaltime = arrivaltime;
        }

        public Object getEndtime() {
            return endtime;
        }

        public void setEndtime(Object endtime) {
            this.endtime = endtime;
        }

        public int getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(int updatetime) {
            this.updatetime = updatetime;
        }

        public Object getMeal_id() {
            return meal_id;
        }

        public void setMeal_id(Object meal_id) {
            this.meal_id = meal_id;
        }

        public Object getMoney() {
            return money;
        }

        public void setMoney(Object money) {
            this.money = money;
        }

        public Object getStart_money() {
            return start_money;
        }

        public void setStart_money(Object start_money) {
            this.start_money = start_money;
        }

        public Object getMileage_money() {
            return mileage_money;
        }

        public void setMileage_money(Object mileage_money) {
            this.mileage_money = mileage_money;
        }

        public Object getTimecost() {
            return timecost;
        }

        public void setTimecost(Object timecost) {
            this.timecost = timecost;
        }

        public Object getLongdistanceexpenses() {
            return longdistanceexpenses;
        }

        public void setLongdistanceexpenses(Object longdistanceexpenses) {
            this.longdistanceexpenses = longdistanceexpenses;
        }

        public Object getWait_money() {
            return wait_money;
        }

        public void setWait_money(Object wait_money) {
            this.wait_money = wait_money;
        }

        public Object getFree_kilometre() {
            return free_kilometre;
        }

        public void setFree_kilometre(Object free_kilometre) {
            this.free_kilometre = free_kilometre;
        }

        public Object getFree_time() {
            return free_time;
        }

        public void setFree_time(Object free_time) {
            this.free_time = free_time;
        }

        public int getTravel_time() {
            return travel_time;
        }

        public void setTravel_time(int travel_time) {
            this.travel_time = travel_time;
        }

        public Object getTravel_interval_time() {
            return travel_interval_time;
        }

        public void setTravel_interval_time(Object travel_interval_time) {
            this.travel_interval_time = travel_interval_time;
        }

        public int getWait_time() {
            return wait_time;
        }

        public void setWait_time(int wait_time) {
            this.wait_time = wait_time;
        }

        public Object getMileage() {
            return mileage;
        }

        public void setMileage(Object mileage) {
            this.mileage = mileage;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public double getEstimated_mileage() {
            return estimated_mileage;
        }

        public void setEstimated_mileage(double estimated_mileage) {
            this.estimated_mileage = estimated_mileage;
        }

        public Object getCoupon_id() {
            return coupon_id;
        }

        public void setCoupon_id(Object coupon_id) {
            this.coupon_id = coupon_id;
        }

        public Object getPaymoney() {
            return paymoney;
        }

        public void setPaymoney(Object paymoney) {
            this.paymoney = paymoney;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getOrder_trip_id() {
            return order_trip_id;
        }

        public void setOrder_trip_id(int order_trip_id) {
            this.order_trip_id = order_trip_id;
        }

        public String getBudget() {
            return budget;
        }

        public void setBudget(String budget) {
            this.budget = budget;
        }

        public String getService_type() {
            return service_type;
        }

        public void setService_type(String service_type) {
            this.service_type = service_type;
        }

        public String getCreateddatetime() {
            return createddatetime;
        }

        public void setCreateddatetime(String createddatetime) {
            this.createddatetime = createddatetime;
        }

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public String getCommission_platform() {
            return commission_platform;
        }

        public void setCommission_platform(String commission_platform) {
            this.commission_platform = commission_platform;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public int getEstimated_time() {
            return estimated_time;
        }

        public void setEstimated_time(int estimated_time) {
            this.estimated_time = estimated_time;
        }

        public Object getRefuse_user() {
            return refuse_user;
        }

        public void setRefuse_user(Object refuse_user) {
            this.refuse_user = refuse_user;
        }

        public Object getArrive_position() {
            return arrive_position;
        }

        public void setArrive_position(Object arrive_position) {
            this.arrive_position = arrive_position;
        }

        public Object getArrive_positionN() {
            return arrive_positionN;
        }

        public void setArrive_positionN(Object arrive_positionN) {
            this.arrive_positionN = arrive_positionN;
        }

        public Object getArrive_positionE() {
            return arrive_positionE;
        }

        public void setArrive_positionE(Object arrive_positionE) {
            this.arrive_positionE = arrive_positionE;
        }

        public Object getPrice_info_json() {
            return price_info_json;
        }

        public void setPrice_info_json(Object price_info_json) {
            this.price_info_json = price_info_json;
        }

        public Object getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(Object out_trade_no) {
            this.out_trade_no = out_trade_no;
        }

        public String getFlightno() {
            return flightno;
        }

        public void setFlightno(String flightno) {
            this.flightno = flightno;
        }

        public Object getRefuse_drver_id() {
            return refuse_drver_id;
        }

        public void setRefuse_drver_id(Object refuse_drver_id) {
            this.refuse_drver_id = refuse_drver_id;
        }

        public Object getOdometerperiod() {
            return odometerperiod;
        }

        public void setOdometerperiod(Object odometerperiod) {
            this.odometerperiod = odometerperiod;
        }

        public Object getTrafficViolation() {
            return trafficViolation;
        }

        public void setTrafficViolation(Object trafficViolation) {
            this.trafficViolation = trafficViolation;
        }

        public String getCoupon_money() {
            return coupon_money;
        }

        public void setCoupon_money(String coupon_money) {
            this.coupon_money = coupon_money;
        }

        public Object getDriver_money() {
            return driver_money;
        }

        public void setDriver_money(Object driver_money) {
            this.driver_money = driver_money;
        }

        public String getTime_of_appointment() {
            return time_of_appointment;
        }

        public void setTime_of_appointment(String time_of_appointment) {
            this.time_of_appointment = time_of_appointment;
        }

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public String getUser_evaluate() {
            return user_evaluate;
        }

        public void setUser_evaluate(String user_evaluate) {
            this.user_evaluate = user_evaluate;
        }

        public String getUser_score_order() {
            return user_score_order;
        }

        public void setUser_score_order(String user_score_order) {
            this.user_score_order = user_score_order;
        }

        public int getInfo_submited() {
            return info_submited;
        }

        public void setInfo_submited(int info_submited) {
            this.info_submited = info_submited;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public Object getDriverInfo() {
            return driverInfo;
        }

        public void setDriverInfo(Object driverInfo) {
            this.driverInfo = driverInfo;
        }

        public static class UserInfoBean {
            /**
             * nickname : 15306987579
             * mobile : 15306987579
             * avatar : http://jdcx.icarefz.cn/uploads/20190719/b25e11841e370737ac4dd3f0d4f2eaae.jpg
             * id : 12
             */

            private String nickname;
            private String mobile;
            private String avatar;
            private int id;

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
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
