package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import java.util.ArrayList;

/*
* 리사이클러뷰 예제
* 순서 1) 리사이클러뷰가 표시될 위치 결정. > xml배치
* 순서 2) 리사이클러뷰 아이템 배치 형태 결정. > LinearLayoutManager(세로) 결정.
* 순서 3) 아이템 뷰 레이아웃 구성.
* 순서 4) 아이템 데이터 클래스 정의.
* 순서 5) 어댑터 상속 및 구현.
* 순서 6) 뷰홀더 상속 및 구현.
* 순서 7) 어댑터 생성 및 지정.
* 순서 8) 레이아웃매니저 생성 및 지정.
* 순서 9) 데이터 추가 및 아이템 표시.
*
* 참고사이트 ) https://recipes4dev.tistory.com/167
* */
public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView = null;
    RecyclerAdapter mAdapter = null;
    ArrayList<RecyclerItem> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);

        // 리사이클러뷰에 Adapter객체 지정
        mAdapter = new RecyclerAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);

        // 리사이클러뷰에 LinearlayoutManager 지정 (vertical)
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            addItem(getDrawable(R.mipmap.ic_launcher),
                    "Box", "test1");
            addItem(getDrawable(R.mipmap.ic_launcher_round),
                    "Circle","test2");
            addItem(getDrawable(R.drawable.ic_launcher_background),
                    "Ind","test3");
            addItem(getDrawable(R.drawable.ic_launcher_background),
                    "Ind","test3");
            addItem(getDrawable(R.drawable.ic_launcher_background),
                    "Ind","test3");
            addItem(getDrawable(R.drawable.ic_launcher_background),
                    "Ind","test3");
            addItem(getDrawable(R.drawable.ic_launcher_background),
                    "Ind","test3");
            addItem(getDrawable(R.drawable.ic_launcher_background),
                    "Ind","test3");
            addItem(getDrawable(R.drawable.ic_launcher_background),
                    "Ind","test3");
            addItem(getDrawable(R.drawable.ic_launcher_background),
                    "Ind","test3");
            addItem(getDrawable(R.drawable.ic_launcher_background),
                    "Ind","test3");
            addItem(getDrawable(R.drawable.ic_launcher_background),
                    "Ind","test3");
            addItem(getDrawable(R.drawable.ic_launcher_background),
                    "Ind","test3");
            addItem(getDrawable(R.drawable.ic_launcher_background),
                    "Ind","test3");
            addItem(getDrawable(R.drawable.ic_launcher_background),
                    "Ind","test3");

            mAdapter.notifyDataSetChanged();
        }
    }

    public void addItem(Drawable icon, String title, String desc) {
        RecyclerItem item = new RecyclerItem();

        item.setIconDrawable(icon);
        item.setTitleStr(title);
        item.setDescStr(desc);

        mList.add(item);
    }
}
