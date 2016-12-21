package com.geek.dailynews.ui.fragment;

import android.os.Bundle;

import com.geek.dailynews.R;
import com.geek.dailynews.mvpframe.base.BaseFragment;

/**
 * MyBlog: xuhaoblog.com
 * GitHub: github.com/git-xuhao
 * Created by Xiho
 * on 2016.12.20  19:27
 * Function: 首页
 */
public class HomeFragment extends BaseFragment {


    private static HomeFragment getInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {

    }
}
