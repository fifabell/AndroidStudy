package com.example.push_sample;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;

class AlertNotificationBuilder {
    Context mContext;

    boolean hasAlert;

    public AlertNotificationBuilder(Context context) {
        mContext = context;
        hasAlert = false;
    }

    public void hasAlert(boolean alert) {
        hasAlert = alert;
    }

    public Notification buildNotification(RemoteMessage remoteMessage, String key, PendingIntent pendingIntent) {
        Log.d("ttt","buildNotification_on");
        Notification notification = null;
        if (remoteMessage != null) {

            String messageBody = remoteMessage.getData().get(key);
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            Notification.Builder notificationBuilder;
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationBuilder = new Notification.Builder(mContext, "parkland_noti");
                notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
            } else {
                notificationBuilder = new Notification.Builder(mContext);
            }

            notificationBuilder.setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            if(hasAlert){
                notificationBuilder.setDefaults(Notification.DEFAULT_ALL) // 노티피케이션 호출시 진동, 알림음 발생
                        .setSound(defaultSoundUri);
            }

            // 구분자별 Notification 세팅
            if (key.equals(Alertmanager.KEY_SUB)) {
                notificationBuilder.setContentTitle("ThisisSUB")
                        .setContentText(messageBody)
                        .setStyle(new Notification.BigTextStyle().setBigContentTitle("SUBTITLE").bigText(messageBody)).
                        setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.auth_stemp)).
                        setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.noti_graph));
            }  else {
                notificationBuilder.setContentTitle("ThisisMain")
                        .setContentText(messageBody)
                        .setStyle(new Notification.BigTextStyle().setBigContentTitle("MAINTITLE").bigText(messageBody)).
                        setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.common_full_open_on_phone));
            }

            // Notification build
            notification = notificationBuilder.build();
        }
        return notification;
    }
}
