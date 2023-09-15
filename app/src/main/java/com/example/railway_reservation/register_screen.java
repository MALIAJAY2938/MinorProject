package com.example.railway_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.railway_reservation.databinding.ActivityRegisterScreenBinding;

import java.text.DecimalFormat;
import java.util.Calendar;

public class register_screen extends AppCompatActivity {
    ActivityRegisterScreenBinding registerbinding;
    MyDbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registerbinding = ActivityRegisterScreenBinding.inflate(getLayoutInflater());
        View view = registerbinding.getRoot();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(view);
        DB = new MyDbHelper(this);

        // register screen login button if user already have an account onclick
        registerbinding.registerscreenbtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(register_screen.this, login_screen.class);
                startActivity(login);
                finish();
            }
        });

        // register screen register button onclick
        registerbinding.registerscreenbtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(register_screen.this, login_screen.class);
                startActivity(register);
                finish();
            }
        });

        // regiser screen country code selection
        String countrycode[] = getResources().getStringArray(R.array.counrtycode);
        ArrayAdapter countrycodeselection = new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,countrycode);
        registerbinding.autoCompleteTextViewCountrycode.setAdapter(countrycodeselection);

        // register screen gender selection
        String gender[] = getResources().getStringArray(R.array.gender);// getting array of gender from strins.xml
        ArrayAdapter genderselection = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,gender);
        registerbinding.autoCompleteTextViewGender.setAdapter(genderselection);

        // register screen state selection
        String state[] = getResources().getStringArray(R.array.state);// getting array of state from strings.xml
        ArrayAdapter stateselection = new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,state);
        registerbinding.autoCompleteTextViewState.setAdapter(stateselection);

        // register screen displaying city according to the state selection
        registerbinding.autoCompleteTextViewState.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedstate = registerbinding.autoCompleteTextViewState.getText().toString(); // getting selected item
                setcity(selectedstate);
                registerbinding.registerscreenSelectcity.getEditText().setText("");
