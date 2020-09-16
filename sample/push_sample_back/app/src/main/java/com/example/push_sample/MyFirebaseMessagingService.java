package com.example.push_sample;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FCM";

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d(TAG, "token[" + s + "]" );
        /*
         * 기존의 FirebaseInstanceIdService에서 수행하던 토큰 생성, 갱신 등의 역할은 이제부터
         * FirebaseMessaging에 새롭게 추가된 위 메소드를 사용하면 된다.
         */
    }

    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // 메시지를 받았을 때 동작하는 메소드
        String title = remoteMessage.getData().get("title");
        String message = remoteMessage.getData().get("message");
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Title: " + title);
        Log.d(TAG, "Message: " + message);

        NotificationHelper notificationHelper = new NotificationHelper(getApplicationContext());
        notificationHelper.createNotification(title,message);
    }



}
