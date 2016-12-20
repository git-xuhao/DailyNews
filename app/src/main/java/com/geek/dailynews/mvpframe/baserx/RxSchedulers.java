package com.geek.dailynews.mvpframe.baserx;


import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * MyBlog: xuhaoblog.com
 * GitHub: github.com/git-xuhao
 * Created by Xiho
 * on 2016.12.16  13:33
 * Function: RxJava调度管理
 */
public class RxSchedulers {

    public static <T> Observable.Transformer<T,T> io_main(){

        return new Observable.Transformer<T,T>(){
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable
                        //生产线程
                        .subscribeOn(Schedulers.io())
                        //消费线程
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
