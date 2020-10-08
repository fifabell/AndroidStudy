package com.example.singleton_sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {
    TextView textView;
    Sample sampleSingleton;
    String tmp_msg="";
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();
        String getMsg = intent.getStringExtra("body");

        textView = findViewById(R.id.textView);
        textView.setText(getMsg);

        editText = findViewById(R.id.editText);
        editText.setText("ttt");
        tmp_msg = editText.getText().toString();

        sampleSingleton = Sample.getInstance(SubActivity.this, tmp_msg);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), tmp_msg, Toast.LENGTH_SHORT).show();
                sampleSingleton.goSub(tmp_msg);
            }
        });
    }
}
