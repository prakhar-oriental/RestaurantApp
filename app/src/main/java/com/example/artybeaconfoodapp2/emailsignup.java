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
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class emailsignup extends AppCompatActivity {
    TextInputLayout emaillayout,passwordlayout;
    TextInputEditText emailsignup,passwordsignup;
Button registerbtn;
TextView alreadyauser;
CardView signupcard;
ProgressBar signuppgbar;
    String email;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emailsignup);
        emaillayout = (TextInputLayout)findViewById(R.id.femaillayout);
        passwordlayout = (TextInputLayout)findViewById(R.id.passwordlayout);
        emailsignup  = (TextInputEditText) findViewById(R.id.signupemail);
        passwordsignup = (TextInputEditText)findViewById(R.id.signuppassword);
        registerbtn = findViewById(R.id.registerbutton);
        alreadyauser = findViewById(R.id.alreadyauser);
        signupcard   = findViewById(R.id.fsignincard);
        signuppgbar  = findViewById(R.id.fsigninprogressbar);
         email = emailsignup.getText().toString().trim();
        password = passwordsignup.getText().toString().trim();
        signupcard.setVisibility(View.INVISIBLE);
        signuppgbar.setVisibility(View.INVISIBLE);
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(emailsignup.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(emailsignup.this, "Please fill email", Toast.LENGTH_SHORT).show();
                }else if(passwordsignup.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(emailsignup.this, "Please fill Password", Toast.LENGTH_SHORT).show();
                }else if(passwordsignup.getText().toString().trim().length()<6)
                {
                    Toast.makeText(emailsignup.this, "Password length alteast 6 character", Toast.LENGTH_SHORT).show();
                }else {
                    signupcard.setVisibility(View.VISIBLE);
                    signuppgbar.setVisibility(View.VISIBLE);
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailsignup.getText().toString().trim(), passwordsignup.getText().toString().trim())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        signupcard.setVisibility(View.INVISIBLE);
                                        signuppgbar.setVisibility(View.INVISIBLE);
                                        Toast.makeText(emailsignup.this, "Account create sucessfully", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(emailsignup.this, setprofile.class));
                                        finish();
                                    } else {
                                        signupcard.setVisibility(View.INVISIBLE);
                                        signuppgbar.setVisibility(View.INVISIBLE);
                                        Toast.makeText(emailsignup.this, "error:" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });
        alreadyauser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(com.example.artybeaconfoodapp2.emailsignup.this,com.example.artybeaconfoodapp2.emailsignin.class));
                finish();
            }
        });
    }
}