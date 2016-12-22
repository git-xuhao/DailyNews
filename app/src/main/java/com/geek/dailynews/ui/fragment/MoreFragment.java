package com.geek.dailynews.ui.fragment;

import android.os.Bundle;

import com.geek.dailynews.R;
import com.geek.dailynews.mvpframe.base.BaseFragment;

public class MoreFragment extends BaseFragment {

    private String title;

    public MoreFragment() {

    }

    public static MoreFragment getInstance(String title) {
        MoreFragment fragment = new MoreFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.title = title;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_more;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {

    }

}
