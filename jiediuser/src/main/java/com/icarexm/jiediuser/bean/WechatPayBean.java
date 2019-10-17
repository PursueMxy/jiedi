package com.icarexm.jiediuser.bean;

import com.google.gson.annotations.SerializedName;

public class WechatPayBean {

    /**
     * code : 200
     * msg : ok
     * time : 1571130435
     * data : {"appid":"wx53ed5d9d32b5040c","partnerid":"1551892971","prepayid":"wx151707162576838bc964c6591710431800","timestamp":"1571130436","noncestr":"6S9GxHPRKDNIgu0I","package":"Sign=WXPay","sign":"4742D317450336CF403CD4C033510B59"}
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
         * appid : wx53ed5d9d32b5040c
         * partnerid : 1551892971
         * prepayid : wx151707162576838bc964c6591710431800
         * timestamp : 1571130436
         * noncestr : 6S9GxHPRKDNIgu0I
         * package : Sign=WXPay
         * sign : 4742D317450336CF403CD4C033510B59
         */

        private String appid;
        private String partnerid;
        private String prepayid;
        private String timestamp;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
