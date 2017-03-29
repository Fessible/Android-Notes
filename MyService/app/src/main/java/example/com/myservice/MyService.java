package example.com.myservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.io.File;

public class MyService extends Service {
    private DownLoadTask downLoadTask;
    private String downLoadUrl;
    //由于接口只需要使用一次所以使用了匿名内部类
    private DownLoadListener listener = new DownLoadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1, getNotification("Downloading.....", progress));

        }

        @Override
        public void onSuccess() {
            downLoadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download success", -1));
            Toast.makeText(MyService.this, "Download Success", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPause() {
            downLoadTask = null;
            Toast.makeText(MyService.this, "Paused", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
            downLoadTask = null;
            stopForeground(true);
            Toast.makeText(MyService.this, "Cancle", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onFailed() {
            downLoadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("Failed",-1));
            Toast.makeText(MyService.this, "Failed", Toast.LENGTH_SHORT).show();
        }
    };

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }
    private DownloadBinder mBinder = new DownloadBinder();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public IBinder onBind(Intent intent) {
       return mBinder;
    }

    //由于要多次用到notification就直接提取出来使用
    public NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    //通知的内容
    public Notification getNotification(String title, int progress) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(title);
        builder.setContentIntent(pendingIntent);
        if (progress > 0) {
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
        }

        return builder.build();
    }

     class DownloadBinder extends Binder {
     public void startDownload(String url ){//开始下载
         if (downLoadTask == null) {
             downLoadUrl = url;
             downLoadTask = new DownLoadTask(listener);
             downLoadTask.execute(downLoadUrl);//AsyncTask执行
             startForeground(1, getNotification("Downloading...", 0));
             Toast.makeText(MyService.this,"Downloading....",Toast.LENGTH_SHORT).show();
         }
     }

     public void pasueDownload() {
         if (downLoadTask != null) {
             downLoadTask.pausDownload();
         }
     }

         public void cancelDownload() {
             if (downLoadTask != null) {
                 downLoadTask.cancelDownload();
             } else {
                 if (downLoadUrl != null) {
                     String filename = downLoadUrl.substring(downLoadUrl.lastIndexOf("/"));
                     String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                     File file = new File(directory + filename);
                     if (file.exists()) {
                         file.delete();
                     }
                     getNotificationManager().cancel(1);
                     stopForeground(true);
                     Toast.makeText(MyService.this,"Canceled",Toast.LENGTH_SHORT).show();
                 }
             }
         }
    }
}
