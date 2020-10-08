package com.example.singleton_sample;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Sample {
    private static String mMsg;
    private static Context mContext;

    // Private constructor prevents instantiation from other classes
    private Sample() { }

    /**
     * SampleHolder is loaded on the first execution of Singleton.getInstance()
     * or the first access to SampleHolder.INSTANCE, not before.
     */
    private static class SampleHolder {
        public static final Sample INSTANCE = new Sample();
    }
    public static Sample getInstance(Context context, String msg) {

        mMsg = msg;
        mContext = context;

        return SampleHolder.INSTANCE;
    }

    public void alert() {
        Toast.makeText(mContext, mMsg, Toast.LENGTH_SHORT).show();
    }

    public void goSub(String msg) {
        Intent intent = settingIntent(msg);
        if (intent != null) {
            mContext.startActivity(intent);
        }
    }

    private Intent settingIntent(String msg) {
        Intent intent = null;

        if (msg != null) {
            intent = new Intent(mContext,SubActivity.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            intent.putExtra("body",msg);
        }

        return intent;
    }
}
