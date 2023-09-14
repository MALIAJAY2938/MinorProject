package com.example.railway_reservation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {
    public static final String DBName = "Login.db";
    public MyDbHelper(Context context) {

        super(context, DBName,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE user_info(fullname TEXT,username TEXT primary key,mobileno TEXT unique,dob TEXT,gender TEXT,state TEXT,city TEXT,password TEXT)");
        MyDB.execSQL("CREATE TABLE  train_info(train_no TEXT primary key,train_name TEXT,train_from TEXT,train_to TEXT,time_duration TEXT,arriving_time TEXT,destination_time TEXT)");
        MyDB.execSQL("INSERT INTO train_info VALUES('12216','DEE GARIBRATH','Surat','Jaipur','14h:44m','15:16','06:00')");
        MyDB.execSQL("INSERT INTO train_info VALUES('12955','MMCT JAIPUR SF','Surat','Jaipur','13h:25m','22:16','12:00')");
        MyDB.execSQL("INSERT INTO train_info VALUES('22737','SC HSR SF EXP','Secunderabad Jn','Bikaner Jn','37h:15m','23:45','12:50')");
        MyDB.execSQL("INSERT INTO train_info VALUES('12489','DDR BKN SF EXP','Mumbai','Bikaner Jn','21h:30m','15:00','12:30')");
        MyDB.execSQL("INSERT INTO train_info VALUES('14708','DDR BKN EXP','Mumbai','Bikaner Jn','24h:10m','12:35','12:45')");
        MyDB.execSQL("INSERT INTO train_info VALUES('12009','ADI SHATABDI EXP','Surat','Ahmedabad Jn','03h:19m','09:18','12:40')");
        MyDB.execSQL("INSERT INTO train_info VALUES('82901','IRCTC TEJAS EXP','Surat','Ahmedabad Jn','03h:15m','19:05','22:20')");
        MyDB.execSQL("INSERT INTO train_info VALUES('14707','BKN DDR EXP','Bikaner Jn','Mumbai','24h:10m','07:05','07:15')");
        MyDB.execSQL("INSERT INTO train_info VALUES('12490','BKN DDR SF EXP','Bikaner Jn','Mumbai','21h:30m','03:00','11:00')");
        MyDB.execSQL("INSERT INTO train_info VALUES('12218','KERLA S KRANTI','Delhi','Mumbai','18h:25m','13:10','07:35')");
        MyDB.execSQL("INSERT INTO train_info VALUES('12472','SWARAJ EXPRESS','Delhi','Mumbai','19h:21m','20:49','16:10')");
        MyDB.execSQL("INSERT INTO train_info VALUES('12473','SWARAJ EXPRESS','Mumbai','Delhi','19h:21m','15:00','10:00')");
        MyDB.execSQL("INSERT INTO train_info VALUES('22717','RJT SC SUP EXP','Rajkot Jn','Secunderabad Jn','26h:00m','05:30','07:30')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("DROP TABLE IF EXISTS user_info");
        MyDB.execSQL("DROP TABLE IF EXISTS train_info");
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

    public Cursor getDataTrainList(String from, String to) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM train_info WHERE train_from=? AND train_to=?",new String[] {from,to});

        return cursor;
    }
}
