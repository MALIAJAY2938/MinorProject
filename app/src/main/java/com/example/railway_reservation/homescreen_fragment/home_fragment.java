package com.example.railway_reservation.homescreen_fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.railway_reservation.R;
import com.example.railway_reservation.booksearchtrain_screen;

public class
home_fragment extends Fragment {


    public home_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_home_fragment, container, false);

        // book ticket set onclick listner
        LinearLayout bookticket = v.findViewById(R.id.linearLayoutbookticket);
        bookticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bookticket = new Intent(getActivity(), booksearchtrain_screen.class);
                startActivity(bookticket);
            }
        });
        LinearLayout pnrstatus = v.findViewById(R.id.linearLayoutpnrstatus);

        pnrstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.indianrail.gov.in/enquiry/PNR/PnrEnquiry.html?locale=en"));
                startActivity(intent);
            }
        });
        LinearLayout trainRoutes = v.findViewById(R.id.linearLayouttrainrootes);

        trainRoutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.indianrail.gov.in/enquiry/SCHEDULE/TrainSchedule.html?locale=en"));
                startActivity(intent);

            }
        });
        LinearLayout busbooking = v.findViewById(R.id.linearLayoutbusbooking);
        busbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.redbus.in"));
                startActivity(intent);
            }
        });
        LinearLayout flightbooking = v.findViewById(R.id.linearLayoutflightbooking);
        flightbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.skyscanner.co.in/?previousCultureSource=GEO_LOCATION&redirectedFrom=www.skyscanner.com"));
                startActivity(intent);
            }
        });

        LinearLayout hotelbooking = v.findViewById(R.id.linearLayouthotelbooking);

        hotelbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.booking.com/index.en-gb.html?aid=2336990;label=en-in-booking-desktop-FNUJyR1iYZtYeFdNgHudMQS652804038422:pl:ta:p1:p2:ac:ap:neg:fi:tikwd-17218370:lp1007753:li:dec:dm;ws=&gclid=CjwKCAjwgZCoBhBnEiwAz35RwiRWPhsx9OqRq02i1HZ888DGWYMMp2Hq-4ODIa1QSQ3YT7SzJs-hTBoCESsQAvD_BwE"));
                startActivity(intent);
            }
        });

        return v;


    }



}