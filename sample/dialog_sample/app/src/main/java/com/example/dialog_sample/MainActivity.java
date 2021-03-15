package com.example.dialog_sample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/*
* show_1() > 기본 dialog
* show_2()
*
*
*
* */

public class MainActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //show_1(); // 기본 dialog 소환 !
                //show_2(); // list dialog 소환 !
                //show_3(); // editText 입력창 dialog 소환 !
                //show_4(); // 다중 선택 리스트 dialog 소환 !
                show_5(); // 단일 선택 리스트 dialog 소환 !
            }
        });
    }
    private void show_1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sample01 Title");
        builder.setMessage("Sample01 Content");
        builder.setPositiveButton("예...",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "예를 선택했습니당.",Toast.LENGTH_SHORT).show();
                    }
                });
        builder.setNegativeButton("아니오...",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "아니오를 선택했습니당.",Toast.LENGTH_SHORT).show();
                    }
                });
        builder.show();
    }

    private void show_2() {
        final List<String> ListItems = new ArrayList<>();
        ListItems.add("사과");
        ListItems.add("배");
        ListItems.add("감");
        ListItems.add("귤");
        ListItems.add("바나낭");

        final CharSequence[] items = ListItems.toArray(new String[ListItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sample02 Title");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String selectedText = items[i].toString();
                Toast.makeText(MainActivity.this, selectedText, Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    private void show_3() {
        final EditText editText = new EditText(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sample03 Title");
        builder.setMessage("당신의 나이는 ?? ?? ? ");
        builder.setView(editText);
        builder.setPositiveButton("입력..",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),editText.getText().toString(),Toast.LENGTH_SHORT).show();
                    }
                });
        builder.setNegativeButton("취소...",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.show();
    }

    private void show_4() {
        final List<String> listItems = new ArrayList<>();
        listItems.add("사과");
        listItems.add("배");
        listItems.add("감");
        listItems.add("귤");
        listItems.add("바나낭");
        final CharSequence[] items = listItems.toArray(new String[listItems.size()]);

        final List selectItems = new ArrayList();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sample04 Title");
        builder.setMultiChoiceItems(items, null,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
                        if(isChecked) {
                            selectItems.add(which);
                        } else if (selectItems.contains(which)) {
                            selectItems.remove(Integer.valueOf(which));
                        }
                    }
                });
        builder.setPositiveButton("Ok...",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String msg = "";
                        for (int i=0; i< selectItems.size(); i++ ) {
                            int index = (int) selectItems.get(i);

                            msg = msg+"\n"+(i+1)+" : "+listItems.get(index);
                        }
                        Toast.makeText(getApplicationContext(),
                                "Total "+selectItems.size() +" Items Selectd.\n"+msg,Toast.LENGTH_SHORT).show();
                    }
                });
        builder.setNegativeButton("Cancel...",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.show();
    }

    private void show_5() {
        final List<String> ListItems = new ArrayList<>();
        ListItems.add("사과");
        ListItems.add("배");
        ListItems.add("귤");
        ListItems.add("바나나");
        final CharSequence[] items =  ListItems.toArray(new String[ ListItems.size()]);

        final List SelectedItems  = new ArrayList();
        int defaultItem = 0;
        SelectedItems.add(defaultItem);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("AlertDialog Title");
        builder.setSingleChoiceItems(items, defaultItem,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SelectedItems.clear();
                        SelectedItems.add(which);
                    }
                });
        builder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String msg="";

                        if (!SelectedItems.isEmpty()) {
                            int index = (int) SelectedItems.get(0);
                            msg = ListItems.get(index);
                        }
                        Toast.makeText(getApplicationContext(),
                                "Items Selected.\n"+ msg , Toast.LENGTH_LONG)
                                .show();
                    }
                });
        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }
}
