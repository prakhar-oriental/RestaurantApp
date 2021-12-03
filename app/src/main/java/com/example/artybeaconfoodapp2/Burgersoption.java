package com.example.artybeaconfoodapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.artybeaconfoodapp2.Adapters.Burgeroptionadapter;
import com.example.artybeaconfoodapp2.Adapters.Pizzaoptionadapter;
import com.example.artybeaconfoodapp2.Data.Burgeroptiondata;
import com.example.artybeaconfoodapp2.Data.Pizzaoptiondata;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Burgersoption extends AppCompatActivity {
    RecyclerView burgerrcv;
    Burgeroptionadapter burgeroptionadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burgersoption);
        burgerrcv = findViewById(R.id.burgeroptionrcv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        burgerrcv.setLayoutManager(linearLayoutManager);
        burgeroptionadapter = new Burgeroptionadapter(burgeropdata(),getApplicationContext());
        burgerrcv.setAdapter(burgeroptionadapter);
        burgerrcv.setLayoutManager(linearLayoutManager);
    }
    public ArrayList<Burgeroptiondata> burgeropdata()
    {
        ArrayList<Burgeroptiondata> burgeropdata = new ArrayList<>();

//        Burgeroptiondata vegburger = new Burgeroptiondata();
//        vegburger.setBurgeropimg(R.drawable.vegburger_adobespark);
//        vegburger.setBurgeropname("Veg Burger");
//        vegburger.setBurgeropprice(80);
//        vegburger.setSubheading("It is a Veg Burger");
//        vegburger.setShowmore("it is delicious and its is tasty it is delicious and its is tasty  ");
//        burgeropdata.add(vegburger);
//
//        Burgeroptiondata nonvegburger = new Burgeroptiondata();
//        nonvegburger.setBurgeropimg(R.drawable.nonvegchickenburger_adobespark);
//        nonvegburger.setBurgeropname("Chicken Burger");
//        nonvegburger.setBurgeropprice(120);
//        nonvegburger.setSubheading("It is a Non-Veg Burger");
//        nonvegburger.setShowmore("it is delicious and its is tasty it is delicious and its is tasty  ");
//        burgeropdata.add( nonvegburger);
//
//        Burgeroptiondata cheeseburger = new Burgeroptiondata();
//        cheeseburger.setBurgeropimg(R.drawable.cheeseburger_adobespark);
//        cheeseburger.setBurgeropname("Cheese Burger");
//        cheeseburger.setBurgeropprice(100);
//        cheeseburger.setSubheading("It is a Cheese Burger");
//        cheeseburger.setShowmore("it is delicious and its is tasty it is delicious and its is tasty  ");
//        burgeropdata.add(cheeseburger);
//
//        Burgeroptiondata alootikkiburger = new Burgeroptiondata();
//        alootikkiburger.setBurgeropimg(R.drawable.alootikkiburger_adobespark);
//        alootikkiburger.setBurgeropname("Alootikki Burger");
//        alootikkiburger.setBurgeropprice(70);
//        alootikkiburger.setSubheading("It is a Aloo tikki Burger");
//        alootikkiburger.setShowmore("it is delicious and its is tasty it is delicious and its is tasty  ");
//        burgeropdata.add(alootikkiburger);

        FirebaseFirestore.getInstance().collection("PopularProducts").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    Log.d("task", String.valueOf(task.getResult().getDocuments()));
                    for(DocumentSnapshot documentSnapshot: task.getResult().getDocuments())
                    {

                       Burgeroptiondata burgeroptiondata = documentSnapshot.toObject(Burgeroptiondata.class);

                        if(burgeroptiondata.getCategory().equals("Burger"))
                        {
                            burgeropdata.add(burgeroptiondata);
                        }

                        burgeroptionadapter.notifyDataSetChanged();



                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(getApplicationContext(), "error : "+e.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });

        return  burgeropdata;
    }
}