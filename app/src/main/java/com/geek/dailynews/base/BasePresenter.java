package com.geek.dailynews.base;

import android.content.Context;

/**
 * MyBlog: xuhaoblog.com
 * GitHub: github.com/git-xuhao
 * Created by Xiho
 * on 2016.12.14  16:18
 * Function:
 */
public abstract class BasePresenter<T> implements IPresenter<T> {
    public Context mContext;
    public T mView;


    /**
     * 关联View
     * @param mView
     * @param mContext
     */
    @Override
    public void attachView(T mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }

    /**
     * 解除View（防止内存泄漏）
     */
    @Override
    public void detachView() {
        this.mView=null;
    }
}
