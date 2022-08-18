package com.example.circlea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void register_method(View v) {
        TextView firstName,lastName,password,phoneNumber,address,email;
        firstName=findViewById(R.id.FirstName);
        lastName=findViewById(R.id.LastName);
        password=findViewById(R.id.regpass);
        email=findViewById(R.id.regemail);
        phoneNumber=findViewById(R.id.PhoneNumber);
        address=findViewById(R.id.Adress);
        if(firstName.getText().toString().isEmpty()||lastName.getText().toString().isEmpty()||password.getText().toString().isEmpty()||
                email.getText().toString().isEmpty()||address.getText().toString().isEmpty()||phoneNumber.getText().toString().isEmpty())
            Toast.makeText(this,"All Fields Required ",Toast.LENGTH_LONG).show();
        else{
            UserDB db=new UserDB(this);
            if(db.checkEmail(email.getText().toString())){
                Toast.makeText(this,"user already exists ",Toast.LENGTH_LONG).show();
            }
            else{
                db.insert(firstName.getText().toString(),lastName.getText().toString(),email.getText().toString(),password.getText().toString(),
                        phoneNumber.getText().toString(),address.getText().toString());
                Toast.makeText(this,"Account was Created Successfully ",Toast.LENGTH_LONG).show();
                Intent i=new Intent(this,LoginActivity.class);
                startActivity(i);

            }
        }

    }
}