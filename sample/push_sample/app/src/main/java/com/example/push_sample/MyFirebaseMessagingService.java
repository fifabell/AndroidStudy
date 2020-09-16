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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    String TAG = "ttt";

    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> params = remoteMessage.getData();
        JSONObject object = new JSONObject(params);
        Log.d(TAG, "received data : " + object.toString());
        if (remoteMessage != null) {
            Log.d(TAG, remoteMessage.toString());
            try {
                String key = remoteMessage.getData().keySet().iterator().next();
                JSONObject jo = new JSONObject(remoteMessage.getData().get(key));
                Log.d(TAG, "received data : " + jo.toString());

                String id = jo.getString("message");
                String title = jo.getString("title");
                String activity =  jo.getString("activity");

                BoardItem item = new BoardItem(id, title, activity);

                new NotificationManagerr(this).makeNotification(item);

            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("pushMessage", "error Josn: " + remoteMessage.getData()
                        .get(remoteMessage.getData().keySet().iterator().next()));
            } catch (Exception e){
                //
            }
        }
    }

    private void sendNotification(RemoteMessage remoteMessage) {
        String title = remoteMessage.getData().get("title");
        String message = remoteMessage.getData().get("message");

        final String CHANNEL_ID = "ChannerID";
        NotificationManager mManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            final String CHANNEL_NAME = "ChannerName";
            final String CHANNEL_DESCRIPTION = "ChannerDescription";
            final int importance = NotificationManager.IMPORTANCE_HIGH;

            // add in API level 26
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            mChannel.setDescription(CHANNEL_DESCRIPTION);
            mChannel.enableLights(true);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 100, 200});
            mChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            mManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.common_google_signin_btn_icon_light_normal);
        builder.setAutoCancel(true);
        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setWhen(System.currentTimeMillis());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(title);
        builder.setContentText(message);
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            builder.setContentTitle(title);
            builder.setVibrate(new long[]{500, 500});
        }
        mManager.notify(0, builder.build());
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d(TAG, "token[" + s + "]" );
        /*
         * 기존의 FirebaseInstanceIdService에서 수행하던 토큰 생성, 갱신 등의 역할은 이제부터
         * FirebaseMessaging에 새롭게 추가된 위 메소드를 사용하면 된다.
         */
    }


}
