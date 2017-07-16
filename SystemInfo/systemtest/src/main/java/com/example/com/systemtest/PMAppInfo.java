package com.example.com.systemtest;

import android.graphics.drawable.Drawable;

/**
 * Created by rhm on 2017/7/16.
 * 应用信息
 */

public class PMAppInfo {
    private  String appLabel;
    private Drawable appIcon;
    private String pkgName;

    public String getAppLabel() {
        return appLabel;
    }

    public void setAppLabel(String appLabel) {
        this.appLabel = appLabel;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }
}
