package com.example.railway_reservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.railway_reservation.databinding.ActivityHomeScreenBinding;
import com.example.railway_reservation.homescreen_fragment.home_fragment;
import com.google.android.material.navigation.NavigationView;

public class home_screen extends AppCompatActivity {

    ActivityHomeScreenBinding homebinding;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homebinding = ActivityHomeScreenBinding.inflate(getLayoutInflater());
        View view = homebinding.getRoot();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(view);

        toolbar = findViewById(R.id.toptoolbar);
        setSupportActionBar(toolbar);

        // set navigation drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,homebinding.drawerlayout,toolbar,R.string.opendrawer,R.string.closedrawer);
        homebinding.drawerlayout.addDrawerListener(toggle);
        toggle.syncState();

        loadFragment(new home_fragment());// load home fragment by default

        // navigation drawer item onclick
        homebinding.navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id==R.id.my_profile){
                    Toast.makeText(getApplicationContext(),"my profile",Toast.LENGTH_SHORT).show();
                    homebinding.drawerlayout.close();

                } else if (id==R.id.update_profile) {
                    Toast.makeText(getApplicationContext(),"update profile",Toast.LENGTH_SHORT).show();
                    homebinding.drawerlayout.close();

                } else if (id==R.id.share_menu) {
                    Toast.makeText(getApplicationContext(),"Share",Toast.LENGTH_SHORT).show();
                    homebinding.drawerlayout.close();

                } else if (id==R.id.setting_menu) {
                    Toast.makeText(home_screen.this, "Setting", Toast.LENGTH_SHORT).show();
                    homebinding.drawerlayout.close();


                } else if (id==R.id.about_menu) {
                    Intent intent = new Intent(home_screen.this, homescreen_navigationdrawer_aboutus.class);
                    startActivity(intent);
                    homebinding.drawerlayout.close();

                }else {
                    SharedPreferences sharedPreferences = getSharedPreferences(login_screen.PREFS_NAME,0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("hasLoggedIn",false);
                    editor.commit();
                    Intent logout = new Intent(home_screen.this,login_screen.class);
                    startActivity(logout);
                    finish();
                }
                return true;
            }
        });

//        // bottom navigation on item select
//        homebinding.navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                int bnvid = item.getItemId();
//                if (bnvid==R.id.bottom_home){
//                    loadFragment(new home_fragment());
//
//                } else if (bnvid==R.id.bottom_search) {
//                    loadFragment(new search_fragment());
//
//                }else if (bnvid==R.id.bottom_favorite){
//
//                } else if (bnvid==R.id.bottom_notification) {
//
//                }
//                return true;
//            }
//        });
    }

    // for fragment load to home screen
    private void loadFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.framelayout_maincontent,fragment);
        ft.commit();
    }

    // for drawer close on back pressed
    @Override
    public void onBackPressed() {
        if (homebinding.drawerlayout.isDrawerOpen(GravityCompat.START)){
            homebinding.drawerlayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}