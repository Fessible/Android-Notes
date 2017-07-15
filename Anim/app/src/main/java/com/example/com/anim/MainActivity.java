package com.example.com.anim;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int[] mImags = {R.id.image_a, R.id.image_b, R.id.image_c, R.id.image_d, R.id.image_e};
    private List<ImageView> mImageViews = new ArrayList<ImageView>();
    //动画标记
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < mImags.length; i++) {
            ImageView imageView = (ImageView) findViewById(mImags[i]);
            imageView.setOnClickListener(this);
            mImageViews.add(imageView);

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_a:
                if (flag) {
                    startAnim();
                } else {
                    closeAnim();
                }
        }

    }


    private void closeAnim() {
        //以点击的位置为坐标轴
      ObjectAnimator animator0 = ObjectAnimator.ofFloat(mImageViews.get(0),
                "alpha", 0.5F, 1F);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mImageViews.get(1),
                "translationY", 0);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mImageViews.get(2),
                "translationX", 200F, 0);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(mImageViews.get(3),
                "translationY", -200F, 0);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(mImageViews.get(4),
                "translationX", -200F, 0);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(animator0, animator1, animator2, animator3, animator4);
        set.start();
        flag = true;

    }


    private void startAnim() {
        ObjectAnimator animn0 = ObjectAnimator.ofFloat(
                mImageViews.get(0),
                "alpha",
                1f,
                0.5f);
        ObjectAnimator animn1 = ObjectAnimator.ofFloat(
                mImageViews.get(1),
                "translationY",
                200f
        );
        ObjectAnimator animn2 = ObjectAnimator.ofFloat(
                mImageViews.get(2),
                "translationX",
                200f
        );
        ObjectAnimator animn3 = ObjectAnimator.ofFloat(
                mImageViews.get(3),
                "translationY",
                -200f
        );
        ObjectAnimator animn4 = ObjectAnimator.ofFloat(
                mImageViews.get(4),
                "translationX",
                -200f
        );

        AnimatorSet set = new AnimatorSet();
        set.setDuration(2000);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(animn0, animn1, animn2, animn3, animn4);
        set.start();
        flag = false;
    }
}
