package com.example.com.paintdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by rhm on 2017/7/9.
 */


public class XfermodeView extends View {
    private Paint mpaint;
    private Canvas mcanvas;
    private Path mpath;
    private Bitmap fgBitmap;
    private Bitmap bgBitmap;

    public XfermodeView(Context context) {
        super(context);
        initview();
    }

    public XfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initview();
    }

    public XfermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initview();
    }


    private void initview() {
        mpaint = new Paint();
        //设置透明，手指划过的路径变为透明
        mpaint.setAlpha(0);
        //设置porterDuffXfermode
        mpaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        //设置画笔
        mpaint.setStyle(Paint.Style.STROKE);
        //让画笔更加圆润
        mpaint.setStrokeJoin(Paint.Join.ROUND);
        mpaint.setStrokeCap(Paint.Cap.ROUND);
        mpaint.setStrokeWidth(50);

        mpath = new Path();

        //背景图片
        bgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic);
        //前景图片大小设置与背景图片大小相同
        fgBitmap = Bitmap.createBitmap(bgBitmap.getWidth(), bgBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mcanvas = new Canvas(fgBitmap);
        //前景设置为灰色
        mcanvas.drawColor(Color.GRAY);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mpath.reset();
                mpath.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mpath.lineTo(event.getX(), event.getY());
                break;

        }
        //重绘
        mcanvas.drawPath(mpath, mpaint);
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //先绘制了背景图片，所以用DST_IN
        canvas.drawBitmap(bgBitmap,0,0,null);
        canvas.drawBitmap(fgBitmap,0,0,null);

    }
}


