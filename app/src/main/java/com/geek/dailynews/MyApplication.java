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
public class MyApplication extends Application {
    private static MyApplication myApplication;


    @Override
    public void onCreate() {
        super.onCreate();
        myApplication =this;
    }

    /**
     * app上下文
     * @return
     */
    public static Context getAppContext(){
        return myApplication;
    }

    /**
     * 资源上下文
     * @return
     */
    public static Resources getAppResources(){
        return myApplication.getResources();
    }





}
