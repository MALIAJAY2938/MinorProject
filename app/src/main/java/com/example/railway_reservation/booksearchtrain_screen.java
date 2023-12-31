package com.example.railway_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.railway_reservation.databinding.ActivityBooksearchtrainScreenBinding;

import java.text.DecimalFormat;
import java.util.Calendar;

public class booksearchtrain_screen extends AppCompatActivity {

    ActivityBooksearchtrainScreenBinding booksearchtrainbinding;

    static String strDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        booksearchtrainbinding = ActivityBooksearchtrainScreenBinding.inflate(getLayoutInflater());
        View view = booksearchtrainbinding.getRoot();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(view);


        String Class[] = getResources().getStringArray(R.array.Class);
        ArrayAdapter classselection = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,Class);
        booksearchtrainbinding.autoCompleteTextViewClass.setAdapter(classselection);

        String Quota[] = getResources().getStringArray(R.array.quota);
        ArrayAdapter quotaselection = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,Quota);
        booksearchtrainbinding.autoCompleteTextViewQuota.setAdapter(quotaselection);

        String Station[] = getResources().getStringArray(R.array.Stations);
        ArrayAdapter stationselection = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,Station);
        booksearchtrainbinding.autoCompleteTextViewFrom.setAdapter(stationselection);
        booksearchtrainbinding.autoCompleteTextViewTo.setAdapter(stationselection);

        booksearchtrainbinding.backToHomeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(booksearchtrain_screen.this,home_screen.class);
                startActivity(intent);
            }
        });
        booksearchtrainbinding.searchtrainscreenedittextJourneydate.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(booksearchtrain_screen.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        DecimalFormat form = new DecimalFormat("00");
                        booksearchtrainbinding.searchtrainscreenedittextJourneydate.getEditText().setText(form.format(dayOfMonth) + "/" +form.format((month+1))+ "/" + year);
                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });
        booksearchtrainbinding.searchtrainscreenbtnSearchtrains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strFrom = booksearchtrainbinding.autoCompleteTextViewFrom.getText().toString();
                String strTo = booksearchtrainbinding.autoCompleteTextViewTo.getText().toString();
                strDate = booksearchtrainbinding.searchtrainscreenedittextJourneydate.getEditText().getText().toString();
                String strClass = booksearchtrainbinding.searchtrainscreenSelectclass.getEditText().getText().toString();
                String strQuota = booksearchtrainbinding.searchtrainscreenSelectquota.getEditText().getText().toString();

                if (TextUtils.isEmpty(strFrom)){
                    Toast.makeText(booksearchtrain_screen.this, "From station is required", Toast.LENGTH_SHORT).show();
                    booksearchtrainbinding.autoCompleteTextViewFrom.requestFocus();
                }
                else if(TextUtils.isEmpty(strTo)){
                    Toast.makeText(booksearchtrain_screen.this, "To station is required", Toast.LENGTH_SHORT).show();
                    booksearchtrainbinding.autoCompleteTextViewTo.requestFocus();
                }
                else if(TextUtils.isEmpty(strDate)){
                    Toast.makeText(booksearchtrain_screen.this, "Journey Date is required", Toast.LENGTH_SHORT).show();
                    booksearchtrainbinding.searchtrainscreenedittextJourneydate.requestFocus();
                }
                else if(TextUtils.isEmpty(strClass)){
                    Toast.makeText(booksearchtrain_screen.this, "Class station is required", Toast.LENGTH_SHORT).show();
                    booksearchtrainbinding.searchtrainscreenSelectclass.requestFocus();
                }
                else if(TextUtils.isEmpty(strQuota)){
                    Toast.makeText(booksearchtrain_screen.this, "Quota station is required", Toast.LENGTH_SHORT).show();
                    booksearchtrainbinding.searchtrainscreenSelectquota.requestFocus();
                }
                else{
                    Intent intent = new Intent(booksearchtrain_screen.this,searchedtrainlist_screen.class);
                    intent.putExtra("strFrom",strFrom);
                    intent.putExtra("strTo",strTo);
                    intent.putExtra("strDate",strDate);
                    intent.putExtra("strClass",strClass);
                    intent.putExtra("strQuota",strQuota);
                    startActivity(intent);

                }



            }
        });
    }
}