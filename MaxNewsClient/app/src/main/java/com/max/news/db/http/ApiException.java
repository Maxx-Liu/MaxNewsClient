package com.max.news.db.http;

/**
 * 管理请求错误的Code状态以及对应的Error
 *
 * @auther MaxLiu
 * @time 2016/12/16
 */

public class ApiException extends RuntimeException{
    //系统调用错误
    private static final int CODE_SYSTEM_CALL = 1;
    //可调用次数或金额为0
    private static final int CODE_PERMISSION_EXPIRES = 2;
    //读取超时
    private static final int CODE_READ_TIMEOUT = 3;
    //服务端返回数据解析错误
    private static final int CODE_PARSE_ERROR = 4;
    //后端服务器DNS解析错误
    private static final int CODE_DNS_ERROR = 5;
    //服务不存在或未上线
    private static final int CODE_SERVICE_NOT_EXIST = 6;
    //系统维护
    private static final int CODE_SYSOM_MAINTENANCE = 1000;
    //showapi_appid字段必传
    private static final int CODE_APPID_LOST = 1002;
    //showapi_sign字段必传
    private static final int CODE_SIGN_LOST = 1003;
    //签名sign验证有误
    private static final int CODE_SIGN_ERROR = 1004;
    //showapi_timestamp无效
    private static final int CODE_TIME_INVALID = 1005;
    //app无权限调用接口
    private static final int CODE_PERMISSION_LOST = 1006;
    //没有订购套餐
    private static final int CODE_PAY_LOST = 1007;
    //服务商关闭对您的调用权限
    private static final int CODE_SERVICE_PROVIDER_CLOSE = 1008;
    //找不到您的应用
    private static final int CODE_APPLICATION_LOST = 1010;
    //子授权app_child_id无效
    private static final int CODE_CHILD_ID_INVALID = 1011;
    //子授权已过期或失效
    private static final int CODE_CHILD_ID_EXPIRED = 1012;
    //子授权ip受限
    private static final int CODE_CHILD_IP_RESTRICTED = 1013;

    private static String message;

    public ApiException(int resultCode){
        this(getApiExceptionMessage(resultCode));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
        message = detailMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     * @param code code
     * @return 用户可以理解的信息
     */
    private static String getApiExceptionMessage(int code){
        switch (code){
            case CODE_SYSTEM_CALL:
            case CODE_PERMISSION_EXPIRES:
            case CODE_PARSE_ERROR:
            case CODE_DNS_ERROR:
            case CODE_SERVICE_NOT_EXIST:
            case CODE_APPID_LOST:
            case CODE_SIGN_LOST:
            case CODE_SIGN_ERROR:
            case CODE_TIME_INVALID:
            case CODE_PAY_LOST:
            case CODE_SERVICE_PROVIDER_CLOSE:
            case CODE_CHILD_ID_INVALID:
            case CODE_CHILD_ID_EXPIRED:
            case CODE_CHILD_IP_RESTRICTED:
                message = "系统错误";
                break;
            case CODE_READ_TIMEOUT:
                message = "读取超时";
                break;
            case CODE_SYSOM_MAINTENANCE:
                message = "系统维护中";
                break;
            case CODE_PERMISSION_LOST:
                message = "没有权限";
                break;
            case CODE_APPLICATION_LOST:
                message = "找不到您的应用";
                break;
        }
        return message;
    }
}
