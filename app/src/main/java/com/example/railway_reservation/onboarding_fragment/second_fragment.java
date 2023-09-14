package com.example.railway_reservation.onboarding_fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.railway_reservation.R;


public class second_fragment extends Fragment {
    Button secondnext,secondback;
    ViewPager viewPagersecond;

    public second_fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second_fragment, container, false);

        secondback = view.findViewById(R.id.onboarding2backbutton);
        secondnext = view.findViewById(R.id.onboarding2nextbutton);
        viewPagersecond = getActivity().findViewById(R.id.viewpager);

        // back button of second fragment
        secondback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPagersecond.setCurrentItem(0);
            }
        });

        //next button of second fragment
        secondnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPagersecond.setCurrentItem(2);
            }
        });
        return view;
    }
}