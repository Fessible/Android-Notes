package com.example.com.paintdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lenovo on 2017/7/9.
 */

public class RectangleView extends View {

    private Bitmap mBitmap;
    private Bitmap mOut;
    private Paint mPaint;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RectangleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void initView() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic);
        //图像副本
        mOut = Bitmap.createBitmap(mBitmap.getWidth(),
                mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mOut);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//无锯齿


        //绘制圆角矩形
        canvas.drawRoundRect(0, 0,
                mBitmap.getWidth(),
                mBitmap.getHeight(), 80, 80, mPaint);

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //绘制bitmap图片到mout中，bitmap是后面绘制的，使用SRC_IN。DST表示先绘制的，SRC表示后面绘制的
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
      //绘制已经变为圆角矩形的图片，mout
     canvas.drawBitmap(mOut, 0, 0, null);


    }
}

