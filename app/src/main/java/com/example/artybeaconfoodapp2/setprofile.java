package com.example.artybeaconfoodapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class setprofile extends AppCompatActivity {

TextInputEditText username,useremail,useraddress,userphone;
FirebaseStorage userfirebaseStorage;
StorageReference userstorageReference;
FirebaseAuth ufirebaseAuth;
Button nextbtn;
Uri imagepath;
private static int PICK_IMAGE=123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setprofile);
        userphone = (TextInputEditText)findViewById(R.id.phoneno);
        username = (TextInputEditText)findViewById(R.id.editusername);
        useremail = (TextInputEditText)findViewById(R.id.useremailid);
        useraddress = (TextInputEditText)findViewById(R.id.useraddress);
        userfirebaseStorage = FirebaseStorage.getInstance();
        userstorageReference = userfirebaseStorage.getReference();
        ufirebaseAuth  = FirebaseAuth.getInstance();
        nextbtn = findViewById(R.id.nextbtn);

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(username.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(setprofile.this, "Please type Username", Toast.LENGTH_SHORT).show();
                }else if(useremail.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(setprofile.this, "Please type Email Id", Toast.LENGTH_SHORT).show();
                }else if(useraddress.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(setprofile.this, "Please type Your Address", Toast.LENGTH_SHORT).show();
                }else if(userphone.getText().toString().isEmpty())
                 {
                     Toast.makeText(setprofile.this, "Please type Your Phone Number", Toast.LENGTH_SHORT).show();
                 }else if(userphone.getText().toString().length()!=10)
                 {
                     Toast.makeText(setprofile.this, "Phone Number length should be equal to 10", Toast.LENGTH_SHORT).show();
                 }else
                {

                    Map<String,String> userprofile = new HashMap<>();
                    userprofile.put("username",username.getText().toString());
                    userprofile.put("useremail",useremail.getText().toString());
                    userprofile.put("useraddress",useraddress.getText().toString());
                    userprofile.put("userphone",userphone.getText().toString());
                    FirebaseFirestore.getInstance().collection("userprofile").document(ufirebaseAuth.getUid()).set(userprofile).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull  Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(setprofile.this, "go", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(setprofile.this,home.class));
                                finish();
                            }else
                            {
                                Toast.makeText(setprofile.this, "error:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

    }


}