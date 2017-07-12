package com.example.com.customanim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnCustomTV(View view) {
        CustomTV customTV = new CustomTV();
        view.startAnimation(customTV);
    }

    public void btnCustomAnim(View view) {
        CustomAnimation animation = new CustomAnimation();
        animation.setmRotateY(50);
        view.startAnimation(animation);

    }
}
