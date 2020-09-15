package com.example.splashexample2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {

    private boolean isLoadData, isLoaded, isUpdated, isReceiveUrls;
    private boolean fromPush = false;
    private String gubun = "";
    private String mErrorDialog;
    Handler mHandler;

    Runnable startMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        isLoadData = false;

        someCheck();
        startMain = new Runnable() {
            @Override
            public void run() {
                if (isLoaded && isUpdated && isReceiveUrls && isLoadData) {
                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    // 푸쉬 알람으로 실행됐을 때
                    // 데이터를 인텐트에 포함시켜 메인액티비티로 전달

                    if(fromPush)
                        intent.putExtra("gubun",gubun);

                    startActivity(intent);

                    // Dialog 종료
                    if (mErrorDialog != null) {
                        //mErrorDialog.dismiss();
                    }
                    // splashActivity 종료

                }
            }
        };

    }

    private void someCheck(){
        boolean isCom = true;

        if(isCom) {
            isLoadData = true;
            mHandler.post(startMain);
        } else {

        }
    }
}
