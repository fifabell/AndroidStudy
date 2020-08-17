package com.example.getpermissiontest;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class GetPermission {
    ArrayList<String> permissions; // 필요한 권한들을 배열에 담아 한번에 요청하기 위해 사용
    Activity activity; // 권한 요청 시 필요

    public GetPermission (Activity activity) {
        this.activity = activity;
        permissions = new ArrayList<>();

        // Getpermisson 생성시 바로 필요한 권한들을 확인하고, ArrayList에 담아둔다.
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_DENIED) {
            permissions.add(Manifest.permission.READ_PHONE_STATE);
        }

        if(ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if(ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if(ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            permissions.add(Manifest.permission.CAMERA);
        }
    } // GetPermission

    public void requestPermission() {
        String[] arrPermission = permissions.toArray(new String[permissions.size()]); // 배열로 변경
    }

    // 권한 확인
    public boolean isGrantedPermission(Context context, String permission) {
        boolean isGranted = false;
        if(ContextCompat.checkSelfPermission(context,permission) == PackageManager.PERMISSION_GRANTED) {
            isGranted = true;
        }
        return isGranted;
    } // isGrantedPermission
} // GetPermission
