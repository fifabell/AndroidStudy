package com.example.test01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    TextInputLayout ti_1;
    TextInputLayout ti_2;
    AppCompatEditText et_1;
    AppCompatEditText et_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ti_1 = (TextInputLayout) findViewById(R.id.ti_1);
        ti_2 = (TextInputLayout) findViewById(R.id.ti_2);

        et_1 = (AppCompatEditText) findViewById(R.id.et_1);
        et_2 = (AppCompatEditText) findViewById(R.id.et_2);


        ti_1.setCounterEnabled(true);
        ti_2.setCounterEnabled(true);

    }
}
