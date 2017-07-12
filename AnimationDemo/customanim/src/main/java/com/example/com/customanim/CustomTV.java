package com.example.com.customanim;

import android.graphics.Matrix;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by rhm on 2017/7/11.
 */

public class CustomTV extends Animation {

    private  int mCenterWidth;
    private  int mCenterHeight;

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        //设置时长
        setDuration(1000);
        //设置动画后保留状态
        setFillAfter(true);
        //设置插值器
        setInterpolator(new AccelerateInterpolator());
        mCenterWidth = width/2;
        mCenterHeight = height/2;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        //获取矩阵
        Matrix matrix = t.getMatrix();
        //1- (1-interpolatedTime)缓慢的关闭
        matrix.preScale(1, 1-interpolatedTime , mCenterWidth, mCenterHeight);
    }
}
