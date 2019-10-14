package com.icarexm.jiediuser.bean;

import java.util.List;

public class PositionBean {

    /**
     * event : position
     * code : 200
     * msg : 位置接收成功
     * data : {"user":{"type":"0","id":12,"nickname":"15306987579","mobile":"15306987579","avatar":"/uploads/20190719/b25e11841e370737ac4dd3f0d4f2eaae.jpg","gender":0,"birthday":null,"money":"982.00","balance":null,"score":0,"token":"bf0c7205-51aa-4081-886e-7e20aeb80fa8","status":"normal","verification":"","openid":null,"VIP":"0","fid":null,"commission":"0.00","fprofit":"0.00","QRcodeexpirestime":null,"last_positionE":null,"last_positionN":null,"login_ws_time":1571020694,"positionE":"118.118728","positionN":"24.475581","speed":"0.0","time":"","driverArr_old":[],"order_trip_id":1106,"city":"厦门市","position":"福建省福建省思明区338号"},"driver":{"nickname":"asd","drivingage":1,"mobile":"18756070310","avatar":"/uploads/20190820/6e0994dc64a12baf44bbfbbaaba02aad.jpg","id":1,"licenseplate":"asd","order_trip_id":null},"driver_position":{"add":[],"reduce":[],"driverArr":[]}}
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
         * user : {"type":"0","id":12,"nickname":"15306987579","mobile":"15306987579","avatar":"/uploads/20190719/b25e11841e370737ac4dd3f0d4f2eaae.jpg","gender":0,"birthday":null,"money":"982.00","balance":null,"score":0,"token":"bf0c7205-51aa-4081-886e-7e20aeb80fa8","status":"normal","verification":"","openid":null,"VIP":"0","fid":null,"commission":"0.00","fprofit":"0.00","QRcodeexpirestime":null,"last_positionE":null,"last_positionN":null,"login_ws_time":1571020694,"positionE":"118.118728","positionN":"24.475581","speed":"0.0","time":"","driverArr_old":[],"order_trip_id":1106,"city":"厦门市","position":"福建省福建省思明区338号"}
         * driver : {"nickname":"asd","drivingage":1,"mobile":"18756070310","avatar":"/uploads/20190820/6e0994dc64a12baf44bbfbbaaba02aad.jpg","id":1,"licenseplate":"asd","order_trip_id":null}
         * driver_position : {"add":[],"reduce":[],"driverArr":[]}
         */

        private UserBean user;
        private DriverBean driver;
        private DriverPositionBean driver_position;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public DriverBean getDriver() {
            return driver;
        }

        public void setDriver(DriverBean driver) {
            this.driver = driver;
        }

        public DriverPositionBean getDriver_position() {
            return driver_position;
        }

        public void setDriver_position(DriverPositionBean driver_position) {
            this.driver_position = driver_position;
        }

        public static class UserBean {
            /**
             * type : 0
             * id : 12
             * nickname : 15306987579
             * mobile : 15306987579
             * avatar : /uploads/20190719/b25e11841e370737ac4dd3f0d4f2eaae.jpg
             * gender : 0
             * birthday : null
             * money : 982.00
             * balance : null
             * score : 0
             * token : bf0c7205-51aa-4081-886e-7e20aeb80fa8
             * status : normal
             * verification :
             * openid : null
             * VIP : 0
             * fid : null
             * commission : 0.00
             * fprofit : 0.00
             * QRcodeexpirestime : null
             * last_positionE : null
             * last_positionN : null
             * login_ws_time : 1571020694
             * positionE : 118.118728
             * positionN : 24.475581
             * speed : 0.0
             * time :
             * driverArr_old : []
             * order_trip_id : 1106
             * city : 厦门市
             * position : 福建省福建省思明区338号
             */

