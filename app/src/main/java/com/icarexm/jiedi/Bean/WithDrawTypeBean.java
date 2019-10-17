package com.icarexm.jiedi.Bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WithDrawTypeBean {

    /**
     * code : 200
     * msg : OK
     * time : 1571313092
     * data : [{"id":6,"user_type":"1","user_id":10,"withdrawal_type":"微信","withdrawal_account":"982****2313","createtime":1571312737,"deletetime":null,"default":0}]
     */

    private int code;
    private String msg;
    private String time;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 6
         * user_type : 1
         * user_id : 10
         * withdrawal_type : 微信
         * withdrawal_account : 982****2313
         * createtime : 1571312737
         * deletetime : null
         * default : 0
         */

        private int id;
        private String user_type;
        private int user_id;
        private String withdrawal_type;
        private String withdrawal_account;
        private int createtime;
        private Object deletetime;
        @SerializedName("default")
        private int defaultX;

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

        public int getDefaultX() {
            return defaultX;
        }

        public void setDefaultX(int defaultX) {
            this.defaultX = defaultX;
        }
    }
}
