package com.max.news.MVP.home.channelist.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 新闻 Item 详情信息
 *
 * @auther MaxLiu
 * @time 2017/2/22
 */

public class ContentlistBean {

    private boolean havePic;
    private String pubDate;
    private String title;
    private String channelName;
    private String desc;
    private String source;
    private String channelId;
    private String nid;
    private String link;
    private int sentiment_display;
    private List<String> allList;
    private List<?> imageurls;

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getSentiment_display() {
        return sentiment_display;
    }

    public void setSentiment_display(int sentiment_display) {
        this.sentiment_display = sentiment_display;
    }

    public List<String> getAllList() {
        return allList;
    }

    public void setAllList(List<String> allList) {
        this.allList = allList;
    }

    public List<?> getImageurls() {
        return imageurls;
    }

    public void setImageurls(List<?> imageurls) {
        this.imageurls = imageurls;
    }

    public boolean isHavePic() {
        return havePic;
    }

    public void setHavePic(boolean havePic) {
        this.havePic = havePic;
    }
}