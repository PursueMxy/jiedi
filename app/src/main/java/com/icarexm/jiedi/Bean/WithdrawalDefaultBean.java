package com.icarexm.jiedi.Bean;

public class WithdrawalDefaultBean {

    /**
     * code : 200
     * msg : OK
     * time : 1571314895
     * data : {"withdrawal":{"id":8,"user_type":"1","user_id":10,"withdrawal_type":"支付宝","withdrawal_account":"982****.com","createtime":1571314488,"deletetime":null},"money":"8.09"}
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
         * withdrawal : {"id":8,"user_type":"1","user_id":10,"withdrawal_type":"支付宝","withdrawal_account":"982****.com","createtime":1571314488,"deletetime":null}
         * money : 8.09
         */

        private WithdrawalBean withdrawal;
        private String money;

        public WithdrawalBean getWithdrawal() {
            return withdrawal;
        }

        public void setWithdrawal(WithdrawalBean withdrawal) {
            this.withdrawal = withdrawal;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public static class WithdrawalBean {
            /**
             * id : 8
             * user_type : 1
             * user_id : 10
             * withdrawal_type : 支付宝
             * withdrawal_account : 982****.com
             * createtime : 1571314488
             * deletetime : null
             */

            private int id;
            private String user_type;
            private int user_id;
            private String withdrawal_type;
            private String withdrawal_account;
            private int createtime;
            private Object deletetime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUser_type() {
                return user_type;
            }

            public void setUser_type(String user_type) {
                this.user_type = user_type;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getWithdrawal_type() {
                return withdrawal_type;
            }

            public void setWithdrawal_type(String withdrawal_type) {
                this.withdrawal_type = withdrawal_type;
            }

            public String getWithdrawal_account() {
                return withdrawal_account;
            }

            public void setWithdrawal_account(String withdrawal_account) {
                this.withdrawal_account = withdrawal_account;
            }

            public int getCreatetime() {
                return createtime;
            }

            public void setCreatetime(int createtime) {
                this.createtime = createtime;
            }

            public Object getDeletetime() {
                return deletetime;
            }

            public void setDeletetime(Object deletetime) {
                this.deletetime = deletetime;
            }
        }
    }
}
