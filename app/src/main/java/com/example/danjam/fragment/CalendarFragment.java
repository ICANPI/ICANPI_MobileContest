package com.example.danjam.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.builders.DatePickerBuilder;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.danjam.R;
import com.example.danjam.api.Apiservice;
import com.example.danjam.data.SleepUpdateInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {
    private CalendarView calendarView;
    private Calendar calendar;
    private int day;
    private int month;
    private int year;
    private Retrofit retrofit;
    String get_day,get_week,get_month;
    final static String BASE_URL = "https://unitaemin.run.goorm.io/danzam/";
    private String sleepDay;
    private String token;
    private String TimeDifference;
    private String[] type = {"day","week","month"};
    private TextView today_get,week_get,month_get;

    public CalendarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        calendarView = view.findViewById(R.id.calendarview);
        today_get = view.findViewById(R.id.today_get_sleep);
        week_get = view.findViewById(R.id.week_get_sleep);
        month_get = view.findViewById(R.id.month_get_sleep);


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //타임값 가져와주기
//        if (getArguments()!= null){
//            sleepDay = getArguments().getString("time_data","");
//        }

        long end_time = System.currentTimeMillis();
        Date day_data = new Date(end_time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        sleepDay = simpleDateFormat.format(day_data);
        GetSleep(type,sleepDay);

        if (get_day!=null){

            today_get.setText(get_day);
            week_get.setText(get_week);
            month_get.setText(get_month);

        }else {
            today_get.setText("00:00:00");
            week_get.setText("00:00:00");
            month_get.setText("00:00:00");

        }




        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {

                calendar = eventDay.getCalendar();
                day = calendar.get(Calendar.DAY_OF_MONTH);
                month = calendar.get(Calendar.MONTH)+1;
                year = calendar.get(Calendar.YEAR);
                sleepDay=year+"-"+month+"-"+day;
                SharedPreferences time_sf = getActivity().getSharedPreferences("time_file", Context.MODE_PRIVATE);
                TimeDifference = time_sf.getString("time_difference", "00:00:00");

                GetSleep(type,sleepDay);

                if (today_get!=null){

                    today_get.setText(get_day);
                    week_get.setText(get_week);
                    month_get.setText(get_month);

                }else {
                    today_get.setText("00:00:00");
                    week_get.setText("00:00:00");
                    month_get.setText("00:00:00");
                }

                if (get_day!=null&&get_week!=null&&get_month!=null){
                    today_get.setText(get_day);
                    week_get.setText(get_week);
                    month_get.setText(get_month);
                }

            }
        });

        return view;
    }
    private void GetSleep(String[] strings,String date) {

        Apiservice apiservice = retrofit.create(Apiservice.class);

        SharedPreferences token_sf = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);
        token = token_sf.getString("access_token", "");

        Call<ResponseBody> call = apiservice.SleepGet("Bearer " + token, strings, date);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("윽으윽", response.code() + "");

                try {
                    String body = response.body().string();
                    String data1 = new JSONObject(body).getString("status");
                    Log.e("asdf",data1);

                }catch (Exception e){

                }

                try {
                    String body = response.body().string();
                    JSONObject data = new JSONObject(body).getJSONObject("data");
                    get_day = data.getString("day");
                    get_week = data.getString("week");
                    get_month = data.getString("month");
                    Log.e("으으으으으으으악", get_day);

                } catch (Exception e) {
                    Log.e("asddd", "나쁭엄");

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
}
