package com.icarexm.jiediuser.utils;

public class RequstUrlUtils {
    public static class URL{

        //长连接 测试
        public static final String WEBSOCKET_HOST_AND_PORT = "ws://47.107.83.170:8284";
//
//        public static  String Host="http://jdcx.icarefz.cn";
//        public static final String WEBSOCKET_HOST_AND_PORT = "ws://47.107.83.170:8284";

        public static  String Host="http://jddc.icarefz.cn";

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

//       我的钱包包括充值页面金额 /api/member/index
        public static  String memberindex=Host+"/api/member/index";

//        会员充值 /api/member/recharge
        public static String memberrecharge=Host+"/api/member/recharge";

        // 微信授权登陆 /api/user/weixinlogin
        public static String weixinlogin=Host+"/api/user/weixinlogin";

        //我的消息 /api/message/index
        public static String messageindex=Host+"/api/message/index";

        //紧急联系人 页面 /api/emergencycontact/index
        public static String emergencycontactIndex=Host+"/api/emergencycontact/index";

        //新增紧急联系人 /api/emergencycontact/add
        public static String emergencycontactAdd=Host+"/api/emergencycontact/add";

        //删除联系人 /api/emergencycontact/delete
        public static String emergencycontactDelete=Host+"/api/emergencycontact/delete";


        //注销登录 /api/user/logout
        public static String logout=Host+"/api/user/logout";


        //订单详情[根据行程ID] /api/order/order_Info
        public static String order_info=Host+"/api/order/order_Info";

        //清空 /api/message/del_all
        public static String messageDlt=Host+"/api/message/del_all";

        // 根据订货获取可用优惠券 /api/coupon/order_coupon
        public static String order_coupon=Host+"/api/coupon/order_coupon";

        // 根据优惠券id获取优惠券详情 /api/coupon/couponInfo
        public static  String couponInfo=Host+"/api/coupon/couponInfo";

        // 检测Token是否过期 /api/token/check
        public static String TokenCheck=Host+"/api/token/check";
    }
}
