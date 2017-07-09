package com.example.com.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {
    private GridLayout gridLayout;
    private int mEdWidth;
    private int mEdHeight;
    private EditText[] editTexts = new EditText[9];
    private float[] matrixs = new float[9];
    private MyView myview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        myview = (MyView) findViewById(R.id.image);
        gridLayout = (GridLayout) findViewById(R.id.group);
        gridLayout.post(new Runnable() {
            @Override
            public void run() {
                //3*3的矩阵
                mEdHeight = gridLayout.getHeight() / 3;
                mEdWidth = gridLayout.getWidth() / 3;
                addEdits();
                //初始化
                initImageMatrix();
            }
        });
    }

    private void initImageMatrix() {
        for (int i = 0; i < 9; i++) {
            if (i % 4 == 0) {
                editTexts[i].setText(String.valueOf(1));
            } else {
                editTexts[i].setText(String.valueOf(0));
            }
        }
    }


    //设置gridlayout的布局
    private void addEdits() {
        for (int i = 0; i < 9; i++) {
            EditText edit = new EditText(this);
            editTexts[i] = edit;
            gridLayout.addView(edit, mEdWidth, mEdHeight);
        }
    }

    //设置点击事件
    public void btnChange(View view) {
      /*  getMatrix();
        setImageMatrix();*/
        Matrix matrix = new Matrix();
        matrix.setRotate(45);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        myview.setImageAndMatrix(bitmap, matrix);
        myview.invalidate();

    }

    public void btnReset(View view) {
        initImageMatrix();
        getMatrix();
        setImageMatrix();
    }

    public void setImageMatrix() {
        Matrix matrix = new Matrix();
        //将数组内容转变成Matrix
        matrix.setValues(matrixs);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        myview.setImageAndMatrix(bitmap, matrix);
        myview.invalidate();
    }


    //获取矩阵的数值
    public void getMatrix() {
        for (int i = 0; i < 9; i++) {
            matrixs[i] = Float.valueOf(editTexts[i].getText().toString());

        }
    }
}
