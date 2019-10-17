package com.icarexm.jiedi.Bean;

import java.util.List;

public class AccountingRulesBean {

    /**
     * code : 200
     * msg :
     * time : 1571203616
     * data : {"customer_service":"888888888","user_service_rule":"用户服务条款","driver_service_rule":"法律条款与平台规则","recharge_doc":null,"logo":null,"adress":"地址","withdrawal":"提现说明","price_rule":[{"id":1,"title":"起步价","key":"flagfallprice","desriptionjson":"{\"\\u666e\\u901a\\u65f6\\u6bb5\":\"9\\u5143\",\"00:00-09:30\":\"10\\u5143\",\"17:00-19:00\":\"10\\u5143\",\"23:00-00:00\":\"10\\u5143\",\"\\u5305\\u542b\\u91cc\\u7a0b\":\"3\\u516c\\u91cc\",\"\\u5305\\u542b\\u65f6\\u957f\":\"6\\u5206\\u949f\"}","mark":"超出部分，按照计价规则收取","createtime":1563933274},{"id":2,"title":"里程费（分时段）","key":"milestonespayment","desriptionjson":"{\"\\u666e\\u901a\\u65f6\\u6bb5\":\"2\\u5143\\/\\u516c\\u91cc\",\"00:00-05:00\":\"2.8\\u5143\\/\\u516c\\u91cc\",\"23:00-00:00\":\"2.8\\u5143\\/\\u516c\\u91cc\"}","mark":"超出部分，按照计价规则收取","createtime":1563933854},{"id":3,"title":"时长费（分时段）","key":"timecost","desriptionjson":"{\"\\u666e\\u901a\\u65f6\\u6bb5\":\"0.48\\u5143\\/\\u5206\\u949f\",\"05:00-09:30\":\"0.6\\u5143\\/\\u5206\\u949f\"}","mark":"超出部分，按照计价规则收取","createtime":1563934241},{"id":4,"title":"远途费","key":"longdistanceexpenses","desriptionjson":"{\"\\u8d85\\u51fa10\\u516c\\u91cc-25\\u516c\\u91cc\":\"0.6\\u5143\\/\\u516c\\u91cc\",\"\\u8d85\\u51fa25\\u516c\\u91cc\\u540e\":\"0.75\\u5143\\/\\u516c\\u91cc\"}","mark":"超出部分，按照计价规则收取","createtime":1563935103},{"id":5,"title":"超时等候费（分时段）","key":"overtimewaitingfee","desriptionjson":"{\"\\u666e\\u901a\\u65f6\\u6bb5\":\"0.38\\u5143\\/\\u5206\\u949f\",\"05:00-09:30\":\"0.47\\u5143\\/\\u5206\\u949f\"}","mark":"超出部分，按照计价规则收取","createtime":1563935733}],"platform_rule":"平台规则"}
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
         * customer_service : 888888888
         * user_service_rule : 用户服务条款
         * driver_service_rule : 法律条款与平台规则
         * recharge_doc : null
         * logo : null
         * adress : 地址
         * withdrawal : 提现说明
         * price_rule : [{"id":1,"title":"起步价","key":"flagfallprice","desriptionjson":"{\"\\u666e\\u901a\\u65f6\\u6bb5\":\"9\\u5143\",\"00:00-09:30\":\"10\\u5143\",\"17:00-19:00\":\"10\\u5143\",\"23:00-00:00\":\"10\\u5143\",\"\\u5305\\u542b\\u91cc\\u7a0b\":\"3\\u516c\\u91cc\",\"\\u5305\\u542b\\u65f6\\u957f\":\"6\\u5206\\u949f\"}","mark":"超出部分，按照计价规则收取","createtime":1563933274},{"id":2,"title":"里程费（分时段）","key":"milestonespayment","desriptionjson":"{\"\\u666e\\u901a\\u65f6\\u6bb5\":\"2\\u5143\\/\\u516c\\u91cc\",\"00:00-05:00\":\"2.8\\u5143\\/\\u516c\\u91cc\",\"23:00-00:00\":\"2.8\\u5143\\/\\u516c\\u91cc\"}","mark":"超出部分，按照计价规则收取","createtime":1563933854},{"id":3,"title":"时长费（分时段）","key":"timecost","desriptionjson":"{\"\\u666e\\u901a\\u65f6\\u6bb5\":\"0.48\\u5143\\/\\u5206\\u949f\",\"05:00-09:30\":\"0.6\\u5143\\/\\u5206\\u949f\"}","mark":"超出部分，按照计价规则收取","createtime":1563934241},{"id":4,"title":"远途费","key":"longdistanceexpenses","desriptionjson":"{\"\\u8d85\\u51fa10\\u516c\\u91cc-25\\u516c\\u91cc\":\"0.6\\u5143\\/\\u516c\\u91cc\",\"\\u8d85\\u51fa25\\u516c\\u91cc\\u540e\":\"0.75\\u5143\\/\\u516c\\u91cc\"}","mark":"超出部分，按照计价规则收取","createtime":1563935103},{"id":5,"title":"超时等候费（分时段）","key":"overtimewaitingfee","desriptionjson":"{\"\\u666e\\u901a\\u65f6\\u6bb5\":\"0.38\\u5143\\/\\u5206\\u949f\",\"05:00-09:30\":\"0.47\\u5143\\/\\u5206\\u949f\"}","mark":"超出部分，按照计价规则收取","createtime":1563935733}]
         * platform_rule : 平台规则
         */

