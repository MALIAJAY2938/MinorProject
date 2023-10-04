package com.example.railway_reservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.railway_reservation.databinding.ActivityMybookedticketsScreenBinding;

import java.util.ArrayList;

public class mybookedtickets_screen extends AppCompatActivity {

    MyDbHelper db;
    ArrayList<String> tr_no,pnr,date;
    MyAdapterForBookedTickets adapter;
    ActivityMybookedticketsScreenBinding mybookedticketsScreenBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mybookedticketsScreenBinding = ActivityMybookedticketsScreenBinding.inflate(getLayoutInflater());
        View view = mybookedticketsScreenBinding.getRoot();
        setContentView(view);
        db = new MyDbHelper(this);
        tr_no = new ArrayList<>();
        pnr = new ArrayList<>();
        date = new ArrayList<>();
        adapter = new MyAdapterForBookedTickets(this,pnr,tr_no,date);
        mybookedticketsScreenBinding.bookedticketRecyclerview.setAdapter(adapter);
        mybookedticketsScreenBinding.bookedticketRecyclerview.setLayoutManager(new LinearLayoutManager(this));
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