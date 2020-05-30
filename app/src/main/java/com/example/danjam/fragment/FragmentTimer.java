package com.example.danjam.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.danjam.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTimer extends Fragment {

    private BroadcastReceiver scrOnReceiver;
    private BroadcastReceiver scrOffReceiver;
    private IntentFilter scrOnFilter;
    private IntentFilter scrOffFilter;


    public FragmentTimer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        scrOffFilter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        registerReceiver(scrOnReceiver, scrOnFilter);
        registerReceiver(scrOffReceiver, scrOffFilter);

        scrOnReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.e("hi", "SCREEN ON" );
            }
        };

        scrOnFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);

        scrOffReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("hi", "SCREEN OFF");
            }
        };



        // Inflate the layout for this fragment


        return view;
    }

    private void registerReceiver(BroadcastReceiver scrOnReceiver, IntentFilter scrOnFilter) {

    }
}
