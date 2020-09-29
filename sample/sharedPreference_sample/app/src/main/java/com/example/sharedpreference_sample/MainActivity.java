package com.example.sharedpreference_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

/*
* 간단한 코드로 가벼운 데이터 저장소를 위한 sharedPreference
* */
public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        resultText = findViewById(R.id.resultText);

        SharedPreferences sf = getSharedPreferences("sNameTest",MODE_PRIVATE); // sNameTest라는 틀을 가져온다.
        String text = sf.getString("textt","");  // 그 안에 textt라는 키를 찾아 내용을 가져오고 없으면 default값은 빈값으로 한다.
        resultText.setText(text);
    }

    @Override
    protected void onStop() {
        super.onStop();

        // 액티비티 종료 전 저장. sNameTest라는 틀에 저장할 것이다.
        SharedPreferences sharedPreferences = getSharedPreferences("sNameTest",MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit(); // editor를 사용해야 값을 저장할 수 있음.
        String textt = editText.getText().toString();  // 사용자가 입력한 저장할 데이터
        editor.putString("textt",textt); // 내가 입력한 값을 "textt"라는 키에 넣을 것이다.

        editor.commit(); // 최종 commit 적용.
    }
}
