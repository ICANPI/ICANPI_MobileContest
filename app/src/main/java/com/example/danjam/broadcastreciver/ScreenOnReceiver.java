package com.example.danjam.broadcastreciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.danjam.fragment.FragmentTimer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class ScreenOnReceiver extends BroadcastReceiver {
    String formatDate;
    FragmentTimer fragmentTimer = new FragmentTimer();


    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action.equals(Intent.ACTION_SCREEN_ON)) {
            long now = System.currentTimeMillis();
            Date date = new Date(now);
            SimpleDateFormat sdfNow = new SimpleDateFormat();
            formatDate = sdfNow.format(date);

            Bundle bundle = new Bundle(1);
            bundle.putString("key",formatDate);

            Log.e("hi",formatDate);
        }
        else if (action.equals(Intent.ACTION_SCREEN_OFF)) {
            Log.e("hi","screen off");
        }

    }
}
