package com.example.danjam.broadcastreciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.danjam.activity.MainActivity;
import com.example.danjam.fragment.FragmentTimer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenOnReceiver extends BroadcastReceiver {
    private String formatDate;



    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action.equals(Intent.ACTION_SCREEN_ON)) {
            long now = System.currentTimeMillis();
            Date date = new Date(now);
            SimpleDateFormat sdfNow = new SimpleDateFormat("HH:mm:ss");
            formatDate = sdfNow.format(date);
            Intent timeSendIntent = new Intent(context, MainActivity.class);
            timeSendIntent.putExtra("time",formatDate);
            context.startActivity(timeSendIntent);

        }
        else if (action.equals(Intent.ACTION_SCREEN_OFF)) {

            Log.e("hi","screen off");
        }

    }
}
