package com.example.railway_reservation.onboarding_fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.railway_reservation.R;
import com.example.railway_reservation.login_screen;
import com.example.railway_reservation.onboarding_screen;


public class third_fragment extends Fragment {

    onboarding_screen onboardingScreen;
    Button thirdback,thirdlogin;
    ViewPager viewPagerthird;
    public third_fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_third_fragment, container, false);

        thirdback = view.findViewById(R.id.onboarding3backbutton);
        thirdlogin = view.findViewById(R.id.onboarding3loginbutton);
        viewPagerthird = getActivity().findViewById(R.id.viewpager);
        onboardingScreen = (onboarding_screen)getActivity();

        thirdback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPagerthird.setCurrentItem(1);
            }
        });

        thirdlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(getActivity(), login_screen.class);
                startActivity(login);
            }
        });

        return view;
    }
}