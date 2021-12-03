package com.example.artybeaconfoodapp2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artybeaconfoodapp2.Burgersoption;
import com.example.artybeaconfoodapp2.Data.categorydata;
import com.example.artybeaconfoodapp2.Donutoption;
import com.example.artybeaconfoodapp2.PizzasOption;
import com.example.artybeaconfoodapp2.R;
import com.example.artybeaconfoodapp2.Shakesoption;

import java.util.ArrayList;

public class categoryadapter extends RecyclerView.Adapter<categoryadapter.categoryviewholder>  {
    @NonNull
    ArrayList<categorydata> catData;
    Context context;

    public categoryadapter(@NonNull ArrayList<categorydata> catData, Context context) {
        this.catData = catData;
        this.context = context;
    }

    @Override
    public categoryviewholder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        LayoutInflater autolayoutInflater = LayoutInflater.from(parent.getContext());
        View view = autolayoutInflater.inflate(R.layout.categorylayout,parent,false);
        categoryviewholder categoryviewholder = new categoryviewholder(view);
        return categoryviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull  categoryadapter.categoryviewholder holder, int position) {
                 holder.catimg.setImageResource(catData.get(position).getCatimg());
                 holder.cattext.setText(catData.get(position).getCattext());
                 holder.categorycard.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         if(position==0)
                         {
                             context.startActivity(new Intent(context,PizzasOption.class));

                         }else if(position==1)
                         {
                             context.startActivity(new Intent(context, Burgersoption.class));
                         }else if(position==2)
                         {
                             context.startActivity(new Intent(context, Shakesoption.class));
                         }else if(position==3)
                         {
                             context.startActivity(new Intent(context, Donutoption.class));
                         }
                     }
                 });
    }

    @Override
    public int getItemCount() {
        return catData.size();
    }

    public class categoryviewholder extends RecyclerView.ViewHolder {
        ImageView catimg;
        TextView cattext;
        CardView categorycard;
        public categoryviewholder(@NonNull  View itemView) {

            super(itemView);
            catimg = itemView.findViewById(R.id.categoryPic);
            cattext = itemView.findViewById(R.id.categoryName);
            categorycard =  itemView.findViewById(R.id.categorycard);
        }
    }
}
