package com.geek.dailynews.base;

import android.content.Context;

/**
 * MyBlog: xuhaoblog.com
 * GitHub: github.com/git-xuhao
 * Created by Xiho
 * on 2016.12.14  16:16
 * Function:
 */
public interface IPresenter<T> {

    /**
     * 关联View
     * @param View
     * @param mContext
     */
    void attachView(T mView, Context mContext);


    /**
     * 解除View （防止内存泄漏)
     */
    void detachView();
}
