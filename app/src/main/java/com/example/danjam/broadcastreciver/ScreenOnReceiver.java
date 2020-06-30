package com.example.danjam.broadcastreciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.danjam.activity.MainActivity;
import com.example.danjam.fragment.FragmentTimer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ScreenOnReceiver extends BroadcastReceiver {
    private String formatDate;
    private String formatDate1;
    private String TimeDifference;



    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action.equals(Intent.ACTION_SCREEN_ON)) {

            long end_time = System.currentTimeMillis();
            Date date1 = new Date(end_time);
            SimpleDateFormat sdfNow = new SimpleDateFormat("HH:mm:ss");
            formatDate1 = sdfNow.format(date1);

            Intent timeSendIntent = new Intent(context, MainActivity.class);

            timeSendIntent.putExtra("end_time",formatDate1);
            timeSendIntent.putExtra("start_time",formatDate);

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date first_time = null;
            Date last_time = null;

            try {
                first_time = sdf.parse(formatDate);
                last_time = sdf.parse(formatDate1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long duration =last_time.getTime() - first_time.getTime();

            TimeDifference = formatDuration(duration);

            SharedPreferences sharedPreferences = context.getSharedPreferences("time_file", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("time_difference",TimeDifference);
            editor.commit();

            if (sharedPreferences.getString("time_difference",null)!=null){
                Log.e("broadcast",sharedPreferences.getString("time_difference",null));
            }

        }

        else if (action.equals(Intent.ACTION_SCREEN_OFF)) {
            long start_time = System.currentTimeMillis();
            Date date = new Date(start_time);
            SimpleDateFormat sdfNow = new SimpleDateFormat("HH:mm:ss");
            formatDate = sdfNow.format(date);
            Log.e("formatDate",formatDate);
        }

    }
    private static String formatDuration(long duration) {
        long hours = TimeUnit.MILLISECONDS.toHours(duration);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(duration) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(duration) % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