        private String customer_service;
        private String user_service_rule;
        private String driver_service_rule;
        private Object recharge_doc;
        private Object logo;
        private String adress;
        private String withdrawal;
        private String platform_rule;
        private List<PriceRuleBean> price_rule;

        public String getCustomer_service() {
            return customer_service;
        }

        public void setCustomer_service(String customer_service) {
            this.customer_service = customer_service;
        }

        public String getUser_service_rule() {
            return user_service_rule;
        }

        public void setUser_service_rule(String user_service_rule) {
            this.user_service_rule = user_service_rule;
        }

        public String getDriver_service_rule() {
            return driver_service_rule;
        }

        public void setDriver_service_rule(String driver_service_rule) {
            this.driver_service_rule = driver_service_rule;
        }

        public Object getRecharge_doc() {
            return recharge_doc;
        }

        public void setRecharge_doc(Object recharge_doc) {
            this.recharge_doc = recharge_doc;
        }

        public Object getLogo() {
            return logo;
        }

        public void setLogo(Object logo) {
            this.logo = logo;
        }

        public String getAdress() {
            return adress;
        }

        public void setAdress(String adress) {
            this.adress = adress;
        }

        public String getWithdrawal() {
            return withdrawal;
        }

        public void setWithdrawal(String withdrawal) {
            this.withdrawal = withdrawal;
        }

        public String getPlatform_rule() {
            return platform_rule;
        }

        public void setPlatform_rule(String platform_rule) {
            this.platform_rule = platform_rule;
        }

        public List<PriceRuleBean> getPrice_rule() {
            return price_rule;
        }

        public void setPrice_rule(List<PriceRuleBean> price_rule) {
            this.price_rule = price_rule;
        }

        public static class PriceRuleBean {
            /**
             * id : 1
             * title : 起步价
             * key : flagfallprice
             * desriptionjson : {"\u666e\u901a\u65f6\u6bb5":"9\u5143","00:00-09:30":"10\u5143","17:00-19:00":"10\u5143","23:00-00:00":"10\u5143","\u5305\u542b\u91cc\u7a0b":"3\u516c\u91cc","\u5305\u542b\u65f6\u957f":"6\u5206\u949f"}
             * mark : 超出部分，按照计价规则收取
             * createtime : 1563933274
             */

            private int id;
            private String title;
            private String key;
            private String desriptionjson;
            private String mark;
            private int createtime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getDesriptionjson() {
                return desriptionjson;
            }

            public void setDesriptionjson(String desriptionjson) {
                this.desriptionjson = desriptionjson;
            }

            public String getMark() {
                return mark;
            }

            public void setMark(String mark) {
                this.mark = mark;
            }

            public int getCreatetime() {
                return createtime;
            }

            public void setCreatetime(int createtime) {
                this.createtime = createtime;
            }
        }
    }
}
