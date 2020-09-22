package com.example.push_sample;

import android.content.BroadcastReceiver;
import android.content.Context;
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

public class SubActivity extends BaseActivity {
    String message;
    TextView textView;
    Button button;
    static String str = "";
    BroadcastReceiver mBR;

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
        button = findViewById(R.id.button);

        //Intent secondIntent = getIntent();
        //message = getIntent().getStringExtra("message");
        //Log.d("ttt: ","getIntentsubM: "+message);

        Log.d("ttt","sub..msg"+mMsg);

        str = mMsg;
        textView.setText("from Broad:"+ mMsg);
//        mBR = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                // ui변경안됨.
//                String data = intent.getStringExtra("inBroadMessage");
//                Log.d("ttt","getReceiveData2: "+data);
//                str = data;
//                textView.setText("from Broad:"+ data);
//            }
//        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
