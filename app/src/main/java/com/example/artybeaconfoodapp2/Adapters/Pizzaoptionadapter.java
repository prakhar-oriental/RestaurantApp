package com.example.artybeaconfoodapp2.Adapters;

import android.content.ClipData;
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
import com.example.artybeaconfoodapp2.Data.Pizzaoptiondata;
import com.example.artybeaconfoodapp2.R;

import java.util.ArrayList;

import ru.embersoft.expandabletextview.ExpandableTextView;

public class Pizzaoptionadapter extends RecyclerView.Adapter<Pizzaoptionadapter.Pizzaoptionviewholder> {
    @NonNull
    ArrayList<Pizzaoptiondata> pizzaoptiondata;
    Context context;

    public Pizzaoptionadapter(@NonNull ArrayList<Pizzaoptiondata> pizzaoptiondata, Context context) {
        this.pizzaoptiondata = pizzaoptiondata;
        this.context = context;
    }

    @Override
    public Pizzaoptionviewholder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pizzasoptionlayout,parent,false);
        return new Pizzaoptionviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  Pizzaoptionadapter.Pizzaoptionviewholder holder, int position) {
            // holder.pizzopimg.setImageResource(pizzaoptiondata.get(position).getPizzaopimg());
        Glide.with(context).load(pizzaoptiondata.get(position).getPimg()).into(holder.pizzopimg);
             holder.pizzaopname.setText(pizzaoptiondata.get(position).getPname());
             holder.pizzaopprice.setText("₹"+pizzaoptiondata.get(position).getPprice());
             holder.subheading.setText(pizzaoptiondata.get(position).getSubheading());
             holder.expandableTextView.setText(pizzaoptiondata.get(position).getMultilinedes());
             holder.pizzaopcart.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent intent = new Intent(context, Addtocart.class);
                     intent.putExtra("pname",pizzaoptiondata.get(position).getPname());
                     intent.putExtra("pprice",pizzaoptiondata.get(position).getPprice());
                     intent.putExtra("pimage",pizzaoptiondata.get(position).getPimg());
                     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                     context.startActivity(intent);
                 }
             });

        holder.expandableTextView.setOnStateChangeListener(new ExpandableTextView.OnStateChangeListener() {
            @Override
            public void onStateChange(boolean isShrink) {
//                Item contentItem = items.get(position);
//                contentItem.setShrink(isShrink);
//                items.set(position, contentItem);
               pizzaoptiondata.get(position).setShrink(isShrink);

                pizzaoptiondata.set(position,pizzaoptiondata.get(position));

            }
        });
        holder.expandableTextView.setText(pizzaoptiondata.get(position).getMultilinedes());
        holder.expandableTextView.resetState(pizzaoptiondata.get(position).isShrink());
    }

    @Override
    public int getItemCount() {
        return pizzaoptiondata.size();
    }

    public class Pizzaoptionviewholder extends RecyclerView.ViewHolder {
        TextView subheading;
        ImageView pizzopimg;
        Button pizzaopcart;
        TextView pizzaopname,pizzaopprice;
        ExpandableTextView expandableTextView;
        public Pizzaoptionviewholder(@NonNull  View itemView) {
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
