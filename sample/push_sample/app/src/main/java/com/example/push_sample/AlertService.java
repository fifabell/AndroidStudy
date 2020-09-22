package com.example.push_sample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

class AlertService extends Service {
    private String gubun;
    private boolean isReceived;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Notification tab 시 실행됨
        gubun = intent.getStringExtra("gubun");

        // gubun 이 null이면 푸시로 부터 온 데이터가 없으므로 다른 액티비티를 실행 할 필요가 없음
        if (gubun == null) {
            stopService(intent);
            return super.onStartCommand(intent, flags, startId);
        }

        // 현재 MainActivity 가 Task에 존재하는지(실행이 되어 있는지) 확인하기 위한 변수
        isReceived = false;
        checkResourse();

        // 액티비티 실행
        startActivity();

        // 서비스 종료
        stopService(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    private void startActivity() {
        if (isReceived) {
            if(gubun.equals("SUB")){
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                intent.putExtra("gubun",gubun);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return;
            } else {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("gubun",gubun);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return;
            }
        }
    }

    private void checkResourse() {
    }
}
