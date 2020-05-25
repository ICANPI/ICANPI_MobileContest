package com.example.danjam.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.danjam.R;
import com.example.danjam.Splash;
import com.example.danjam.fragment.FragmentCommunity;
import com.example.danjam.fragment.FragmentRank;
import com.example.danjam.fragment.FragmentSet;
import com.example.danjam.fragment.FragmentTimer;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    private FragmentManager fm = getSupportFragmentManager();

    private FragmentTimer fragmenttimer = new FragmentTimer();
    private FragmentRank fragmentrank = new FragmentRank();
    private FragmentCommunity fragmentcommunity = new FragmentCommunity();
    private FragmentSet fragmentset = new FragmentSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent splashintent = new Intent(this, Splash.class);
        startActivity(splashintent);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom);

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment,fragmenttimer);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fm.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.bottom_timer: {
                        transaction.replace(R.id.fragment,fragmenttimer).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.bottom_rank: {
                        transaction.replace(R.id.fragment, fragmentrank).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.bottom_community: {
                        transaction.replace(R.id.fragment, fragmentcommunity).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.bottom_set:{
                        transaction.replace(R.id.fragment,fragmentset).commitAllowingStateLoss();
                        break;
                    }
                }
                return true;
            }
        });






    }
}
