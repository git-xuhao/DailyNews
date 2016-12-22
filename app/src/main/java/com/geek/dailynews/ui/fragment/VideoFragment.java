package com.geek.dailynews.ui.fragment;

import android.os.Bundle;

import com.geek.dailynews.R;
import com.geek.dailynews.mvpframe.base.BaseFragment;

/**
 * MyBlog: xuhaoblog.com
 * GitHub: github.com/git-xuhao
 * Created by Xiho
 * on 2016.12.21  15:10
 * Function:
 */
public class VideoFragment extends BaseFragment {

    private String title;


    public static VideoFragment getInstance(String title) {
        VideoFragment fragment = new VideoFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        fragment.title = title;
        return fragment;
    }
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_video;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {

    }
}
