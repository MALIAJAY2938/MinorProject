package com.example.railway_reservation.onboarding_fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.railway_reservation.R;
import com.example.railway_reservation.databinding.ActivityOnboardingScreenBinding;
import com.example.railway_reservation.databinding.FragmentFirstFragmentBinding;

public class first_fragment extends Fragment {
    Button firstnext;
    ViewPager viewPagerfirst;

    public first_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first_fragment, container, false);

        //initialize viewpager from onboarding_screen
        viewPagerfirst = getActivity().findViewById(R.id.viewpager);
        firstnext = view.findViewById(R.id.onboarding1nextbutton);

        firstnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPagerfirst.setCurrentItem(1);
            }
        });

        return view;
    }
}