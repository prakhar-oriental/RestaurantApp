package com.example.artybeaconfoodapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class firstscreen extends AppCompatActivity {
       Button phoneauth,emailauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstscreen);
        phoneauth = findViewById(R.id.phoneauth);
        emailauth = findViewById(R.id.emailidauth);
        phoneauth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(firstscreen.this,signup.class));


            }
        });
        emailauth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(firstscreen.this,emailsignup.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
        {
            startActivity(new Intent(firstscreen.this,home.class));
            finish();
        }
    }
}