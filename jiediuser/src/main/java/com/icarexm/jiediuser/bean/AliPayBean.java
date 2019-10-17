package com.icarexm.jiediuser.bean;

public class AliPayBean {

    /**
     * code : 200
     * msg : ok
     * time : 1571132796
     * data : alipay_sdk=alipay-sdk-php-20180705&app_id=2019083066701501&biz_content=%7B%22body%22%3A%22%E4%BD%99%E9%A2%9D%22%2C%22subject%22%3A+%22%E4%BD%99%E9%A2%9D%E5%85%85%E5%80%BC%22%2C%22out_trade_no%22%3A+%22r5da5957cad82f20191015174636%22%2C%22timeout_express%22%3A+%2230m%22%2C%22total_amount%22%3A+%220.01%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fjdcx.icarefz.cn%2Fapi%2Fmember%2Fnotify&sign_type=RSA2&timestamp=2019-10-15+17%3A46%3A36&version=1.0&sign=XUaeXEnLgzUkwuGKa9aeYjQxnPFXHLfKuYQ9mUs6f8SWYNrE4lhDf2hhaCa%2FXAGkgLdH61gHYFmC%2BUJX5aCqanIuy1P487YGiDqrxGpWtvNsWDa3Tn8ASTP5t8OSEp7Ye%2Bo2BYgl5KtRJE%2BbiNrecx6r5mLuM9Xp6BREapF6o9cb4fTOdp%2BrAPM%2BXjWsCnTJ2I%2F4Qew%2FGkXrVebqJGIGR2s5LafXmNF%2B185oBkRB3F4ocoUNYBgiNxNbKubxesznlx%2F6QHN%2BLoNLUc3jUXzNocMftCInpCRVyajXf6I8alOXIn2i7XYXUYs7DPnTzORdd7jgMl%2B81gjFUwVoE5Vlew%3D%3D
     */

    private int code;
    private String msg;
    private String time;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
