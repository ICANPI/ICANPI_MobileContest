package com.example.danjam.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.danjam.R;
import com.example.danjam.fragment.FragmentCommunity;
import com.example.danjam.fragment.FragmentRank;
import com.example.danjam.fragment.FragmentSet;
import com.example.danjam.fragment.FragmentTimer;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

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

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom);

        timer_view = findViewById(R.id.timer_view);
        rank_view = findViewById(R.id.rank_view);
        community_view = findViewById(R.id.community_view);
        set_view = findViewById(R.id.set_view);


        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment, fragmenttimer).commitAllowingStateLoss();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fm.beginTransaction();

                switch (item.getItemId()) {

                    case R.id.bottom_timer: {
                        transaction.replace(R.id.fragment, fragmenttimer).addToBackStack(null).commit();
                        timer_view.setVisibility(View.VISIBLE);
                        rank_view.setVisibility(View.INVISIBLE);
                        community_view.setVisibility(View.INVISIBLE);
                        set_view.setVisibility(View.INVISIBLE);

                        break;
                    }
                    case R.id.bottom_rank: {
                        transaction.replace(R.id.fragment, fragmentrank).addToBackStack(null).commit();
                        timer_view.setVisibility(View.INVISIBLE);
                        rank_view.setVisibility(View.VISIBLE);
                        community_view.setVisibility(View.INVISIBLE);
                        set_view.setVisibility(View.INVISIBLE);
                        break;
                    }
                    case R.id.bottom_community: {
                        transaction.replace(R.id.fragment, fragmentcommunity).addToBackStack(null).commit();
                        timer_view.setVisibility(View.INVISIBLE);
                        rank_view.setVisibility(View.INVISIBLE);
                        community_view.setVisibility(View.VISIBLE);
                        set_view.setVisibility(View.INVISIBLE);
                        break;
                    }
                    case R.id.bottom_set: {
                        transaction.replace(R.id.fragment, fragmentset).addToBackStack(null).commit();
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
}
