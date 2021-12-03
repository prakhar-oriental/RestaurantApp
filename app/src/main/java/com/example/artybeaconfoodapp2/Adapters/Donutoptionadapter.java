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
import com.example.artybeaconfoodapp2.Data.Donutoptiondata;
import com.example.artybeaconfoodapp2.Data.Shakeoptiondata;
import com.example.artybeaconfoodapp2.R;

import java.util.ArrayList;

import ru.embersoft.expandabletextview.ExpandableTextView;

public class Donutoptionadapter extends RecyclerView.Adapter<Donutoptionadapter.Donutoptionviewholder> {
    @NonNull
    ArrayList<Donutoptiondata> donutoptiondata;
    Context context;

    public Donutoptionadapter(@NonNull ArrayList<Donutoptiondata> donutoptiondata, Context context) {
        this.donutoptiondata = donutoptiondata;
        this.context = context;
    }

    @Override
    public Donutoptionviewholder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pizzasoptionlayout,parent,false);
        return new Donutoptionviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Donutoptionviewholder holder, int position) {
            // holder.pizzopimg.setImageResource(donutoptiondata.get(position).getDonutopimg());
        Glide.with(context).load(donutoptiondata.get(position).getPimg()).into(holder.pizzopimg);
             holder.pizzaopname.setText(donutoptiondata.get(position).getPname());
             holder.pizzaopprice.setText("₹"+donutoptiondata.get(position).getPprice());
             holder.subheading.setText(donutoptiondata.get(position).getSubheading());
             holder.pizzaopcart.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent intent = new Intent(context, Addtocart.class);
                     intent.putExtra("pname",donutoptiondata.get(position).getPname());
                     intent.putExtra("pprice",donutoptiondata.get(position).getPprice());
                     intent.putExtra("pimage",donutoptiondata.get(position).getPimg());
                     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                     context.startActivity(intent);
                 }
             });

        holder.expandableTextView.setText(donutoptiondata.get(position).getMultilinedes());
        holder.expandableTextView.setOnStateChangeListener(new ExpandableTextView.OnStateChangeListener() {
            @Override
            public void onStateChange(boolean isShrink) {
//                Item contentItem = items.get(position);
//                contentItem.setShrink(isShrink);
//                items.set(position, contentItem);
                donutoptiondata.get(position).setShrink(isShrink);

                donutoptiondata.set(position,donutoptiondata.get(position));

            }
        });
        holder.expandableTextView.setText(donutoptiondata.get(position).getMultilinedes());
        holder.expandableTextView.resetState(donutoptiondata.get(position).isShrink());
    }

    @Override
    public int getItemCount() {
        return donutoptiondata.size();
    }

    public class Donutoptionviewholder extends RecyclerView.ViewHolder {
        ImageView pizzopimg;
        Button pizzaopcart;
        TextView pizzaopname,pizzaopprice;
        ExpandableTextView expandableTextView;
        TextView subheading;

        public Donutoptionviewholder(@NonNull  View itemView) {
            super(itemView);
            pizzopimg    = itemView.findViewById(R.id.pizzaopimg);
            pizzaopname  = itemView.findViewById(R.id.pizzaopname);
            pizzaopprice = itemView.findViewById(R.id.pizzaopprice);
            pizzaopcart  = itemView.findViewById(R.id.atc);
            subheading = itemView.findViewById(R.id.subheading);
            expandableTextView  = itemView.findViewById(R.id.descTextView);
        }
    }
}
