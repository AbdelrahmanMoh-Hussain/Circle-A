package com.example.circlea;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.ContentView;
import androidx.annotation.Nullable;

public class ProductDBHelper extends SQLiteOpenHelper {
    private static String databaseName = "productDatabase9";
    SQLiteDatabase productDatabase;

    public ProductDBHelper(Context context){
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table product9(id integer primary key, title text not null, price integer not null, type text not null, addition text, size text not null, quantity integer not null, email text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists product9");
        onCreate(db);
    }

    public void addNewProduct(String title, int price, String type, String addition, String size, int quantity, String email)
    {
        ContentValues row = new ContentValues();
        row.put("title", title);
        row.put("price", price);
        row.put("type", type);
        row.put("addition", addition);
        row.put("size", size);
        row.put("quantity", quantity);
        row.put("email", email);
        productDatabase = getWritableDatabase();
        productDatabase.insert("product9", null, row);
        productDatabase.close();
    }

    public Cursor fetchAllProducts()
    {
        productDatabase = getReadableDatabase();
        String[] rowDetails = {"title", "price", "type", "addition", "size", "quantity", "email"};
        Cursor cursor = productDatabase.query("product9", rowDetails, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        productDatabase.close();
        return cursor;
    }

    public void clear()
    {
        productDatabase = getWritableDatabase();
        productDatabase.delete("product9", "email='" +User.getEmail2()+"'",null);
        productDatabase.close();
    }
}
