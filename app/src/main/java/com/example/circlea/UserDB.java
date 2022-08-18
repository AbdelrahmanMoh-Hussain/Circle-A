package com.example.circlea;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserDB extends SQLiteOpenHelper {
    private final static String DBName="user.db";
    public UserDB( Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(email TEXT primary key,password TEXT,firstname TEXT,lastname TEXT,phonenumber TEXT,address TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists users");
    }
    public boolean insert(String firstName, String lastName, String email, String password,String phoneNumber,String address){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("firstname",firstName);
        values.put("email",email);
        values.put("password",password);
        values.put("lastname",lastName);
        values.put("phonenumber",phoneNumber);
        values.put("address",address);
        long result=db.insert("users",null,values);
        return result != -1;
    }
    public boolean checkEmail(String email){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor= db.rawQuery("select * from users where email=?", new String[]{email});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean checkEmailAndPassword(String email,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor= db.rawQuery("select * from users where email=? and password=?", new String[]{email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Cursor fetchAllUsers()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String[] rowDetails = {"email", "password", "firstname", "lastname", "phonenumber", "address"};
        Cursor cursor = db.query("users", rowDetails, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

}
