package com.example.com.anim;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

/**
 * Created by rhm on 2017/7/15.
 */

public class TimeActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_main);
    }

    public void tvTimer(final View view) {
        //时间为0-100
         ValueAnimator animator = ValueAnimator.ofInt(0, 100);
        animator.addUpdateListener(
                new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //一定要变为字符串类型，这里使用的是这个方法中的valueAnimator
                ((TextView) view).setText((Integer) valueAnimator.getAnimatedValue()+" ");
            }
        });

        animator.setDuration(3000);
        animator.start();
    }
}
