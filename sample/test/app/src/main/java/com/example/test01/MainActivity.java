package com.example.test01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;
    String msg;
    Dialog mDialog;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        msg = (String) textView.getText();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog = new TestDialog(mContext);
//                mDialog.
                ((TestDialog) mDialog).setValue("e");
                mDialog.show();

                /*
                Intent intent = new Intent();

                intent.putExtra("tt",msg);

                 */

            }
        });

    }
}
