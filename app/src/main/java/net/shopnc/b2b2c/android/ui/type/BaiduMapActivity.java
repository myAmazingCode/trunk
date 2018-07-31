package net.shopnc.b2b2c.android.ui.type;

import android.app.Activity;
import android.os.Bundle;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.GpsInfo;
import net.shopnc.b2b2c.android.common.LogHelper;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;

import java.util.ArrayList;

public class BaiduMapActivity extends Activity {
    MapView mMapView;
    BaiduMap mBaiduMap;
    private LocationClient mLocationClient;
    private MyShopApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_baidu_map);
        MyExceptionHandler.getInstance().setContext(this);
        app = (MyShopApplication)getApplication();
        mLocationClient = app.mLocationClient;
        mMapView = findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();

        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mBaiduMap.setMyLocationEnabled(true);

        GpsInfo gpsInfo = ShopHelper.getGpsInfo(BaiduMapActivity.this);
        LogHelper.d("baiduMap:", gpsInfo.toString());
        LatLng location = new LatLng(gpsInfo.getLat(), gpsInfo.getLng());

        ArrayList<GpsInfo> gpsList = getIntent().getParcelableArrayListExtra("gps_list");
        for (int i = 0; i < gpsList.size(); i++) {
            GpsInfo info = gpsList.get(i);

            //定义Maker坐标点
            LatLng point = new LatLng(info.getLat(), info.getLng());
            if (i == 0) {
                location = new LatLng(info.getLat(), info.getLng());
            }
            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.nc_icon_location_mark);
            OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
            mBaiduMap.addOverlay(option);
        }

        //设置中心点和缩放比例
        int zoom = 16;
        if (gpsList.size() > 1) {
            zoom = 12;
        }
        MapStatus mapStatus = new MapStatus.Builder().target(location).zoom(zoom).build();
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        mBaiduMap.setMapStatus(mapStatusUpdate);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLocationClient.start();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocationClient.stop();
        mMapView.onPause();
    }

}

