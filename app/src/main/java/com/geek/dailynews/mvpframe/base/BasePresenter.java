package com.geek.dailynews.mvpframe.base;

import android.content.Context;

import com.geek.dailynews.mvpframe.baserx.RxManager;

/**
 * MyBlog: xuhaoblog.com
 * GitHub: github.com/git-xuhao
 * Created by Xiho
 * on 2016.12.14  16:18
 * Function:
 */
public abstract class BasePresenter<M, V> {
    public Context mContext;
    public M mModel;
    public V mView;
    public RxManager mRxManager = new RxManager();

    public void setVM(V v, M m) {
        this.mView = v;
        this.mModel = m;
    }

    public void onDestroy() {
        mRxManager.clear();
    }
}
