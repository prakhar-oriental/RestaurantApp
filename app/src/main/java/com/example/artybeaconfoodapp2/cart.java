package com.example.artybeaconfoodapp2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artybeaconfoodapp2.Adapters.showcartAdapter;
import com.example.artybeaconfoodapp2.Data.Showincartdata;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link cart#newInstance} factory method to
 * create an instance of this fragment.
 */
public class cart extends Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public cart() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment aboutus.
     */
    // TODO: Rename and change types and number of parameters
    public static cart newInstance(String param1, String param2) {
        cart fragment = new cart();
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
    RecyclerView recyclerView;
    ArrayList<Showincartdata> showincartdataArrayList;
    com.example.artybeaconfoodapp2.Adapters.showcartAdapter showcartAdapter;
    TextView finalprice;
    ImageView refreshcart;
    ImageView emptycart;
    ImageView returnnew;
    double totalAmount;
    Button checkout;
    BottomSheetDialog bottomSheetDialog;
    Showincartdata showincartdata;
    TextInputEditText addressone,addresstwo,pincode,state,city;
    Button placeorder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_cart, container, false);
        checkout = view.findViewById(R.id.checkoutbtn);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog = new BottomSheetDialog(getActivity(),R.style.BottomSheetTheme);
            View sheetview = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet_layout,(ViewGroup)view.findViewById(R.id.bottom_sheet));
//                TextInputEditText addressone,addresstwo,pincode,state,city;
//                Button placeorder;
                addressone = sheetview.findViewById(R.id.addressone);
                addresstwo = sheetview.findViewById(R.id.addresstwo);
                pincode= sheetview.findViewById(R.id.pincode);
                state = sheetview.findViewById(R.id.state);
                city = sheetview.findViewById(R.id.city);
                placeorder = sheetview.findViewById(R.id.placeorder);
              placeorder.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {


                      if(addressone.getText().toString().isEmpty() || addresstwo.getText().toString().isEmpty() || pincode.getText().toString().isEmpty() || state.getText().toString().isEmpty()|| city.getText().toString().isEmpty())
                      {
                          Toast.makeText(getActivity(), "Please fill  All Feilds ", Toast.LENGTH_SHORT).show();
                      }else if(pincode.getText().toString().length()!=6)
                      {
                          Toast.makeText(getActivity(), "Pincode Length Should be equal to 6", Toast.LENGTH_SHORT).show();
                      }else
                      {
                           // makepayment();
                          //startActivity(new Intent(getActivity(),Paymentwindow.class));
                          Intent intent = new Intent(getContext(),Paymentwindow.class);
                          intent.putExtra("productlist",(Serializable) showincartdataArrayList);

                          intent.putExtra("finalAmount",totalAmount);
                          intent.putExtra("address1",addressone.getText().toString());
                          intent.putExtra("address2",addresstwo.getText().toString());
                          intent.putExtra("pincode",pincode.getText().toString());
                          intent.putExtra("state",state.getText().toString());
                          intent.putExtra("city",city.getText().toString());
                          getActivity().startActivity(intent);
                      }

                  }
              });
            bottomSheetDialog.setContentView(sheetview);
            bottomSheetDialog.show();

            }
        });

        returnnew = view.findViewById(R.id.returnnew);
        emptycart = view.findViewById(R.id.emptycart);
        recyclerView = view.findViewById(R.id.showcartrcv);
        finalprice = view.findViewById(R.id.placeorderprice);
        refreshcart =  view.findViewById(R.id.refreshcart);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        showincartdataArrayList = new ArrayList<>();
        showcartAdapter = new showcartAdapter(showincartdataArrayList,getContext());
        recyclerView.setAdapter(showcartAdapter);
        returnnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(showcart.this,homee.class));
            }
        });
        refreshcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new cart();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontainer,new cart());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        FirebaseFirestore.getInstance().collection("Cartdata").document(FirebaseAuth.getInstance().getUid()).collection("cart")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments())
                    {

                        String documentid = documentSnapshot.getId();
                         showincartdata = documentSnapshot.toObject(Showincartdata.class);
                        showincartdata.setDocumentid(documentid);
                        //sum  = sum+Integer.parseInt(showincartdata.getDishprice());
                        showincartdataArrayList.add(showincartdata);
                        //  finalprice.setText(String.valueOf(sum));

                        if(showincartdata==null)
                        {
                            emptycart.setVisibility(View.VISIBLE);
                        }else
                        {
                            emptycart.setVisibility(View.INVISIBLE);
                        }
                        showcartAdapter.notifyDataSetChanged();


                    }
                    calculateTotalAmount(showincartdataArrayList);
                }
            }
        }) ;

        return view;
    }






    public void calculateTotalAmount(ArrayList<Showincartdata> showincartdataArrayList) {
         totalAmount = 0.0;
        for(Showincartdata showincartdata:showincartdataArrayList)
        {
            totalAmount += Integer.parseInt(showincartdata.getDishprice());
            showcartAdapter.notifyDataSetChanged();

        }
        finalprice.setText("₹"+totalAmount);

    }
}