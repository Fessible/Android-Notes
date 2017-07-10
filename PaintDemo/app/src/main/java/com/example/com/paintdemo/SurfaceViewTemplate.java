package com.example.com.paintdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by lenovo on 2017/7/10.
 */

public class SurfaceViewTemplate extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder holder;
    //绘图canva
    private Canvas mcanvas;
    //控制子线程
    private boolean isDrawing;
    private Paint mpaint;
    private Path mpath;
    private int x;
    private int y;


    public SurfaceViewTemplate(Context context, AttributeSet attrs) {
        super(context, attrs);

        //初始化
        holder = getHolder();
        holder.addCallback(this);
        mpaint = new Paint();
        mpaint.setStyle(Paint.Style.STROKE);
        mpaint.setStrokeWidth(20);
        mpath = new Path();
        mpath.moveTo(0,100);

    }

    //SurfaceView的创建
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        isDrawing = true;
        new Thread(this).start();//开始线程
    }

    //SurfaceView改变
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    //SurfaceView销毁
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        isDrawing = false;
    }

    @Override
    public void run() {
        while (isDrawing) {
            draw();
            x += 1;
            //y=Asin(wx)+k
            y = (int) (100 * Math.sin(x * 2 * Math.PI / 180) + 400);
            mpath.lineTo(x,y);
        }

    }

    public void draw() {
        try {
            //获取对象
            mcanvas = holder.lockCanvas();
            mcanvas.drawPath(mpath, mpaint);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mcanvas != null) {
                holder.unlockCanvasAndPost(mcanvas);
            }
        }
    }
}
