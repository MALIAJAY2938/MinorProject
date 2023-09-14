package com.example.railway_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.railway_reservation.databinding.ActivityOnboardingScreenBinding;
import com.example.railway_reservation.onboarding_fragment.onboarding_adapter;

public class onboarding_screen extends AppCompatActivity {

    ActivityOnboardingScreenBinding onboardingbinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_onboarding_screen);
        onboardingbinding = ActivityOnboardingScreenBinding.inflate(getLayoutInflater());
        View view = onboardingbinding.getRoot();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(view);

        // for only one time execution
        SharedPreferences preferences = getSharedPreferences("PREFERENCES",MODE_PRIVATE);
        String fisrttime = preferences.getString("FIRSTTIMEINSTALL","");

        if(fisrttime.equals("yes")){
            Intent login = new Intent(onboarding_screen.this, login_screen.class);
            startActivity(login);
            finish();
        }else {
            onboarding_adapter adapter = new onboarding_adapter(getSupportFragmentManager());
            onboardingbinding.viewpager.setAdapter(adapter);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("FIRSTTIMEINSTALL", "yes");
            editor.apply();
        }
    }
}