package com.max.news.http;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 对获取到的 Code Error Data(JSON数据)做封装.
 *
 * 不同的Code 对应不同的错误信息
 * Error:错误提示的展示
 *
 * Code: 易源返回标志,0为成功，其他为失败。
 * 0成功
 * -1，系统调用错误
 * -2，可调用次数或金额为0
 * -3，读取超时
 * -4，服务端返回数据解析错误
 * -5，后端服务器DNS解析错误
 * -6，服务不存在或未上线
 * -1000，系统维护
 * -1002，showapi_appid字段必传
 * -1003，showapi_sign字段必传
 * -1004，签名sign验证有误
 * -1005，showapi_timestamp无效
 * -1006，app无权限调用接口
 * -1007，没有订购套餐
 * -1008，服务商关闭对您的调用权限
 * -1010，找不到您的应用
 * -1011，子授权app_child_id无效
 * -1012，子授权已过期或失效
 * -1013，子授权ip受限
 */

public class HttpResult<T> {
    @SerializedName("showapi_res_code")
    @Expose
    private Integer mCode;
    @SerializedName("showapi_res_error")
    @Expose
    private String mError;
    @SerializedName("showapi_res_body")
    @Expose
    private T mData;

    /**
     *
     * @return
     * The Code
     */
    public Integer getCode() {
        return mCode;
    }

    /**
     *
     * @param mCode
     * The mCode
     */
    public void setCode(Integer mCode) {
        this.mCode = mCode;
    }

    /**
     *
     * @return
     * The Error
     */
    public String getError() {
        return mError;
    }

    /**
     *
     * @param mError
     * The Error
     */
    public void setError(String mError) {
        this.mError = mError;
    }

    /**
     *
     * @return
     * The Data
     */
    public T getData() {
        return mData;
    }

    /**
     *
     * @param mData
     * The Data
     */
    public void setData(T mData) {
        this.mData = mData;
    }
}
