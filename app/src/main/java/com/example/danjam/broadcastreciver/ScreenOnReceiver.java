package com.example.danjam.broadcastreciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.danjam.activity.MainActivity;
import com.example.danjam.api.Apiservice;
import com.example.danjam.data.SleepUpdateInfo;
import com.example.danjam.data.TimeAddData;
import com.example.danjam.fragment.FragmentTimer;
import com.example.danjam.service.Screen_check;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;






public class ScreenOnReceiver extends BroadcastReceiver {
    private String formatDate;
    private String formatDate1;
    private String TimeDifference;
    private String getFormatDate;
    private Retrofit retrofit;
    private String token;
    final static String BASE_URL = "https://unitaemin.run.goorm.io/danzam/";
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        if (action.equals(Intent.ACTION_SCREEN_ON)) {

            long end_time = System.currentTimeMillis();
            Date date1 = new Date(end_time);
            SimpleDateFormat sdfNow = new SimpleDateFormat("HH:mm:ss");
            formatDate1 = sdfNow.format(date1);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date first_time = null;
            Date last_time = null;
            try {
                first_time = sdf.parse(formatDate);
                last_time = sdf.parse(formatDate1);

            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (formatDate == null) {
                long start_time = System.currentTimeMillis();
                Date date = new Date(start_time);
                SimpleDateFormat sdfNow1 = new SimpleDateFormat("HH:mm:ss");
                formatDate = sdfNow1.format(date);
                Log.e("formatDate", formatDate);
            }
            if (formatDate1 == null) {
                Date date = new Date(end_time);
                SimpleDateFormat sdfNow2 = new SimpleDateFormat("HH:mm:ss");
                formatDate = sdfNow2.format(date);
                Log.e("formatDate", formatDate);
            }

            long duration = last_time.getTime() - first_time.getTime();
            Date day_data = new Date(end_time);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            getFormatDate = simpleDateFormat.format(day_data);

            TimeDifference = formatDuration(duration);
            SharedPreferences sharedPreferences = context.getSharedPreferences("time_file", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("time_difference", TimeDifference);
            editor.commit();

            Intent restart_intent = new Intent(context, MainActivity.class);
            context.startActivity(restart_intent);
            Intent stop_check = new Intent(context, Screen_check.class);
            context.stopService(stop_check);

            UpdateSleep(TimeDifference,getFormatDate,context);

        } else if (action.equals(Intent.ACTION_SCREEN_OFF)) {
            long start_time = System.currentTimeMillis();
            Date date = new Date(start_time);
            SimpleDateFormat sdfNow = new SimpleDateFormat("HH:mm:ss");
            formatDate = sdfNow.format(date);
            Log.e("formatDate", formatDate);
        }

    }

    private static String formatDuration(long duration) {
        long hours = TimeUnit.MILLISECONDS.toHours(duration);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(duration) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(duration) % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private void UpdateSleep(String SleepTime,String SleepDay,Context context){

        SleepUpdateInfo sleepUpdateInfo = new SleepUpdateInfo(SleepDay,SleepTime);
        Apiservice apiservice = retrofit.create(Apiservice.class);
        SharedPreferences token_sf = context.getSharedPreferences("token",Context.MODE_PRIVATE);
        token = token_sf.getString("access_token","");
        Call<SleepUpdateInfo> call = apiservice.SleepUpdate("Bearer "+token,sleepUpdateInfo);
        call.enqueue(new Callback<SleepUpdateInfo>() {
            @Override
            public void onResponse(Call<SleepUpdateInfo> call, Response<SleepUpdateInfo> response) {
                Log.e("asd",response.code()+"");
            }

            @Override
            public void onFailure(Call<SleepUpdateInfo> call, Throwable t) {

            }
        });
    }
}


//public class ScreenOnReceiver extends BroadcastReceiver {
//    private String formatDate;
//    private String formatDate1;
//    private String getFormatDate;
//    private String TimeDifference;
//    private String token;
//    private Retrofit retrofit;
//    private String result_time;
//    private String time;
//    final static String BASE_URL = "https://unitaemin.run.goorm.io/danzam/";
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        String action = intent.getAction();
//
//        if (action.equals(Intent.ACTION_SCREEN_ON)) {
//
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//
//            long end_time = System.currentTimeMillis();
//            Date date1 = new Date(end_time);
//            SimpleDateFormat sdfNow = new SimpleDateFormat("HH:mm:ss");
//            formatDate1 = sdfNow.format(date1);
//
//            Calendar calendar = Calendar.getInstance();
//            int hour = calendar.get(Calendar.HOUR_OF_DAY);
//
//            Date day_data = new Date(end_time);
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            getFormatDate = simpleDateFormat.format(day_data);
//
//            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//            Date first_time = null;
//            Date last_time = null;
//
//            if (formatDate==null){
//
//                long start_time = System.currentTimeMillis();
//                Date date = new Date(start_time);
//                SimpleDateFormat sdfNow1 = new SimpleDateFormat("HH:mm:ss");
//                formatDate = sdfNow1.format(date);
//                Log.e("formatDate",formatDate);
//
//            }
//
//            try {
//                first_time = sdf.parse(formatDate);
//                last_time = sdf.parse(formatDate1);
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//            long duration =last_time.getTime() - first_time.getTime();
//
//            SharedPreferences sharedPreferences1 = context.getSharedPreferences("time_file",Context.MODE_PRIVATE);
//            time= sharedPreferences1.getString("time_difference","");
//            TimeDifference = formatDuration(duration);
//            AddTime(time,TimeDifference,context);
//            SharedPreferences sharedPreferences = context.getSharedPreferences("time_file", Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("time_difference",result_time);
//            editor.commit();
//
//            Intent restart_intent = new Intent(context,MainActivity.class);
//            context.startActivity(restart_intent);
//            Intent stop_check = new Intent(context, Screen_check.class);
//            context.stopService(stop_check);
//
//            UpdateSleep(TimeDifference,getFormatDate,context);
//
//            if (sharedPreferences.getString("time_difference",null)!=null){
//                Log.e("broadcast",sharedPreferences.getString("time_difference",null));
//            }
//
//        }
//
//        else if (action.equals(Intent.ACTION_SCREEN_OFF)) {
//            long start_time = System.currentTimeMillis();
//            Date date = new Date(start_time);
//            SimpleDateFormat sdfNow = new SimpleDateFormat("HH:mm:ss");
//            formatDate = sdfNow.format(date);
//            Log.e("formatDate",formatDate);
//
//        }
//
//    }
//
//    private static String formatDuration(long duration) {
//        long hours = TimeUnit.MILLISECONDS.toHours(duration);
//        long minutes = TimeUnit.MILLISECONDS.toMinutes(duration) % 60;
//        long seconds = TimeUnit.MILLISECONDS.toSeconds(duration) % 60;
//        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
//    }
//
//
//

//
//    }
//
//
//}
