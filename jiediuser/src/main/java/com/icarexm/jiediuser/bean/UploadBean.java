package com.icarexm.jiediuser.bean;

public class UploadBean {

    /**
     * code : 200
     * msg : 上传成功
     * time : 1571054018
     * data : {"url":"http://jdcx.icarefz.cn/uploads/20191014/bef29604268409458f0caca463d7e942.jpg"}
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
         * url : http://jdcx.icarefz.cn/uploads/20191014/bef29604268409458f0caca463d7e942.jpg
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
