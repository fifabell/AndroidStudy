package com.example.webview_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/*
* 1. manifest Internet 허용
* 2. xml에 webview 넣기
* 3. java에 webview 코드 작성.
* */

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private String url = "https://m.parkland.co.kr/ca/video_pl.asp?SETURL=https://www.youtube.com/embed/-zptVMvsMOQ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true); // javascript 코드 사용가능.
        webView.loadUrl(url); // url 연결
        webView.setWebChromeClient(new WebChromeClient()); // 크롬에서 염
        webView.setWebViewClient(new WebViewClientClass()); // 빡쎄게 url 연결 ?
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 굳이 뭐..
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    private class WebViewClientClass extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
