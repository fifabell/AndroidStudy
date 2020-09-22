package com.example.push_sample;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    BroadcastReceiver receiver;
    private static final String TAG = "ttt";
    long now = System.currentTimeMillis();
    Context mContext;

    Intent intent;

    private Context context;

    private android.app.NotificationManager notiManager;
    Notification.Builder notificationBuilder;

    MediaPlayer mediaPlayer;

    // [START receive_message]

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d("ttt","receive_on");
        //super.onMessageReceived(remoteMessage);


        //푸시울렸을때 화면깨우기.
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK
                | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
        wakeLock.acquire(3000);

        if (remoteMessage.getData() == null)
            return;

        Alertmanager alertManager = Alertmanager.getInstance(getApplicationContext(), remoteMessage);
        alertManager.alert();

//        sendNotification(remoteMessage.getData().get("title"), remoteMessage.getData().get("message"));
    }

    private void sendNotification(String title, String content) {
        // 포그라운드 및 백그라운드 푸시알림 처리

        Log.d("ttt","messageBody / messagTitle : "+title+" / "+content );

        //Intent intent = new Intent(this, SubActivity.class);
        intent = new Intent(this, AlertActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("title",title);
        intent.putExtra("content",content);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        // 오레오 8.0이상일 경우 채널을 반드시 생성
        final String CHANNEL_ID = "my_channel";
        NotificationManager mManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            final String CHANNEL_NAME = "채널 이르음";
            final String CHANNEL_DESCRIPTION = "채널 설명";
            final int importance = NotificationManager.IMPORTANCE_HIGH;

            // add in API level 26
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            mChannel.setDescription(CHANNEL_DESCRIPTION);
            mChannel.enableLights(true);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 100, 200});
            mChannel.setSound(defaultSoundUri, null);
            mChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            mManager.createNotificationChannel(mChannel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setAutoCancel(true);
        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setWhen(System.currentTimeMillis());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentText(content);
        builder.setContentIntent(pendingIntent);
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            // 아래 설정은 오레오부터 deprecated 되면서 NotificationChannel에서 동일 기능을 하는 메소드를 사용.
            builder.setContentTitle(title);
            builder.setSound(defaultSoundUri);
            builder.setVibrate(new long[]{500, 500});
        }
        mManager.notify(0,builder.build());

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
