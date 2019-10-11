package com.icarexm.jiediuser.bean;

public class OrderDetailBean {

    /**
     * code : 200
     * msg : OK
     * time : 1570777421
     * data : {"id":170,"type":"0","driver_id":5,"user_id":12,"startingpointE":"118.119308","startingpointN":"24.475619","startingpoint":"厦门市思明区湖滨南路裕发广场388号","destinationE":"118.240365","destinationN":"24.609989","destination":"翔安区人力资源和社会保障局","status":"5","createtime":1570688390,"estimatedeparturetime":1570689000,"acceptance_time":1570713741,"driver_arrivaltime":1570713744,"departuretime":1570713746,"arrivaltime":1570713748,"endtime":null,"updatetime":1570713748,"meal_id":null,"money":"9.00","start_money":"9.00","mileage_money":"0.00","timecost":"0.00","longdistanceexpenses":"0.00","wait_money":"0.00","free_kilometre":3,"free_time":6,"travel_time":0,"travel_interval_time":"21:26-21:28","wait_time":0,"mileage":"0","city":"厦门市","estimated_mileage":22.245,"coupon_id":null,"paymoney":null,"mobile":"15306987579","order_trip_id":1097,"budget":"67.837","service_type":"0","createddatetime":"14:19:50","pay_type":"","commission_platform":"0.00","reason":"","estimated_time":31,"refuse_user":null,"arrive_position":"","arrive_positionN":"24.467862684462","arrive_positionE":"118.17746609158","price_info_json":"[{\"title\":\"\\u8d77\\u6b65\\u4ef7\",\"money\":\"9\\u5143\"},{\"title\":\"\\u91cc\\u7a0b\\u8d39(0\\u516c\\u91cc)\",\"money\":\"9\\u5143\",\"info\":[{\"title\":\"\\u8d77\\u6b65\\u91cc\\u7a0b(3\\u516c\\u91cc)\",\"money\":\"\"},{\"title\":\"\\u666e\\u901a\\u65f6\\u6bb5(-3\\u516c\\u91cc)\",\"money\":\"9\\u5143\"}]}]","out_trade_no":null,"flightno":"","refuse_drver_id":null,"odometerperiod":"普通时段","trafficViolation":null,"coupon_money":"0.00","driver_money":"8.0946","driverInfo":{"nickname":"在乎科技测试","drivingage":1,"mobile":"13666067610","avatar":"http://jdcx.icarefz.cn/uploads/20190829/8a773a478058e3acf8a30ac7305a2d98.jpg","id":5,"licenseplate":"闽DP5R63","order_count":16},"time_of_appointment":"2019-10-10 14:30:00pm","driver_evaluate":"5","driver_score_order":5,"userInfo":{"nickname":"15306987579","mobile":"15306987579","avatar":"http://jdcx.icarefz.cn/uploads/20190719/b25e11841e370737ac4dd3f0d4f2eaae.jpg","id":12},"user_evaluate":"0","user_score_order":"0","info_submited":0,"score":"5"}
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
         * id : 170
         * type : 0
         * driver_id : 5
         * user_id : 12
         * startingpointE : 118.119308
         * startingpointN : 24.475619
         * startingpoint : 厦门市思明区湖滨南路裕发广场388号
         * destinationE : 118.240365
         * destinationN : 24.609989
         * destination : 翔安区人力资源和社会保障局
         * status : 5
         * createtime : 1570688390
         * estimatedeparturetime : 1570689000
         * acceptance_time : 1570713741
         * driver_arrivaltime : 1570713744
         * departuretime : 1570713746
         * arrivaltime : 1570713748
         * endtime : null
         * updatetime : 1570713748
         * meal_id : null
         * money : 9.00
         * start_money : 9.00
         * mileage_money : 0.00
         * timecost : 0.00
         * longdistanceexpenses : 0.00
         * wait_money : 0.00
         * free_kilometre : 3
         * free_time : 6
         * travel_time : 0
         * travel_interval_time : 21:26-21:28
         * wait_time : 0
         * mileage : 0
         * city : 厦门市
         * estimated_mileage : 22.245
         * coupon_id : null
         * paymoney : null
         * mobile : 15306987579
         * order_trip_id : 1097
         * budget : 67.837
         * service_type : 0
         * createddatetime : 14:19:50
         * pay_type :
         * commission_platform : 0.00
         * reason :
         * estimated_time : 31
         * refuse_user : null
         * arrive_position :
         * arrive_positionN : 24.467862684462
         * arrive_positionE : 118.17746609158
         * price_info_json : [{"title":"\u8d77\u6b65\u4ef7","money":"9\u5143"},{"title":"\u91cc\u7a0b\u8d39(0\u516c\u91cc)","money":"9\u5143","info":[{"title":"\u8d77\u6b65\u91cc\u7a0b(3\u516c\u91cc)","money":""},{"title":"\u666e\u901a\u65f6\u6bb5(-3\u516c\u91cc)","money":"9\u5143"}]}]
         * out_trade_no : null
         * flightno :
         * refuse_drver_id : null
         * odometerperiod : 普通时段
         * trafficViolation : null
         * coupon_money : 0.00
         * driver_money : 8.0946
         * driverInfo : {"nickname":"在乎科技测试","drivingage":1,"mobile":"13666067610","avatar":"http://jdcx.icarefz.cn/uploads/20190829/8a773a478058e3acf8a30ac7305a2d98.jpg","id":5,"licenseplate":"闽DP5R63","order_count":16}
         * time_of_appointment : 2019-10-10 14:30:00pm
         * driver_evaluate : 5
         * driver_score_order : 5
         * userInfo : {"nickname":"15306987579","mobile":"15306987579","avatar":"http://jdcx.icarefz.cn/uploads/20190719/b25e11841e370737ac4dd3f0d4f2eaae.jpg","id":12}
         * user_evaluate : 0
         * user_score_order : 0
         * info_submited : 0
         * score : 5
         */

