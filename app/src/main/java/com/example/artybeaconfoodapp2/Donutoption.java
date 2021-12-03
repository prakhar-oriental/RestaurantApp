package com.example.artybeaconfoodapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.artybeaconfoodapp2.Adapters.Donutoptionadapter;
import com.example.artybeaconfoodapp2.Adapters.Shakeoptionadapter;
import com.example.artybeaconfoodapp2.Data.Donutoptiondata;
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

public class Donutoption extends AppCompatActivity {
    RecyclerView donutrcv;
    Donutoptionadapter donutoptionadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donutoption);
        donutrcv = findViewById(R.id.donutoptionrcv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        donutrcv.setLayoutManager(linearLayoutManager);
        donutoptionadapter = new Donutoptionadapter(donutopdata(),getApplicationContext());
        donutrcv.setAdapter(donutoptionadapter);

        donutrcv.setLayoutManager(linearLayoutManager);
    }
    public ArrayList<Donutoptiondata> donutopdata() {
        ArrayList<Donutoptiondata> donutopdata = new ArrayList<>();
//
//        Donutoptiondata  choclate = new Donutoptiondata();
//        choclate.setDonutopimg(R.drawable.choclatedonut_adobespark);
//        choclate.setDonutopname("Choclate Donut");
//        choclate.setDonutopprice(89);
//        choclate.setSubheading("It is a Oreo Shake");
//        choclate.setShowmore("it is delicious and its is tasty it is delicious and its is tasty ");
//        donutopdata.add(choclate);
//
//        Donutoptiondata  strawberry = new Donutoptiondata();
//        strawberry.setDonutopimg(R.drawable.strawberrydonut_adobespark);
//        strawberry.setDonutopname("Strawberry Donut");
//        strawberry.setDonutopprice(79);
//        strawberry.setSubheading("It is a Oreo Shake");
//        strawberry.setShowmore("it is delicious and its is tasty it is delicious and its is tasty ");
//        donutopdata.add(strawberry);
//
//        Donutoptiondata  jelly = new Donutoptiondata();
//        jelly.setDonutopimg(R.drawable.jellydonut_adobespark);
//        jelly.setDonutopname("Jelly Donut");
//        jelly.setDonutopprice(99);
//        jelly.setSubheading("It is a Oreo Shake");
//        jelly.setShowmore("it is delicious and its is tasty it is delicious and its is tasty ");
//        donutopdata.add(jelly);
//
//        Donutoptiondata  cream = new Donutoptiondata();
//        cream.setDonutopimg(R.drawable.creamfilleddonut_adobespark);
//        cream.setDonutopname("Cream Donut");
//        cream.setDonutopprice(79);
//        cream.setSubheading("It is a Oreo Shake");
//        cream.setShowmore("it is delicious and its is tasty it is delicious and its is tasty ");
//        donutopdata.add(cream);

        FirebaseFirestore.getInstance().collection("PopularProducts").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    Log.d("task", String.valueOf(task.getResult().getDocuments()));
                    for(DocumentSnapshot documentSnapshot: task.getResult().getDocuments())
                    {

                        Donutoptiondata donutoptiondata = documentSnapshot.toObject(Donutoptiondata.class);
                        if(donutoptiondata.getCategory().equals("Donut"))
                        {
                            donutopdata.add(donutoptiondata);
                        }

                        donutoptionadapter.notifyDataSetChanged();



                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(getApplicationContext(), "error : "+e.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
        return donutopdata;
    }
}