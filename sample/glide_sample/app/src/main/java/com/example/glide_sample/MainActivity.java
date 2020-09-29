package com.example.glide_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/*
* Glide :: 구글에서 밀고있는 Android Image Loading Library
*
* 1. Gradle 추가
* 2. 바로 쓰면 됌.
*
* 속성 *
* override : 이미지 사이즈 조정 ex ) .override(600,200)
* placeholder : 로딩하는동안 이미지 ex ) .placeholder(R.drawable.loading)
* error : 실패했을 때 이미지 ex ) .error(R.drawable.error)
* asGif() : 이미지를 로딩할 때 호출하는 함수.
*
* 참고 : https://velog.io/@rjsdnqkr1/Glide-%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%AC-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0-yuk1fmwzo1
* */
public class MainActivity extends AppCompatActivity {
    ImageView img1, img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1 = findViewById(R.id.imageView);
        img2 = findViewById(R.id.imageView2);

        // Glide.with(this).load("http://www.parkland.co.kr/images/top_bg_200908.jpg").into(img1); // directUrl 안먹힘.
        Glide.with(this).load(getResources().getDrawable(R.drawable.no_image)).into(img2); // 기본사용법
    }
}
