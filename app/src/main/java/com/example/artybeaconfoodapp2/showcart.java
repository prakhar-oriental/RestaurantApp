package com.example.artybeaconfoodapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.artybeaconfoodapp2.Adapters.showcartAdapter;
import com.example.artybeaconfoodapp2.Data.Showincartdata;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class showcart extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<Showincartdata> showincartdataArrayList;
showcartAdapter showcartAdapter;
TextView finalprice;
ImageView refreshcart;
ImageView emptycart;
ImageView returnnew;
 //int placeorderprice = 0;
  //  int sum = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_showcart);
        returnnew = findViewById(R.id.returnnew);
        emptycart = findViewById(R.id.emptycart);
        recyclerView = findViewById(R.id.showcartrcv);
        finalprice = findViewById(R.id.placeorderprice);
        refreshcart =  findViewById(R.id.refreshcart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        showincartdataArrayList = new ArrayList<>();
        showcartAdapter = new showcartAdapter(showincartdataArrayList,getApplicationContext());
        recyclerView.setAdapter(showcartAdapter);
         returnnew.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                // startActivity(new Intent(showcart.this,homee.class));
             }
         });
        refreshcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(showcart.this,showcart.class));
                finish();
            }
        });
        FirebaseFirestore.getInstance().collection("Cartdata").document(FirebaseAuth.getInstance().getUid()).collection("cart")
        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull  Task<QuerySnapshot> task) {
                 if(task.isSuccessful())
                 {
                     for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments())
                     {

                         String documentid = documentSnapshot.getId();
                         Showincartdata showincartdata = documentSnapshot.toObject(Showincartdata.class);
                         showincartdata.setDocumentid(documentid);
                         //sum  = sum+Integer.parseInt(showincartdata.getDishprice());
                         showincartdataArrayList.add(showincartdata);
                       //  finalprice.setText(String.valueOf(sum));

                         if(showincartdata==null)
                         {
                             emptycart.setVisibility(View.VISIBLE);
                         }else
                         {
                             emptycart.setVisibility(View.INVISIBLE);
                         }
                         showcartAdapter.notifyDataSetChanged();


                     }
                    calculateTotalAmount(showincartdataArrayList);
                 }
            }
        }) ;

    }

    public void calculateTotalAmount(ArrayList<Showincartdata> showincartdataArrayList) {
        double totalAmount = 0.0;
        for(Showincartdata showincartdata:showincartdataArrayList)
        {
            totalAmount += Integer.parseInt(showincartdata.getDishprice());
            showcartAdapter.notifyDataSetChanged();

        }
       finalprice.setText("₹"+totalAmount);

    }



}