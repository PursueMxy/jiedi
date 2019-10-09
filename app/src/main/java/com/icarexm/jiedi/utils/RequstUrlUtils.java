package com.icarexm.jiedi.utils;

public class RequstUrlUtils {
    public static class URL{

        //长连接 测试
        public static final String WEBSOCKET_HOST_AND_PORT = "ws://39.98.177.238:8284";
//        public static final String WEBSOCKET_HOST_AND_PORT = "wss://iclock.zkteco.com:7272/wss";

        public static  String Host="http://jdcx.icarefz.cn";

        //加载初始化
        public static String init=Host+"/api/common/init";


//        账号密码登录
        public static String Login=Host+"/api/user/login";

//        获取验证码
        public static String MobileCode=Host+"/api/user/getMobileCode";

        //获取订单详情
        public static String order_price_info=Host+"/api/order/order_price_info";

        // 司机首页
        public static String driver_index=Host+"/api/user/driver_index";

       // 订单状态
        public static String orderInfo=Host+"/api/order/orderInfo";

        //  用户给司机星级评价
        public static  String evaluate=Host+"/api/order/evaluate";

        //自动接单转态 /api/user/auto_order
        public static String auto_order=Host+"/api/user/auto_order";

        //订单列表 /api/order/index
        public static String OrderIndex=Host+"/api/order/index";
    }
}
