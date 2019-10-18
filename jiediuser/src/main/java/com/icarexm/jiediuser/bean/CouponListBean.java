package com.icarexm.jiediuser.bean;

import java.util.List;

public class CouponListBean {


    /**
     * code : 200
     * msg : ok
     * time : 1571376812
     * data : {"coupon":[{"id":320,"cid":2,"type":3,"uid":12,"order_id":null,"get_order_id":0,"use_time":null,"code":"lSh54yTQ","send_time":1571376687,"status":0,"updatetime":0,"coupon_id":2,"name":"满6减3","money":"3.00","condition":"6.00","createnum":100,"send_num":15,"send_start_time":0,"send_end_time":0,"use_start_time":1560664417,"use_end_time":1579845217,"add_time":1571376707,"coupon_status":1,"use_type":3},{"id":321,"cid":2,"type":3,"uid":12,"order_id":null,"get_order_id":0,"use_time":null,"code":"iPLNvwGJ","send_time":1571376687,"status":0,"updatetime":0,"coupon_id":2,"name":"满6减3","money":"3.00","condition":"6.00","createnum":100,"send_num":15,"send_start_time":0,"send_end_time":0,"use_start_time":1560664417,"use_end_time":1579845217,"add_time":1571376707,"coupon_status":1,"use_type":3},{"id":322,"cid":2,"type":3,"uid":12,"order_id":null,"get_order_id":0,"use_time":null,"code":"KDKfkjnJ","send_time":1571376687,"status":0,"updatetime":0,"coupon_id":2,"name":"满6减3","money":"3.00","condition":"6.00","createnum":100,"send_num":15,"send_start_time":0,"send_end_time":0,"use_start_time":1560664417,"use_end_time":1579845217,"add_time":1571376707,"coupon_status":1,"use_type":3},{"id":323,"cid":2,"type":3,"uid":12,"order_id":null,"get_order_id":0,"use_time":null,"code":"LjSuSvcM","send_time":1571376687,"status":0,"updatetime":0,"coupon_id":2,"name":"满6减3","money":"3.00","condition":"6.00","createnum":100,"send_num":15,"send_start_time":0,"send_end_time":0,"use_start_time":1560664417,"use_end_time":1579845217,"add_time":1571376707,"coupon_status":1,"use_type":3},{"id":324,"cid":2,"type":3,"uid":12,"order_id":null,"get_order_id":0,"use_time":null,"code":"hQqEEEwC","send_time":1571376687,"status":0,"updatetime":0,"coupon_id":2,"name":"满6减3","money":"3.00","condition":"6.00","createnum":100,"send_num":15,"send_start_time":0,"send_end_time":0,"use_start_time":1560664417,"use_end_time":1579845217,"add_time":1571376707,"coupon_status":1,"use_type":3}]}
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
        private List<CouponBean> coupon;

        public List<CouponBean> getCoupon() {
            return coupon;
        }

        public void setCoupon(List<CouponBean> coupon) {
            this.coupon = coupon;
        }

        public static class CouponBean {
            /**
             * id : 320
             * cid : 2
             * type : 3
             * uid : 12
             * order_id : null
             * get_order_id : 0
             * use_time : null
             * code : lSh54yTQ
             * send_time : 1571376687
             * status : 0
             * updatetime : 0
             * coupon_id : 2
             * name : 满6减3
             * money : 3.00
             * condition : 6.00
             * createnum : 100
             * send_num : 15
             * send_start_time : 0
             * send_end_time : 0
             * use_start_time : 1560664417
             * use_end_time : 1579845217
             * add_time : 1571376707
             * coupon_status : 1
             * use_type : 3
             */

            private int id;
            private int cid;
            private int type;
            private int uid;
            private Object order_id;
            private int get_order_id;
            private Object use_time;
            private String code;
            private int send_time;
            private int status;
            private int updatetime;
            private int coupon_id;
            private String name;
            private String money;
            private String condition;
            private int createnum;
            private int send_num;
            private int send_start_time;
            private int send_end_time;
            private int use_start_time;
            private int use_end_time;
            private int add_time;
            private int coupon_status;
            private int use_type;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCid() {
                return cid;
            }

            public void setCid(int cid) {
                this.cid = cid;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public Object getOrder_id() {
                return order_id;
            }

            public void setOrder_id(Object order_id) {
                this.order_id = order_id;
            }

            public int getGet_order_id() {
                return get_order_id;
            }

            public void setGet_order_id(int get_order_id) {
                this.get_order_id = get_order_id;
            }

            public Object getUse_time() {
                return use_time;
            }

            public void setUse_time(Object use_time) {
                this.use_time = use_time;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public int getSend_time() {
                return send_time;
            }

            public void setSend_time(int send_time) {
                this.send_time = send_time;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(int updatetime) {
                this.updatetime = updatetime;
            }

            public int getCoupon_id() {
                return coupon_id;
            }

            public void setCoupon_id(int coupon_id) {
                this.coupon_id = coupon_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

            public int getCoupon_status() {
                return coupon_status;
            }

            public void setCoupon_status(int coupon_status) {
                this.coupon_status = coupon_status;
            }

            public int getUse_type() {
                return use_type;
            }

            public void setUse_type(int use_type) {
                this.use_type = use_type;
            }
        }
    }
}
