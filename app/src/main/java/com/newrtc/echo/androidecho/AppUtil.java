package com.newrtc.echo.androidecho;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChengleiQiu .
 */

public class AppUtil {
    public static boolean checkAndRequestAppPermission(@NonNull Activity activity, String[] permissions, int reqCode) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true;

        List<String> permissionList = new ArrayList<>();

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED)
                permissionList.add(permission);
        }
        if (permissionList.size() == 0)
            return true;

        String[] requestPermissions = permissionList.toArray(new String[permissionList.size()]);
        activity.requestPermissions(requestPermissions, reqCode);
        return false;
    }
}
