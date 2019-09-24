package com.example.mizansen.Helper;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class WidthAnimationHelper extends Animation {
    private int mWidth;
    private int mStartWidth;
    private View mView;
    private boolean mstatus;

    public WidthAnimationHelper(View view, int width,boolean status) {
        mView = view;
        mWidth = width;
        mstatus = status;
        mStartWidth = view.getWidth();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newWidth = 0;

        if (mstatus){
            newWidth = mStartWidth + (int) ((mWidth + mStartWidth) * interpolatedTime);
        }else{
            newWidth = mStartWidth + (int) ((mWidth - mStartWidth) * interpolatedTime);

        }

        mView.getLayoutParams().width = newWidth;
        mView.requestLayout();
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }

}