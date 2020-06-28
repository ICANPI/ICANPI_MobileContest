package com.example.danjam.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.danjam.R;
import com.example.danjam.broadcastreciver.Alarm;
import com.example.danjam.fragment.FragmentCommunity;
import com.example.danjam.fragment.FragmentRank;
import com.example.danjam.fragment.FragmentSet;
import com.example.danjam.fragment.FragmentTimer;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Time;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private String time;
    private String Timer;
    private String token;
    private View timer_view, rank_view, community_view, set_view;
    private FragmentManager fm = getSupportFragmentManager();

    private FragmentTimer fragmenttimer = new FragmentTimer();
    private FragmentRank fragmentrank = new FragmentRank();
    private FragmentCommunity fragmentcommunity = new FragmentCommunity();
    private FragmentSet fragmentset = new FragmentSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom);

        timer_view = findViewById(R.id.timer_view);
        rank_view = findViewById(R.id.rank_view);
        community_view = findViewById(R.id.community_view);
        set_view = findViewById(R.id.set_view);


        SharedPreferences sf = getSharedPreferences("time_file", Context.MODE_PRIVATE);
        //값없으면 00:00:00 뿌려줌
        Timer = sf.getString("time","00:00:00");
        token = getIntent().getStringExtra("token");

        //fragment 에 값 보내주기
        Bundle bundle = new Bundle();
        bundle.putString("time_data", Timer);
        fragmenttimer.setArguments(bundle);

        Bundle bundle1 = new Bundle();
        bundle1.putString("token",token);
        fragmentset.setArguments(bundle1);

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment, fragmenttimer).commitAllowingStateLoss();
        bottomNavigationView.setItemIconTintList(null);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fm.beginTransaction();

                switch (item.getItemId()) {

                    case R.id.bottom_timer: {
                        transaction.replace(R.id.fragment, fragmenttimer).commitAllowingStateLoss();
                        timer_view.setVisibility(View.VISIBLE);
                        rank_view.setVisibility(View.INVISIBLE);
                        community_view.setVisibility(View.INVISIBLE);
                        set_view.setVisibility(View.INVISIBLE);
                        break;
                    }
                    case R.id.bottom_rank: {
                        transaction.replace(R.id.fragment, fragmentrank).commitAllowingStateLoss();
                        timer_view.setVisibility(View.INVISIBLE);
                        rank_view.setVisibility(View.VISIBLE);
                        community_view.setVisibility(View.INVISIBLE);
                        set_view.setVisibility(View.INVISIBLE);
                        break;
                    }
                    case R.id.bottom_community: {
                        transaction.replace(R.id.fragment, fragmentcommunity).commitAllowingStateLoss();
                        timer_view.setVisibility(View.INVISIBLE);
                        rank_view.setVisibility(View.INVISIBLE);
                        community_view.setVisibility(View.VISIBLE);
                        set_view.setVisibility(View.INVISIBLE);
                        break;
                    }
                    case R.id.bottom_set: {
                        transaction.replace(R.id.fragment, fragmentset).commitAllowingStateLoss();
                        timer_view.setVisibility(View.INVISIBLE);
                        rank_view.setVisibility(View.INVISIBLE);
                        community_view.setVisibility(View.INVISIBLE);
                        set_view.setVisibility(View.VISIBLE);
                        break;
                    }
                }
                return true;
            }
        });





    }

    @Override
    protected void onPause() {
        super.onPause();
        //실시간으로 값 받기
        if (getIntent().getStringExtra("time")!=null){
            time = getIntent().getStringExtra("time");
            Log.e("activity",time);
            SharedPreferences sharedPreferences = getSharedPreferences("time_file", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("time",time);
            editor.commit();
        }
        else {
            Log.e("hi","값 안받아와짐");
        }

    }
}

//알람 매니저
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //알람 10시부터 스타뜨
//                Intent AlarmIntent = new Intent(getApplicationContext(),Alarm.class);
//                PendingIntent AlarmPIntent = PendingIntent.getBroadcast(getApplicationContext(),0,AlarmIntent,0);
//
//                alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//
//                Calendar calendar = Calendar.getInstance();
//                calendar.set(Calendar.HOUR_OF_DAY, hour);
//                calendar.set(Calendar.MINUTE, minute);
//                calendar.set(Calendar.SECOND, 0);
//                calendar.set(Calendar.MILLISECOND, 0);
//
//                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),  AlarmManager.INTERVAL_DAY, AlarmPIntent);
//
//
//            }
//        });
