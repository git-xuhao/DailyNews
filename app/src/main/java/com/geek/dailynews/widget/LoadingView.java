package com.geek.dailynews.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.geek.dailynews.R;


/**
 * Created by Xiho on 2017/2/7.
 * 加載的View
 */

public class LoadingView extends FrameLayout {

    private static final int ANIMATION_DURATION = 500;
    // 距离
    private static  float mDistance = 200;

    private ShapeLoadingView mShapeLoadingView;

    private ImageView mIndicationIm;

    private TextView mLoadTextView;
    private int mTextApperance;
    private String mLoadText; //加载文字


    public float factor=1.2f;

    public LoadingView(Context context) {
        super(context);
    }
    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingView);
        mLoadText = typedArray.getString(R.styleable.LoadingView_loadingText);
        mTextApperance = typedArray.getResourceId(R.styleable.LoadingView_loadingTextAppearance,-1);
        typedArray.recycle();
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_loading,null);
        mDistance = dip2px(54f);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        mShapeLoadingView = (ShapeLoadingView) view.findViewById(R.id.shapeLoadingView);

        mIndicationIm = (ImageView) view.findViewById(R.id.indication);
        mLoadTextView = (TextView) view.findViewById(R.id.promptTv);

        if(mTextApperance!=-1){
            mLoadTextView.setTextAppearance(getContext(),mTextApperance);
        }
        setLoadingText(mLoadText);

        addView(view,layoutParams);

        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                freeFall();
            }
        },300);
    }

    /**
     * 设置加载文字
     * @param loadingText
     */
    public void setLoadingText(CharSequence loadingText){
        if(TextUtils.isEmpty(loadingText)){
            mLoadTextView.setVisibility(GONE);
        }else {
            mLoadTextView.setVisibility(View.VISIBLE);
        }
        mLoadTextView.setText(loadingText);
    }


    public int dip2px(float dipValue){
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }


    /**
     * 上抛
     */
    public void upThrow(){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mShapeLoadingView,"translationY",mDistance,0);
        ObjectAnimator scaleIndication = ObjectAnimator.ofFloat(mIndicationIm,"scaleX",0.2f,1);

        ObjectAnimator objectAnimator1 = null;
        switch (mShapeLoadingView.getShape()){
            case SHAPE_RECT:
                objectAnimator1 = ObjectAnimator.ofFloat(mShapeLoadingView, "rotation", 0, -120);

                break;
            case SHAPE_CIRCLE:
                objectAnimator1 = ObjectAnimator.ofFloat(mShapeLoadingView, "rotation", 0, 180);

                break;
            case SHAPE_TRIANGLE:

                objectAnimator1 = ObjectAnimator.ofFloat(mShapeLoadingView, "rotation", 0, 180);
                break;
        }

        objectAnimator.setDuration(ANIMATION_DURATION);
        objectAnimator1.setDuration(ANIMATION_DURATION);
        objectAnimator.setInterpolator(new DecelerateInterpolator(factor));
        objectAnimator1.setInterpolator(new DecelerateInterpolator((factor)));

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(ANIMATION_DURATION);
        animatorSet.playTogether(objectAnimator,objectAnimator1,scaleIndication);

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                freeFall();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();

    }

    /**
     * 下落
     */
    public void freeFall() {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mShapeLoadingView, "translationY", 0, mDistance);
        ObjectAnimator scaleIndication = ObjectAnimator.ofFloat(mIndicationIm, "scaleX", 1, 0.2f);

        objectAnimator.setDuration(ANIMATION_DURATION);
        objectAnimator.setInterpolator(new AccelerateInterpolator(factor));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(ANIMATION_DURATION);
        animatorSet.playTogether(objectAnimator, scaleIndication);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mShapeLoadingView.changeShape();
                upThrow();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();

    }

}
