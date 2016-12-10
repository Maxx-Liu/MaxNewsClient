package com.max.news.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */

public class ChannelListBean {
    @SerializedName("showapi_res_code")
    @Expose
    private Integer showapiResCode;
    @SerializedName("showapi_res_error")
    @Expose
    private String showapiResError;
    @SerializedName("showapi_res_body")
    @Expose
    private ChannelListResBody mChannelListResBody;

    /**
     *
     * @return
     * The showapiResCode
     */
    public Integer getShowapiResCode() {
        return showapiResCode;
    }

    /**
     *
     * @param showapiResCode
     * The showapi_res_code
     */
    public void setShowapiResCode(Integer showapiResCode) {
        this.showapiResCode = showapiResCode;
    }

    /**
     *
     * @return
     * The showapiResError
     */
    public String getShowapiResError() {
        return showapiResError;
    }

    /**
     *
     * @param showapiResError
     * The showapi_res_error
     */
    public void setShowapiResError(String showapiResError) {
        this.showapiResError = showapiResError;
    }

    /**
     *
     * @return
     * The mChannelListResBody
     */
    public ChannelListResBody getChannelListResBody() {
        return mChannelListResBody;
    }

    /**
     *
     * @param channelListResBody
     * The showapi_res_body
     */
    public void setChannelListResBody(ChannelListResBody channelListResBody) {
        this.mChannelListResBody = channelListResBody;
    }
}
