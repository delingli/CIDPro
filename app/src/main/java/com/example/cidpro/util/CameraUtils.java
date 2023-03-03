package com.example.cidpro.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;

public class CameraUtils {
    /**
     * Check if this device has a camera
     * 检查这个设备是否有摄像头
     */
    public static boolean hasCamera(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    /**
     * A safe way to get an instance of the Camera object.
     * 获取摄像机对象实例的安全方法。
     */
    public static Camera openCamera() {
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        } catch (Exception e) {
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }
}