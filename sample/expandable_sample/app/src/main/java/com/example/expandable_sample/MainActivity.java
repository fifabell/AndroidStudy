package com.example.expandable_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Display;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ExpandableListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display newDisplay = getWindowManager().getDefaultDisplay();
        int width = newDisplay.getWidth();

        ArrayList<MyGroup> DataList = new ArrayList<MyGroup>();
        listView = (ExpandableListView)findViewById(R.id.mylist);
        MyGroup temp = new MyGroup("한글");
        temp.child.add("ㄱ");
        temp.child.add("ㄴ");
        temp.child.add("ㄷ");
        DataList.add(temp);
        temp = new MyGroup("영어");
        temp.child.add("a");
        temp.child.add("b");
        temp.child.add("c");
        DataList.add(temp);
        temp = new MyGroup("숫자");
        temp.child.add("1");
        temp.child.add("2");
        temp.child.add("3");
        DataList.add(temp);

        ExpandAdapter adapter = new ExpandAdapter(getApplicationContext(),R.layout.group_row,R.layout.child_row,DataList);
        listView.setIndicatorBounds(width-50, width); //이 코드를 지우면 화살표 위치가 바뀐다.
        listView.setAdapter(adapter);


    }
}
