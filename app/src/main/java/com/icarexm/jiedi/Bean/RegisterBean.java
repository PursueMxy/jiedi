package com.icarexm.jiedi.Bean;

public class RegisterBean {

    /**
     * code : 202
     * msg : 注册成功
     * time : 1571293839
     * data : {"userinfo":{"id":10,"order_trip_id":null,"username":"","nickname":"mxy","mobile":"15306987579","avatar":"/uploads/20191017/4d4d70c8990f874625b9533bda360120.jpg","gender":1,"money":"0.00","openid":null,"type":"1","onlinetime":0,"driving_license_time":1571155200,"licenseplate":"闽D8585B","age_group":null,"identity_card":null,"occupation":null,"company":null,"auto_order":"0","token":"0361fc8f-c47b-41c6-9f4c-fbccda2ef498","user_id":10,"createtime":1571293839,"expiretime":1573885839,"expires_in":2592000}}
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
         * userinfo : {"id":10,"order_trip_id":null,"username":"","nickname":"mxy","mobile":"15306987579","avatar":"/uploads/20191017/4d4d70c8990f874625b9533bda360120.jpg","gender":1,"money":"0.00","openid":null,"type":"1","onlinetime":0,"driving_license_time":1571155200,"licenseplate":"闽D8585B","age_group":null,"identity_card":null,"occupation":null,"company":null,"auto_order":"0","token":"0361fc8f-c47b-41c6-9f4c-fbccda2ef498","user_id":10,"createtime":1571293839,"expiretime":1573885839,"expires_in":2592000}
         */

        private UserinfoBean userinfo;

        public UserinfoBean getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserinfoBean userinfo) {
            this.userinfo = userinfo;
        }

        public static class UserinfoBean {
            /**
             * id : 10
             * order_trip_id : null
             * username :
             * nickname : mxy
             * mobile : 15306987579
             * avatar : /uploads/20191017/4d4d70c8990f874625b9533bda360120.jpg
             * gender : 1
             * money : 0.00
             * openid : null
             * type : 1
             * onlinetime : 0
             * driving_license_time : 1571155200
             * licenseplate : 闽D8585B
             * age_group : null
             * identity_card : null
             * occupation : null
             * company : null
             * auto_order : 0
             * token : 0361fc8f-c47b-41c6-9f4c-fbccda2ef498
             * user_id : 10
             * createtime : 1571293839
             * expiretime : 1573885839
             * expires_in : 2592000
             */

            private int id;
            private Object order_trip_id;
            private String username;
            private String nickname;
            private String mobile;
            private String avatar;
            private int gender;
            private String money;
            private Object openid;
            private String type;
            private int onlinetime;
            private int driving_license_time;
            private String licenseplate;
            private Object age_group;
            private Object identity_card;
            private Object occupation;
            private Object company;
            private String auto_order;
            private String token;
            private int user_id;
            private int createtime;
            private int expiretime;
            private int expires_in;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getOrder_trip_id() {
                return order_trip_id;
            }

            public void setOrder_trip_id(Object order_trip_id) {
                this.order_trip_id = order_trip_id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
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

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public Object getOpenid() {
                return openid;
            }

            public void setOpenid(Object openid) {
                this.openid = openid;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getOnlinetime() {
                return onlinetime;
            }

            public void setOnlinetime(int onlinetime) {
                this.onlinetime = onlinetime;
            }

            public int getDriving_license_time() {
                return driving_license_time;
            }

            public void setDriving_license_time(int driving_license_time) {
                this.driving_license_time = driving_license_time;
            }

            public String getLicenseplate() {
                return licenseplate;
            }

            public void setLicenseplate(String licenseplate) {
                this.licenseplate = licenseplate;
            }

            public Object getAge_group() {
                return age_group;
            }

            public void setAge_group(Object age_group) {
                this.age_group = age_group;
            }

            public Object getIdentity_card() {
                return identity_card;
            }

            public void setIdentity_card(Object identity_card) {
                this.identity_card = identity_card;
            }

            public Object getOccupation() {
                return occupation;
            }

            public void setOccupation(Object occupation) {
                this.occupation = occupation;
            }

            public Object getCompany() {
                return company;
            }

            public void setCompany(Object company) {
                this.company = company;
            }

            public String getAuto_order() {
                return auto_order;
            }

            public void setAuto_order(String auto_order) {
                this.auto_order = auto_order;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getCreatetime() {
                return createtime;
            }

            public void setCreatetime(int createtime) {
                this.createtime = createtime;
            }

            public int getExpiretime() {
                return expiretime;
            }

            public void setExpiretime(int expiretime) {
                this.expiretime = expiretime;
            }

            public int getExpires_in() {
                return expires_in;
            }

            public void setExpires_in(int expires_in) {
                this.expires_in = expires_in;
            }
        }
    }
}
