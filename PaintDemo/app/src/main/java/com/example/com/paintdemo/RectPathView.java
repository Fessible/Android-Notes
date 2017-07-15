package com.example.com.paintdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by rhm on 2017/7/15.
 */

public class RectPathView extends View {
    public RectPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path ccwRectPath = new Path();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(Color.RED);
        RectF rect = new RectF(50,50,100,100);
        //逆时针
        ccwRectPath.addRect(rect, Path.Direction.CCW);
        canvas.drawPath(ccwRectPath, paint);

        //顺时针
        RectF rect2 = new RectF(200, 50, 250, 100);
        Path cwRectPath = new Path();
        cwRectPath.addRect(rect2, Path.Direction.CW);
        canvas.drawPath(cwRectPath, paint);

        String text = "风萧萧兮易水寒";
        paint.setColor(Color.GRAY);
        canvas.drawTextOnPath(text, ccwRectPath, 0, 18, paint);
        canvas.drawTextOnPath(text,cwRectPath,0,0,paint);
    }
}
