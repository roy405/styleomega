package com.example.androidclothstore.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidclothstore.Models.Users;
import com.example.androidclothstore.R;

public class SignUpActivity extends AppCompatActivity {

    EditText firstname,lastname, email, password, dob, shippingAddress, billingAddress;
    Button signup, dbPurge;
    Users user=new Users();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        firstname=(EditText)findViewById(R.id.fname);
        lastname=(EditText)findViewById(R.id.lname);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.pass);
        dob=(EditText)findViewById(R.id.DOB);
        shippingAddress=(EditText)findViewById(R.id.Saddress);
        billingAddress=(EditText)findViewById(R.id.Baddress);

        signup=(Button)findViewById(R.id.CustSignup);
        dbPurge=(Button)findViewById(R.id.bDatabase);

        signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                user= new Users(firstname.getText().toString(),lastname.getText().toString(),email.getText().toString(),password.getText().toString(),dob.getText().toString(),shippingAddress.getText().toString(),billingAddress.getText().toString());
                user.save();
                Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Registration Successful" , Toast.LENGTH_LONG).show();

            }
        });

        dbPurge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Users.deleteAll(Users.class);
            }
        });

    }
}
