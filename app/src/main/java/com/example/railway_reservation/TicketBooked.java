package com.example.railway_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.railway_reservation.databinding.ActivityTicketBookedBinding;

public class TicketBooked extends AppCompatActivity {
    ActivityTicketBookedBinding ticketBookedBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ticketBookedBinding = ActivityTicketBookedBinding.inflate(getLayoutInflater());
        View view = ticketBookedBinding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        ticketBookedBinding.ticketbookedName.setText(name);

    }
}