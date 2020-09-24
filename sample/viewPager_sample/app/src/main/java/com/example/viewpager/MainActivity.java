package com.example.viewpager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vp = findViewById(R.id.vp);
        Button button = findViewById(R.id.btn_first);
        Button button2 = findViewById(R.id.btn_second);
        Button button3 = findViewById(R.id.btn_third);

        vp.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        vp.setCurrentItem(0); // 최초 페이지를 0번

        button.setOnClickListener(movePagerListener);
        button.setTag(0);
        button2.setOnClickListener(movePagerListener);
        button2.setTag(1);
        button3.setOnClickListener(movePagerListener);
        button3.setTag(2);
        // setTag : 태그선언 , getTag : 태그가져오기.
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
                    return new Fragment01();
                case 1:
                    return new Fragment02();
                case 2:
                    return new Fragment03();
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
