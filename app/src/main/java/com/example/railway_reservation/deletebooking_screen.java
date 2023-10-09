package com.example.railway_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
        String date = intent.getStringExtra("date");
        String tr_no = intent.getStringExtra("tr_no");

        deletebookingScreenBinding.deleteticketPnrno.setText(String.valueOf(pnr));
        deletebookingScreenBinding.deleteticketDate.setText(String.valueOf(date));
        deletebookingScreenBinding.deleteticketTrainno.setText(String.valueOf(tr_no));



        deletebookingScreenBinding.deletebookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB.deletebookedticket(pnr);
                Toast.makeText(deletebooking_screen.this, "your ticket is cancled", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(deletebooking_screen.this, cancelbooking_screen.class));
            }
        });
    }
}