package com.example.railway_reservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.railway_reservation.databinding.ActivitySearchedtrainlistScreenBinding;

import java.util.ArrayList;

public class searchedtrainlist_screen extends AppCompatActivity {


    ArrayList train_no,train_name,train_from,train_to,duration_time,arriving_time,destination_time;
    ActivitySearchedtrainlistScreenBinding searchedtrainlistScreenBinding;
    MyDbHelper DB;
    MyAdapterSearchedTrainlist adapterSearchedTrainlist;

    String strFrom,strTo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchedtrainlistScreenBinding = ActivitySearchedtrainlistScreenBinding.inflate(getLayoutInflater());
        View view = searchedtrainlistScreenBinding.getRoot();
        setContentView(view);
        DB = new MyDbHelper(this);

        Intent intent = getIntent();

        strFrom = intent.getStringExtra("strFrom");
        strTo = intent.getStringExtra("strTo");

        train_no = new ArrayList();
        train_name = new ArrayList();
        train_from = new ArrayList();
        train_to = new ArrayList();
        duration_time = new ArrayList();
        arriving_time = new ArrayList();
        destination_time = new ArrayList();
        adapterSearchedTrainlist = new MyAdapterSearchedTrainlist(this,train_no,train_name,train_from,train_to,duration_time,arriving_time,destination_time);
        searchedtrainlistScreenBinding.searchedtrainRecyclerview.setAdapter(adapterSearchedTrainlist);
        searchedtrainlistScreenBinding.searchedtrainRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        displaData();


    }

    private void displaData() {
        Cursor cursor = DB.getDataTrainList(strFrom,strTo);
        if (cursor.getCount()==0){
            Toast.makeText(this, "no such train found", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            while (cursor.moveToNext()){
                train_no.add(cursor.getString(0));
                train_name.add(cursor.getString(1));
                train_from.add(cursor.getString(2));
                train_to.add(cursor.getString(3));
                duration_time.add(cursor.getString(4));
                arriving_time.add(cursor.getString(5));
                destination_time.add(cursor.getString(6));
            }
        }
    }
}