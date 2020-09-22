package com.example.push_sample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class AlertActivity extends BaseActivity {

    private String mTitleText;
    private String mContentsText;
    private String mGubun;
    private String mMessage;

    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        Log.d("ttt","AlertActivity ");
        // 잠금화면위에 액티비티를 띄우기 위한 플래그
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

        // 풀 스크린 플래그
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_alert);

        initData(getIntent());
        initComponent();

        Button open_btn = findViewById(R.id.button);
        open_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("where to go: ",mGubun);
                if (mGubun.equals("SUB")) {
                    intent = new Intent(AlertActivity.this,SubActivity.class);
                } else {
                    intent = new Intent(AlertActivity.this, MainActivity.class);
                }
                //Log.d("ttt","intentMessage: "+mMessage);
                //intent.putExtra("message",mMessage);
                startActivity(intent);
                finish();
            }
        });

        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.SCREEN_OFF");
        registerReceiver(mPowerBroadcast, filter);

        /*
         * 알람이 울릴시 진동주기
         * */

        long[] pattern = {0, 500, 200, 400, 100};
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // pattern 을 진동의 패턴 -1은 패턴의 반복은 한번
        vibe.vibrate(pattern, -1);

    }

    private void initData(Intent intent) {
        mTitleText = intent.getStringExtra("title");
        mContentsText = intent.getStringExtra("content");
        mGubun = intent.getStringExtra("gubun");
        mMessage = intent.getStringExtra("message");

        Log.d("ttt","mTitleText:"+mTitleText);
        Log.d("ttt","mContentsText:"+mContentsText);
        Log.d("ttt","mGubun:"+mGubun);
        Log.d("ttt","mMessage:"+mMessage);

        Intent broadIntent = new Intent("woww");
        broadIntent.putExtra("inBroadMessage",mMessage);
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadIntent);
    }

    TextView mTitle;
    TextView mContents;
    private void initComponent() {
        mTitle = (TextView) findViewById(R.id.alert_title);
        mContents = (TextView) findViewById(R.id.alert_contents);
        mTitle.setText(mTitleText);
        mContents.setText(mContentsText);
    }

    /*
     * 알람이 띄워진후 다시 잠금화면으로 돌아가는 것을
     * 감지하기위한 브로드캐스트리시버
     * 한번만 감지하면 되기때문에 감지된이후에는 브로드캐스트리시버를 제거해준다
     * */
    BroadcastReceiver mPowerBroadcast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
                Log.e("ttt","브로드캐스트리시버 제거 !");

                // 브로드캐스트 리시버 제거
                unregisterReceiver(mPowerBroadcast);

                //액티비티 종료
                AlertActivity.this.finish();

            }
        }
    };
}
