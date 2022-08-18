package com.example.circlea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View v) {
        TextView textEmail=findViewById(R.id.email);
        TextView textPassword=findViewById(R.id.password);
        String email=textEmail.getText().toString();
        String password =textPassword.getText().toString();
        UserDB db =new UserDB(this);
        if(email.isEmpty()||password.isEmpty()){
            Toast.makeText(this,"All Fields Required ",Toast.LENGTH_LONG).show();
        }
       else {
            if (db.checkEmailAndPassword(email, password)) {
                User.setEmail(email);
                Intent i = new Intent(this, HomeActivity.class);
                Toast.makeText(this, "login Successful", Toast.LENGTH_LONG).show();
                startActivity(i);
            } else {
                Toast.makeText(this, "wrong email or password", Toast.LENGTH_LONG).show();
            }
        }

        }

    public void goTORegister(View v) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}