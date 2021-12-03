package com.example.admin.AdminAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.R;
import com.example.admin.aAdminData.Showincartdata;
import com.example.admin.aAdminData.vieworderitemdata;

import java.util.ArrayList;

public class vieworderitemadapter extends RecyclerView.Adapter<vieworderitemadapter.vieworderitemadapterviewholder> {
    ArrayList<Showincartdata> vieworderitemdataList;
    Context context;



    public vieworderitemadapter(ArrayList<Showincartdata> orderlist) {
        this.vieworderitemdataList = orderlist;
    }

    @NonNull
    @Override
    public vieworderitemadapterviewholder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vieworderitemlayout,parent,false);
        return new vieworderitemadapterviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  vieworderitemadapter.vieworderitemadapterviewholder holder, int position) {
             // vieworderitemdata vieworderitemdata  = vieworderitemdataList.get(position);
        Log.d("riya", String.valueOf(vieworderitemdataList.size()));
              holder.dname.setText(vieworderitemdataList.get(position).getDish());
              holder.dprice.setText(vieworderitemdataList.get(position).getDishprice());
              holder.dquantity.setText(vieworderitemdataList.get(position).getDishquantity());
    }

    @Override
    public int getItemCount() {
        return vieworderitemdataList.size();
    }

    public class vieworderitemadapterviewholder extends RecyclerView.ViewHolder {
        TextView dname,dprice,dquantity;
        public vieworderitemadapterviewholder(@NonNull  View itemView) {
            super(itemView);
            dname = itemView.findViewById(R.id.dname);
            dprice = itemView.findViewById(R.id.dprice);
            dquantity = itemView.findViewById(R.id.dquantity);
        }
    }
}