//                switch (selectedstate){
//                    case "Andhra Pradesh":
////                        String city[] = getResources().getStringArray(R.array.AndhraPradesh);
//                        ArrayAdapter cityselection = ArrayAdapter.createFromResource(parent.getContext(),R.array.AndhraPradesh, android.R.layout.simple_expandable_list_item_1);
//                        registerbinding.autoCompleteTextViewCity.setAdapter(cityselection);
//                        break;
//                }

            }
        });

        //register screen birthdate selection
        registerbinding.registerscreenedittextBirthdate.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(register_screen.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        DecimalFormat form = new DecimalFormat("00");
                        registerbinding.registerscreenedittextBirthdate.getEditText().setText(form.format(dayOfMonth) + "/" +form.format((month+1))+ "/" + year);
                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });

        registerbinding.registerscreenbtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = registerbinding.registerscreenedittextFullname.getEditText().getText().toString();
                String username = registerbinding.registerscreenedittextUsername.getEditText().getText().toString();
                String mobileno = registerbinding.registerscreenedittextMobilenumber.getEditText().getText().toString();
                String dob = registerbinding.registerscreenedittextBirthdate.getEditText().getText().toString();
                String gender = registerbinding.registerscreenSelectgender.getEditText().getText().toString();
                String state = registerbinding.registerscreenSelectstate.getEditText().getText().toString();
                String city = registerbinding.registerscreenSelectcity.getEditText().getText().toString();
                String password = registerbinding.registerscreenedittextPassword.getEditText().getText().toString();
                String countrycode = registerbinding.registerscreenedittextCountrycode.getEditText().getText().toString();
                if(TextUtils.isEmpty(fullname)){
                    Toast.makeText(register_screen.this, "please enter fullname", Toast.LENGTH_SHORT).show();
                    registerbinding.registerscreenedittextFullname.requestFocus();
                }
                else if (TextUtils.isEmpty(username)){
                    Toast.makeText(register_screen.this, "please enter username", Toast.LENGTH_SHORT).show();
                    registerbinding.registerscreenedittextUsername.requestFocus();
                }
                else if (TextUtils.isEmpty(mobileno)){
                    Toast.makeText(register_screen.this, "please enter mobileno", Toast.LENGTH_SHORT).show();
                    registerbinding.registerscreenedittextMobilenumber.requestFocus();
                }
                else if(mobileno.length() != 10){
                    Toast.makeText(register_screen.this, "Enter Mobile No Correctly", Toast.LENGTH_SHORT).show();
                    registerbinding.registerscreenedittextMobilenumber.requestFocus();
                } else if (TextUtils.isEmpty(countrycode)) {
                    Toast.makeText(register_screen.this, "Please select Country code", Toast.LENGTH_SHORT).show();
                    registerbinding.registerscreenedittextCountrycode.requestFocus();
                } else if (TextUtils.isEmpty(dob)){
                    Toast.makeText(register_screen.this, "please select date of birth", Toast.LENGTH_SHORT).show();
                    registerbinding.registerscreenedittextBirthdate.requestFocus();
                }
                else if (TextUtils.isEmpty(gender)){
                    Toast.makeText(register_screen.this, "please enter gender", Toast.LENGTH_SHORT).show();
                    registerbinding.registerscreenedittextFullname.requestFocus();
                }
                else if (TextUtils.isEmpty(state)){
                    Toast.makeText(register_screen.this, "please select state", Toast.LENGTH_SHORT).show();
                    registerbinding.registerscreenSelectstate.requestFocus();
                }
                else if (TextUtils.isEmpty(city)){
                    Toast.makeText(register_screen.this, "please select city", Toast.LENGTH_SHORT).show();
                    registerbinding.registerscreenSelectcity.requestFocus();
                }
                else if (TextUtils.isEmpty(password)){
                    Toast.makeText(register_screen.this, "please Enter password", Toast.LENGTH_SHORT).show();
                    registerbinding.registerscreenedittextPassword.requestFocus();
                }
                else if (password.length() < 8){
                    Toast.makeText(register_screen.this, "please Enter Strong password", Toast.LENGTH_SHORT).show();
                    registerbinding.registerscreenedittextPassword.requestFocus();
                }
                else {
                    Boolean checkmobileno = DB.checkMobileno(mobileno);
                    if (checkmobileno==true){
                        Toast.makeText(register_screen.this, "Mobile no is already exists", Toast.LENGTH_SHORT).show();
                        registerbinding.registerscreenedittextMobilenumber.requestFocus();
                    }
                    else {
                        mobileno = countrycode+mobileno;
                        Boolean checkuser = DB.checkUser(username);
                        if (checkuser == false){
                            boolean insert = DB.insertData(fullname,username,mobileno,dob,gender,state,city,password);
                            if (insert == true){
                                Toast.makeText(register_screen.this, "user Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(register_screen.this,login_screen.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(register_screen.this, "Registration is failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(register_screen.this, "Username is already exists! you can go to sign in", Toast.LENGTH_SHORT).show();
                            registerbinding.registerscreenedittextUsername.setError("username is already exists");
                        }
                    }
                }


            }
        });
    }
    //set city according to state selection
    public void setcity(String state){
        switch (state)
        {
            case "Andhra Pradesh":
//                String city[] = getResources().getStringArray(R.array.AndhraPradesh);
                ArrayAdapter cityselection_1 = ArrayAdapter.createFromResource(this,R.array.AndhraPradesh,android.R.layout.simple_expandable_list_item_1);
                registerbinding.autoCompleteTextViewCity.setAdapter(cityselection_1);
                break;

            case "Arunachal Pradesh" :
                ArrayAdapter cityselection_2 = ArrayAdapter.createFromResource(this,R.array.ArunachalPradesh,android.R.layout.simple_expandable_list_item_1);
                registerbinding.autoCompleteTextViewCity.setAdapter(cityselection_2);
                break;

            case "Assam" :
                ArrayAdapter cityselection_3 = ArrayAdapter.createFromResource(this,R.array.Assam,android.R.layout.simple_expandable_list_item_1);
                registerbinding.autoCompleteTextViewCity.setAdapter(cityselection_3);
                break;

            case "Bihar" :
                ArrayAdapter cityselection_4 = ArrayAdapter.createFromResource(this,R.array.Bihar,android.R.layout.simple_expandable_list_item_1);
                registerbinding.autoCompleteTextViewCity.setAdapter(cityselection_4);
                break;

            case "Chhattisgarh" :
                ArrayAdapter cityselection_5 = ArrayAdapter.createFromResource(this,R.array.Chhattisgarh,android.R.layout.simple_expandable_list_item_1);
                registerbinding.autoCompleteTextViewCity.setAdapter(cityselection_5);
                break;

            case "Goa" :
                ArrayAdapter cityselection_6 = ArrayAdapter.createFromResource(this,R.array.Goa,android.R.layout.simple_expandable_list_item_1);
                registerbinding.autoCompleteTextViewCity.setAdapter(cityselection_6);
                break;

            case "Gujarat" :
                ArrayAdapter cityselection_7 = ArrayAdapter.createFromResource(this,R.array.Gujarat,android.R.layout.simple_expandable_list_item_1);
                registerbinding.autoCompleteTextViewCity.setAdapter(cityselection_7);
                break;

            case "Haryana" :
                ArrayAdapter cityselection_8 = ArrayAdapter.createFromResource(this,R.array.Haryana,android.R.layout.simple_expandable_list_item_1);
                registerbinding.autoCompleteTextViewCity.setAdapter(cityselection_8);
                break;

            case "Himachal Pradesh" :
                ArrayAdapter cityselection_9 = ArrayAdapter.createFromResource(this,R.array.HimachalPradesh,android.R.layout.simple_expandable_list_item_1);
                registerbinding.autoCompleteTextViewCity.setAdapter(cityselection_9);
                break;

            case "Jharkhand" :
                ArrayAdapter cityselection_10 = ArrayAdapter.createFromResource(this,R.array.Jharkhand,android.R.layout.simple_expandable_list_item_1);
                registerbinding.autoCompleteTextViewCity.setAdapter(cityselection_10);
                break;

            case "Karnataka" :
                ArrayAdapter cityselection_11 = ArrayAdapter.createFromResource(this,R.array.Karnataka,android.R.layout.simple_expandable_list_item_1);
                registerbinding.autoCompleteTextViewCity.setAdapter(cityselection_11);
                break;

            case "Kerala" :
                ArrayAdapter cityselection_12 = ArrayAdapter.createFromResource(this,R.array.Kerala,android.R.layout.simple_expandable_list_item_1);
                registerbinding.autoCompleteTextViewCity.setAdapter(cityselection_12);
                break;

            case "Madhya Pradesh" :
                ArrayAdapter cityselection_13 = ArrayAdapter.createFromResource(this,R.array.MadhyaPradesh,android.R.layout.simple_expandable_list_item_1);
                registerbinding.autoCompleteTextViewCity.setAdapter(cityselection_13);
                break;

            case "Maharashtra" :
                ArrayAdapter cityselection_14 = ArrayAdapter.createFromResource(this,R.array.Maharashtra,android.R.layout.simple_expandable_list_item_1);
                registerbinding.autoCompleteTextViewCity.setAdapter(cityselection_14);
                break;

            case "Manipur" :
                ArrayAdapter cityselection_15 = ArrayAdapter.createFromResource(this,R.array.Manipur,android.R.layout.simple_expandable_list_item_1);
                registerbinding.autoCompleteTextViewCity.setAdapter(cityselection_15);
                break;

            case "Meghalaya" :
                ArrayAdapter cityselection_16 = ArrayAdapter.createFromResource(this,R.array.Meghalaya,android.R.layout.simple_expandable_list_item_1);
                registerbinding.autoCompleteTextViewCity.setAdapter(cityselection_16);
                break;

            case "Mizoram" :
                ArrayAdapter cityselection_17 = ArrayAdapter.createFromResource(this,R.array.Mizoram,android.R.layout.simple_expandable_list_item_1);
                registerbinding.autoCompleteTextViewCity.setAdapter(cityselection_17);
                break;

            case "Nagaland" :
                ArrayAdapter cityselection_18 = ArrayAdapter.createFromResource(this,R.array.Nagaland,android.R.layout.simple_expandable_list_item_1);
                registerbinding.autoCompleteTextViewCity.setAdapter(cityselection_18);
                break;

            case "Odisha" :
                ArrayAdapter cityselection_19 = ArrayAdapter.createFromResource(this,R.array.Odisha,android.R.layout.simple_expandable_list_item_1);
                registerbinding.autoCompleteTextViewCity.setAdapter(cityselection_19);
                break;

            case "Punjab" :
                ArrayAdapter cityselection_20 = ArrayAdapter.createFromResource(this,R.array.Punjab,android.R.layout.simple_expandable_list_item_1);
                registerbinding.autoCompleteTextViewCity.setAdapter(cityselection_20);
                break;

            case "Rajasthan" :
                ArrayAdapter cityselection_21 = ArrayAdapter.createFromResource(this,R.array.Rajasthan,android.R.layout.simple_expandable_list_item_1);
                registerbinding.autoCompleteTextViewCity.setAdapter(cityselection_21);
                break;

            case "Sikkim" :
                ArrayAdapter cityselection_22 = ArrayAdapter.createFromResource(this,R.array.Sikkim,android.R.layout.simple_expandable_list_item_1);
                registerbinding.autoCompleteTextViewCity.setAdapter(cityselection_22);
                break;

            case "Tamil Nadu" :
                ArrayAdapter cityselection_23 = ArrayAdapter.createFromResource(this,R.array.TamilNadu,android.R.layout.simple_expandable_list_item_1);
                registerbinding.autoCompleteTextViewCity.setAdapter(cityselection_23);
                break;

            case "Telangana" :
                ArrayAdapter cityselection_24 = ArrayAdapter.createFromResource(this,R.array.Telangana,android.R.layout.simple_expandable_list_item_1);
                registerbinding.autoCompleteTextViewCity.setAdapter(cityselection_24);
                break;

            case "Tripura" :
                ArrayAdapter cityselection_25 = ArrayAdapter.createFromResource(this,R.array.Tripura,android.R.layout.simple_expandable_list_item_1);
                registerbinding.autoCompleteTextViewCity.setAdapter(cityselection_25);
                break;

            case "Uttar Pradesh" :
                ArrayAdapter cityselection_26 = ArrayAdapter.createFromResource(this,R.array.UttarPradesh,android.R.layout.simple_expandable_list_item_1);
                registerbinding.autoCompleteTextViewCity.setAdapter(cityselection_26);
                break;

            case "Uttarakhand" :
                ArrayAdapter cityselection_27 = ArrayAdapter.createFromResource(this,R.array.Uttarakhand,android.R.layout.simple_expandable_list_item_1);
                registerbinding.autoCompleteTextViewCity.setAdapter(cityselection_27);
                break;

            case "West Bengal" :
                ArrayAdapter cityselection_28 = ArrayAdapter.createFromResource(this,R.array.WestBengal,android.R.layout.simple_expandable_list_item_1);
                registerbinding.autoCompleteTextViewCity.setAdapter(cityselection_28);
                break;

            default:break;
        }

    }
}