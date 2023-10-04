package com.example.railway_reservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.railway_reservation.databinding.ActivityCancelbookingScreenBinding;

import java.util.ArrayList;

public class cancelbooking_screen extends AppCompatActivity {

    MyDbHelper db;
    ArrayList<String> tr_no,pnr,date;
    MyAdapterfordeletebooking adapter;
    ActivityCancelbookingScreenBinding cancelbookingScreenBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cancelbookingScreenBinding = ActivityCancelbookingScreenBinding.inflate(getLayoutInflater());
        View view = cancelbookingScreenBinding.getRoot();
        setContentView(view);
        db = new MyDbHelper(this);
        tr_no = new ArrayList<>();
        pnr = new ArrayList<>();
        date = new ArrayList<>();
        adapter = new MyAdapterfordeletebooking(this,tr_no,date,pnr);
        cancelbookingScreenBinding.cancelbookingRecyclerview.setAdapter(adapter);
        cancelbookingScreenBinding.cancelbookingRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
    }

    private void displaydata() {
        Cursor cursor = db.getBookedTickets();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "no data available", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                pnr.add(cursor.getString(0));
                tr_no.add(cursor.getString(1));
                date.add(cursor.getString(2));
            }
        }
    }
}