package com.example.admin.AdminAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.R;
import com.example.admin.aAdminData.viewproductsdata;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.R;

public class viewproductsadapter extends RecyclerView.Adapter<viewproductsadapter.viewproductsviewholder>{
    ArrayList<viewproductsdata> viewproductsdataList;
    Context context;

    public viewproductsadapter(ArrayList<viewproductsdata> viewproductsdataList, Context context) {
        this.viewproductsdataList = viewproductsdataList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewproductsviewholder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(com.example.admin.R.layout.viewproductslayout,parent,false);
       return new viewproductsviewholder(view);
    }
   public void filterlist(ArrayList<viewproductsdata> filterlist)
   {
       viewproductsdataList = filterlist;
       notifyDataSetChanged();
   }
    @Override
    public void onBindViewHolder(@NonNull  viewproductsadapter.viewproductsviewholder holder, int position) {
        viewproductsdata temp = viewproductsdataList.get(position);
                     holder.vname.setText(viewproductsdataList.get(position).getPname());
                     holder.vprice.setText(String.valueOf("₹"+viewproductsdataList.get(position).getPprice()));
                     holder.vuniqueid.setText("Unique id : "+viewproductsdataList.get(position).getPuniqueid());
                     holder.vcategory.setText(viewproductsdataList.get(position).getCategory());

                     holder.vdel.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             FirebaseFirestore.getInstance().collection("PopularProducts").document(viewproductsdataList.get(position).getDocumentid()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                 @Override
                                 public void onComplete(@NonNull Task<Void> task) {
                                     viewproductsdataList.remove(viewproductsdataList.get(position));
//
                                     notifyDataSetChanged();
                                     notifyItemRemoved(position);

                                     Toast.makeText(v.getContext(), "item removed!Please Refresh your Cart", Toast.LENGTH_SHORT).show();
                                 }
                             }).addOnFailureListener(new OnFailureListener() {
                                 @Override
                                 public void onFailure(@NonNull  Exception e) {
                                     Toast.makeText(context, "error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                 }
                             });
                         }
                     });
    }

    @Override
    public int getItemCount() {
        return viewproductsdataList.size();
    }

    public class viewproductsviewholder extends RecyclerView.ViewHolder {
        TextView vname,vprice,vcategory,vuniqueid;
        ImageView vdel;
    public viewproductsviewholder(@NonNull  View itemView) {
        super(itemView);
        vname = itemView.findViewById(com.example.admin.R.id.vname);
        vprice = itemView.findViewById(com.example.admin.R.id.vprice);
        vdel = itemView.findViewById(com.example.admin.R.id.vdel);
        vcategory = itemView.findViewById(com.example.admin.R.id.vcategory);
        vuniqueid = itemView.findViewById(com.example.admin.R.id.vuniqueid);

    }

}


}



