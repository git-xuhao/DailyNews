package com.geek.dailynews.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.geek.dailynews.R;
import com.geek.dailynews.mvpframe.base.BaseFragment;
import com.geek.dailynews.utils.ToastUitls;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import butterknife.Bind;

/**
 * MyBlog: xuhaoblog.com
 * GitHub: github.com/git-xuhao
 * Created by Xiho
 * on 2016.12.20  19:27
 * Function: 首页
 */
public class HomeFragment extends BaseFragment {
    @Bind(R.id.materialViewPager)
    MaterialViewPager mViewPager;
    @Bind(R.id.fab)
    FloatingActionButton mFab;

    private Toolbar toolbar;
    private String title;

    public static HomeFragment getInstance(String title) {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        fragment.title = title;
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
        if(toolbar!=null){

        }

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position % 4){

                    default:
                        return RecyclerViewFragment.newInstance();
                }

            }

            @Override
            public int getCount() {
                return 4;
            }


            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 4){
                    case 0:
                        return "头条";

                    case 1:
                        return "社会";
                    case 2:
                        return "科技";

                    case 3:
                        return "体育";
                }

                return "";

            }
        });

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.green,
                                "http://img3.imgtn.bdimg.com/it/u=147401387,631048534&fm=23&gp=0.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blue,
                                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.cyan,
                                "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.red,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                }

                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

//        View logo = rootView.findViewById(R.id.logo_white);
//        if (logo != null) {
//            logo.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mViewPager.notifyHeaderChanged();
//                    ToastUitls.showShort("Yes, the title is clickable");
//                }
//            });
//        }




    }



}
