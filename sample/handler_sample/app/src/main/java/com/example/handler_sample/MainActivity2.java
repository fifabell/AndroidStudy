package com.example.handler_sample;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        super.onCreate(savedInstanceState);
        mHandler = new Handler();

        Thread t = new Thread(new Runnable(){
            @Override
            public void run() {
                // 밑에 핸들러코드가 없으면 UI 작업 수행 X
                mHandler.post(new Runnable(){
                    @Override
                    public void run() {
                        // 핸들러로 인해 UI 작업 수행 O
                    }
                });
            }
        });
        t.start();

    }
}
