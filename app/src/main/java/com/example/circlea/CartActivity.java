package com.example.circlea;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class CartActivity extends AppCompatActivity {

    ListView lvProducts;
    ArrayAdapter<String> adapter;
    UserDB userDB=new UserDB(this);
    ProductDBHelper productDBHelper;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        lvProducts = (ListView)findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
        lvProducts.setAdapter(adapter);
        TextView priceText = (TextView)findViewById(R.id.priceTxt);
        Button clearCartBtn = (Button)findViewById(R.id.clearCart);
        TextView address= (TextView) findViewById(R.id.locationEditTxt);
        productDBHelper = new ProductDBHelper(getApplicationContext());
        Cursor cursor = productDBHelper.fetchAllProducts();
        Cursor cursor2 = userDB.fetchAllUsers();
       for(int i=0;i<cursor2.getCount();i++){
           if(cursor2.getString(0).equals(User.getEmail2())){
               address.setText(cursor2.getString(5));
               break;
           }
           cursor2.moveToNext();
       }

        int totalPrice = 0;
        while(!cursor.isAfterLast()){
            if(cursor.getString(6).equals(User.getEmail2())){
                adapter.add("Title: " + cursor.getString(0) + "\tPrice: " + cursor.getString(1) + "\t Quantity: " + cursor.getString(5) + "\nType: " + cursor.getString(2) + "\tAdditions: " + cursor.getString(3) + "\tSize: " + cursor.getString(4));
                totalPrice += (Integer.parseInt(cursor.getString(1)) * Integer.parseInt(cursor.getString(5)));
            }
            cursor.moveToNext();
        }
        priceText.setText(Integer.toString(totalPrice));

        clearCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productDBHelper.clear();
                lvProducts.setAdapter(null);
                priceText.setText("0");
            }
        });


    }
}