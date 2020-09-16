package com.example.push_sample;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;

import java.net.URL;

public class NotificationManagerr {



    private final String TAG = "ManagerNotification";

    private Context context;
    private android.app.NotificationManager notiManager;
    public static final int NOTIFY_ID = 777;

    public static final String NOTIFY_ACTION = "parkland.notification";
    private final String CHANNEL_ID = "parkland_notification_channel";

    Intent intent;
    Notification.Builder notificationBuilder;

    BoardItem item;
    public NotificationManagerr(Context context) {
        Log.d("ttt","new notificationManagerr");
        this.context = context;
        notiManager = (android.app.NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
    }


    public void makeNotification(BoardItem item) {
        this.item = item;
        long now = System.currentTimeMillis();
        Log.d("ttt_now",""+now);
        Log.d("tt_item: ",item.toString());

        // 오픈 할 페이지
        if (iisEmpty(item.getActivity())) {
        } else {
            intent = new Intent(context, SubActivity.class);
        }

        intent.putExtra("NOTIFICATION", NOTIFY_ID);

        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "파크랜드 매장관리 알림", android.app.NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("channel description");
            notificationChannel.enableLights(false);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.enableVibration(false);
            notificationChannel.setVibrationPattern(new long[]{0, 300, 50, 300});
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            notiManager.createNotificationChannel(notificationChannel);
            notificationBuilder = new Notification.Builder(context, CHANNEL_ID);
        } else {
            notificationBuilder = new Notification.Builder(context);
        }
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(now)
                .setSubText("PARKLAND")
                .setContentTitle("titlttte")
                .setContentText("contentttt")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_ALL);

    }

    private boolean iisEmpty(String word) {
        if (word == null) {
            return true;
        }
        if (word.equals("") || word.equals("null")) {
            return true;
        }
        return false;
    }

    // 화면 켜기
    private void screenOn() {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK
                | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
        wakeLock.acquire(3000);
    }


    @SuppressLint("StaticFieldLeak")
    private void loadImage() {
        new AsyncTask<Void, Bitmap, Bitmap>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Bitmap doInBackground(Void... voids) {
                Bitmap bitmap = null;

                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap o) {
                super.onPostExecute(o);
                if (o == null) {
                    loadImage();
                    return;
                }
                Notification.BigPictureStyle pictureStyle = new Notification.BigPictureStyle();
                pictureStyle.bigPicture(o);
                notificationBuilder.setStyle(pictureStyle);
                String id = "";
                intent = new Intent(id);
                context.sendBroadcast(intent);
                notiManager.notify(NOTIFY_ID, notificationBuilder.build());
                screenOn();
            }
        }.execute();
    }

}
