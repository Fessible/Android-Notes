package com.example.com.systeminfo;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);
        String buildInfo = SystemInfoTools.getBuildInfo();
        String propertyInfo = SystemInfoTools.getSystemPropertyInfo();
        String seperate = "--------------------\n";

        textView.setText(buildInfo + seperate + propertyInfo);
    }
}
