package com.hyt.myproject.common.constant;

/**
 * Created by mark on 19/06/2017.
 */
public class StatusConstants {
    //================================================================================================================//
    //================================================== 订单表相关  ==================================================//
    //================================================================================================================//
    /**
     * 订单退款 - 状态
     **/
    public static int TRADE_NOT_NEED_REFUND = 0;  //默认状态
    public static int TRADE_REFUND_SUCCESS = 1;  //退款成功
    public static int TRADE_REFUND_APPROVE = 2;  //退款审核
    public static int TRADE_REFUND_REFUSE = 3;  //退款拒绝
    public static int TRADE_REFUND_FAILURE = -1; //退款失败

    /**
     * 订单对账 - 状态
     **/
    public static int TRADE_NOT_CHECK = 0;  //未对
    public static int TRADE_CHECKED_SUCCESS = 1;  //已对
    public static int TRADE_CHECKED_EXCEPTION = -1; //异常
    public static int TRADE_CHECKED_UNKONW = 99; // 未知

    /**
     * 订单通知 - 状态
     **/
    public static int TRADE_WAIT_NOTIFY = 0;  //等待通知
    public static int TRADE_NOTIFY_SUCCESS = 1;  //通知成功
    public static int TRADE_HAD_NOTIFY = 2;  //已通知
    public static int TRADE_NOTIFY_FAILURE = -1; //通知失败
    public static int TRADE_NOTIFY_EXCEPTION = -9; //通知异常

    /**
     * 订单交易 - 状态
     **/
    public static int TRADE_WAIT_TO_BANK = 0;                // 未提交到银行
    public static int TRADE_SUCCESS = 1;              // 交易成功
    public static int TRADE_WAIT_BANK_HANDLE = 2;        // 已提交待处理
    public static int TRADE_FAILURE = -1;              // 交易失败
    public static int TRADE_EXCEPTION = -9;          // 异常订单
    public static int TRADE_CLOSE = -2;                  // 订单自动关闭

    /**
     * 代付订单通知 - 状态
     **/
    public static int TRANSFER_WAIT_NOTIFY = 0;  //等待通知
    public static int TRANSFER_NOTIFY_SUCCESS = 1;  //通知成功
    public static int TRANSFER_HAD_NOTIFY = 2;  //已通知
    public static int TRANSFER_NOTIFY_FAILURE = -1; //通知失败
    public static int TRANSFER_NOTIFY_EXCEPTION = -9; //通知异常

    /**
     * 代付订单交易 - 状态
     **/
    public static int TRANSFER_HAS_SUBMITED                     = 0; //提现-已申请
    public static int TRANSFER_PAY_SUCCESS                      = 1; //提现-提现成功
    public static int TRANSFER_HAS_CONFIRMED                    = 2; //提现-已初审
    public static int TRANSFER_HAS_REVIEWED                     = 3; //提现-已复审
    public static int TRANSFER_HAS_PAID                         = 4; //提现-已提交
    public static int TRANSFER_WAIT_REFUND                      = 5; //提现等待退款
    public static int TRANSFER_PAY_UNKNOW                       = 6; //提现-提现未知
    public static int TRANSFER_PAY_FAILURE                      = -1; //提现-提现失败
    public static int TRANSFER_PAY_CLOSE                        = -2; //提现-提现关闭
    public static int TRANSFER_PAY_EXCEPTION                    = -9; //提现-提现异常
    public static int TRANSFER_PAY_RISK                         = -8; //提现-风控订单


    //================================================================================================================//
    //================================================== 代付表相关  ==================================================//
    //================================================================================================================//
    /**
     * 代付退款 - 状态
     **/
    public static int TRANSFER_NOT_NEED_REFUND = 0;  //默认状态
    public static int TRANSFER_REFUND_SUCCESS = 1;  //退款成功
    public static int TRANSFER_REFUND_FAILURE = -1; //退款失败

    /**
     * 代付对账 - 状态 对账状态： 异常 = -1  未对 = 0 已对 = 1
     **/
    public static int TRANSFER_NOT_CHECK = 0;  //未对
    public static int TRANSFER_CHECKED_SUCCESS = 1;  //已对
    public static int TRANSFER_CHECKED_EXCEPTION = -1; //异常
    public static int TRANSFER_CHECKED_UNKONW = 99; /** 提现-提现未知 */

    /**
     * 代付扣款 - 状态
     **/
    public static int TRANSFER_DEBIT_SUCCESS = 1; // 代付扣款成功
    public static int TRANSFER_UNDEBIT = 0;       // 代付未扣款

    /**
     * 代付来源 - 状态
     **/
    public static int TRANSFER_SOURCE_API = 1;       // API提交代付
    public static int TRANSFER_SOURCE_HANDLE = 0;    // 手动提交代付
    public static int TRANSFER_NOTIFY_SOURCE_MERCHANT  = 1; //商户手动通知
}

