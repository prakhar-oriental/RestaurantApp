package com.example.artybeaconfoodapp2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artybeaconfoodapp2.Data.Showincartdata;
import com.example.artybeaconfoodapp2.R;
import com.example.artybeaconfoodapp2.homee;
import com.example.artybeaconfoodapp2.showcart;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class showcartAdapter extends RecyclerView.Adapter<showcartAdapter.showcartviewholder> {
ArrayList<Showincartdata> showincartdataList;
Context context;
int totalprice = 0;


    public showcartAdapter(ArrayList<Showincartdata> showincartdataList, Context context) {
        this.showincartdataList = showincartdataList;
        this.context = context;
    }

    @NonNull

    @Override
    public showcartviewholder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.showcartlayout,parent,false);
        return new showcartviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  showcartAdapter.showcartviewholder holder, int position) {
             holder.name.setText(showincartdataList.get(position).getDish());
             holder.quantity.setText("Qty : "+String.valueOf(showincartdataList.get(position).getDishquantity())+"item");
             holder.price.setText( String.valueOf("  ₹"+showincartdataList.get(position).getDishprice()));

             holder.del.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     FirebaseFirestore.getInstance().collection("Cartdata").document(FirebaseAuth.getInstance().getUid()).collection("cart").document(showincartdataList.get(position).getDocumentid())
                     .delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                         @Override
                         public void onComplete(@NonNull  Task<Void> task) {
                             if(task.isSuccessful())
                             {
                                 showincartdataList.remove(showincartdataList.get(position));
//                                 Intent refresh = new Intent(context,showcart.class);
//                                 refresh.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                 context.startActivity(refresh);

                                   notifyDataSetChanged();
                                   notifyItemRemoved(position);
//                                 Intent refresh = new Intent(context,showcart.class);
//                                 refresh.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                 context.startActivity(refresh);



                                 Toast.makeText(v.getContext(), "item removed!Please Refresh your Cart", Toast.LENGTH_SHORT).show();
                             }else
                             {
                                 Toast.makeText(v.getContext(), "Erro"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                             }

                         }

                     });
                 }
             });

    }

    @Override
    public int getItemCount() {
        return showincartdataList.size();
    }

    public class showcartviewholder extends RecyclerView.ViewHolder {
        TextView name,price,quantity;
        ImageView del;
        public showcartviewholder(@NonNull  View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price  = itemView.findViewById(R.id.pprice);
            quantity = itemView.findViewById(R.id.quantity);
            del = itemView.findViewById(R.id.del);
        }
    }
}
