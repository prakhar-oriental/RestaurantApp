package com.example.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.AdminAdapter.viewproductsadapter;
import com.example.admin.aAdminData.viewproductsdata;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class viewproducts extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<viewproductsdata> viewproductsdataArrayList;
    viewproductsadapter viewproductsadapter;
    ImageView emty;
    EditText searchbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewproducts);
        recyclerView = findViewById(R.id.vrcv);
        searchbar = findViewById(R.id.search);
        emty = findViewById(R.id.emtyimg);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewproductsdataArrayList = new ArrayList<>();
        viewproductsadapter = new viewproductsadapter(viewproductsdataArrayList,getApplicationContext());
        recyclerView.setAdapter(viewproductsadapter);
        FirebaseFirestore.getInstance().collection("PopularProducts").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull  Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    Log.d("taskerror", String.valueOf(task.getResult().getDocuments()));
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments())
                    {

                        String documentid = documentSnapshot.getId();
                        viewproductsdata viewproductsdata = documentSnapshot.toObject(viewproductsdata.class);
                        viewproductsdata.setDocumentid(documentid);
                        //sum  = sum+Integer.parseInt(showincartdata.getDishprice());
                        viewproductsdataArrayList.add(viewproductsdata);
                        //  finalprice.setText(String.valueOf(sum));
                        viewproductsadapter.notifyDataSetChanged();
                        if(viewproductsdata==null)
                        {
                            emty.setVisibility(View.VISIBLE);
                        }else
                        {
                            emty.setVisibility(View.INVISIBLE);
                        }




                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull  Exception e) {
                Toast.makeText(viewproducts.this, "error"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

       searchbar.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

           }

           @Override
           public void afterTextChanged(Editable s) {
                filter(s.toString());
           }
       });
    }

    private void filter(String text) {
        ArrayList<viewproductsdata> filterlist = new ArrayList<>();
        for(viewproductsdata items: viewproductsdataArrayList)
        {
             if(items.getPname().toLowerCase().contains(text.toLowerCase()))
             {
                 filterlist.add(items);
             }
        }
        viewproductsadapter.filterlist(filterlist);

    }
}