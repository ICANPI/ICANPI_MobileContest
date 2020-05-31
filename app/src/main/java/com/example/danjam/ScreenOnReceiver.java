package com.example.danjam;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ScreenOnReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_SCREEN_ON)) {
            Log.e("hi","screen on");
        }
        else if (action.equals(Intent.ACTION_SCREEN_OFF)) {
            Log.e("hi","screen off");
        }



    }
}
