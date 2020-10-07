package com.example.test01;

import android.os.Bundle;
import android.view.animation.Animation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProgressTestActivity extends AppCompatActivity {

    ProgressCard progressView;
    ProgressCard progressView1;
    ProgressCard progressView2;
    ProgressCard progressView3;
    ProgressCard progressView4;
    ProgressCard progressView5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_progress_tester);

        progressView = findViewById(R.id.test);
        progressView1 = findViewById(R.id.test1);
        progressView2 = findViewById(R.id.test2);
        progressView3 = findViewById(R.id.test3);
        progressView4 = findViewById(R.id.test4);
        progressView5 = findViewById(R.id.test5);

        AnimationTest.showWithAlpha(progressView, 0);
        AnimationTest.showWithAlpha(progressView1, 0);
        AnimationTest.showWithAlpha(progressView2, 0);
        AnimationTest.showWithAlpha(progressView3, 0);
        AnimationTest.showWithAlpha(progressView4, 0);
        AnimationTest.showWithAlpha(progressView5, 0);
    }
}
