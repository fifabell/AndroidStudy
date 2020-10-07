package com.example.test01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ProgressCard progress_test;
    Button button;
    ImageView view_mainimages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress_test = findViewById(R.id.progress_test);
        view_mainimages = findViewById(R.id.view_mainimages);

        AnimationTest.showWithAlpha(progress_test,0);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationTest.hideWithAlpha(progress_test,0, View.GONE);
                AnimationTest.showWithAlphaSlideUp(view_mainimages,500);
            }
        });


    }
}
