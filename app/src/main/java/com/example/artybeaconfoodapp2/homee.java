package com.example.artybeaconfoodapp2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;


import com.example.artybeaconfoodapp2.Adapters.categoryadapter;
import com.example.artybeaconfoodapp2.Adapters.popularAdapter;
import com.example.artybeaconfoodapp2.Data.Showincartdata;
import com.example.artybeaconfoodapp2.Data.categorydata;
import com.example.artybeaconfoodapp2.Data.populardata;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homee#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homee extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public homee() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homee.
     */
    // TODO: Rename and change types and number of parameters
    public static homee newInstance(String param1, String param2) {
        homee fragment = new homee();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
     RecyclerView catrecyclerView,poprecylerview;
     categoryadapter catrgortadapter;
     popularAdapter popularAdapter;
    ViewFlipper flipper;
    ImageView cartimgview;
    TextView usernamehomee;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_homee, container, false);
        usernamehomee = view.findViewById(R.id.usernamehomee);
        FirebaseFirestore.getInstance().collection("userprofile").document(FirebaseAuth.getInstance().getUid()).get()
         .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
             @Override
             public void onSuccess(DocumentSnapshot documentSnapshot) {
                 String uname = documentSnapshot.getString("username");
                 usernamehomee.setText("Hi, "+uname+"!");
             }
         });

        catrecyclerView = view.findViewById(R.id.catrcv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        catrecyclerView.setLayoutManager(linearLayoutManager);
        catrgortadapter = new categoryadapter(catdata(),getContext());
        catrecyclerView.setAdapter(catrgortadapter);

        poprecylerview = view.findViewById(R.id.poprcv);
        LinearLayoutManager poplinearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        poprecylerview.setLayoutManager(poplinearLayoutManager);
        popularAdapter = new popularAdapter(popdata(),getContext());
        poprecylerview.setAdapter(popularAdapter);
        int imgarray[] = {R.drawable.food,R.drawable.deliveryboy,R.drawable.restaurant};
        flipper = view.findViewById(R.id.filpper);
        for(int i = 0;i<imgarray.length;i++)
        {
            showImage(imgarray[i]);

        }
        cartimgview = view.findViewById(R.id.cartimgview);
        cartimgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),showcart.class));
            }
        });
        return view;
    }
    public void showImage(int img)
    {
        ImageView imageView = new ImageView(getContext());

        imageView.setBackgroundResource(img);

        flipper.addView(imageView);
        flipper.setFlipInterval(3000);
        flipper.setAutoStart(true);

        flipper.setOutAnimation(getContext(), android.R.anim.slide_out_right);
        flipper.setInAnimation(getContext(), android.R.anim.slide_in_left);
    }
    public ArrayList<categorydata> catdata()
    {
        ArrayList<categorydata> catdata = new ArrayList<>();
        categorydata first = new categorydata();
        first.setCatimg(R.drawable.cat_1);
        first.setCattext("Pizza");
        catdata.add(first);

        categorydata second = new categorydata();
        second.setCatimg(R.drawable.cat_2);
        second.setCattext("Burger");
        catdata.add(second);

        categorydata third = new categorydata();
        third.setCatimg(R.drawable.cat_4);
        third.setCattext("Shake");
        catdata.add(third);

        categorydata fifth = new categorydata();
        fifth.setCatimg(R.drawable.cat_5);
        fifth.setCattext("Donut");
        catdata.add(fifth);



        return catdata;
    }
    public ArrayList<populardata> popdata()
    {
        ArrayList<populardata> popdata = new ArrayList<>();
//        populardata noodle =  new populardata();
//         noodle.setPname("Noodles");
//         noodle.setPimg(R.drawable.noodle);
//         noodle.setPprice(180);
//         popdata.add(noodle);
//
//        populardata fries =  new populardata();
//        fries.setPname("French Fries");
//        fries.setPimg(R.drawable.fries_adobespark);
//        fries.setPprice(150);
//        popdata.add(fries);
//
//        populardata dosa =  new populardata();
//        dosa.setPname("Dosa");
//        dosa.setPimg(R.drawable.dosatransimgnew5adobespark);
//        dosa.setPprice(90);
//        popdata.add(dosa);
//
//        populardata thali =  new populardata();
//        thali.setPname("Thali");
//        thali.setPimg(R.drawable.thali_adobespark);
//        thali.setPprice(100);
//        popdata.add(thali);
        FirebaseFirestore.getInstance().collection("PopularProducts").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    for(DocumentSnapshot documentSnapshot: task.getResult().getDocuments())
                    {
                        populardata populardata = documentSnapshot.toObject(populardata.class);
                        if(populardata.getCategory().equals("Popular"))
                        popdata.add(populardata);
                        popularAdapter.notifyDataSetChanged();
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(getActivity(), "error : "+e.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
        return popdata;

}
}