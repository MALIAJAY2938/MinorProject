package com.example.railway_reservation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDbHelper extends SQLiteOpenHelper {
    public static final String DBName = "Login.db";
    public MyDbHelper(Context context) {
        super(context, DBName,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE user_info(fullname TEXT,username TEXT primary key,mobileno TEXT unique,dob TEXT,gender TEXT,state TEXT,city TEXT,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("DROP TABLE IF EXISTS user_info");
    }
    public boolean insertData(String fullname,String username,String mobileno,String dob,String gender,String state,String city,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullname",fullname);
        contentValues.put("username",username);
        contentValues.put("mobileno",mobileno);
        contentValues.put("dob",dob);
        contentValues.put("gender",gender);
        contentValues.put("state",state);
        contentValues.put("city",city);
        contentValues.put("password",password);
        long result = MyDB.insert("user_info",null,contentValues);
        if (result==-1)
            return false;
        else
            return  true;
    }

    public boolean checkUser(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM user_info WHERE username=?",new String[] {username});
        if (cursor.getCount()>0){
            return true;
        }
        else
            return false;
    }
    public boolean checkMobileno(String mobileno){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM user_info WHERE mobileno=?",new String[] {mobileno});
        if(cursor.getCount()>0){
            return true;
        }
        else
            return false;
    }

    public boolean checkUsernamePassword(String username,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM user_info WHERE username=? AND password=?",new String[] {username,password});
        if (cursor.getCount()>0){
            return true;
        }
        else
            return false;

    }
}
