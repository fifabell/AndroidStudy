package com.example.butterknifeviewbinding_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
* ButterKnife 라이브러리
* http://jakewharton.github.io/butterknife/
* https://calyfactory.github.io/view-binding/
* */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_main_title)
    TextView tvTitle;

    @BindView(R.id.btn_main_intent)
    Button btnIntent;

    @OnClick(R.id.btn_main_intent)
    public void onIntentButtonClick(){
        Log.d("ttt","click!");
        Intent intent = new Intent(this,TestFragment.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("ttt","1");
        ButterKnife.bind(this);
        Log.d("ttt","2");

        tvTitle.setText("tt....");
    }


}
