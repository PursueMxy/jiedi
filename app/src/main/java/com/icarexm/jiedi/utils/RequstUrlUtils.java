package com.icarexm.jiedi.utils;

public class RequstUrlUtils {
    public static class URL{

        //长连接 测试
//        public static final String WEBSOCKET_HOST_AND_PORT = "ws://39.98.177.238:8284";
//
//        public static  String Host="http://jdcx.icarefz.cn";
        public static final String WEBSOCKET_HOST_AND_PORT = "ws://47.107.83.170:8284";

        public static  String Host="http://jddc.icarefz.cn";

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

        //注册会员 /api/user/register
        public static String register=Host+"/api/user/register";

        //上传文件ST 上传文件 /api/common/upload
        public static String upload=Host+"/api/common/upload";

        //忘记密码 /api/user/resetpwd

        //设置默认提现 /api/member/set_withdrawal
        public static String set_withdrawal=Host+"/api/member/set_withdrawal";

        //       我的钱包包括充值页面金额 /api/member/index
        public static  String memberindex=Host+"/api/member/index";

       // 用户信息 /api/user/getUserInfo
        public static  String getUserInfo=Host+"/api/user/getUserInfo";

        //        余额明细 /api/member/money_log
        public static String money_log=Host+"/api/member/money_log";

        //获取提现方式 /api/member/get_withdrawal
        public static String getWithdrawal=Host+"/api/member/get_withdrawal";

        //添加提现方式 /api/member/add_withdrawal
        public static String add_withdrawal=Host+"/api/member/add_withdrawal";

        // 删除提现方式 /api/member/del_withdrawal
        public static String del_witgdrawal=Host+"/api/member/del_withdrawal";

        //获取默认提现方式-主页 /api/member/get_withdrawal_default
        public static String get_withdrawal_default=Host+"/api/member/get_withdrawal_default";

        // 余额提现-司机 /api/member/withdraw
        public static String withdraw=Host+"/api/member/withdraw";
       //T 我的消息 /api/message/index
        public static  String meaasgeIndex=Host+"/api/message/index";

        //注销登录 /api/user/logout
        public static String logout=Host+"/api/user/logout";

        //清空 /api/message/del_all
        public static String messageDlt=Host+"/api/message/del_all";


        //刷新Token /api/token/refresh
        public static String tokenRrfresh=Host+"/api/token/refresh";

        // 检测Token是否过期 /api/token/check
        public static String TokenCheck=Host+"/api/token/check";
    }
}
