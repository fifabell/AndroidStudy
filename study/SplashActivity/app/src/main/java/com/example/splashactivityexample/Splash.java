package com.example.splashactivityexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {

    private boolean isLoadData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler hd = new Handler();
        hd.postDelayed(new splashHandler(),3000); // 3초 뒤 hd handler 실행
    }

    private class splashHandler implements Runnable {

        @Override
        public void run() {
            startActivity(new Intent(getApplication(), MainActivity.class)); // 로딩이 끝난 후, main액티비티로 이동
            Splash.this.finish(); // 로딩페이지 activity stack에서 제거
        }
    }

    @Override
    public void onBackPressed() {
        // 초반 플래시 화면에서 넘어갈 때 뒤로가기 버튼 못누르게 함
    }
}
