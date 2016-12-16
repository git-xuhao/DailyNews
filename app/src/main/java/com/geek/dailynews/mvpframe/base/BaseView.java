package com.geek.dailynews.mvpframe.base;

/**
 * MyBlog: xuhaoblog.com
 * GitHub: github.com/git-xuhao
 * Created by Xiho
 * on 2016.12.13  18:05
 * Function: BaseView
 */
public interface BaseView {

    //请求开始
    void onRequestStart();

    //请求错误
    void onRequestError(String msg);

    //请求结束
    void onRequestEnd();

    //请求网络异常
    void onRequestNetError();



}
