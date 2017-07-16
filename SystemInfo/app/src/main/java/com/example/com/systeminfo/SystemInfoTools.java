package com.example.com.systeminfo;

import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by rhm on 2017/7/16.
 * 系统信息工具
 */



public class SystemInfoTools {

    private static String makeInfoString(String[] description, String[] prop) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<description.length; i++) {
            sb.append(description[i]).append(":").append(prop[i]).append("\r\n");
        }
        return sb.toString();
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static String getBuildInfo() {
        String board = Build.BOARD;//主板
        String brand = Build.BRAND;//系统定制商
        String supported_abis = Build.SUPPORTED_ABIS[0];//CPU指令集
        String device = Build.DEVICE;//设备参数
        String display = Build.DISPLAY;//显示屏参数
        String fingerprint = Build.FINGERPRINT;//唯一编号
        String serial = Build.SERIAL;//硬件序列号
        String id = Build.ID;//修订版本编号
        String manufacturer = Build.MANUFACTURER;//硬件制造商
        String model = Build.MODEL;//版本
        String hardware = Build.HARDWARE;//硬件名
        String product = Build.PRODUCT;//手机产品名
        String tags = Build.TAGS;//描述Build的标签
        String type = Build.TYPE;//Build类型
        String codename = Build.VERSION.CODENAME;//当前开发代号
        String incremental = Build.VERSION.INCREMENTAL;//源码控制版本号
        String release = Build.VERSION.RELEASE;//版本字符串
        String sdk_int = "" + Build.VERSION.SDK_INT;//版本号
        String host = Build.HOST;//Host值
        String user = Build.USER;//User名
        String time = "" + Build.TIME;//编译时间
        String[] prop = {
                board,
                brand,
                supported_abis,
                device,
                display,
                fingerprint,
                serial,
                id,
                manufacturer,
                model,
                hardware,
                product,
                tags,
                type,
                codename,
                incremental,
                release,
                sdk_int,
                host,
                user,
                time
        };
        String[] description = {
                "board",
                "brand",
                "supported_abis",
                "device",
                "display",
                "fingerprint",
                "serial",
                "id",
                "manufacturer",
                "model",
                "hardware",
                "product",
                "tags",
                "type",
                "codename",
                "incremental",
                "release",
                "sdk_int",
                "host",
                "user",
                "time"
        };
        return makeInfoString(description, prop);
    }


    public static String getSystemPropertyInfo() {
        String os_version = System.getProperty("os.version");//OS版本
        String os_name = System.getProperty("os.name");//OS名称
        String os_arch = System.getProperty("os.arch");//OS构架
        String user_home = System.getProperty("user.home");//Home属性
        String user_name = System.getProperty("user.name");//Name属性
        String user_dir = System.getProperty("user.dir");//dir属性
        String user_timezone = System.getProperty("user.timezone");//时区
        String path_separator = System.getProperty("path.separator");//路径分隔符
        String line_separator = System.getProperty("line.separator");//行分隔符
        String file_separator = System.getProperty("file.separator");//文件分隔符
        String java_vendor_url = System.getProperty("java.vendor.url");//java vender URL属性
        String java_class_path = System.getProperty("java.class.path");//java class 路径
        String java_class_version = System.getProperty("java.class.version");//java class版本
        String java_vendor = System.getProperty("java.vendor");//java vender 属性
        String java_version = System.getProperty("java.version");//java 版本
        String java_home = System.getProperty("java_home");//java home 属性
        String[] prop = {
                os_version,
                os_name,
                os_arch,
                user_home,
                user_name,
                user_dir,
                user_timezone,
                path_separator,
                line_separator,
                file_separator,
                java_vendor_url,
                java_class_path,
                java_class_version,
                java_vendor,
                java_version,
                java_home
        };
        String[] description = {
                "os_version",
                "os_name",
                "os_arch",
                "user_home",
                "user_name",
                "user_dir",
                "user_timezone",
                "path_separator",
                "line_separator",
                "file_separator",
                "java_vendor_url",
                "java_class_path",
                "java_class_version",
                "java_vendor",
                "java_version",
                "java_home"
        };
        return makeInfoString(description, prop);
    }
}
