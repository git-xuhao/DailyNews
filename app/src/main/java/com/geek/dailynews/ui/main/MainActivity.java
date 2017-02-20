package com.geek.dailynews.ui.main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.geek.dailynews.R;
import com.geek.dailynews.bean.TabEntity;
import com.geek.dailynews.mvpframe.base.BaseActivity;
import com.geek.dailynews.ui.fragment.HomeFragment;
import com.geek.dailynews.ui.fragment.MoreFragment;
import com.geek.dailynews.ui.fragment.PictureFragment;
import com.geek.dailynews.ui.fragment.VideoFragment;
import com.geek.dailynews.utils.LogUtils;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 主Activity
 */
public class MainActivity extends BaseActivity {

    private HomeFragment mHomeFragment;
    private VideoFragment mVideoFragment;
    private PictureFragment mPictureFragment;
    private MoreFragment mMoreFragment;

    private String[] mTitles = {"头条","社会","国内","娱乐","体育","军事","科技","财经","时尚"};

    private int[] mIconUnSelectIds = {R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private int[] mIconSelectIds = {R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    // 默认为0;
    private int mCurrIndex = 0;


    @Bind(R.id.fl_container)
    FrameLayout flContainer;
    @Bind(R.id.tab_layout)
    CommonTabLayout tabLayout;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        initTab();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.logd("onCreate...........");
        if(savedInstanceState!=null) {
            LogUtils.loge("onRestore enter...."+mCurrIndex);
            mCurrIndex = savedInstanceState.getInt("currTabIndex");
        }
        tabLayout.setCurrentTab(mCurrIndex);
        switchFragment(mCurrIndex);



    }

    /**
     * 初始化底部菜单
     */
    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnSelectIds[i]));
        }
        //为Tab赋值数据
        tabLayout.setTabData(mTabEntities);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                //切换Fragment
                switchFragment(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    /**
     * 切换Fragment
     * @param position 下标
     */
    private void switchFragment(int position) {
        // Fragment事务管理器
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragments(transaction);
        LogUtils.logd("current position tab"+position);
        switch (position){
            case 0: //首页
                if(mHomeFragment==null) {
                    mHomeFragment = HomeFragment.getInstance(mTitles[0]);
                    transaction.add(R.id.fl_container,mHomeFragment,"home");
                }else{
                    transaction.show(mHomeFragment);
                }
                break;
            case 1: //视频
                if(mVideoFragment==null) {
                    mVideoFragment = VideoFragment.getInstance(mTitles[1]);
                    transaction.add(R.id.fl_container,mVideoFragment,"video");
                }else{
                    transaction.show(mVideoFragment);
                }
                break;

            case 2: //图库
                if(mPictureFragment==null) {
                    mPictureFragment = PictureFragment.getInstance(mTitles[2]);
                    transaction.add(R.id.fl_container,mPictureFragment,"picture");
                }else{
                    transaction.show(mPictureFragment);
                }
                break;

            case 3: //更多
                if(mMoreFragment==null) {
                    mMoreFragment = MoreFragment.getInstance(mTitles[3]);
                    transaction.add(R.id.fl_container,mMoreFragment,"more");
                }else{
                    transaction.show(mMoreFragment);
                }
                break;
            default:
                break;
        }
        mCurrIndex =position;
        tabLayout.setCurrentTab(mCurrIndex);
        transaction.commitAllowingStateLoss();

    }

    /**
     * 隐藏所有的Fragment
     * @param transaction transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (null != mHomeFragment) {
            transaction.hide(mHomeFragment);
        }
        if (null != mVideoFragment) {
            transaction.hide(mVideoFragment);
        }
        if (null != mPictureFragment) {
            transaction.hide(mPictureFragment);
        }
        if (null != mMoreFragment) {
            transaction.hide(mMoreFragment);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //记录fragment的位置,防止崩溃 activity被系统回收时，fragment错乱
        LogUtils.loge("onSaveInstanceState crash..."+mCurrIndex);
        if (tabLayout != null) {
            outState.putInt("currTabIndex", mCurrIndex);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }
}
