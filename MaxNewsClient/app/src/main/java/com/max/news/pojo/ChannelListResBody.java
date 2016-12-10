package com.max.news.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */

public class ChannelListResBody {

    @SerializedName("channelList")
    @Expose
    private List<ChannelList> channelList = null;
    @SerializedName("ret_code")
    @Expose
    private Integer retCode;
    @SerializedName("totalNum")
    @Expose
    private Integer totalNum;

    /**
     *
     * @return
     * The channelList
     */
    public List<ChannelList> getChannelList() {
        return channelList;
    }

    /**
     *
     * @param channelList
     * The channelList
     */
    public void setChannelList(List<ChannelList> channelList) {
        this.channelList = channelList;
    }

    /**
     *
     * @return
     * The retCode
     */
    public Integer getRetCode() {
        return retCode;
    }

    /**
     *
     * @param retCode
     * The ret_code
     */
    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    /**
     *
     * @return
     * The totalNum
     */
    public Integer getTotalNum() {
        return totalNum;
    }

    /**
     *
     * @param totalNum
     * The totalNum
     */
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }
}