        private int id;
        private String type;
        private int driver_id;
        private int user_id;
        private String startingpointE;
        private String startingpointN;
        private String startingpoint;
        private String destinationE;
        private String destinationN;
        private String destination;
        private String status;
        private int createtime;
        private int estimatedeparturetime;
        private int acceptance_time;
        private int driver_arrivaltime;
        private int departuretime;
        private int arrivaltime;
        private Object endtime;
        private int updatetime;
        private Object meal_id;
        private String money;
        private String start_money;
        private String mileage_money;
        private String timecost;
        private String longdistanceexpenses;
        private String wait_money;
        private int free_kilometre;
        private int free_time;
        private int travel_time;
        private String travel_interval_time;
        private int wait_time;
        private String mileage;
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
        private String arrive_position;
        private String arrive_positionN;
        private String arrive_positionE;
        private String price_info_json;
        private Object out_trade_no;
        private String flightno;
        private Object refuse_drver_id;
        private String odometerperiod;
        private Object trafficViolation;
        private String coupon_money;
        private String driver_money;
        private DriverInfoBean driverInfo;
        private String time_of_appointment;
        private String driver_evaluate;
        private int driver_score_order;
        private UserInfoBean userInfo;
        private String user_evaluate;
        private String user_score_order;
        private int info_submited;
        private String score;

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

        public int getDriver_id() {
            return driver_id;
        }

