package com.example.listview_sample;

/*
 * 기본 샘플
 * */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView2;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView2 = findViewById(R.id.textView2);
        listView = findViewById(R.id.listView);

        // 데이터 저장 list
        List<String> list = new ArrayList<>();

        // 리스트뷰와 리스트를 연결하기 위해 사용되는 어댑터.
        // 어댑터에 리스트를 먼저 담는다.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list); // 내장되어있는 item list사용.

        // 리스트뷰에 어댑터를 지정해준다.
        listView.setAdapter(adapter);

        // 리스트뷰의 아이템 클릭 시 해당 아이템의 문자열을 가져오는 listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id) {

                //클릭한 아이템의 문자열을 가져옴
                String selected_item = (String)adapterView.getItemAtPosition(position);

                //텍스트뷰에 출력
                textView2.setText(selected_item);
            }
        });

        // 리스뷰에 보여질 아이템을 추가
        list.add("사과");
        list.add("배");
        list.add("마늘");
        list.add("귤");

    }
}
