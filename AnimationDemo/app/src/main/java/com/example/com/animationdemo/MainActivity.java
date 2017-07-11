package com.example.com.animationdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnAlpha(View view) {
        //从0到1
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(1000);
        view.startAnimation(alphaAnimation);
    }

    public void btnRotate(View view) {
        //以(100,100)为中心旋转360
        //RotateAnimation rotateAnimation = new RotateAnimation(90, 360,100,100);
        //以自身为中心旋转
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(1000);
        view.startAnimation(rotateAnimation);
    }

    public void btnTranslate(View view) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 200, 0, 300);
        translateAnimation.setDuration(1000);
        view.startAnimation(translateAnimation);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(MainActivity.this,"start Animation",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(MainActivity.this,"End Animation",Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Toast.makeText(MainActivity.this,"repeat Animation",Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void btnScale(View view) {
        //ScaleAnimation scaleAnimation = new ScaleAnimation(0, 2, 0,2);
        //以自身为中心缩放
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0,1, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(1000);
        view.startAnimation(scaleAnimation);
    }

    public void btnAnimationSet(View view) {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(1000);

        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(1000);
        animationSet.addAnimation(aa);

        TranslateAnimation ta = new TranslateAnimation(0, 100, 0, 200);
        ta.setDuration(1000);
        animationSet.addAnimation(ta);
        view.startAnimation(animationSet);
    }
}
