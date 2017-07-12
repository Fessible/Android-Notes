package com.example.com.customanim;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.Transformation;

/**
 * Created by lenovo on 2017/7/11.
 */

public class CustomAnimation extends Animation {
    private int mCenterWidth;
    private  int mCenterHeight;
    private Camera camera = new Camera();
    private  float mRotateY = 0.0f;


    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        setDuration(1000);
        //动画结束都的保留状态
        setFillAfter(true);
        //设置默认插值器
        setInterpolator(new BounceInterpolator());
        mCenterWidth = width/2;
        mCenterHeight = height/2;
    }

    public void setmRotateY(float rotateY) {
        mRotateY = rotateY;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        Matrix matrix = t.getMatrix();
        camera.save();
        //设置旋转角度,有了插值器就会缓慢到达mRotateY的位置
        camera.rotateY(mRotateY * interpolatedTime);
        camera.getMatrix(matrix);
        camera.restore();

        matrix.preTranslate(mCenterWidth, mCenterHeight);
        matrix.preTranslate(-mCenterWidth, -mCenterHeight);
    }
}
