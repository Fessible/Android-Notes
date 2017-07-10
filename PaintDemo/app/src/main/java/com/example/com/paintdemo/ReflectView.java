package com.example.com.paintdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lenovo on 2017/7/10.
 */

public class ReflectView extends View {
    public ReflectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap srcbitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic);

        //设置翻转图片
        Matrix matrix = new Matrix();
        matrix.setScale(1F, -1F);
        Bitmap refbitmap = Bitmap.createBitmap(srcbitmap, 0, 0, srcbitmap.getWidth(), srcbitmap.getHeight(), matrix, true);

        //设置画笔渐变
        Paint paint = new Paint();
        paint.setShader(new LinearGradient(0, srcbitmap.getHeight(), 0, srcbitmap.getHeight() + srcbitmap.getHeight() / 4, 0xdd000000, 0x10000000, Shader.TileMode.CLAMP));
//        canvas.drawColor(Color.BLACK);
        //绘制图像
        canvas.drawBitmap(srcbitmap, 0, 0, null);
        canvas.drawBitmap(refbitmap, 0, srcbitmap.getHeight(), null);
        //设置叠加方式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        //绘制渐变矩形
        canvas.drawRect(0, srcbitmap.getHeight(), refbitmap.getWidth(), refbitmap.getHeight() * 2, paint);
        paint.setXfermode(null);
    }
}
