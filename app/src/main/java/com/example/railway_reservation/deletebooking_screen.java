package com.example.railway_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.railway_reservation.databinding.ActivityDeletebookingScreenBinding;

public class deletebooking_screen extends AppCompatActivity {

    ActivityDeletebookingScreenBinding deletebookingScreenBinding;

    MyDbHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deletebookingScreenBinding = ActivityDeletebookingScreenBinding.inflate(getLayoutInflater());
        View view = deletebookingScreenBinding.getRoot();
        setContentView(view);
        DB = new MyDbHelper(this);
        Intent intent = getIntent();
        int pnr = Integer.parseInt(intent.getStringExtra("pnr"));

        deletebookingScreenBinding.deletebookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB.deletebookedticket(pnr);
            }
        });
    }
}