            private String type;
            private int id;
            private String nickname;
            private String mobile;
            private String avatar;
            private int gender;
            private Object birthday;
            private String money;
            private Object balance;
            private int score;
            private String token;
            private String status;
            private String verification;
            private Object openid;
            private String VIP;
            private Object fid;
            private String commission;
            private String fprofit;
            private Object QRcodeexpirestime;
            private Object last_positionE;
            private Object last_positionN;
            private int login_ws_time;
            private String positionE;
            private String positionN;
            private String speed;
            private String time;
            private int order_trip_id;
            private String city;
            private String position;
            private List<?> driverArr_old;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

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

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public Object getBirthday() {
                return birthday;
            }

            public void setBirthday(Object birthday) {
                this.birthday = birthday;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public Object getBalance() {
                return balance;
            }

            public void setBalance(Object balance) {
                this.balance = balance;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getVerification() {
                return verification;
            }

            public void setVerification(String verification) {
                this.verification = verification;
            }

            public Object getOpenid() {
                return openid;
            }

            public void setOpenid(Object openid) {
                this.openid = openid;
            }

            public String getVIP() {
                return VIP;
            }

            public void setVIP(String VIP) {
                this.VIP = VIP;
            }

            public Object getFid() {
                return fid;
            }

            public void setFid(Object fid) {
                this.fid = fid;
            }

            public String getCommission() {
                return commission;
            }

            public void setCommission(String commission) {
                this.commission = commission;
            }

            public String getFprofit() {
                return fprofit;
            }

            public void setFprofit(String fprofit) {
                this.fprofit = fprofit;
            }

            public Object getQRcodeexpirestime() {
                return QRcodeexpirestime;
            }

            public void setQRcodeexpirestime(Object QRcodeexpirestime) {
                this.QRcodeexpirestime = QRcodeexpirestime;
            }

            public Object getLast_positionE() {
                return last_positionE;
            }

            public void setLast_positionE(Object last_positionE) {
                this.last_positionE = last_positionE;
            }

            public Object getLast_positionN() {
                return last_positionN;
            }

            public void setLast_positionN(Object last_positionN) {
                this.last_positionN = last_positionN;
            }

            public int getLogin_ws_time() {
                return login_ws_time;
            }

            public void setLogin_ws_time(int login_ws_time) {
                this.login_ws_time = login_ws_time;
            }

            public String getPositionE() {
                return positionE;
            }

            public void setPositionE(String positionE) {
                this.positionE = positionE;
            }

            public String getPositionN() {
                return positionN;
            }

            public void setPositionN(String positionN) {
                this.positionN = positionN;
            }

            public String getSpeed() {
                return speed;
            }

            public void setSpeed(String speed) {
                this.speed = speed;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public int getOrder_trip_id() {
                return order_trip_id;
            }

            public void setOrder_trip_id(int order_trip_id) {
                this.order_trip_id = order_trip_id;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public List<?> getDriverArr_old() {
                return driverArr_old;
            }

            public void setDriverArr_old(List<?> driverArr_old) {
                this.driverArr_old = driverArr_old;
            }
        }

        public static class DriverBean {
            /**
             * nickname : asd
             * drivingage : 1
             * mobile : 18756070310
             * avatar : /uploads/20190820/6e0994dc64a12baf44bbfbbaaba02aad.jpg
             * id : 1
             * licenseplate : asd
             * order_trip_id : null
             */

            private String nickname;
            private int drivingage;
            private String mobile;
            private String avatar;
            private int id;
            private String licenseplate;
            private Object order_trip_id;

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

            public Object getOrder_trip_id() {
                return order_trip_id;
            }

            public void setOrder_trip_id(Object order_trip_id) {
                this.order_trip_id = order_trip_id;
            }
        }

        public static class DriverPositionBean {
            private List<?> add;
            private List<?> reduce;
            private List<?> driverArr;

            public List<?> getAdd() {
                return add;
            }

            public void setAdd(List<?> add) {
                this.add = add;
            }

            public List<?> getReduce() {
                return reduce;
            }

            public void setReduce(List<?> reduce) {
                this.reduce = reduce;
            }

            public List<?> getDriverArr() {
                return driverArr;
            }

            public void setDriverArr(List<?> driverArr) {
                this.driverArr = driverArr;
            }
        }
    }
}
