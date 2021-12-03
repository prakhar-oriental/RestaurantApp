package com.example.artybeaconfoodapp2;
//intent.putExtra("pname",popData.get(position).getPname());
//        intent.putExtra("pprice",String.valueOf(popData.get(position).getPprice()));
//        intent.putExtra("pimage",popData.get(position).getPimg());
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Addtocart extends AppCompatActivity {
TextView name,price,totalprice,quantity;
ImageView imageView;
int count = 1;
Button addtocart,back,addbutton,subtractbtn;
int baseprice;
int finalprice;
String dishname,dishquantity,dishprice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtocart);
        name = findViewById(R.id.pname);
        price = findViewById(R.id.price);
        totalprice = findViewById(R.id.ptotalprice);
        quantity = findViewById(R.id.pqantitiy);
        imageView = findViewById(R.id.pizzaopimg);
        addtocart = findViewById(R.id.patcbtn);
        //back = findViewById(R.id.pbackbtn);
        addbutton = findViewById(R.id.addbutton);
        subtractbtn = findViewById(R.id.subtractbutton);
        baseprice = getIntent().getIntExtra("pprice",0);
        finalprice = baseprice*count;
       // imageView.setImageResource(getIntent().getIntExtra("pimage",0));
        Glide.with(this).load(getIntent().getStringExtra("pimage")).into(imageView);


        price.setText(String.valueOf("₹"+getIntent().getIntExtra("pprice",0)));
        name.setText(getIntent().getStringExtra("pname"));
        totalprice.setText(String.valueOf("₹ "+  finalprice));
        quantity.setText(String.valueOf(count));
        dishname = getIntent().getStringExtra("pname");

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count<5)
                {
                    ++count;
                    quantity.setText(String.valueOf(count));
                    totalprice.setText(String.valueOf("₹  "+baseprice*count));
                }else 
                {
                    Toast.makeText(Addtocart.this, "You can only add at max 5 items", Toast.LENGTH_LONG).show();
                }
            }
        });
        subtractbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>1)
                {
                    count--;
                    quantity.setText(String.valueOf(count));
                    totalprice.setText(String.valueOf("₹  "+baseprice*count));
                }
            }
        });
         addtocart.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 HashMap<String,String> cartdata = new HashMap<>();
                   cartdata.put("dish",dishname);
                   cartdata.put("dishquantity", String.valueOf(count));
                   cartdata.put("dishprice", String.valueOf(baseprice*count));
                 FirebaseFirestore.getInstance().collection("Cartdata").document(FirebaseAuth.getInstance().getUid()).collection("cart")
                         .add(cartdata).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                     @Override
                     public void onComplete(@NonNull  Task<DocumentReference> task) {
                         if(task.isSuccessful()) {
                             Toast.makeText(Addtocart.this, "item added", Toast.LENGTH_LONG).show();
                         }else
                         {
                             Toast.makeText(Addtocart.this, "error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                         }
                     }

                 });


             }
         });

    }
}