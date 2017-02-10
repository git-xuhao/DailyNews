package com.geek.dailynews.widget;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.geek.dailynews.R;

/**
 * Created by Xiho on 2017/2/6.
 */

public class ShapeLoadingView extends View {

    private static final float genhao3 = 1.7320508075689f;
    private static  final  float mTriangle2Circle =0.25555555f;

    private Shape mDefaultShape = Shape.SHAPE_CIRCLE;

    /**
     * 用赛贝尔曲线画圆
     */
    private float mMagicNumber = 0.55228475f;


    public ShapeLoadingView(Context context) {
        super(context);
        init();
    }
    public ShapeLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public ShapeLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private boolean mIsLoading = false;

    private Paint mPaint;
    private float mControlX = 0;
    private float mControlY=0;
    private float mAnimPercent;

    private void init(){
        mPaint = new Paint();
        //画笔颜色
        mPaint.setColor(getResources().getColor(R.color.triangle));
        //抗锯齿功能
        mPaint.setAntiAlias(true);
        //填充样式
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        setBackgroundColor(getResources().getColor(R.color.view_bg));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(getVisibility()==GONE){
            return;
        }
        switch (mDefaultShape){
            // 圆形
            case SHAPE_CIRCLE:
                if(mIsLoading){
                    float magicNumber = mMagicNumber + mAnimPercent;
                    mAnimPercent+=0.12;
                    if(magicNumber+mAnimPercent>=1.9f){
                        mDefaultShape = Shape.SHAPE_RECT;
                        mIsLoading = false;
                    }
                    Path path = new Path();
                    path.moveTo(relativeXFromView(0.5f), relativeYFromView(0f));
                    path.cubicTo(relativeXFromView(0.5f + magicNumber / 2), relativeYFromView(0f),
                            relativeXFromView(1), relativeYFromView(0.5f - magicNumber / 2),
                            relativeXFromView(1f), relativeYFromView(0.5f));
                    path.cubicTo(
                            relativeXFromView(1), relativeXFromView(0.5f + magicNumber / 2),
                            relativeXFromView(0.5f + magicNumber / 2), relativeYFromView(1f),
                            relativeXFromView(0.5f), relativeYFromView(1f));
                    path.cubicTo(relativeXFromView(0.5f - magicNumber / 2), relativeXFromView(1f),
                            relativeXFromView(0), relativeYFromView(0.5f + magicNumber / 2),
                            relativeXFromView(0f), relativeYFromView(0.5f));
                    path.cubicTo(relativeXFromView(0f), relativeXFromView(0.5f - magicNumber / 2),
                            relativeXFromView(0.5f - magicNumber / 2), relativeYFromView(0),
                            relativeXFromView(0.5f), relativeYFromView(0f));


                    path.close();
                    canvas.drawPath(path, mPaint);
                    invalidate();
                } else {

                    mPaint.setColor(getResources().getColor(R.color.circle));
                    Path path = new Path();

                    float magicNumber = mMagicNumber;
                    path.moveTo(relativeXFromView(0.5f), relativeYFromView(0f));
                    path.cubicTo(relativeXFromView(0.5f + magicNumber / 2), 0,
                            relativeXFromView(1), relativeYFromView(magicNumber / 2),
                            relativeXFromView(1f), relativeYFromView(0.5f));
                    path.cubicTo(
                            relativeXFromView(1), relativeXFromView(0.5f + magicNumber / 2),
                            relativeXFromView(0.5f + magicNumber / 2), relativeYFromView(1f),
                            relativeXFromView(0.5f), relativeYFromView(1f));
                    path.cubicTo(relativeXFromView(0.5f - magicNumber / 2), relativeXFromView(1f),
                            relativeXFromView(0), relativeYFromView(0.5f + magicNumber / 2),
                            relativeXFromView(0f), relativeYFromView(0.5f));
                    path.cubicTo(relativeXFromView(0f), relativeXFromView(0.5f - magicNumber / 2),
                            relativeXFromView(0.5f - magicNumber / 2), relativeYFromView(0),
                            relativeXFromView(0.5f), relativeYFromView(0f));
                    mAnimPercent = 0;
                    path.close();
                    canvas.drawPath(path, mPaint);

                }
                break;
            //正方形
            case SHAPE_RECT:

                if (mIsLoading) {


                    mAnimPercent += 0.15;
                    if (mAnimPercent >= 1) {
                        mDefaultShape = Shape.SHAPE_TRIANGLE;
                        mIsLoading = false;
                        mAnimPercent = 1;
                    }
                    Path path = new Path();
                    path.moveTo(relativeXFromView(0.5f * mAnimPercent), 0);
                    path.lineTo(relativeYFromView(1 - 0.5f * mAnimPercent), 0);
                    float distanceX = (mControlX) * mAnimPercent;
                    float distanceY = (relativeYFromView(1f) - mControlY) * mAnimPercent;

                    path.lineTo(relativeXFromView(1f) - distanceX, relativeYFromView(1f) - distanceY);
                    path.lineTo(relativeXFromView(0f) + distanceX, relativeYFromView(1f) - distanceY);

                    path.close();


                    canvas.drawPath(path, mPaint);
                    invalidate();

                } else {
                    mPaint.setColor(getResources().getColor(R.color.rect));
                    mControlX = relativeXFromView(0.5f - genhao3 / 4);
                    mControlY = relativeYFromView(0.75f);
                    Path path = new Path();
                    path.moveTo(relativeXFromView(0f), relativeYFromView(0f));
                    path.lineTo(relativeXFromView(1f), relativeYFromView(0f));
                    path.lineTo(relativeXFromView(1f), relativeYFromView(1f));
                    path.lineTo(relativeXFromView(0f), relativeYFromView(1f));
                    path.close();
                    mAnimPercent = 0;
                    canvas.drawPath(path, mPaint);

                }
                break;
            //三角形
            case SHAPE_TRIANGLE:

                if (mIsLoading) {
                    mAnimPercent += 0.1611113;
                    // triangle to circle
                    Path path = new Path();
                    path.moveTo(relativeXFromView(0.5f), relativeYFromView(0f));

                    if (mAnimPercent >= 1) {
                        mDefaultShape = Shape.SHAPE_CIRCLE;
                        mIsLoading = false;
                        mAnimPercent=1;
                    }
                    float controlX = mControlX - relativeXFromView(mAnimPercent* mTriangle2Circle)
                            * genhao3;

                    float controlY = mControlY - relativeYFromView(mAnimPercent* mTriangle2Circle);


                    path.quadTo(relativeXFromView(1) - controlX, controlY, relativeXFromView(0.5f + genhao3 / 4), relativeYFromView(0.75f));


                    path.quadTo(relativeXFromView(0.5f), relativeYFromView(0.75f + 2 * mAnimPercent* mTriangle2Circle), relativeXFromView(0.5f - genhao3 / 4), relativeYFromView(0.75f));

                    path.quadTo(controlX, controlY, relativeXFromView(0.5f), relativeYFromView(0f));
                    path.close();
                    canvas.drawPath(path, mPaint);

                    invalidate();

                } else {
                    Path path = new Path();
                    mPaint.setColor(getResources().getColor(R.color.triangle));

                    path.moveTo(relativeXFromView(0.5f), relativeYFromView(0f));
                    path.lineTo(relativeXFromView(1), relativeYFromView(genhao3 / 2f));
                    path.lineTo(relativeXFromView(0), relativeYFromView(genhao3/2f));

                    mControlX = relativeXFromView(0.5f - genhao3 / 8.0f);
                    mControlY = relativeYFromView(3 / 8.0f);
                    mAnimPercent = 0;
                    path.close();
                    canvas.drawPath(path, mPaint);
                }
                break;
        }



    }

    private float relativeXFromView(float percent) {
        return getWidth() * percent;
    }

    private float relativeYFromView(float percent) {
        return getHeight() * percent;
    }

    /**
     * 改变形状
     */
    public void changeShape() {
        mIsLoading = true;

        invalidate();
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if(visibility==VISIBLE){
            invalidate();
        }
    }

    public enum Shape {
        SHAPE_TRIANGLE, SHAPE_RECT, SHAPE_CIRCLE
    }

    public Shape getShape(){
        return mDefaultShape;
    }
}
