package com.geek.dailynews.api;

import android.database.Observable;

import com.geek.dailynews.bean.NewsBean;
import com.geek.dailynews.bean.Result;
import com.geek.dailynews.bean.ResultBean;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Xiho on 2017/2/14.
 * Api 接口服务类
 */

public interface ApiService {

    /**
     * 获取新闻列表
     * @param cacheControl
     * @param type
     * @param key
     * @return
     */
    @GET("toutiao/index")
    Observable<Result<ResultBean<ArrayList<NewsBean>>>> getNewsList(
            @Header("Cache-Control") String cacheControl,
            @Query("type") String type,
            @Query("key") String key);



}
