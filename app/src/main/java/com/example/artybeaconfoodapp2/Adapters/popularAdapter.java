package com.example.artybeaconfoodapp2.Adapters;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.artybeaconfoodapp2.Addtocart;
import com.example.artybeaconfoodapp2.Data.categorydata;
import com.example.artybeaconfoodapp2.Data.populardata;
import com.example.artybeaconfoodapp2.R;

import java.util.ArrayList;

public class popularAdapter extends RecyclerView.Adapter<popularAdapter.popularviewholder> {
    @NonNull
    ArrayList<populardata> popData;
    Context context;

    public popularAdapter(@NonNull ArrayList<populardata> popData, Context context) {
        this.popData = popData;
        this.context = context;
    }

    @Override
    public popularviewholder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        LayoutInflater autolayoutInflater = LayoutInflater.from(parent.getContext());
        View view = autolayoutInflater.inflate(R.layout.popularlayout,parent,false);
        popularviewholder popularviewholder = new popularviewholder(view);
        return popularviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull  popularAdapter.popularviewholder holder, int position) {
         holder.name.setText(popData.get(position).getPname());
         holder.price.setText(String.valueOf(popData.get(position).getPprice()));
        // holder.img.setImageResource(popData.get(position).getPimg());
        Glide.with(context).load(popData.get(position).getPimg()).into(holder.img);

         holder.add.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(context,Addtocart.class);
                 intent.putExtra("pname",popData.get(position).getPname());
                 intent.putExtra("pprice",popData.get(position).getPprice());
                 intent.putExtra("pimage",popData.get(position).getPimg());
                   context.startActivity(intent);
             }
         });

    }

    @Override
    public int getItemCount() {
        return popData.size();
    }

    public class popularviewholder extends RecyclerView.ViewHolder {
        TextView name,price,add;
        ImageView img;
      public popularviewholder(@NonNull  View itemView) {
          super(itemView);
          name = itemView.findViewById(R.id.title);
          price = itemView.findViewById(R.id.fee);
          add = itemView.findViewById(R.id.addBtn);
          img = itemView.findViewById(R.id.pic);
      }
  }
}
