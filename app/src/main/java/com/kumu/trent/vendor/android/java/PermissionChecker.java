package com.kumu.trent.vendor.android.java;

import android.app.Activity;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;



public class PermissionChecker {
    public static final int REQUEST_PERMISSION = 123;

    public static boolean checkPermissions(Activity activity, String permission){
        return checkPermissions(activity, permission, REQUEST_PERMISSION);
    }

    public static boolean checkPermissions(Activity activity, String permission, int requestCode){
        if (ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(activity,  new String[]{permission}, requestCode);
            return false;
        }
        return true;
    }

    public static boolean checkPermissions(Activity activity, String[] permissions){
        return checkPermissions(activity, permissions, REQUEST_PERMISSION);
    }

    public static boolean checkPermissions(Activity activity, String[] permissions, int requestCode){
        List<String> newPermissions = new ArrayList<>();
        for (String permission : permissions){
            if (ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_DENIED) {
                newPermissions.add(permission);
            }
        }

        if(newPermissions.size() > 0){
            ActivityCompat.requestPermissions(activity,  newPermissions.toArray(new String[0]), requestCode);
            return false;
        }
        return true;
    }

    public static boolean checkPermissions(FragmentActivity activity, String[] permissions, int requestCode){
        List<String> newPermissions = new ArrayList<>();
        for (String permission : permissions){
            if (ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_DENIED) {
                newPermissions.add(permission);
            }
        }

        if(newPermissions.size() > 0){
            ActivityCompat.requestPermissions(activity,  newPermissions.toArray(new String[0]), requestCode);
            return false;
        }
        return true;
    }
}
