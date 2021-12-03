package com.example.artybeaconfoodapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.artybeaconfoodapp2.Adapters.Burgeroptionadapter;
import com.example.artybeaconfoodapp2.Adapters.Shakeoptionadapter;
import com.example.artybeaconfoodapp2.Data.Burgeroptiondata;
import com.example.artybeaconfoodapp2.Data.Pizzaoptiondata;
import com.example.artybeaconfoodapp2.Data.Shakeoptiondata;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Shakesoption extends AppCompatActivity {
    RecyclerView shakercv;
    Shakeoptionadapter shakeoptionadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shakesoption);

        shakercv = findViewById(R.id.sorcv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        shakercv.setLayoutManager(linearLayoutManager);
        shakeoptionadapter = new Shakeoptionadapter(shakeopdata(),getApplicationContext());
        shakercv.setAdapter(shakeoptionadapter);
        shakercv.setLayoutManager(linearLayoutManager);

    }
    public ArrayList<Shakeoptiondata> shakeopdata()
    {
        ArrayList<Shakeoptiondata> shakeopdata = new ArrayList<>();
//
//        Shakeoptiondata oreoshake = new Shakeoptiondata();
//        oreoshake.setShakeopimg(R.drawable.oreoshake_adobespark);
//        oreoshake.setShakeopname("Oreo Shake");
//        oreoshake.setShakeopprice(187);
//        oreoshake.setSubheading("It is a Oreo Shake");
//        oreoshake.setShowmore("it is delicious and its is tasty it is delicious and its is tasty ");
//        shakeopdata.add(oreoshake);
//
//        Shakeoptiondata strawberry = new Shakeoptiondata();
//        strawberry.setShakeopimg(R.drawable.strawberryshake_adobespark);
//        strawberry.setShakeopname("StrawBerry Shake");
//        strawberry.setShakeopprice(150);
//        strawberry.setSubheading("It is a Strawberry Shake");
//        strawberry.setShowmore("it is delicious and its is tasty it is delicious and its is tasty ");
//        shakeopdata.add(strawberry);
//
//        Shakeoptiondata butterscotch  = new Shakeoptiondata();
//        butterscotch.setShakeopimg(R.drawable.butterscotchshake_adobespark);
//        butterscotch.setShakeopname("Butter Scotch Shake");
//        butterscotch.setShakeopprice(137);
//        butterscotch.setSubheading("It is  ButterScotch");
//        butterscotch.setShowmore("it is delicious and its is tasty it is delicious and its is tasty ");
//        shakeopdata.add(butterscotch);
//
//        Shakeoptiondata choclate = new Shakeoptiondata();
//        choclate.setShakeopimg(R.drawable.darkchoclateshakeadobespark);
//        choclate.setShakeopname("Choclate Shake");
//        choclate.setSubheading("It is  Choclate Shake");
//        choclate.setShowmore("it is delicious and its is tasty it is delicious and its is tasty ");
//        choclate.setShakeopprice(187);
//
//        shakeopdata.add(choclate);
        FirebaseFirestore.getInstance().collection("PopularProducts").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    Log.d("task", String.valueOf(task.getResult().getDocuments()));
                    for(DocumentSnapshot documentSnapshot: task.getResult().getDocuments())
                    {

                        Shakeoptiondata shakeoptiondata = documentSnapshot.toObject(Shakeoptiondata.class);
                        if(shakeoptiondata.getCategory().equals("Shake"))
                        {
                            shakeopdata.add(shakeoptiondata);
                        }

                        shakeoptionadapter.notifyDataSetChanged();



                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(getApplicationContext(), "error : "+e.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
        return  shakeopdata;
    }
}