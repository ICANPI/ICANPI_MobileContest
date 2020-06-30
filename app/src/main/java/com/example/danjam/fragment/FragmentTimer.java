package com.example.danjam.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danjam.R;
import com.example.danjam.service.Screen_check;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTimer extends Fragment {

    private String Timer;
    Button calender_move;
    Button timer;
    TextView timer_tv;

    CalendarFragment calendarFragment = new CalendarFragment();


    public FragmentTimer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        calender_move = view.findViewById(R.id.calendar_move);
        timer = view.findViewById(R.id.timer);
        timer_tv = view.findViewById(R.id.today_sleep);

        calender_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                final FragmentTransaction transaction = fm.beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_anim,R.anim.fade_out);
                transaction.replace(R.id.fragment,calendarFragment).commitAllowingStateLoss();

            }
        });

        //타임값 가져와주기
        if (getArguments()!= null){

            Timer = getArguments().getString("time_data","");
        }

        if (Timer!=null){
            timer_tv.setText(Timer);
        }

        //버튼 클릭시 서비스 시작
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "화면체크가 시작됩니다", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(), Screen_check.class);
                getContext().startService(i);
            }
        });
        return view;
    }

}



