package com.cugxy.imprint;


/**
 * Created by cugxy on 2018/9/12.
 *
 */

public class MarkerInfo extends Object {
    public final static String TAG = "MarkerInfo";

    private double  mLatitude;

    private double  mLongitude;

    private String  mLabelInfo;

    private String  mTumbImageData;  /// < BASE64 缩略图

    public MarkerInfo() {
        mLatitude = 1 << 7;
        mLongitude = 1 << 8;
        mLabelInfo = "";
        mTumbImageData = "";
    }

    public MarkerInfo(double lat, double lng, String label, String imageData) {
        mLatitude = lat;
        mLongitude = lng;
        mLabelInfo = label;
        mTumbImageData = imageData;
    }


    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        this.mLatitude = latitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        this.mLongitude = longitude;
    }

    public String getLabelInfo() {
        return mLabelInfo;
    }

    public void setLabelInfo(String labelInfo) {
        this.mLabelInfo = labelInfo;
    }

    public String getmTumbImageData() {
        return mTumbImageData;
    }

    public void setTumbImageData(String tumbImageData) {
        this.mTumbImageData = tumbImageData;
    }
}
