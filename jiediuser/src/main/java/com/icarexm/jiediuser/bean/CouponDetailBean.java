package com.icarexm.jiediuser.bean;

public class CouponDetailBean {

    /**
     * code : 200
     * msg : 获取成功
     * time : 1571658096
     * data : {"coupon":{"id":2,"name":"满6减3","type":3,"money":"3.00","condition":"6.00","createnum":100,"send_num":15,"use_num":0,"send_start_time":0,"send_end_time":0,"use_start_time":1560664417,"use_end_time":1579845217,"add_time":1571376707,"status":1,"use_type":3,"return_driver":1,"u_status":1,"uid":12}}
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
         * coupon : {"id":2,"name":"满6减3","type":3,"money":"3.00","condition":"6.00","createnum":100,"send_num":15,"use_num":0,"send_start_time":0,"send_end_time":0,"use_start_time":1560664417,"use_end_time":1579845217,"add_time":1571376707,"status":1,"use_type":3,"return_driver":1,"u_status":1,"uid":12}
         */

        private CouponBean coupon;

        public CouponBean getCoupon() {
            return coupon;
        }

        public void setCoupon(CouponBean coupon) {
            this.coupon = coupon;
        }

        public static class CouponBean {
            /**
             * id : 2
             * name : 满6减3
             * type : 3
             * money : 3.00
             * condition : 6.00
             * createnum : 100
             * send_num : 15
             * use_num : 0
             * send_start_time : 0
             * send_end_time : 0
             * use_start_time : 1560664417
             * use_end_time : 1579845217
             * add_time : 1571376707
             * status : 1
             * use_type : 3
             * return_driver : 1
             * u_status : 1
             * uid : 12
             */

            private int id;
            private String name;
            private int type;
            private String money;
            private String condition;
            private int createnum;
            private int send_num;
            private int use_num;
            private int send_start_time;
            private int send_end_time;
            private int use_start_time;
            private int use_end_time;
            private int add_time;
            private int status;
            private int use_type;
            private int return_driver;
            private int u_status;
            private int uid;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getCondition() {
                return condition;
            }

            public void setCondition(String condition) {
                this.condition = condition;
            }

            public int getCreatenum() {
                return createnum;
            }

            public void setCreatenum(int createnum) {
                this.createnum = createnum;
            }

            public int getSend_num() {
                return send_num;
            }

            public void setSend_num(int send_num) {
                this.send_num = send_num;
            }

            public int getUse_num() {
                return use_num;
            }

            public void setUse_num(int use_num) {
                this.use_num = use_num;
            }

            public int getSend_start_time() {
                return send_start_time;
            }

            public void setSend_start_time(int send_start_time) {
                this.send_start_time = send_start_time;
            }

            public int getSend_end_time() {
                return send_end_time;
            }

            public void setSend_end_time(int send_end_time) {
                this.send_end_time = send_end_time;
            }

            public int getUse_start_time() {
                return use_start_time;
            }

            public void setUse_start_time(int use_start_time) {
                this.use_start_time = use_start_time;
            }

            public int getUse_end_time() {
                return use_end_time;
            }

            public void setUse_end_time(int use_end_time) {
                this.use_end_time = use_end_time;
            }

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getUse_type() {
                return use_type;
            }

            public void setUse_type(int use_type) {
                this.use_type = use_type;
            }

            public int getReturn_driver() {
                return return_driver;
            }

            public void setReturn_driver(int return_driver) {
                this.return_driver = return_driver;
            }

            public int getU_status() {
                return u_status;
            }

            public void setU_status(int u_status) {
                this.u_status = u_status;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }
        }
    }
}
