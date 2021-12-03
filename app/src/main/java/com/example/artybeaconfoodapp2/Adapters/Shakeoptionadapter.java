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
import com.example.artybeaconfoodapp2.Data.Shakeoptiondata;
import com.example.artybeaconfoodapp2.R;

import java.util.ArrayList;

import ru.embersoft.expandabletextview.ExpandableTextView;

public class Shakeoptionadapter extends RecyclerView.Adapter<Shakeoptionadapter.Shakeoptionviewholder> {
    @NonNull
    ArrayList<Shakeoptiondata> shakeoptiondata;
    Context context;

    public Shakeoptionadapter(@NonNull ArrayList<Shakeoptiondata> shakeoptiondata, Context context) {
        this.shakeoptiondata = shakeoptiondata;
        this.context = context;
    }

    @Override
    public Shakeoptionviewholder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pizzasoptionlayout,parent,false);
        return new Shakeoptionviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Shakeoptionviewholder holder, int position) {
           //  holder.pizzopimg.setImageResource(shakeoptiondata.get(position).getShakeopimg());
        Glide.with(context).load(shakeoptiondata.get(position).getPimg()).into(holder.pizzopimg);
             holder.pizzaopname.setText(shakeoptiondata.get(position).getPname());
             holder.pizzaopprice.setText("₹"+shakeoptiondata.get(position).getPprice());
        holder.subheading.setText(shakeoptiondata.get(position).getSubheading());
             holder.pizzaopcart.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent intent = new Intent(context, Addtocart.class);
                     intent.putExtra("pname",shakeoptiondata.get(position).getPname());
                     intent.putExtra("pprice",shakeoptiondata.get(position).getPprice());
                     intent.putExtra("pimage",shakeoptiondata.get(position).getPimg());
                     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                     context.startActivity(intent);
                 }
             });
        holder.expandableTextView.setText(shakeoptiondata.get(position).getMultilinedes());
        holder.expandableTextView.setOnStateChangeListener(new ExpandableTextView.OnStateChangeListener() {
            @Override
            public void onStateChange(boolean isShrink) {
//                Item contentItem = items.get(position);
//                contentItem.setShrink(isShrink);
//                items.set(position, contentItem);
                shakeoptiondata.get(position).setShrink(isShrink);

                shakeoptiondata.set(position,shakeoptiondata.get(position));

            }
        });
        holder.expandableTextView.setText(shakeoptiondata.get(position).getMultilinedes());
        holder.expandableTextView.resetState(shakeoptiondata.get(position).isShrink());
    }

    @Override
    public int getItemCount() {
        return shakeoptiondata.size();
    }

    public class Shakeoptionviewholder extends RecyclerView.ViewHolder {
        ImageView pizzopimg;
        Button pizzaopcart;
        TextView pizzaopname,pizzaopprice;
        ExpandableTextView expandableTextView;
        TextView subheading;

        public Shakeoptionviewholder(@NonNull  View itemView) {
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
