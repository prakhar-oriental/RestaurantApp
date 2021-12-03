package com.example.artybeaconfoodapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class forgetpassword extends AppCompatActivity {
    TextInputEditText femail;
    Button forgetpasswordbtn;
    TextView backtologin;
    CardView signincard;
    ProgressBar signinpgbar;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        femail = findViewById(R.id.fsigninemail);
        forgetpasswordbtn = findViewById(R.id.forgetpassbtn);
        backtologin = findViewById(R.id.backtologin);
        signincard = findViewById(R.id.fsignincard);
        signinpgbar = findViewById(R.id.fsigninprogressbar);
        signincard.setVisibility(View.INVISIBLE);
        signinpgbar.setVisibility(View.VISIBLE);

        forgetpasswordbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });

    }

    public void validateData() {
            email = femail.getText().toString();
            if(email.isEmpty())
            {
                femail.setError("Required");
            }else
            {
                forgetpass();
            }
    }

    private void forgetpass() {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(forgetpassword.this, "Please check your email", Toast.LENGTH_SHORT).show();
                        }else
                        {
                           // Toast.makeText(forgetpassword.this, ""+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(forgetpassword.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}