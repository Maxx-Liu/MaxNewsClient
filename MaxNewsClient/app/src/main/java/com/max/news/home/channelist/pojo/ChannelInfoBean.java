package com.max.news.home.channelist.pojo;

import java.util.List;

/**
 * @auther MaxLiu
 * @time 2016/12/16
 */

public class ChannelInfoBean {

    private int ret_code;
    private Pagebean pagebean;

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public Pagebean getPagebean() {
        return pagebean;
    }

    public void setPagebean(Pagebean pagebean) {
        this.pagebean = pagebean;
    }

    public  class Pagebean {
        private int allPages;
        private int currentPage;
        private int allNum;
        private int maxResult;
        private List<ContentlistBean> contentlist;

        public int getAllPages() {
            return allPages;
        }

        public void setAllPages(int allPages) {
            this.allPages = allPages;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getAllNum() {
            return allNum;
        }

        public void setAllNum(int allNum) {
            this.allNum = allNum;
        }

        public int getMaxResult() {
            return maxResult;
        }

        public void setMaxResult(int maxResult) {
            this.maxResult = maxResult;
        }

        public List<ContentlistBean> getContentlist() {
            return contentlist;
        }

        public void setContentlist(List<ContentlistBean> contentlist) {
            this.contentlist = contentlist;
        }

        public  class ContentlistBean {

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
        }
    }
}
