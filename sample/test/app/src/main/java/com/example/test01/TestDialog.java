package com.example.test01;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class TestDialog extends Dialog {

    Button button;
    TextView textView;
    Dialog dialog;

    public TestDialog(@NonNull Context context) {
        super(context);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testdialog);
        textView = (TextView) findViewById(R.id.textView);
        dialog = this;

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

    }


    public void setValue(String val) {
        if(textView != null) {
            textView.setText(val);
        }


    }

    @Override
    public void show() {
        if (textView != null) {
            super.show();
        }

    }
}
