package com.example.danjam.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.danjam.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCommunity extends Fragment {

    public FragmentCommunity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community, container, false);


        // Inflate the layout for this fragment
        return view;
    }




}
