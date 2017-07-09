package com.example.com.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lenovo on 2017/7/8.
 */

public class MyView extends View {
    private Bitmap bitmap;
    private Matrix matrix;

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        setImageAndMatrix(bitmap, new Matrix());
    }

    public  void setImageAndMatrix(Bitmap bitmap, Matrix matrix) {
        this.bitmap = bitmap;
        this.matrix = matrix;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,matrix,null);
    }
}
