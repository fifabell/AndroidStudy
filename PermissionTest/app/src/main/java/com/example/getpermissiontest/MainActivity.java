package com.example.getpermissiontest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("fiabell0","0");
        requestPermission();
        Log.d("fiabell5","5");
    } // onCreate

    private void requestPermission() {
        int version = Build.VERSION.SDK_INT; // 현재 안드로이드 버전 가져오기
        if(version > Build.VERSION_CODES.M) {
            // 마시멜로우 버전 이상은 사용자 권한을 승인 해주어야 한다.
            GetPermission getPermission = new GetPermission(this);
            getPermission.requestPermission();
        } else {
            //마시멜로우 버전 이하는 자동 권한 부여
            Log.d("permission","권한 승인 불필요");
        }

        Log.d("fiabell1","1");
    } // requestPermission


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("fiabell2","2");
        GetPermission getPermission = new GetPermission(this);

        if        (getPermission.isGrantedPermission(this, Manifest.permission.CAMERA)
                && getPermission.isGrantedPermission(this, Manifest.permission.READ_PHONE_STATE)
                && getPermission.isGrantedPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                && getPermission.isGrantedPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                 ) { // 모든 권한이 승인되었는지 확인
            Log.d("fiabell3","3");
            Toast.makeText(this,"권한이 설정되었습니다.", Toast.LENGTH_SHORT).show();

            /*
            * 권한을 얻은 후 실행할 메소드 입력란
            * */

        } else {
            Log.d("fiabell4","4");
            getPermission.requestPermission(); // 허용되지 않은 권한이 있을 시 다시 권한 얻
        }
    } // onRequestPermissionResult
} // MainActivity
