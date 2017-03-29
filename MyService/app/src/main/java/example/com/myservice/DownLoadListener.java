package example.com.myservice;

/**
 * Created by rhm on 2017/3/29.
 * 回调接口
 */

public interface DownLoadListener {
    void onProgress(int progress);
    void onSuccess();
    void onPause();
    void onCancel();
    void onFailed();

}
