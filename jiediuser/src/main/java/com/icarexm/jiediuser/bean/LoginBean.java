package com.icarexm.jiediuser.bean;

public class LoginBean {

    /**
     * code : 200
     * msg : 登陆成功
     * time : 1569743846
     * data : {"userinfo":{"id":12,"order_trip_id":"","username":"15306987579","nickname":"15306987579","mobile":"15306987579","avatar":"http://jdcx.icarefz.cn/uploads/20190719/b25e11841e370737ac4dd3f0d4f2eaae.jpg","gender":0,"money":"0.00","onlinetime":0,"type":"0","openid":null,"driving_license_time":0,"age_group":null,"identity_card":null,"company":null,"occupation":null,"token":"28a4c7e7-6f35-4a53-afae-d41c4a32f612","user_id":12,"createtime":1569743846,"expiretime":1572335846,"expires_in":2592000}}
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
         * userinfo : {"id":12,"order_trip_id":"","username":"15306987579","nickname":"15306987579","mobile":"15306987579","avatar":"http://jdcx.icarefz.cn/uploads/20190719/b25e11841e370737ac4dd3f0d4f2eaae.jpg","gender":0,"money":"0.00","onlinetime":0,"type":"0","openid":null,"driving_license_time":0,"age_group":null,"identity_card":null,"company":null,"occupation":null,"token":"28a4c7e7-6f35-4a53-afae-d41c4a32f612","user_id":12,"createtime":1569743846,"expiretime":1572335846,"expires_in":2592000}
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
             * id : 12
             * order_trip_id : 
             * username : 15306987579
             * nickname : 15306987579
             * mobile : 15306987579
             * avatar : http://jdcx.icarefz.cn/uploads/20190719/b25e11841e370737ac4dd3f0d4f2eaae.jpg
             * gender : 0
             * money : 0.00
             * onlinetime : 0
             * type : 0
             * openid : null
             * driving_license_time : 0
             * age_group : null
             * identity_card : null
             * company : null
             * occupation : null
             * token : 28a4c7e7-6f35-4a53-afae-d41c4a32f612
             * user_id : 12
             * createtime : 1569743846
             * expiretime : 1572335846
             * expires_in : 2592000
             */

            private int id;
            private String order_trip_id;
            private String username;
            private String nickname;
            private String mobile;
            private String avatar;
            private int gender;
            private String money;
            private int onlinetime;
            private String type;
            private String openid;
            private int driving_license_time;
            private String age_group;
            private String identity_card;
            private String company;
            private String occupation;
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

            public String getOrder_trip_id() {
                return order_trip_id;
            }

            public void setOrder_trip_id(String order_trip_id) {
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

            public int getOnlinetime() {
                return onlinetime;
            }

            public void setOnlinetime(int onlinetime) {
                this.onlinetime = onlinetime;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public int getDriving_license_time() {
                return driving_license_time;
            }

            public void setDriving_license_time(int driving_license_time) {
                this.driving_license_time = driving_license_time;
            }

            public String getAge_group() {
                return age_group;
            }

            public void setAge_group(String age_group) {
                this.age_group = age_group;
            }

            public String getIdentity_card() {
                return identity_card;
            }

            public void setIdentity_card(String identity_card) {
                this.identity_card = identity_card;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public String getOccupation() {
                return occupation;
            }

            public void setOccupation(String occupation) {
                this.occupation = occupation;
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
