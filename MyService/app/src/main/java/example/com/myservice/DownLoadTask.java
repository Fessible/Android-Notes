package example.com.myservice;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 异步消息处理
 * Created by rhm on 2017/3/29.
 */

public class DownLoadTask extends AsyncTask<String, Integer, Integer> {
    //下载过程中的四种状态：
    public static final int TYPE_SUCCESS = 0;
    public static final int TYPE_FAILED = 1;
    public static final int TYPE_PAUSE = 2;
    public static final int TYPE_CANCELED = 3;
    private  DownLoadListener listener;
    private boolean isCanceled = false;
    private boolean isPaused = false;
    private int lastProgress;

    public DownLoadTask(DownLoadListener listener) {
        this.listener = listener;
    }

    //开始之前对UI进行初始化
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected Integer doInBackground(String... params) {
        InputStream in = null;
        RandomAccessFile savedFile = null;
        File file = null;
        long downLength = 0;
        String downloadUrl = params[0];
        try {
            String filename = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(directory + filename);//文件为目录加上文件名
            long contentLength = getContentLength(downloadUrl);//获取文件的总长度
            //如果文件存在就之间返回successful
            if (file.exists()) {
                downLength = file.length();//如果文件已经存在就获取当前文件的长度
            }
            //文件的总长度为0说明下载失败。
            if (0 == contentLength) {
                return TYPE_FAILED;
                //下载的长度和总长度一样说明下载完成
            } else if (downLength == contentLength) {
                return TYPE_SUCCESS;
            }
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().addHeader("RANGE", "bytes=" + downLength + "-").url(downloadUrl).
                    build();
            Response respone = client.newCall(request).execute();
            //判断response！=null 然后开始进行写入文件
            if (respone != null) {
                in = respone.body().byteStream();
                savedFile = new RandomAccessFile(file, "rw");
                savedFile.seek(downLength);//跳过已经下载的字节
                byte b[] = new byte[1024];
                int length = 0;
                int total = 0;
                while ((length = in.read(b)) != -1) {
                    Log.d("filename", "len的长度为： "+length);
                    if (isCanceled) {
                        return  TYPE_CANCELED;
                    } else if (isPaused) {
                        return TYPE_PAUSE;
                    } else {//在没有暂停，没有取消的情况下，开始进行写入文件
                        total += length;
                        Log.d("filename", "total的长度为：" + total);
                        savedFile.write(b, 0, length);
                        int progtess = (int) ((total + downLength) * 100 / contentLength);
                        publishProgress(progtess);//传递progress
                    }
                }
                respone.body().close();
                return  TYPE_SUCCESS;


            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            Log.d("filename", "这是finally ");
                try {
                    if (in != null) {
                    in.close();}
                    if (savedFile != null) {
                        savedFile.close();
                    }
                    if (isCanceled && file != null) {//取消下载，将文件删除
                        file.delete();
                    }
                } catch (IOException e) {
                    e.printStackTrace();

            }
        }

        return TYPE_FAILED;
    }

    //利用参数对界面元素更新
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int progress = values[0];//接收publishProgress传递过来的progress
        if (progress > lastProgress) {//如果当前的progress的值比上一次的大，那么就把这一次的progres的值赋予lastprogess
            listener.onProgress(progress);//监听了progress
            lastProgress = progress;
        }

    }
    public void cancelDownload() {
        isCanceled = true;
    }

    public void pausDownload() {
        isPaused = true;
    }



    //当任务结束后,返回结果
    @Override
    protected void onPostExecute(Integer status) {
        super.onPostExecute(status);
        switch (status) {
            case TYPE_SUCCESS:
                listener.onSuccess();
                break;
            case TYPE_PAUSE:
                listener.onPause();
                break;
            case TYPE_CANCELED:
                listener.onCancel();
                break;
            case TYPE_FAILED:
                listener.onFailed();
        }
    }

    //获取contentLength
    public long getContentLength(String  downloadUrl) throws IOException {
        long contentLength = 0;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(downloadUrl).build();
        Response response = client.newCall(request).execute();
        //对response进行判断
        if (response != null && response.isSuccessful()) {
            contentLength = response.body().contentLength();
            response.close();
            return contentLength;
        }
        return  0;
    }
}
