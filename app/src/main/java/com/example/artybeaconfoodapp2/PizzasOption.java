package com.example.artybeaconfoodapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.artybeaconfoodapp2.Adapters.Pizzaoptionadapter;
import com.example.artybeaconfoodapp2.Data.Pizzaoptiondata;
import com.example.artybeaconfoodapp2.Data.populardata;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PizzasOption extends AppCompatActivity {
  RecyclerView pizzaoprcv;
  Pizzaoptionadapter pizzaoptionadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizzas_option);
        pizzaoprcv = findViewById(R.id.pizzaoptionrcv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        pizzaoprcv.setLayoutManager(linearLayoutManager);
        pizzaoptionadapter = new Pizzaoptionadapter(pizzaopdata(),getApplicationContext());
        pizzaoprcv.setAdapter(pizzaoptionadapter);
        pizzaoprcv.setLayoutManager(linearLayoutManager);
    }
    public ArrayList<Pizzaoptiondata> pizzaopdata()
    {
        ArrayList<Pizzaoptiondata> pizzaopdata = new ArrayList<>();

//        Pizzaoptiondata pannerpizza = new Pizzaoptiondata();
//        pannerpizza.setPizzaopimg(R.drawable.pannerpiza_adobespark);
//        pannerpizza.setPizzaopname("Panner Pizza");
//        pannerpizza.setPizzaopprice(180);
//        pannerpizza.setSubheading("It is a Panner Pizza and");
//        pannerpizza.setShowmore("it is delicious and its is tasty it is delicious and its is tasty  ");
//        pizzaopdata.add(pannerpizza);
//
//        Pizzaoptiondata cheesepizza = new Pizzaoptiondata();
//        cheesepizza.setPizzaopimg(R.drawable.cheesepiza_adobespark);
//        cheesepizza.setPizzaopname("Cheese Pizza");
//        cheesepizza.setPizzaopprice(210);
//        cheesepizza.setSubheading("It is a cheese Pizza and");
//        cheesepizza.setShowmore("it is delicious and its is tasty it is delicious and its is tasty");
//        pizzaopdata.add(cheesepizza);
//
//        Pizzaoptiondata tomatopizza = new Pizzaoptiondata();
//        tomatopizza.setPizzaopimg(R.drawable.tomatopizza_adobespark);
//        tomatopizza.setPizzaopname("Tomato Pizza");
//        tomatopizza.setPizzaopprice(80);
//        tomatopizza.setSubheading("It is a Tomato Pizza and");
//        tomatopizza.setShowmore("it is delicious and its is tasty it is delicious and its is tasty");
//        pizzaopdata.add(tomatopizza);
//
//        Pizzaoptiondata cornpizza = new Pizzaoptiondata();
//        cornpizza.setPizzaopimg(R.drawable.cornpiza_adobespark);
//        cornpizza.setPizzaopname("Corn Pizza");
//        cornpizza.setPizzaopprice(240);
//        cornpizza.setSubheading("It is a Tomato Pizza and");
//        cornpizza.setShowmore(" it is delicious and its is tasty it is delicious and its is tasty");
//        pizzaopdata.add(cornpizza);
//
        FirebaseFirestore.getInstance().collection("PopularProducts").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    Log.d("task", String.valueOf(task.getResult().getDocuments()));
                    for(DocumentSnapshot documentSnapshot: task.getResult().getDocuments())
                    {

                       Pizzaoptiondata pizzaoptiondata = documentSnapshot.toObject(Pizzaoptiondata.class);
                           if(pizzaoptiondata.getCategory().equals("Pizza"))
                           {
                               pizzaopdata.add(pizzaoptiondata);
                           }

                            pizzaoptionadapter.notifyDataSetChanged();



                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(getApplicationContext(), "error : "+e.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
        return pizzaopdata;
    }

}