package com.example.push_sample;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class SubActivity extends AppCompatActivity {
    String msg,cust;
    TextView textView,textView2;
    Button button;
    BroadcastReceiver mBR;
    static String str = "";

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("ttt","onNewIntent() !");
        setIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("ttt","onresume() !");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        final String TAG="TEST01";

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        button = findViewById(R.id.button);

        Intent secondIntent = getIntent();
        msg = secondIntent.getStringExtra("message");
        cust = secondIntent.getStringExtra("custom");

        textView.setText(msg);
        textView2.setText(cust);

        Intent intent = new Intent("woww");
        intent.putExtra("message",msg);
        intent.putExtra("custom",cust);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        // 테스트용 토큰 수령
        FirebaseInstanceId.getInstance().getInstanceId()
        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (!task.isSuccessful()) {
                    Log.w(TAG, "getInstanceId failed", task.getException());
                    return;
                }

                // Get new Instance ID token
                String token = task.getResult().getToken();
                Log.d("tokennn",token);

                // Log and toast
                Toast.makeText(SubActivity.this, token, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
