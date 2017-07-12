package com.example.com.objectanimator;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout);
        ScaleAnimation animation = new ScaleAnimation(0, 1, 0,1);
        animation.setDuration(2000);
        LayoutAnimationController lac = new LayoutAnimationController(animation, 0.5f);
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        linearLayout.setLayoutAnimation(lac);
*/

    }
}
