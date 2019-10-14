package com.icarexm.jiediuser.utils;

public class RequstUrlUtils {
    public static class URL{

        //长连接 测试
        public static final String WEBSOCKET_HOST_AND_PORT = "ws://39.98.177.238:8284";

        public static  String Host="http://jdcx.icarefz.cn";

        //加载初始化
        public static String init=Host+"/api/common/init";

//        账号密码登录
        public static String Login=Host+"/api/user/login";

//        手机验证码登陆
         public static final String mobileLogin=Host+"/api/user/mobilelogin";

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

        //计价【选填订单号】
        public static String price=Host+"/api/order/price";

        //优惠券
        public static  String couponindex=Host+"/api/coupon/index";

//        余额明细 /api/member/money_log
        public static String money_log=Host+"/api/member/money_log";

//        结算支付 /api/order/settlement
        public static String Settlement=Host+"/api/order/settlement";

        //上传文件ST 上传文件 /api/common/upload
        public static String upload=Host+"/api/common/upload";
//修改会员个人信息 /api/user/profile
        public static String profile=Host+"/api/user/profile";

    }
}
