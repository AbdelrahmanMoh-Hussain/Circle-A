package com.example.circlea;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    TextView Name,Email,Address,PhoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Name=findViewById(R.id.NameDisplay);
        Email=findViewById(R.id.EmailDisplay);
        Address=findViewById(R.id.AddressDisplay);
        PhoneNumber=findViewById(R.id.PhoneNumberDisplay);
        UserDB userDB=new UserDB(this);
        Cursor cursor2 = userDB.fetchAllUsers();
        for(int i=0;i<cursor2.getCount();i++){
            if(cursor2.getString(0).equals(User.getEmail2())){
                Name.setText("Name: " + cursor2.getString(2) + " " + cursor2.getString(3));
                Email.setText("Email: " + cursor2.getString(0));
                Address.setText("Address: " + cursor2.getString(5));
                PhoneNumber.setText("Phone Number: " + cursor2.getString(4));
                break;
            }
            cursor2.moveToNext();
        }

    }


}