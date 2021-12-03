package com.example.artybeaconfoodapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class emailsignin extends AppCompatActivity {
    TextInputEditText emailsignin,passwordsignin;
    Button loginbtn;
    TextView newuser;
    CardView signincard;
    ProgressBar signinpgbar;
    TextView forgetpasswordtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emailsignin);
        forgetpasswordtext = findViewById(R.id.forgetpasswordtext);
        emailsignin  =(TextInputEditText) findViewById(R.id.fsigninemail);
        passwordsignin =(TextInputEditText) findViewById(R.id.signinpassword);
        loginbtn  = findViewById(R.id.forgetpassbtn);
        newuser = findViewById(R.id.newuser);
        signincard  = findViewById(R.id.fsignincard);
        signinpgbar = findViewById(R.id.fsigninprogressbar);
        String email2 = emailsignin.getText().toString().trim();
        String password2 = passwordsignin.getText().toString().trim();
        signincard.setVisibility(View.INVISIBLE);
        signinpgbar.setVisibility(View.INVISIBLE);
        forgetpasswordtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(com.example.artybeaconfoodapp2.emailsignin.this,forgetpassword.class));
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailsignin.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(emailsignin.this, "Please fill email", Toast.LENGTH_SHORT).show();
                }else if(passwordsignin.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(emailsignin.this, "Please fill Password", Toast.LENGTH_SHORT).show();
                }else if(passwordsignin.getText().toString().trim().length()<6)
                {
                    Toast.makeText(emailsignin.this, "Password length alteast 6 character", Toast.LENGTH_SHORT).show();
                }else {
                    signincard.setVisibility(View.VISIBLE);
                    signinpgbar.setVisibility(View.VISIBLE);
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(emailsignin.getText().toString().trim(),passwordsignin.getText().toString().trim())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull  Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                        signincard.setVisibility(View.INVISIBLE);
                                        signinpgbar.setVisibility(View.INVISIBLE);
                                        Toast.makeText(emailsignin.this, "Sign In Sucess", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(com.example.artybeaconfoodapp2.emailsignin.this,home.class));
                                        finish();
                                    }else {
                                        signincard.setVisibility(View.INVISIBLE);
                                        signinpgbar.setVisibility(View.INVISIBLE);
                                        Toast.makeText(emailsignin.this, "error:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
      newuser.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(com.example.artybeaconfoodapp2.emailsignin.this,emailsignup.class));
              finish();
          }
      });

    }
}