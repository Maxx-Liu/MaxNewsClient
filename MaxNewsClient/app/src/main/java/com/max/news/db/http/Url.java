package com.max.news.db.http;

/**
 * 该APP用到的API集合（包括 URL APPID SIGN PARAMS）
 */

public class Url {
    public static final String BASE_URL= "http://route.showapi.com/";

    public static final String URL_SEARCH_NEWS = "109-35";
    public static final String URL_CHANNEL_NEWS= "109-34";

    //频道信息的请求参数
    public static final String PARAM_CHANNEL_ID = "channelId";
    public static final String PARAM_CHANNEL_NAME = "channelName";
    public static final String PARAM_CHANNEL_TITLE = "title";
    public static final String PARAM_CHANNEL_PAGE = "page";
    public static final String PARAM_CHANNEL_NEED_CONTENT = "needContent";
    public static final String PARAM_CHANNEL_NEED_HTML = "needHtml";
    public static final String PARAM_CHANNEL_NEED_ALLLIST = "needAllList";
    public static final String PARAM_CHANNEL_NEED_RESULT = "needResult";

}
