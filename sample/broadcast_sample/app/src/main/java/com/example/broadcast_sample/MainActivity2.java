package com.example.broadcast_sample;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

public class MainActivity2 extends Activity {
    BroadcastReceiver br  = new BroadcastReceiver() {
        // broadcastreceiver 선언. 받았을 때 행동까지 정의.
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
                Log.d("ttt","배터리충전중..");
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                Log.d("ttt","스크린 켜짐..");
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                Log.d("ttt","스크린 꺼짐..");
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        //filter.addAction("example.test.broadcast");
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        //filter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(br,filter);
        Log.d("ttt","resume / register");
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(br);
        Log.d("ttt","pause / unregister");
    }
}
