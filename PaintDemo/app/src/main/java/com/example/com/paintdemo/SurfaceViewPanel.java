package com.example.com.paintdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by lenovo on 2017/7/10.
 */

public class SurfaceViewPanel extends SurfaceView implements SurfaceHolder.Callback,Runnable {
    private SurfaceHolder holder;
    private Canvas mcanvas;
    private boolean isDrawing;//标志位，控制子线程
    private Paint mpaint;
    private Path mpath;


    public SurfaceViewPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        //初始化
        holder = getHolder();
        holder.addCallback(this);
        mpaint = new Paint();
        mpaint.setStyle(Paint.Style.STROKE);
        mpaint.setStrokeWidth(20);
        mpath = new Path();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        isDrawing = true;
        new Thread(this).start();//开启线程

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        isDrawing = false;

    }


    @Override
    public void run() {
        //设置睡眠，节省系统资源
        long  start = System.currentTimeMillis();
        while (isDrawing) {
            draw();
        }
        long end = System.currentTimeMillis();
        if (end - start < 100) {
            try {
                Thread.sleep(100-(end-start));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取触摸位置的x，y
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
               // mpath.reset();
                mpath.moveTo(x,y);
                break;
            case MotionEvent.ACTION_MOVE:
                mpath.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }

    public void draw(){
        try {
            mcanvas = holder.lockCanvas();
            mcanvas.drawPath(mpath, mpaint);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (mcanvas != null) {
                holder.unlockCanvasAndPost(mcanvas);
            }
        }
    }
}
