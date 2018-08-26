package com.cugxy.imprint;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity";

    private static int REQUEST_EXTERNAL_STORAGE_PERMISSION = 101;
    private static int REQUEST_LOCATION_PERMISSION = 102;
    private static int REQUEST_PHONE_STATE_PERMISSION = 103;


    private static final String FRAGMENT_PERMISSION = "permission";

    private MapView     mMapView = null;

    private AMap        mAMap = null;

    private UiSettings  mUiSettings = null;

    private ConfirmationDialogFragment  mExternalStorageDialog = null;

    private ConfirmationDialogFragment  mLocationDialog = null;

    private ConfirmationDialogFragment  mPhoneStateDialog = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMapView = findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        initMap();
    }

    private void initMap() {
        mAMap = mMapView.getMap();
        mUiSettings = mAMap.getUiSettings();

        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setCompassEnabled(true);
        mUiSettings.setMyLocationButtonEnabled(true);
        mAMap.setMyLocationEnabled(true);
        mUiSettings.setScaleControlsEnabled(true);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();

        requestLocationPermission();
        requestExternalStoragePermission();

    }
    /**
     * 请求外部存储权限
     */
    private void requestExternalStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return;
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            if (mExternalStorageDialog == null){
                mExternalStorageDialog = ConfirmationDialogFragment.newInstance(R.string.storage_permission_confirmation,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE },
                        REQUEST_EXTERNAL_STORAGE_PERMISSION,
                        R.string.storage_permission_not_granted);
            }
            if (mExternalStorageDialog != null){
                mExternalStorageDialog.show(getSupportFragmentManager(), FRAGMENT_PERMISSION);
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_EXTERNAL_STORAGE_PERMISSION);
        }
    }

    /**
     * 请求定位权限
     */
    private void requestLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //检查权限
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                return;
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                if (mLocationDialog == null){
                    mLocationDialog = ConfirmationDialogFragment.newInstance(R.string.location_permission_confirmation,
                            new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION },
                            REQUEST_LOCATION_PERMISSION,
                            R.string.location_permission_not_granted);
                }
                if (mLocationDialog != null){
                    mLocationDialog.show(getSupportFragmentManager(), FRAGMENT_PERMISSION);
                }
            }
            else {
                //请求权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
            }
        }
    }

    /**
     * 请求手机状态权限
     */
    private void requestPhoneStatePermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //检查权限
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                return;
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)) {
                if (mPhoneStateDialog == null){
                    mPhoneStateDialog = ConfirmationDialogFragment.newInstance(R.string.phone_state_permission_confirmation,
                            new String[]{Manifest.permission.READ_PHONE_STATE },
                            REQUEST_PHONE_STATE_PERMISSION,
                            R.string.phone_state_permission_not_granted);
                }
                if (mPhoneStateDialog != null){
                    mPhoneStateDialog.show(getSupportFragmentManager(), FRAGMENT_PERMISSION);
                }
            }
            else {
                //请求权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_PHONE_STATE_PERMISSION);
            }
        }
    }



    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
}
