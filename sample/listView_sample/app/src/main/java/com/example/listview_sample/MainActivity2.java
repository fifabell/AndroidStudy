package com.example.listview_sample;

/*
 * 응용 > 다이얼로그
 * 문제있음 . 찾아볼 것.
 * */

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private Button button;
    private TextView textView2;
    private ListView listView;
    private LinearLayout linearLayout;
    private Context context;

    private ArrayList<TestItem> testItems;
    private TestListAdapter testListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        context = this;

        button = findViewById(R.id.button);
        textView2 = findViewById(R.id.textView2);
        listView = findViewById(R.id.listView);
        linearLayout = findViewById(R.id.linearLayout);

        // 데이터 저장 list
//        List<String> list = new ArrayList<>();
        testItems = new ArrayList<>();
        // 리스트뷰와 리스트를 연결하기 위해 사용되는 어댑터.
        // 어댑터에 리스트를 먼저 담는다.
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.custom_list_item, list);

        // 리스트뷰에 어댑터를 지정해준다.
        //listView.setAdapter(adapter);

        // 리스뷰에 보여질 아이템을 추가
        testItems.add(new TestItem("타이틀"));
        testItems.add(new TestItem("사과"));
        testItems.add(new TestItem("배"));
        testItems.add(new TestItem("바나나"));
        testItems.add(new TestItem("마늘"));
        testItems.add(new TestItem("귤"));

        testListAdapter = new TestListAdapter(context, testItems, new Callback() {
            @Override
            public void run(Object result) {
                textView2.setText(""+result);
                //Toast.makeText(context, ""+result,Toast.LENGTH_SHORT).show();
            }
        });
        listView.setAdapter(testListAdapter);

        /*
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                final List<String> selectedItems = new ArrayList<>();

                //리스트뷰에서 선택된 아이템의 목록을 가져온다.
                SparseBooleanArray checkedItemPositions = listView.getCheckedItemPositions();
                for( int i=0; i<checkedItemPositions.size(); i++){
                    int pos = checkedItemPositions.keyAt(i);

                    if (checkedItemPositions.valueAt(i))
                    {
                        selectedItems.add(listView.getItemAtPosition(pos).toString());
                    }
                }

                final CharSequence[] items = selectedItems.toArray(new String[selectedItems.size()]);


                //다이얼 로그에 가져온 목록을 보여준다.
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity2.this);
                dialogBuilder.setTitle("선택한 아이템 목록");
                dialogBuilder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int pos) {
                        String selectedText = items[pos].toString();
                        Toast.makeText(MainActivity2.this, selectedText, Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialogObject = dialogBuilder.create();
                alertDialogObject.show();

            }
        });
        */
    }
}
