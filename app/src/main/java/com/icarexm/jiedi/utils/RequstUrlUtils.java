package com.icarexm.jiedi.utils;

public class RequstUrlUtils {
    public static class URL{

        //长连接 测试
        public static final String WEBSOCKET_HOST_AND_PORT = "ws://39.98.177.238:8284";

        public static  String Host="http://jdcx.icarefz.cn";

//        账号密码登录
        public static String Login=Host+"/api/user/login";

//        获取验证码
        public static String MobileCode=Host+"/api/user/getMobileCode";
    }
}
