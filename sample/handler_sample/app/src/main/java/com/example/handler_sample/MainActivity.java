package com.example.handler_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

/*
* 단독 사용 Handler
* 1. 기본 생성자를 통해 Handler를 생성하면, 생성되는 Handler는 해당 Handler를 호출한 스레드의 MessageQueue와 Looper에 자동 연결된다. > MainActivity2
*  핸들러는 메인쓰레드를 호출시켜 UI변경 작업이 가능하다.
*  이 때, Message를 사용해서 메인스레드를 호출시킬 수 있는데, 아래 두 가지 방법으로 sendMessage를 수행할 수 있다.
* Handler.sendEmptyMessage(int what)
    Message what(ID) 를 사용할 경우 사용하는 메서드
* Handler.sendMessage(Message msg)
    Message what, arg1, obj 등 ID와 정보 등을 같이 사용하는 메서드

* 간접 스레드 내부에서 핸들러를 정의하지 않아도 runOnUiThread 메서드를 통해 UI작업이 가능하다.
*
* */
public class MainActivity extends AppCompatActivity {
    Button btn_start, btn_stop;
    TextView textView;
    Thread thread;
    boolean isThread = false;
    int timer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        // 스레드 시작
        btn_start = (Button)findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isThread = true;
                thread = new Thread() {
                    public void run() {
                        while(isThread) {
                            try {
                                timer++;

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        textView.setText(" "+timer);
                                    }
                                });

                                sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            handler.sendEmptyMessage(0);
                        }
                    }
                };
                thread.start();
            }
        });

        // 스레드 종료
        btn_stop = (Button)findViewById(R.id.btn_stop);
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isThread = false;
                Toast.makeText(getApplicationContext(),"Thread 종료", Toast.LENGTH_SHORT).show();
            }
        });


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread.start();


    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(getApplicationContext(), "in Thread", Toast.LENGTH_SHORT).show();
        }
    };
}
