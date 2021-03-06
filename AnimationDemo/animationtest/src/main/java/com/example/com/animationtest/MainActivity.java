package com.example.com.animationtest;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnObjectAnimator(View view) {
        /****************ObjectAnimator********************/
//        WrapperView wrapperView = new WrapperView(view);
       /* ObjectAnimator animator = ObjectAnimator.ofFloat(view,
                "translationX",
                300);
        animator.setDuration(1000);
        animator.start();*/
//      ObjectAnimator.ofInt(wrapperView,"swidth",300).setDuration(5000).start();


        /****************PropertyValueHolder*************************/
       /* PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat("translationX",200f);
        PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat("scaleX", 1f, 0, 1f);
        PropertyValuesHolder pvh3 = PropertyValuesHolder.ofFloat("scaleY", 1f, 0, 1f);
        ObjectAnimator.ofPropertyValuesHolder(view,pvh1, pvh2, pvh3).setDuration(1000).start();*/

       /****************ValueAnimator*********************/
     /*   final ValueAnimator animator = ValueAnimator.ofFloat(0, 100);
        animator.setTarget(view);
        animator.setDuration(1000).start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Float value = (Float) animator.getAnimatedValue();
                Log.d("tag",value + " ");
            }
        });*/

     /******************AnimatorSet**************************/
     /*   ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", 200);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f, 1f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0f, 1f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        set.playSequentially(animator, animator1, animator2);
        set.start();*/

        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.scale);
        animator.setTarget(view);
        animator.start();
    }

    class WrapperView{
        private  View mtarget;

        public WrapperView(View target) {
            mtarget = target;
        }

        public int getSwidth(){
            return  mtarget.getLayoutParams().width;
        }

        public void setSwidth(int width) {
            mtarget.getLayoutParams().width = width;
            mtarget.requestLayout();
        }
    }
}
