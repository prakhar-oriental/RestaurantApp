package com.example.admin.AdminAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.R;
import com.example.admin.aAdminData.Showincartdata;
import com.example.admin.aAdminData.vieworderdata;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class vieworderadapter extends RecyclerView.Adapter<vieworderadapter.vieworderviewholder> {
    ArrayList<vieworderdata> vieworderdataList;    // parentlist
    Context context;
    ArrayList<Showincartdata> vieworderitemdataArrayList;   //child
    String omg  =" ";
    public vieworderadapter(ArrayList<vieworderdata> vieworderdataList,ArrayList<Showincartdata> vieworderitemdataArrayList ,Context context) {
        this.vieworderdataList = vieworderdataList;
        this.vieworderitemdataArrayList = vieworderitemdataArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public vieworderviewholder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vieworderlayout,parent,false);
        return new vieworderviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  vieworderadapter.vieworderviewholder holder, @SuppressLint("RecyclerView") int position) {
          vieworderdata temp = vieworderdataList.get(position);
          holder.add1.setText("Add 1 : "+temp.getAddress1());
          holder.add2.setText("Add 2 : "+temp.getAddress2());
          holder.total.setText("Total Amt : "+String.valueOf(temp.getTotal())+"Rs");
          holder.city.setText("City : "+temp.getCity());
          holder.pincode.setText("Pincode : "+temp.getPincode());
          holder.state.setText("State : "+temp.getState());
           vieworderitemadapter vieworderitemadapter = new vieworderitemadapter(temp.getOrderlist());
              holder.vieworderitemrcv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true));
              holder.vieworderitemrcv.setAdapter(vieworderitemadapter);
          holder.delbtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  FirebaseFirestore.getInstance().collection("OrdersData").document("ODD").collection("order").document(vieworderdataList.get(position).getDocumentid()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                      @Override
                      public void onComplete(@NonNull Task<Void> task) {
                          vieworderdataList.remove(vieworderdataList.get(position));
                          notifyDataSetChanged();
                          notifyItemRemoved(position);
                      }
                  }).addOnFailureListener(new OnFailureListener() {
                      @Override
                      public void onFailure(@NonNull Exception e) {

                      }
                  });
              }
          });


    }

    @Override
    public int getItemCount() {
        return vieworderdataList.size();
    }

    public class vieworderviewholder extends RecyclerView.ViewHolder {
        TextView add1,add2,state,pincode,city,total;
        RecyclerView vieworderitemrcv;
        ImageView delbtn;

        public vieworderviewholder(@NonNull  View itemView) {
            super(itemView);
            vieworderitemrcv = itemView.findViewById(R.id.vieworderlayoutrcv);
            add1 = itemView.findViewById(R.id.add1);
            add2 = itemView.findViewById(R.id.add2);
            state = itemView.findViewById(R.id.state);
            pincode = itemView.findViewById(R.id.pincode);
            city = itemView.findViewById(R.id.city);
            total = itemView.findViewById(R.id.total);
            delbtn = itemView.findViewById(R.id.delbtn);


        }
    }
}