        public void setDriver_id(int driver_id) {
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

        public int getEstimatedeparturetime() {
            return estimatedeparturetime;
        }

        public void setEstimatedeparturetime(int estimatedeparturetime) {
            this.estimatedeparturetime = estimatedeparturetime;
        }

        public int getAcceptance_time() {
            return acceptance_time;
        }

        public void setAcceptance_time(int acceptance_time) {
            this.acceptance_time = acceptance_time;
        }

        public int getDriver_arrivaltime() {
            return driver_arrivaltime;
        }

        public void setDriver_arrivaltime(int driver_arrivaltime) {
            this.driver_arrivaltime = driver_arrivaltime;
        }

        public int getDeparturetime() {
            return departuretime;
        }

        public void setDeparturetime(int departuretime) {
            this.departuretime = departuretime;
        }

        public int getArrivaltime() {
            return arrivaltime;
        }

        public void setArrivaltime(int arrivaltime) {
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

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getStart_money() {
            return start_money;
        }

        public void setStart_money(String start_money) {
            this.start_money = start_money;
        }

        public String getMileage_money() {
            return mileage_money;
        }

        public void setMileage_money(String mileage_money) {
            this.mileage_money = mileage_money;
        }

        public String getTimecost() {
            return timecost;
        }

        public void setTimecost(String timecost) {
            this.timecost = timecost;
        }

        public String getLongdistanceexpenses() {
            return longdistanceexpenses;
        }

        public void setLongdistanceexpenses(String longdistanceexpenses) {
            this.longdistanceexpenses = longdistanceexpenses;
        }

        public String getWait_money() {
            return wait_money;
        }

        public void setWait_money(String wait_money) {
            this.wait_money = wait_money;
        }

        public int getFree_kilometre() {
            return free_kilometre;
        }

        public void setFree_kilometre(int free_kilometre) {
            this.free_kilometre = free_kilometre;
        }

        public int getFree_time() {
            return free_time;
        }

        public void setFree_time(int free_time) {
            this.free_time = free_time;
        }

        public int getTravel_time() {
            return travel_time;
        }

        public void setTravel_time(int travel_time) {
            this.travel_time = travel_time;
        }

        public String getTravel_interval_time() {
            return travel_interval_time;
        }

        public void setTravel_interval_time(String travel_interval_time) {
            this.travel_interval_time = travel_interval_time;
        }

        public int getWait_time() {
            return wait_time;
        }

        public void setWait_time(int wait_time) {
            this.wait_time = wait_time;
        }

        public String getMileage() {
            return mileage;
        }

        public void setMileage(String mileage) {
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

        public String getArrive_position() {
            return arrive_position;
        }

        public void setArrive_position(String arrive_position) {
            this.arrive_position = arrive_position;
        }

        public String getArrive_positionN() {
            return arrive_positionN;
        }

        public void setArrive_positionN(String arrive_positionN) {
            this.arrive_positionN = arrive_positionN;
        }

        public String getArrive_positionE() {
            return arrive_positionE;
        }

        public void setArrive_positionE(String arrive_positionE) {
            this.arrive_positionE = arrive_positionE;
        }

        public String getPrice_info_json() {
            return price_info_json;
        }

        public void setPrice_info_json(String price_info_json) {
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

        public String getOdometerperiod() {
            return odometerperiod;
        }

        public void setOdometerperiod(String odometerperiod) {
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

        public String getDriver_money() {
            return driver_money;
        }

        public void setDriver_money(String driver_money) {
            this.driver_money = driver_money;
        }

        public DriverInfoBean getDriverInfo() {
            return driverInfo;
        }

        public void setDriverInfo(DriverInfoBean driverInfo) {
            this.driverInfo = driverInfo;
        }

        public String getTime_of_appointment() {
            return time_of_appointment;
        }

        public void setTime_of_appointment(String time_of_appointment) {
            this.time_of_appointment = time_of_appointment;
        }

        public String getDriver_evaluate() {
            return driver_evaluate;
        }

        public void setDriver_evaluate(String driver_evaluate) {
            this.driver_evaluate = driver_evaluate;
        }

        public int getDriver_score_order() {
            return driver_score_order;
        }

        public void setDriver_score_order(int driver_score_order) {
            this.driver_score_order = driver_score_order;
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

        public static class DriverInfoBean {
            /**
             * nickname : 在乎科技测试
             * drivingage : 1
             * mobile : 13666067610
             * avatar : http://jdcx.icarefz.cn/uploads/20190829/8a773a478058e3acf8a30ac7305a2d98.jpg
             * id : 5
             * licenseplate : 闽DP5R63
             * order_count : 16
             */

            private String nickname;
            private int drivingage;
            private String mobile;
            private String avatar;
            private int id;
            private String licenseplate;
            private int order_count;

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getDrivingage() {
                return drivingage;
            }

            public void setDrivingage(int drivingage) {
                this.drivingage = drivingage;
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

            public String getLicenseplate() {
                return licenseplate;
            }

            public void setLicenseplate(String licenseplate) {
                this.licenseplate = licenseplate;
            }

            public int getOrder_count() {
                return order_count;
            }

            public void setOrder_count(int order_count) {
                this.order_count = order_count;
            }
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
