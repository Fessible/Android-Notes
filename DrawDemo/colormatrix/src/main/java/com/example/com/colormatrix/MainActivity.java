package com.example.com.colormatrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private GridLayout group;
    private Bitmap bitmap;
    private ImageView imageView;
    //每个编辑框宽度和长度
    private int mEditWidth;
    private int mEditHeight;

    private EditText[] mEdits = new EditText[20];
    private float[] mMatrix = new float[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置图片
        imageView = (ImageView) findViewById(R.id.image);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.beauty);
        imageView.setImageBitmap(bitmap);

        group = (GridLayout) findViewById(R.id.group);

        //在onCreate中是无法获得视图的长度和宽度，使用post方法可以在视图创建完毕后获得长宽
        group.post(new Runnable() {
            @Override
            public void run() {
                mEditHeight = group.getHeight()/4;
                mEditWidth = group.getWidth()/5;
                //创建编辑框
                addEdits();

                //初始化矩阵
                initMatrix();


            }
        });


    }

    //初始化矩阵
    private void initMatrix() {
        for (int i=0; i<20; i++) {
            if (i % 6 == 0) {
                mEdits[i].setText(String.valueOf(1));
            } else {
                mEdits[i].setText(String.valueOf(0));
            }
        }
    }

    //添加编辑框
    private void addEdits() {
        for (int i=0; i<20; i++) {
            EditText editText = new EditText(this);
            mEdits[i] = editText;
            //添加到group中
            group.addView(editText,mEditWidth,mEditHeight);
        }


    }

    public void btnChange(View view) {
        getMatrix();
        setImageMatrix();

    }

    //将矩阵设置到图像中
    private void setImageMatrix() {

        //将数组转换成颜色矩阵
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(mMatrix);

        //创建图像副本
        Bitmap bm = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        //在副本上对原图进行修改
        canvas.drawBitmap(bitmap, 0, 0, paint);
        imageView.setImageBitmap(bm);



    }

    //获得矩阵的值
    private void getMatrix() {
        for (int i=0; i<20; i++) {
            mMatrix[i] = Float.valueOf(mEdits[i].getText().toString());
        }

    }

    public void btnReset(View view) {
        initMatrix();
        getMatrix();
        setImageMatrix();


    }
}
