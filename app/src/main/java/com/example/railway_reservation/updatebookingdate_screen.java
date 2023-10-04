package com.example.railway_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.railway_reservation.databinding.ActivityUpdatebookingdateScreenBinding;

import java.text.DecimalFormat;
import java.util.Calendar;

public class updatebookingdate_screen extends AppCompatActivity {

    ActivityUpdatebookingdateScreenBinding updatebookingdateScreenBinding;
    MyDbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updatebookingdateScreenBinding = ActivityUpdatebookingdateScreenBinding.inflate(getLayoutInflater());
        View view =  updatebookingdateScreenBinding.getRoot();
        setContentView(view);
        db = new MyDbHelper(this);
        Intent intent = getIntent();
        int pnr = Integer.parseInt(intent.getStringExtra("pnr"));



        updatebookingdateScreenBinding.updatebookingDate.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(updatebookingdate_screen.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        updatebookingdateScreenBinding.updatebookingDate.getEditText().setText(String.valueOf(dayOfMonth + "/" +(month+1)+ "/" + year));
                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });

        updatebookingdateScreenBinding.updatebookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = updatebookingdateScreenBinding.updatebookingDate.getEditText().getText().toString();
//                Toast.makeText(updatebookingdate_screen.this, date, Toast.LENGTH_SHORT).show();
                db.updatebookedticket(date,pnr);
            }
        });
    }
}