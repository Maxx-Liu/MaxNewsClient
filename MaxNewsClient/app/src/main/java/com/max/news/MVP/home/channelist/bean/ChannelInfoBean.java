package com.max.news.MVP.home.channelist.bean;

/**
 * 请求到的数据信息
 *
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
}
