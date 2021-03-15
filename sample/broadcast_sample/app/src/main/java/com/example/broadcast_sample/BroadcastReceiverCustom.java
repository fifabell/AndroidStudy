package com.example.broadcast_sample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BroadcastReceiverCustom extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Log.d("ttt","on custom broadcast");
        if (intent.getAction().equals("example.test.broadcast")) {
            Log.d("ttt","on custom broadcast");
        }
    }
}
