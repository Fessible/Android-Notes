package com.example.com.piexeltest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.image);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.beauty);
        imageView.setImageBitmap(bitmap);
    }

    //底片效果
    public void btnNegativePic(View view) {
        imageView.setImageBitmap(handleImageNegative(bitmap));
    }

    //老照片效果
    public void btnOldPic(View view) {
        imageView.setImageBitmap(handleImageOld(bitmap));
    }



    //浮雕效果
    public void btnReliefPic(View view) {
        imageView.setImageBitmap(handleImageRelief(bitmap));
    }




    public  Bitmap handleImageNegative(Bitmap bitmap) {

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        //创建像素数组
        int[] oldPx = new int[width * height];
        int[] newPx = new int[width * height];

        int color ;
        int r,g,b,a;

        //创建图像副本
        Bitmap bm = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        //获取原图像的像素，保存到oldPx数组中
        bitmap.getPixels(oldPx, 0, width, 0, 0, width, height);

        //获取每个像素的ARGB值
        for (int i = 0; i < width * height; i++) {
            color = oldPx[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            //底片的计算方式
            r = 255 - r;
            g = 255 - g;
            b = 255 - b;

            if (r > 255) {
                r = 255;
            } else if (r < 0) {
                r = 0;
            }
            if (g > 255) {
                g = 255;
            } else if (g < 0) {
                g = 0;
            }
            if (b > 255) {
                b = 255;
            } else if (b < 0) {
                b = 0;
            }

            //设置新的像素组
            newPx[i] = Color.argb(a, r, g, b);
        }

        bm.setPixels(newPx,0,width,0,0,width,height);
        return bm;
    }

    //老照片效果
    public Bitmap handleImageOld(Bitmap bitmap) {

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int oldPx[] = new int[width * height];
        int newPx[] = new int[width * height];
        int color;
        int a,r,g,b;

        Bitmap bm = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.getPixels(oldPx, 0, width, 0, 0, width, height);
        for (int i = 0; i < width * height; i ++) {
            //获取每个像素的ARGB
            color = oldPx[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            r = (int) (0.393 * r + 0.769 * g + 0.189 * b);
            g = (int) (0.349 * r + 0.686 * g + 0.168 * b);
            b = (int) (0.272 * r + 0.534 * g + 0.131 * b);

            if (r > 255) {
                r = 255;
            }
            if (g > 255) {
                g = 255;
            }
            if (b > 255) {
                b = 255;
            }

            newPx[i] = Color.argb(a, r, g, b);

        }
            bm.setPixels(newPx,0,width,0,0,width,height);
            return bm;

    }

    public Bitmap handleImageRelief(Bitmap bitmap) {

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int oldPx[] = new int[width * height];
        int newPx[] = new int[width * height];
        int color,color1;
        int a,r,g,b;
        int a1,r1,g1,b1;

        Bitmap bm = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.getPixels(oldPx, 0, width, 0, 0, width, height);
        for (int i = 1; i < width * height; i ++) {
            //获取每个像素的ARGB
            color = oldPx[i-1];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            color1 = oldPx[i];
            r1 = Color.red(color1);
            g1 = Color.green(color1);
            b1 = Color.blue(color1);
            a1 = Color.alpha(color1);

            r = r - r1 + 127;
            g = g - g1 + 127;
            b = b - b1 + 127;

            if (r > 255) {
                r = 255;
            }
            if (g > 255) {
                g = 255;
            }
            if (b > 255) {
                b = 255;
            }

            newPx[i] = Color.argb(a, r, g, b);


        }

        bm.setPixels(newPx,0,width,0,0,width,height);
        return  bm;
    }

}
