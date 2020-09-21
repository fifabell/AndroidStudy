package com.example.push_sample;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    BroadcastReceiver receiver;
    private static final String TAG = "ttt";

    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d("ttt","receive_on");

        //Handle FCM Message
        Log.e(TAG, remoteMessage.getFrom());

        // check if message contains a data payload
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG,  "message data payload: "+remoteMessage.getData());

            handleNow();
        }

        // Check if message contains a notification payload
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Message Notification Title: "+ remoteMessage.getNotification().getTitle());
            Log.e(TAG, "Message Notification Body: "+ remoteMessage.getNotification().getBody());
            Log.e(TAG, "Message Notification clickAction: "+ remoteMessage.getNotification().getClickAction());
            Log.e(TAG, "Message Notification form: "+ remoteMessage.getFrom());
            Log.e(TAG, "Message Notification custom: "+ remoteMessage.getData().get("custom"));

            String isBody = remoteMessage.getNotification().getBody();
            String getCustom = remoteMessage.getData().get("custom");
            if (TextUtils.isEmpty(isBody)) {
                Log.e(TAG,"ERR: Message data is empty...");
            } else {

                // Broadcast Data Sending Test
//                Intent intent = new Intent("alert_data");
//                intent.putExtra("custom",getCustom);
//                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
            }
        }
    }

    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");
    }

    /*  새로운 토큰이 생성되는 경우 */

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e(TAG, "token[" + s + "]" );
        sendRegistrationToServer(s);
    }

    private void sendRegistrationToServer(String token) {
        Log.e(TAG,"here! sendRegistrationToServer! token is "+token);
    }


}
