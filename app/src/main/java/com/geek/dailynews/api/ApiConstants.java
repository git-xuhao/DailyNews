package com.geek.dailynews.api;

/**
 * Created by Xiho on 2017/2/14.
 */

public class ApiConstants {

    /**
     * 新闻资讯类
     */
    public static final String APP_KEY="e8ab79ef9b07d71b3009e7ed009ae2ec";

    public static final String TOU_TIAO ="http://v.juhe.cn/";


    /**
     * 获取对应的Host类型
     * @param hostType
     * @return
     */
    public static String getHost(int hostType){
        String host="";
        switch (hostType){
            case HostType.TYPE_NEWS:
                host =TOU_TIAO;
            break;
            case HostType.TYPE_VEDIO:
                host = TOU_TIAO;
            break;
            case HostType.TYPE_PHOTO:
                host = TOU_TIAO;
            break;
            default:
                host="";

        }
        return host;
    }

}
