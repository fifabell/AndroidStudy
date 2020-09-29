package com.example.viewpager.viewpager_sample.indicator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.viewpager.R;
import com.example.viewpager.viewpager_sample.viewpager_sample.MainActivity;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;


/*
* 1. (어느 라이브러리를 사용하는가에 따라) 프로젝트 단위 gladle 추가 /  app 추가
* 2. xml 에서 viewpager 와 indicator 연결
* */

public class MainActivity_indi extends AppCompatActivity {
    ViewPager vp;
    DotsIndicator dotsIndicator;
    MyPagerAdapter pagerAdapter;
    View mainIamges;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.indi_activity_main_sample);
        mainIamges = findViewById(R.id.view_mainimages);
        pagerAdapter = new MyPagerAdapter(this);

        dotsIndicator = (DotsIndicator) findViewById(R.id.dots_indicator);
        vp = findViewById(R.id.vp);

        vp.setAdapter(new pagerAdapter(getSupportFragmentManager()));

        dotsIndicator.setViewPager(vp);
        Button button = findViewById(R.id.btn_first);
        Button button2 = findViewById(R.id.btn_second);
        Button button3 = findViewById(R.id.btn_third);

        vp.setCurrentItem(0); // 최초 페이지를 0번

        button.setOnClickListener(movePagerListener);
        button.setTag(0);
        button2.setOnClickListener(movePagerListener);
        button2.setTag(1);
        button3.setOnClickListener(movePagerListener);
        button3.setTag(2);
    }

    // 페이지 끌기 리스너
    View.OnClickListener movePagerListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int tag = (int) view.getTag();
            vp.setCurrentItem(tag);
        }
    };

    private class pagerAdapter extends FragmentStatePagerAdapter {
        // 기본 생성자 + 함수 2개 (getItem + getCount)

        public pagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0:
                    return new Fragment01_indi();
                case 1:
                    return new Fragment02_indi();
                case 2:
                    return new Fragment03_indi();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
