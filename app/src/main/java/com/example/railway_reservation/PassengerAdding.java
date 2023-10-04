package com.example.railway_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.railway_reservation.databinding.ActivityPassengerAddingBinding;

public class PassengerAdding extends AppCompatActivity {

    ActivityPassengerAddingBinding passengerAddingBinding;

    MyDbHelper Db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Db = new MyDbHelper(this);
        passengerAddingBinding = ActivityPassengerAddingBinding.inflate(getLayoutInflater());
        View view = passengerAddingBinding.getRoot();
        setContentView(view);
        String gender[] = getResources().getStringArray(R.array.gender);// getting array of gender from strins.xml
        ArrayAdapter genderselection = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,gender);
        passengerAddingBinding.autoCompleteTextViewGender.setAdapter(genderselection);

        String berth[] = getResources().getStringArray(R.array.BerthPreference);// getting array of gender from strins.xml
        ArrayAdapter berthselection = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,berth);
        passengerAddingBinding.autoCompleteTextViewBerthPreference.setAdapter(berthselection);
        Intent intent = getIntent();
        String date = booksearchtrain_screen.strDate;
        String tr_no = intent.getStringExtra("tr_no");
        String tr_name = intent.getStringExtra("tr_name");
        String tr_from = intent.getStringExtra("tr_from");
        String tr_to = intent.getStringExtra("tr_to");
        String tr_durationtime = intent.getStringExtra("tr_durationtime");
        String tr_arrivingtime = intent.getStringExtra("tr_arrivingtime");
        String tr_destinationtime = intent.getStringExtra("tr_destinationtime");

        passengerAddingBinding.txtTrainNo.setText(tr_no);
        passengerAddingBinding.txtTrainName.setText(tr_name);
        passengerAddingBinding.txtTrainfrom.setText(tr_from);
        passengerAddingBinding.txtTrainTo.setText(tr_to);
        passengerAddingBinding.txtTraindurationtime.setText(tr_durationtime);
        passengerAddingBinding.txtTrainArrivingtime.setText(tr_arrivingtime);
        passengerAddingBinding.txtTraindestitime.setText(tr_destinationtime);



        passengerAddingBinding.passengerscreenBtnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = passengerAddingBinding.passengerscreenedittextAdharName.getEditText().getText().toString();
                String Age = passengerAddingBinding.passengerscreenedittextAge.getEditText().getText().toString();
                String gender = passengerAddingBinding.passengerscreenSelectgender.getEditText().getText().toString();
                String berth = passengerAddingBinding.passengerscreenSelectBerthPreference.getEditText().getText().toString();
                String mobile = passengerAddingBinding.passengerscreenedittextMobilenumber.getEditText().getText().toString();
                if (TextUtils.isEmpty(name)){
                    Toast.makeText(PassengerAdding.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                    passengerAddingBinding.passengerscreenedittextAdharName.requestFocus();
                }
                else if(TextUtils.isEmpty(Age)){
                    Toast.makeText(PassengerAdding.this, "Please Enter Age", Toast.LENGTH_SHORT).show();
                    passengerAddingBinding.passengerscreenedittextAge.requestFocus();
                } else if (TextUtils.isEmpty(gender)) {
                    Toast.makeText(PassengerAdding.this, "Please select Gender", Toast.LENGTH_SHORT).show();
                    passengerAddingBinding.passengerscreenSelectgender.requestFocus();
                } else if (TextUtils.isEmpty(berth)) {
                    Toast.makeText(PassengerAdding.this, "Please select berth Preference", Toast.LENGTH_SHORT).show();
                    passengerAddingBinding.passengerscreenSelectBerthPreference.requestFocus();
                } else if (TextUtils.isEmpty(mobile)) {
                    Toast.makeText(PassengerAdding.this, "Please Enter Mobile no", Toast.LENGTH_SHORT).show();
                    passengerAddingBinding.passengerscreenedittextMobilenumber.requestFocus();
                }
                else{
                    Db.insertBookedTicket(tr_no,date);
                    Intent intent = new Intent(PassengerAdding.this,TicketBooked.class);
                    intent.putExtra("name",name);
                    startActivity(intent);
                }

            }
        });
        passengerAddingBinding.backToTrainSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PassengerAdding.this,searchedtrainlist_screen.class);
                startActivity(intent);
            }
        });
    }
}