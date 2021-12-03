package com.example.artybeaconfoodapp2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.artybeaconfoodapp2.Addtocart;
import com.example.artybeaconfoodapp2.Data.Burgeroptiondata;
import com.example.artybeaconfoodapp2.Data.Pizzaoptiondata;
import com.example.artybeaconfoodapp2.R;

import java.util.ArrayList;

import ru.embersoft.expandabletextview.ExpandableTextView;

public class Burgeroptionadapter extends RecyclerView.Adapter<Burgeroptionadapter.Burgeroptionviewholder> {
    @NonNull
    ArrayList<Burgeroptiondata> burgeroptiondata;
    Context context;

    public Burgeroptionadapter(@NonNull ArrayList<Burgeroptiondata> burgeroptiondata, Context context) {
        this.burgeroptiondata = burgeroptiondata;
        this.context = context;
    }

    @Override
    public Burgeroptionviewholder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pizzasoptionlayout,parent,false);
        return new Burgeroptionviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Burgeroptionviewholder holder, int position) {
           //  holder.pizzopimg.setImageResource(burgeroptiondata.get(position).getBurgeropimg());
        Glide.with(context).load(burgeroptiondata.get(position).getPimg()).into(holder.pizzopimg);
             holder.pizzaopname.setText(burgeroptiondata.get(position).getPname());
             holder.subheading.setText(burgeroptiondata.get(position).getSubheading());
             holder.pizzaopprice.setText("₹"+burgeroptiondata.get(position).getPprice());
             holder.pizzaopcart.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent intent = new Intent(context, Addtocart.class);
                     intent.putExtra("pname",burgeroptiondata.get(position).getPname());
                     intent.putExtra("pprice",burgeroptiondata.get(position).getPprice());
                     intent.putExtra("pimage",burgeroptiondata.get(position).getPimg());
                     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                     context.startActivity(intent);
                 }
             });
        holder.expandableTextView.setText(burgeroptiondata.get(position).getMultilinedes());
        holder.expandableTextView.setOnStateChangeListener(new ExpandableTextView.OnStateChangeListener() {
            @Override
            public void onStateChange(boolean isShrink) {
//                Item contentItem = items.get(position);
//                contentItem.setShrink(isShrink);
//                items.set(position, contentItem);
                burgeroptiondata.get(position).setShrink(isShrink);

                burgeroptiondata.set(position,burgeroptiondata.get(position));

            }
        });
        holder.expandableTextView.setText(burgeroptiondata.get(position).getMultilinedes());
        holder.expandableTextView.resetState(burgeroptiondata.get(position).isShrink());
    }

    @Override
    public int getItemCount() {
        return burgeroptiondata.size();
    }

    public class Burgeroptionviewholder extends RecyclerView.ViewHolder {
        TextView subheading;
        ImageView pizzopimg;
        Button pizzaopcart;
        TextView pizzaopname,pizzaopprice;
        ExpandableTextView expandableTextView;
        public Burgeroptionviewholder(@NonNull  View itemView) {
            super(itemView);
            subheading = itemView.findViewById(R.id.subheading);
            pizzopimg    = itemView.findViewById(R.id.pizzaopimg);
            pizzaopname  = itemView.findViewById(R.id.pizzaopname);
            pizzaopprice = itemView.findViewById(R.id.pizzaopprice);
            pizzaopcart  = itemView.findViewById(R.id.atc);
            expandableTextView  = itemView.findViewById(R.id.descTextView);
        }
    }
}
