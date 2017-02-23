package com.geek.dailynews.ui.news.contract;

import com.geek.dailynews.bean.NewsBean;
import com.geek.dailynews.mvpframe.base.BaseModel;

import java.util.List;

import rx.Observable;

/**
 * Created by Xiho on 2017/2/23.
 * 新闻列表的协议
 */

public interface NewsListContract {

    interface Model extends BaseModel{
        Observable<List<NewsBean>> getNewsListData(String type,String appKey);
    }

}
