package com.max.news.utils;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.max.news.R;

import java.util.List;

/**
 * 获取位置信息（经纬度）
 *
 * @auther MaxLiu
 * @time 2016/12/28
 */

public class LocationUtil {

    public interface OnLocationListener{
        void OnLocation(String location);
    }

    private Context mContext;
    public static String mLocation;
    private OnLocationListener mOnLocationListener;

    public LocationUtil(Context context){
        mContext = context;
    }

    // 经纬度获取
    public void initLocation() {
        // 获取地理位置管理器
        LocationManager locationManager = (LocationManager)
                mContext.getSystemService(Context.LOCATION_SERVICE);
        // 获取所有可用的位置提供器
        List<String> providers = locationManager.getProviders(true);
        String locationProvider;
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            // 如果是GPS
            locationProvider = LocationManager.GPS_PROVIDER;
            Log.d("location","locationProvider is GPS !!");
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            // 如果是Network
            locationProvider = LocationManager.NETWORK_PROVIDER;
            Log.d("location","locationProvider is NetWork !!");
        } else {
            Log.d("location","locationProvider is null !!");
            Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
            return;
        }
        // 获取Location
        Location location = locationManager
                .getLastKnownLocation(locationProvider);
        if (location != null) {
            // 不为空,显示地理位置经纬度
            mOnLocationListener.OnLocation(location.getLatitude()+":"+location.getLongitude());
        }else{
            mOnLocationListener.OnLocation("beijing");
            Log.d("location","Location is null !!");
        }
        // 监视地理位置变化
        locationManager.requestLocationUpdates(locationProvider, 5000, 5000,
                locationListener);
    }

    /**
     * 显示地理位置经度和纬度信息
     *
     * @param location
     */
    private void setLocation(Location location) {
        mLocation = location.getLatitude()+":"+location.getLongitude();
    }

    public void setOnLoccationListener(OnLocationListener loccationListener){
        mOnLocationListener = loccationListener;
    }

    /**
     * LocationListern监听器 参数：地理位置提供器、监听位置变化的时间间隔、位置变化的距离间隔、LocationListener监听器
     */

    private LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle arg2) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onLocationChanged(Location location) {
            String mLocation = location.getLatitude()+":"+location.getLongitude();
            mOnLocationListener.OnLocation(mLocation);
            Log.d("location","Location is changed !!");
        }
    };
}
