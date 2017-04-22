package example.com.lbstest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LocationClient mLocationClient;
    private TextView positonText;
    private MapView mapView;
    private BaiduMap baiduMap;//百度SDK提供的一个BaiduMap类，地图的总控制器
    private boolean isFirstLocate = true;//防止多次调用，只有一开始就直接定位

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new LocationListner());
        //需要在setContentView之前使用initialize来接收一个Context参数
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.bmapView);
        positonText = (TextView) findViewById(R.id.position_text_view);
        baiduMap = mapView.getMap();//获取baiduMap的是实例
        baiduMap.setMyLocationEnabled(true);//设置显示我的位置

        //运行时权限控制
        List<String> permissionList = new ArrayList<String>();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
        } else {
            initLocation();
            requestLocation();
        }
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(5000);
//        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        mLocationClient.setLocOption(option);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        mLocationClient.stop();
        baiduMap.setMyLocationEnabled(false);

    }

    private void requestLocation() {
        mLocationClient.start();//开始定位
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    class LocationListner implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
          /*  StringBuilder currentPosition = new StringBuilder();
            currentPosition.append("纬度：" + bdLocation.getLatitude()).append("\n");
            Log.d("tag", bdLocation.getLatitude() + "纬度为");
            currentPosition.append("经度：" + bdLocation.getLongitude()).append("\n");
            currentPosition.append("国家：" + bdLocation.getCountry()).append("\n");
            currentPosition.append("省：" + bdLocation.getProvince()).append("\n");
            currentPosition.append("市：" + bdLocation.getCity()).append("\n");
            currentPosition.append("区：" + bdLocation.getDistrict()).append("\n");
            currentPosition.append("县：" + bdLocation.getStreet()).append("\n");
            currentPosition.append("定位方式：");
            if (bdLocation.getLocType() == BDLocation.TypeGpsLocation) {
                currentPosition.append("GPS");
            } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                currentPosition.append("网络");
            }
            positonText.setText(currentPosition);*/
            if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation || bdLocation.getLocType() == BDLocation.TypeGpsLocation) {
                navigationTo(bdLocation);
            }

        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }

    private void navigationTo(BDLocation bdLocation) {
        if (isFirstLocate) {
            //将地图移动到制定的经纬度上面
            LatLng ll = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());//纬度，经度
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.setMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(16f);//缩放级别
            baiduMap.setMapStatus(update);
            isFirstLocate = false;
        }
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(bdLocation.getLatitude()).longitude(bdLocation.getLongitude());
        MyLocationData locationData = locationBuilder.build();
        baiduMap.setMyLocationData(locationData);

    }
}
