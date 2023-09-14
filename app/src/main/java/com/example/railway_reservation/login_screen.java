package com.example.railway_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.railway_reservation.databinding.ActivityLoginScreenBinding;

public class login_screen extends AppCompatActivity {

    public static  final String PREFS_NAME = "MyPrefsFile";
    ActivityLoginScreenBinding loginbinding;
    MyDbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DB = new MyDbHelper(this);

        loginbinding = ActivityLoginScreenBinding.inflate(getLayoutInflater());
        View view = loginbinding.getRoot();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(view);

        loginbinding.loginscreenbtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = loginbinding.loginscreenedittextUsername.getEditText().getText().toString();
                String password = loginbinding.loginscreenedittextPassword.getEditText().getText().toString();

                if(TextUtils.isEmpty(username)){
                    Toast.makeText(login_screen.this, "please Enter username", Toast.LENGTH_SHORT).show();
                    loginbinding.loginscreenedittextUsername.requestFocus();
                }
                else if (TextUtils.isEmpty(password)){
                    Toast.makeText(login_screen.this, "please Enter password", Toast.LENGTH_SHORT).show();
                    loginbinding.loginscreenedittextPassword.requestFocus();
                }
                else {
                    boolean checkuserpass = DB.checkUsernamePassword(username,password);
                    if (checkuserpass == true){
                        SharedPreferences sharedPreferences = getSharedPreferences(login_screen.PREFS_NAME,0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("hasLoggedIn",true);
                        editor.commit();
                        Toast.makeText(login_screen.this, "Sign in is successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(login_screen.this,home_screen.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(login_screen.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // register button of login screen if user have no account onclick
        loginbinding.loginscreenbtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(login_screen.this, register_screen.class);
                startActivity(register);
            }
        });

    }
}