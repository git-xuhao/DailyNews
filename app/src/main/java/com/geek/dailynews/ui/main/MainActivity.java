package com.geek.dailynews.ui.main;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;

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

    private String[] mTitles={"资讯","视频","图库","我的"};

    private int[] mIconUnSelectIds = {
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    private int[] mIconSelectIds = {
            R.mipmap.ic_launcher,R.mipmap.ic_launcher, R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    // 当前显示的index
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
        switch (position){
            case 0:
                if(mHomeFragment==null) {
                    mHomeFragment = new HomeFragment();
                    transaction.add(R.id.fl_container,mHomeFragment,"home");
                }else{
                    transaction.show(mHomeFragment);
                }
                break;
            case 1:
                if(mVideoFragment==null) {
                    mVideoFragment = new VideoFragment();
                    transaction.add(R.id.fl_container,mVideoFragment,"video");
                }else{
                    transaction.show(mVideoFragment);
                }
                break;

            case 2:
                if(mPictureFragment==null) {
                    mPictureFragment = new PictureFragment();
                    transaction.add(R.id.fl_container,mPictureFragment,"picture");
                }else{
                    transaction.show(mPictureFragment);
                }
                break;

            case 3:
                if(mMoreFragment==null) {
                    mMoreFragment = new MoreFragment();
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
        LogUtils.loge("onSaveInstanceState crash...");
        if (tabLayout != null) {
            outState.putInt("currTabIndex", tabLayout.getCurrentTab());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mCurrIndex = savedInstanceState.getInt("currTabIndex");
    }

    /**
     * 监听返回键
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onResume() {
        super.onResume();
        tabLayout.setCurrentTab(mCurrIndex);
    }
}
