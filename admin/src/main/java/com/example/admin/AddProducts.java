package com.example.admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.UUID;

public class AddProducts extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
ImageView dishimg;
Spinner spinner;
TextInputEditText dishname,dishprice,dishuniqueid,onelinedes,multilinedis;
Button savedish;
String category;

    private static int PICK_IMAGE=123;
    Uri imagepath;
   String accesstoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_products);
        onelinedes = findViewById(R.id.onelinedis);
        multilinedis = findViewById(R.id.multilinedis);
        dishuniqueid =  findViewById(R.id.dishuniqueid);
        dishimg = findViewById(R.id.dishimg);
        dishname = findViewById(R.id.dishname);
        dishprice = findViewById(R.id.dishprice);
        savedish = findViewById(R.id.savedish);
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



        dishimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickimg();

            }
        });
        savedish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dishname.getText().toString().isEmpty() || dishprice.getText().toString().isEmpty() || dishuniqueid.getText().toString().isEmpty() || onelinedes.getText().toString().isEmpty()|| multilinedis.getText().toString().isEmpty())
                {
                    Toast.makeText(AddProducts.this, "Please filled All feilds", Toast.LENGTH_SHORT).show();
                }else if(dishuniqueid.getText().toString().length()!=5)
                {
                    Toast.makeText(AddProducts.this, "Length of unique id should be equal to 5", Toast.LENGTH_SHORT).show();
//                    HashMap<String,Object> popularproduct = new HashMap<>();
//                    popularproduct.put("pname",dishname.getText().toString());
//                    popularproduct.put("pprice",dishprice.getText().toString());
//                    popularproduct.put("pimg",imagepath.toString());
//                    FirebaseFirestore.getInstance().collection("PopularProducts").document().set(popularproduct).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull  Task<Void> task) {
//                            Toast.makeText(AddProducts.this, "Product Add", Toast.LENGTH_SHORT).show();
//                        }
//                    });

                }else
                {
                    FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
                    StorageReference storageReference = firebaseStorage.getReference().child("Popularimg").child(dishuniqueid.getText().toString());
                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            // Got the download URL for 'users/me/profile.png'
                            if(uri!=null)
                            {
                                Toast.makeText(AddProducts.this, "Please change product id", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle any errors
                            storageReference.putFile(imagepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AddProducts.this, "Upload", Toast.LENGTH_SHORT).show();
                Log.d("upload","upload img");

                                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            accesstoken = uri.toString();

                                            HashMap<String,Object> popularproduct = new HashMap<>();
                    popularproduct.put("pname",dishname.getText().toString());
                    popularproduct.put("pprice",Integer.parseInt(dishprice.getText().toString()));
                    popularproduct.put("pimg",accesstoken);
                    popularproduct.put("puniqueid",dishuniqueid.getText().toString());
                    popularproduct.put("subheading",onelinedes.getText().toString());
                    popularproduct.put("multilinedes",multilinedis.getText().toString());
                    popularproduct.put("category",category);
                    FirebaseFirestore.getInstance().collection("PopularProducts").document().set(popularproduct).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull  Task<Void> task) {
                            Toast.makeText(AddProducts.this, "Product Add", Toast.LENGTH_SHORT).show();
                        }
                    });

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception exception) {
                                            // Handle any errors
                                        }
                                    });

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull  Exception e) {
                                    Toast.makeText(AddProducts.this, "error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });

                }
            }
        });
    }

    private void uploadimg() {
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference().child("Popularimg").child(dishuniqueid.getText().toString());

        storageReference.putFile(imagepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                Toast.makeText(AddProducts.this, "Upload", Toast.LENGTH_SHORT).show();
//                Log.d("upload","upload img");


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull  Exception e) {
                Toast.makeText(AddProducts.this, "error"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void pickimg() {
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent,PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==PICK_IMAGE && resultCode==RESULT_OK)
        {
            imagepath=data.getData();
            Log.d("uri",String.valueOf(imagepath));
            dishimg.setImageURI(imagepath);
            uploadimg();
            downloadimg();
        }




        super.onActivityResult(requestCode, resultCode, data);
    }

    private void downloadimg() {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
          category = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, "cat"+category, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}