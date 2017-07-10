package com.example.com.paintdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lenovo on 2017/7/10.
 */


public class PathEffectView extends View {
    Path mpath;
    Paint paint;
    private PathEffect[] meffects;

    public PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        mpath = new Path();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.DKGRAY);
        mpath.moveTo(0, 0);
        //设置路径的位置

        for (int i = 0; i <= 30; i++) {
            mpath.lineTo(i * 30, (float) (Math.random() * 100));
        }
        meffects = new PathEffect[6];

        meffects[0] = null;
        meffects[1] = new CornerPathEffect(10);
        //(段长，偏差)
        meffects[2] = new DiscretePathEffect(3.0f, 5.0f);//杂点
        //虚线(间隔，段)
        meffects[3] = new DashPathEffect(new float[]{20, 20, 10,5}, 0);
        //设置显示点的图形,这里我们设置每个点显示为矩形

        Path path = new Path();
        path.addRect(0, 0, 8, 8, Path.Direction.CCW);//逆时针方向
        meffects[4] = new PathDashPathEffect(path, 12, 0, PathDashPathEffect.Style.ROTATE);
        //组合
        meffects[5] = new ComposePathEffect(meffects[3], meffects[1]);

        //绘制
        for (int i = 0; i < meffects.length; i++) {
            paint.setPathEffect(meffects[i]);
            canvas.drawPath(mpath, paint);
            canvas.translate(0, 100);//因为每个的初始绘制点都是(0,0),画布向下移动，起点位置仍为(0,0)

        }

    }
}

