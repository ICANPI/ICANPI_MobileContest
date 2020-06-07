package com.example.danjam.fragment;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.danjam.R;
import com.example.danjam.service.Screen_check;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTimer extends Fragment {


    ImageView timer;
    TextView timer_tv;

    private BroadcastReceiver scrOnReceiver;
    private BroadcastReceiver scrOffReceiver;
    private IntentFilter scrOnFilter;
    private IntentFilter scrOffFilter;
    private String str;

    public FragmentTimer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        timer = view.findViewById(R.id.timer);
        timer_tv = view.findViewById(R.id.today_sleep);


        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), Screen_check.class);
                getContext().startService(i);
            }
        });




        return view;
    }



}
