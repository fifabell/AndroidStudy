package com.example.broadcast_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/*
* 1. 정적리시버 (MainActivity)
* 생성 순서 :BroadcastReceiver를 상속받는 클래스 생성 > manifest에 intent-filter 등록 > 테스트
* 동작 순서 : IntentFilter로 registerReceiver(등록) + 키 생성 > 원하는 시기에 sendBroadcast + 키 포함 > 만들어놓은 CustomReceiver에서 키를 받아 원하는 동작
* BroadcastReceiverCustom 주목하면 됌.
*
* 2. 동적리시버 (MainActivity2)
* 생성 : 내가 원하는 곳에 객체생성 하면 됌.
* registerReceiver만 하면 sendBroadcast 하지 않아도 사용 가능.
*
* 참고 *
* broadcastreceive 는 백그라운드에서 자꾸 동작이 되므로 pause에서 unregister를 해주는 게 좋다.
* */
public class MainActivity extends AppCompatActivity {
    Button button;
    BroadcastReceiverCustom broadcastReceiverCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // receiver 등록
        broadcastReceiverCustom = new BroadcastReceiverCustom();
        IntentFilter filter = new IntentFilter();
        filter.addAction("example.test.broadcast");
        registerReceiver(broadcastReceiverCustom,filter);


        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            // go to custom_broadcast
            @Override
            public void onClick(View view) {
                // sendBroadcast
                Intent intent = new Intent();
                intent.setAction("example.test.broadcast");
                sendBroadcast(intent);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ttt","resume / register");
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiverCustom);
        Log.d("ttt","pause / unregister");
    }
}
