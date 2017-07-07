package  com.example.com.drawdemo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private ImageView imageView;
    private SeekBar mHue;
    private SeekBar mStauration;
    private SeekBar mLum;
    private Bitmap bitmap;
    private float hue;
    private float stauartion;
    private float lum;

    public final static int MAX_VALUE = 255;
    public final static int MID_VALUE = 127;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.image);
        mHue = (SeekBar) findViewById(R.id.hue);
        mStauration = (SeekBar) findViewById(R.id.stauration);
        mLum = (SeekBar) findViewById(R.id.lum);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.p);
        imageView.setImageBitmap(bitmap);
/*
        //设置最大值
        mHue.setMax(MAX_VALUE);
        mStauration.setMax(MAX_VALUE);
        mLum.setMax(MAX_VALUE);

        //设置默认值
        mHue.setProgress(MID_VALUE);
        mStauration.setProgress(MID_VALUE);
        mLum.setProgress(MID_VALUE);
        */

        //设置点击监听事件
        mHue.setOnSeekBarChangeListener(this);
        mStauration.setOnSeekBarChangeListener(this);
        mLum.setOnSeekBarChangeListener(this);

    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        switch (seekBar.getId()) {
            //色调
            case R.id.hue:
                hue = (i - MID_VALUE) * 1.0f / MID_VALUE * 180;
                break;
            //饱和度
            case R.id.stauration:
                stauartion = i * 1.0f / MID_VALUE;
                break;
            //亮度
            case R.id.lum:
                lum = i * 1.0f / MID_VALUE;
                break;
        }

        //设置图片的效果
        imageView.setImageBitmap(handleImageEffect(bitmap, hue, stauartion, lum));
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    //根据seekBar传递过来的数值变化，改变图片效果
    public static Bitmap handleImageEffect(Bitmap bm, float hue, float staraution, float lum) {

        //由于不能再原图上修改图片，创建图片的副本, Bitmap.Config.ARGB_8888表示每个像素存储4个字节
        Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);//指定绘制位图
        Paint paint = new Paint();

        //设置色调
        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0, hue);
        hueMatrix.setRotate(1, hue);
        hueMatrix.setRotate(2, hue);

        //设置饱和度
        ColorMatrix starautionMatrix = new ColorMatrix();
        starautionMatrix.setSaturation(staraution);

        //设置亮度
        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum, lum, lum, 1);//设置RGBA，1设置不透明

        //混合效果
        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(starautionMatrix);
        imageMatrix.postConcat(lumMatrix);

        //设置画笔的颜色
        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        //将原图绘制到副本中，通过这个副本来修改图片效果
        canvas.drawBitmap(bm, 0, 0, paint);

        return  bmp;

    }
}

