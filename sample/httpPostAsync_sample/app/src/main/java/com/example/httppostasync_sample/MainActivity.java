package com.example.httppostasync_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

// http://mwebserver.parkland.co.kr/ShopApp/ordersuit/test.php

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        String url_tmp = "https://mwebserver.parkland.co.kr/ShopApp/ordersuit/test.php";
        Log.d("ttt","1");

//        runThread();

        HashMap<String, String> inputValues = new HashMap<>();
        inputValues.put("t1","abc");

        new HttpPostAsyncTaskTest(inputValues, url_tmp, new CallBack() {
            @Override
            public void run(Object result) {


                Log.d("ttt","2");
                Log.d("ttt","2.5"+result.toString());
                if (result != "null") {
                    try {
                        Log.d("ttt","3");
                        JSONArray datas = new JSONArray(result.toString()); // 에러날 경우 json 형식을 따르지 않으므로 이 부분에서 에러 - > catch문
                        Log.d("ttt","4");

                        Log.d("ttt",datas.toString());

                    } catch (JSONException e) {
                        Log.d("ttt","error...");
                        // timeout 또는 정상적으로 데이터를 받지 못했을 때
                        // 다시시도 또는 앱 종료 하는 다이얼로그 띄우기
                    }
                } else {
                    Log.d("ttt","empty");
                }

            }
        }, MainActivity.this).execute();

    }




    private void runThread(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    URL url = new URL("https://mwebserver.parkland.co.kr/ShopApp/ordersuit/test.php");

//                    URL url = new URL("https://mrgamza.tistory.com/639");
                    Log.d("ttt","inHttp");
                    HttpURLConnection http = (HttpURLConnection) url.openConnection();


                    http.setDefaultUseCaches(false);
                    http.setDoInput(true);                         // 서버에서 읽기 모드 지정
                    http.setDoOutput(true);                        // 서버로 쓰기 모드 지정
                    http.setRequestMethod("POST");                // 전송 방식은 POST
                    try {
                        http.setConnectTimeout(15000); //접근 시간 제한 15초
                        http.setReadTimeout(15000); //서버 로직 시간 제한 15초

                    }catch (NullPointerException e){
                        // url 리소스를 얻어오는 경우 위 if문에서 사용하는 데이터들이 nullpointerror가 나기때문에 에러처리
                        Log.d("HTTP_LOG","FROM SplashActivity Process");
                    }
                    http.setRequestProperty("content-type", "application/x-www-form-urlencoded");
                    http.setRequestProperty("Connection", "Keep-Alive");



                    Log.d("ttt","A.");
                    OutputStream os = http.getOutputStream();
                    Log.d("ttt","B.");
                    OutputStreamWriter outStream = new OutputStreamWriter(os, "EUC-KR");
                    Log.d("ttt","C.");

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();


    }



}
