package com.example.com.systemtest;

/**
 * Created by rhm on 2017/7/16.
 */

public class AMProcessInfo {
    private String pid;//进程pid
    private String uid;//进程uid
    private String memorySize;//内存大小
    private String processName;//进程名

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(String memorySize) {
        this.memorySize = memorySize;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }
}
