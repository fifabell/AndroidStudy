package com.example.push_sample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class BaseActivity extends AppCompatActivity {

    static String mMsg;

    // 뒤로가기 더블클릭 적용여부
    private BackPressCloseHandler backPressCloseHandler;
    boolean isAbleDoubleClick = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        backPressCloseHandler = new BackPressCloseHandler(this);

        registerReceiver();
    }

    // 알림 수신 브로드캐스트 리시버
    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mMsg = intent.getStringExtra("inBroadMessage");
            Log.d("ttt_broadReceiver","action: "+mMsg);
        }
    };

    //브로드캐스트 리시버 등록
    private void registerReceiver() {
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mReceiver, new IntentFilter("woww"));
    }


    // 뒤로가기 두번 누르면 종료
    public void setBackPressDoubleClick(boolean isAbleDoubleClick){
        this.isAbleDoubleClick = isAbleDoubleClick;
    }


    @Override
    public void onBackPressed() {
        if(isAbleDoubleClick) {
            backPressCloseHandler.onBackPressed();
        } else {
            super.onBackPressed();
        }

    }

}
