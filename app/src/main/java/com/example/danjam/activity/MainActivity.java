package com.example.danjam.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.danjam.R;
import com.example.danjam.api.Apiservice;
import com.example.danjam.fragment.FragmentCommunity;
import com.example.danjam.fragment.FragmentRank;
import com.example.danjam.fragment.FragmentSet;
import com.example.danjam.fragment.FragmentTimer;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private String TimeDifference;
    private View timer_view, rank_view, community_view, set_view;
    final static String BASE_URL = "https://unitaemin.run.goorm.io/danzam/";
    private Retrofit retrofit;
    private FragmentManager fm = getSupportFragmentManager();
    private String[] type = {"id","password","email","username"};

    private FragmentTimer fragmenttimer = new FragmentTimer();
    private FragmentRank fragmentrank = new FragmentRank();
    private FragmentCommunity fragmentcommunity = new FragmentCommunity();
    private FragmentSet fragmentset = new FragmentSet();

    FragmentTransaction transaction = fm.beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom);

        timer_view = findViewById(R.id.timer_view);
        rank_view = findViewById(R.id.rank_view);
        community_view = findViewById(R.id.community_view);
        set_view = findViewById(R.id.set_view);




        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserUpdate(type);


        SharedPreferences sf = getSharedPreferences("time_file", MODE_PRIVATE);

        TimeDifference = sf.getString("time_difference", "00:00:00");

        //fragment 에 값 보내주기
        Bundle bundle = new Bundle();
        bundle.putString("time_data", TimeDifference);
        fragmenttimer.setArguments(bundle);

        transaction.replace(R.id.fragment, fragmenttimer).commitAllowingStateLoss();
        bottomNavigationView.setItemIconTintList(null);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fm.beginTransaction();

                switch (item.getItemId()) {

                    case R.id.bottom_timer: {
                        transaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
                        transaction.replace(R.id.fragment, fragmenttimer).commitAllowingStateLoss();
                        timer_view.setVisibility(View.VISIBLE);
                        rank_view.setVisibility(View.INVISIBLE);
                        community_view.setVisibility(View.INVISIBLE);
                        set_view.setVisibility(View.INVISIBLE);
                        break;
                    }
                    case R.id.bottom_rank: {
                        transaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
                        transaction.replace(R.id.fragment, fragmentrank).commitAllowingStateLoss();
                        timer_view.setVisibility(View.INVISIBLE);
                        rank_view.setVisibility(View.VISIBLE);
                        community_view.setVisibility(View.INVISIBLE);
                        set_view.setVisibility(View.INVISIBLE);
                        break;
                    }
                    case R.id.bottom_community: {
                        transaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
                        transaction.replace(R.id.fragment, fragmentcommunity).commitAllowingStateLoss();
                        timer_view.setVisibility(View.INVISIBLE);
                        rank_view.setVisibility(View.INVISIBLE);
                        community_view.setVisibility(View.VISIBLE);
                        set_view.setVisibility(View.INVISIBLE);
                        break;
                    }
                    case R.id.bottom_set: {
                        transaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
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
    protected void onRestart() {
        super.onRestart();
            Log.e("윽ㄱ","frag,etsad");
            transaction.remove(fragmenttimer);
            fragmenttimer = new FragmentTimer();
            SharedPreferences sf1 = getSharedPreferences("time_file",MODE_PRIVATE);
            TimeDifference = sf1.getString("time_difference", "00:00:00");
            Bundle bundle = new Bundle();
            Log.e("asfdgaf",TimeDifference);
    }


    private void UserUpdate(String[] type){
        Apiservice apiservice = retrofit.create(Apiservice.class);


        SharedPreferences token_sf = getSharedPreferences("token",MODE_PRIVATE);
        Call<ResponseBody> call = apiservice.UserUpdate("Bearer "+token_sf.getString("access_token",""),type);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 401) {
                    //refreshtoken
                }
                try {
                    String body = response.body().string();
                    Log.e("hi",body);
                    JSONObject data = new JSONObject(body).getJSONObject("data");
                    String id = data.getString("id");
                    String pw = data.getString("password");
                    String email = data.getString("email");
                    String username = data.getString("username");
                    Bundle update_info = new Bundle();
                    update_info.putString("update_id", id);
                    update_info.putString("update_pw", pw);
                    update_info.putString("update_email", email);
                    update_info.putString("update_name", username);
                    fragmentset.setArguments(update_info);
//
//                    JSONObject dataObject = new JSONObject(body).getJSONObject("data");
//                    JSONObject tokenObject = dataObject.getJSONObject("token");
//                    JSONObject userObject = dataObject.getJSONObject("user");



                } catch(Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
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
