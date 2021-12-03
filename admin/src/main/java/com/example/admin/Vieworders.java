package com.example.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.AdminAdapter.vieworderadapter;
import com.example.admin.AdminAdapter.vieworderitemadapter;
import com.example.admin.aAdminData.Showincartdata;
import com.example.admin.aAdminData.vieworderdata;
import com.example.admin.aAdminData.vieworderitemdata;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Vieworders extends AppCompatActivity {
RecyclerView recyclerView;

vieworderadapter vieworderadapter;
vieworderitemadapter vieworderitemadapter;
ArrayList<vieworderdata> vieworderdataArrayList;
ArrayList<Showincartdata> vieworderitemdataArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vieworders);
        recyclerView = findViewById(R.id.vieworderrcv);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        vieworderdataArrayList = new ArrayList<>();
        vieworderadapter = new vieworderadapter(vieworderdataArrayList,vieworderitemdataArrayList,getApplicationContext());
        recyclerView.setAdapter(vieworderadapter);








        FirebaseFirestore.getInstance().collection("OrdersData").document("ODD").collection("order").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull  Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments())
                    {
                        String documentid = documentSnapshot.getId();
                        vieworderdata vieworderdata = documentSnapshot.toObject(vieworderdata.class);
                        vieworderdata.setDocumentid(documentid);

                         vieworderitemdata vieworderitemdata = documentSnapshot.toObject(com.example.admin.aAdminData.vieworderitemdata.class);


                        vieworderdataArrayList.add(vieworderdata);

                        vieworderadapter.notifyDataSetChanged();



                        Log.d("gth", String.valueOf(vieworderitemdataArrayList));
//                        vieworderitemadapter.notifyDataSetChanged();
                        Log.d("rtr", String.valueOf(vieworderdata));

                    }
                }else
                {
                    Log.d("errorp",task.getException().getMessage());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull  Exception e) {
                Log.d("errorq",e.getMessage());
                Toast.makeText(Vieworders.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}