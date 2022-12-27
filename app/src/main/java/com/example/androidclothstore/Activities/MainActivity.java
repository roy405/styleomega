package com.example.androidclothstore.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidclothstore.Models.Users;
import com.example.androidclothstore.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText email,password;
    TextView signUP;
    Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signUP=(TextView)findViewById(R.id.signup);
        Login=(Button)findViewById(R.id.login);

        email=(EditText)findViewById(R.id.user) ;
        password=(EditText)findViewById(R.id.pass) ;

        signUP.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

        Login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                validate(email.getText().toString(), password.getText().toString());
            }
        });
    }

    public void validate(String userEmail, String userPassword) {
        List<Users> users = Users.listAll(Users.class);
        for(Users user : users){
            if((userEmail.equals(user.getEmail())) && (userPassword.equals(user.getPassword())))
            {
                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();

                SharedPreferences shared = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shared.edit();
                editor.putString("email",userEmail);
                editor.putString("pass",userPassword);
                editor.apply();

                Intent intent = new Intent(MainActivity.this,NavigationDrawerActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(getApplicationContext(),"Incorrect username/password" , Toast.LENGTH_LONG).show();
            }
        }

    }
}