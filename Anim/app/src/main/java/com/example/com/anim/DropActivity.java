package com.example.com.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by rhm on 2017/7/15.
 */

public class DropActivity extends Activity {
    private  LinearLayout mHiddenLayout;
    private   float mDensity;//像素
    private  int mHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drop_main);
        mHiddenLayout = (LinearLayout) findViewById(R.id.hiden_view);
        mDensity = getResources().getDisplayMetrics().density;

        //布局高度
        mHeight = (int) (mDensity * 40 + 0.5);
    }

    public void onClick(View view) {
        if (mHiddenLayout.getVisibility() == View.GONE) {
            //打开
            animOpen(mHiddenLayout);
        } else {
            //关闭
            ainmClose(mHiddenLayout);
        }
    }

    private void ainmClose(final LinearLayout view) {
        int height = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, height, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.GONE);
            }
        });
       //view.setVisibility(View.GONE);
        animator.start();
    }

    private void animOpen(LinearLayout view) {
        view.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(view, 0, mHeight);
        animator.start();
    }

    private ValueAnimator createDropAnimator(final LinearLayout view, int start, int end) {

        final ValueAnimator animator = ValueAnimator.ofInt( start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams params = view.getLayoutParams();
                params.height = value;
                view.setLayoutParams(params);
            }
        });

        return animator;

    }
}