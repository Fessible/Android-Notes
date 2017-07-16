package com.example.com.systemtest;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.Nullable;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rhm on 2017/7/16.
 */

public class AMActivity extends Activity {
    private ListView mListView;
    private List<AMProcessInfo> mAmProcessInfoList;
    private ActivityManager mActivityManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.amavtivity_main);
        mListView = findViewById(R.id.amprocess_list);
        mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        AMAdapter adapter = new AMAdapter(this, R.layout.am_listview_item, getRunningProcessInfo());
        mListView.setAdapter(adapter);

    }

    private List<AMProcessInfo> getRunningProcessInfo() {
        mAmProcessInfoList = new ArrayList<>();

        List<ActivityManager.RunningAppProcessInfo> appProcessInfos =
                mActivityManager.getRunningAppProcesses();
        for (int i = 0; i < appProcessInfos.size(); i++) {
            //获取信息
            ActivityManager.RunningAppProcessInfo info = appProcessInfos.get(i);
            int pid = info.pid;
            int uid = info.uid;
            String processName = info.processName;

            //获取memorySize
            int[] memoryPid = new int[]{pid};
            Debug.MemoryInfo[] memoryInfos = mActivityManager.getProcessMemoryInfo(memoryPid);
            int memorySize = memoryInfos[0].getTotalPss();

            //存储进AMProcessInfo中
            AMProcessInfo amProcessInfo = new AMProcessInfo();
            amProcessInfo.setPid(pid + "");
            amProcessInfo.setUid(uid + "");
            amProcessInfo.setMemorySize(memorySize + "");
            amProcessInfo.setProcessName(processName);

            mAmProcessInfoList.add(amProcessInfo);

        }
        return mAmProcessInfoList;

    }
}
