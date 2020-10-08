package com.example.singleton_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    String tmp_msg="";
    Sample sampleSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        tmp_msg = textView.getText().toString();

        sampleSingleton = Sample.getInstance(MainActivity.this, tmp_msg);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sampleSingleton.alert();
                sampleSingleton.goSub(tmp_msg);
            }
        });
    }
}
