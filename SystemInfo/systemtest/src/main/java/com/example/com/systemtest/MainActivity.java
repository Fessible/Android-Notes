package com.example.com.systemtest;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private PackageManager pm;

    public static final int ALL_APP = 0;
    public static final int SYSYTEM_APP = 1;
    public static final int THRID_APP = 2;
    public static final int SDCARD_APP = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lisetview);
    }


    //获取list信息
    public List<PMAppInfo> getInfo(int flag) {
        pm = getPackageManager();//获取PackManager
        //获取应用信息
        List<ApplicationInfo> applicationInfoList = pm.getInstalledApplications(
                PackageManager.GET_UNINSTALLED_PACKAGES
        );

        List<PMAppInfo> pmAppInfoList = new ArrayList<>();

        //判断类型,每个判断前都需要将list清空，来显示新的内容
        switch (flag) {

            case ALL_APP:
                pmAppInfoList.clear();
                for (ApplicationInfo app : applicationInfoList) {
                    pmAppInfoList.add(makeAppInfo(app));
                }
                break;

            case SYSYTEM_APP:
                pmAppInfoList.clear();
                for (ApplicationInfo app : applicationInfoList) {
                    if ((app.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                        pmAppInfoList.add(makeAppInfo(app));

                    }
                }
                break;

            case THRID_APP:
                pmAppInfoList.clear();
                for (ApplicationInfo app : applicationInfoList) {
                    if ((app.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                        pmAppInfoList.add(makeAppInfo(app));
                    } else if ((app.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
                        pmAppInfoList.add(makeAppInfo(app));
                    }
                }
                break;

            case SDCARD_APP:
                pmAppInfoList.clear();
                for (ApplicationInfo app : applicationInfoList) {
                    if ((app.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE)!=0) {
                        pmAppInfoList.add(makeAppInfo(app));
                    }
                }
                break;
            default:
                return null;

        }
        return pmAppInfoList;

    }


    private PMAppInfo makeAppInfo(ApplicationInfo app) {
        PMAppInfo info = new PMAppInfo();
        info.setAppLabel((String) app.loadLabel(pm));
        info.setAppIcon(app.loadIcon(pm));
        info.setPkgName(app.packageName);
        return info;
    }

    public void setData(int flag) {
        PMAdapter adapter = new PMAdapter(this, R.layout.listview_item, getInfo(flag));
        listView.setAdapter(adapter);
    }


    public void btnAllApp(View view) {
        setData(ALL_APP);

    }

    public void btnSystemApp(View view) {
        setData(SYSYTEM_APP);

    }

    public void btn3RDApp(View view) {
        setData(THRID_APP);

    }

    public void btnSDCardApp(View view) {
        setData(SDCARD_APP);
    }
}
