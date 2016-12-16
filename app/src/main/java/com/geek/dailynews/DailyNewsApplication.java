package com.geek.dailynews;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

/**
 * MyBlog: xuhaoblog.com
 * GitHub: github.com/git-xuhao
 * Created by Xiho
 * on 2016.12.16  16:31
 * Function:
 */
public class DailyNewsApplication extends Application {
    private static DailyNewsApplication mDailyNewsApplication;


    @Override
    public void onCreate() {
        super.onCreate();
        mDailyNewsApplication=this;
    }

    /**
     * app上下文
     * @return
     */
    public static Context getAppContext(){
        return mDailyNewsApplication;
    }

    /**
     * 资源上下文
     * @return
     */
    public static Resources getAppResources(){
        return mDailyNewsApplication.getResources();
    }





}
