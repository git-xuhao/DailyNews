package com.geek.dailynews.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.geek.dailynews.R;

/**
 * Created by Xiho on 2017/2/7.
 * 多种加载状态的View
 */

public class MultiStateView extends FrameLayout {
    private static final int UNKNOWN_VIEW = -1;

    private static final int CONTENT_VIEW = 0;

    private static final int ERROR_VIEW = 1;

    private static final int EMPTY_VIEW = 2;

    private static final int LOADING_VIEW = 3;

    //View 的各种状态
    public enum ViewState {
        CONTENT, LOADING, EMPTY, ERROR
    }

    private LayoutInflater mInflater;

    private View mContentView;

    private View mLoadingView;

    private View mErrorView;

    private View mEmptyView;

    private ViewState mViewState = ViewState.CONTENT;

    public MultiStateView(Context context) {
        super(context);
    }

    public MultiStateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);

    }
    public MultiStateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        mInflater = LayoutInflater.from(getContext());
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MultiStateView);

        int loadingViewResId = typedArray.getResourceId(R.styleable.MultiStateView_msv_loadingView, -1);
        if (loadingViewResId > -1) {
            mLoadingView = mInflater.inflate(loadingViewResId, this, false);
            addView(mLoadingView, mLoadingView.getLayoutParams());
        }

        int viewState = typedArray.getInt(R.styleable.MultiStateView_msv_viewState, UNKNOWN_VIEW);
        if (viewState != UNKNOWN_VIEW) {
            switch (viewState) {
                case CONTENT_VIEW:
                    mViewState = ViewState.CONTENT;
                    break;
                case ERROR_VIEW:
                    mViewState = ViewState.EMPTY;
                    break;

                case EMPTY_VIEW:
                    mViewState = ViewState.EMPTY;
                    break;
                case LOADING_VIEW:
                    mViewState = ViewState.LOADING;
                    break;
            }
        }
        typedArray.recycle();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(mContentView==null)
            throw new IllegalArgumentException("Content View is not defined");
        setView();
    }

    private void setView() {
        switch (mViewState){
            case LOADING:
                if(mLoadingView ==null) {
                    throw new NullPointerException("Loading View");
                }
                mLoadingView.setVisibility(View.VISIBLE);
                if(mContentView!=null)
                    mContentView.setVisibility(View.GONE);
                if (mErrorView != null)
                    mErrorView.setVisibility(View.GONE);
                if (mEmptyView != null)
                    mEmptyView.setVisibility(View.GONE);
                break;

            case EMPTY:
                if (mEmptyView == null) {
                    throw new NullPointerException("Empty View");
                }

                mEmptyView.setVisibility(View.VISIBLE);
                if (mLoadingView != null)
                    mLoadingView.setVisibility(View.GONE);
                if (mErrorView != null)
                    mErrorView.setVisibility(View.GONE);
                if (mContentView != null)
                    mContentView.setVisibility(View.GONE);
                break;

            case ERROR:
                if (mErrorView == null) {
                    throw new NullPointerException("Error View");
                }

                mErrorView.setVisibility(View.VISIBLE);
                if (mLoadingView != null)
                    mLoadingView.setVisibility(View.GONE);
                if (mContentView != null)
                    mContentView.setVisibility(View.GONE);
                if (mEmptyView != null)
                    mEmptyView.setVisibility(View.GONE);
                break;

            case CONTENT:
            default:
                if (mContentView == null) {
                    throw new NullPointerException("Content View");
                }
                mContentView.setVisibility(View.VISIBLE);
                if (mLoadingView != null)
                    mLoadingView.setVisibility(View.GONE);
                if (mErrorView != null)
                    mErrorView.setVisibility(View.GONE);
                if (mEmptyView != null)
                    mEmptyView.setVisibility(View.GONE);
                break;
        }

    }

    @Override
    public void addView(View child) {
        if (isValidContentView(child))
            mContentView = child;
        super.addView(child);
    }

    @Override
    public void addView(View child, int index) {
        if (isValidContentView(child))
            mContentView = child;
        super.addView(child, index);
    }

    @Override
    public void addView(View child, int width, int height) {
        if (isValidContentView(child))
            mContentView = child;
        super.addView(child, width, height);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        if (isValidContentView(child))
            mContentView = child;
        super.addView(child, params);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (isValidContentView(child))
            mContentView = child;
        super.addView(child, index, params);
    }

    @Override
    protected boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params) {
        if (isValidContentView(child))
            mContentView = child;
        return super.addViewInLayout(child, index, params);
    }

    @Override
    protected boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params, boolean preventRequestLayout) {
        if (isValidContentView(child))
            mContentView = child;
        return super.addViewInLayout(child, index, params, preventRequestLayout);
    }

    /**
     * 是否失效的Content View
     * @param view
     * @return
     */
    private boolean isValidContentView(View view) {
        if (mContentView != null && mContentView != view) {
            return false;
        }
        return view != mLoadingView && view != mErrorView && view != mEmptyView;
    }

    public void setViewForState(View view, ViewState state,
                                boolean switchToState) {
        switch (state) {
            case LOADING:
                if (mLoadingView != null)
                    removeView(mLoadingView);
                mLoadingView = view;
                addView(mLoadingView);
                break;

            case EMPTY:
                if (mEmptyView != null)
                    removeView(mEmptyView);
                mEmptyView = view;
                addView(mEmptyView);
                break;

            case ERROR:
                if (mErrorView != null)
                    removeView(mErrorView);
                mErrorView = view;
                addView(mErrorView);
                break;

            case CONTENT:
                if (mContentView != null)
                    removeView(mContentView);
                mContentView = view;
                addView(mContentView);
                break;
        }

        //切换状态
        if (switchToState) {
            setViewState(state);
        }
    }


    public ViewState getViewState() {
        return mViewState;
    }

    /**
     * 设置当前状态
     * @param state
     */
    public void setViewState(ViewState state) {
        if(state!=mViewState){
            mViewState = state;
            setView();
        }
    }

    /**
     * 设置自定义VIEW 的状态 （默认不改变当前状态)
     * @param view 自定义View
     * @param state 状态
     */
    public void setViewForState(View view, ViewState state) {
        setViewForState(view, state, false);
    }

    /**
     * 设置自定义View的状态
     * @param layoutRes 自定义布局
     * @param state 状态
     * @param switchToState 是否改变状态
     */
    public void setViewForState(@LayoutRes int layoutRes, ViewState state,
                                boolean switchToState) {
        if (mInflater == null)
            mInflater = LayoutInflater.from(getContext());
        View view = mInflater.inflate(layoutRes, this, false);
        setViewForState(view, state, switchToState);
    }

    /**
     * 设置自定义状态的View
     * @param layoutRes 自定义布局
     * @param state 状态 （默认不改变当前状态）
     */
    public void setViewForState(@LayoutRes int layoutRes, ViewState state) {
        setViewForState(layoutRes, state, false);
    }


}
