package com.example.artybeaconfoodapp2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class profile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment profile.
     */
    // TODO: Rename and change types and number of parameters
    public static profile newInstance(String param1, String param2) {
        profile fragment = new profile();
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
     TextView profilename,profileemail,profileaddress,profilephone,aboutus,feedback,rateus,logout;
    ImageView edit;
    String pname ;
    String pemail;
    String paddress ;
    String pphone;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        edit = view.findViewById(R.id.edit);
        profilename = view.findViewById(R.id.profilename);
        profileemail = view.findViewById(R.id.profileemail);
        profileaddress = view.findViewById(R.id.profileaddress);
        aboutus = view.findViewById(R.id.profileaboutus);
        feedback = view.findViewById(R.id.profilefeedback);
        rateus = view.findViewById(R.id.profilerateus);
        logout = view.findViewById(R.id.profilelogout);
        profilephone = view.findViewById(R.id.profilephonenumber);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(getContext(),editaccount.class));

                DialogPlus dialog = DialogPlus.newDialog(v.getContext())
                        .setGravity(Gravity.CENTER)
                        .setMargin(50,0,50,0)
                        .setContentHolder(new ViewHolder(R.layout.updatelayout))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View  holderView = dialog.getHolderView();

                TextInputEditText editname = (TextInputEditText) holderView.findViewById(R.id.editusername);
                TextInputEditText editphone =(TextInputEditText) holderView.findViewById(R.id.editphone);
                TextInputEditText editaddress =(TextInputEditText) holderView.findViewById(R.id.editaddress);

                editname.setText(pname);
                editphone.setText(pphone);
                editaddress.setText(paddress);


                Button update = holderView.findViewById(R.id.updatebtn);
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        //     map.put("notedesc",ntitle.getText().toString());
                        map.put("username",editname.getText().toString());
                        map.put("userphone",editphone.getText().toString());
                        map.put("useraddress",editaddress.getText().toString());

                        FirebaseFirestore.getInstance().collection("userprofile").document(FirebaseAuth.getInstance().getUid()).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    Fragment fragment = new cart();
                                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                    fragmentTransaction.replace(R.id.fragmentcontainer,new profile());
                                    fragmentTransaction.addToBackStack(null);
                                    fragmentTransaction.commit();
                                        dialog.dismiss();
                                }
                            }
                        });

                    }
                });
                dialog.show();
            }


        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(),firstscreen.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),feedbackactivity.class));
            }
        });
        FirebaseFirestore.getInstance().collection("userprofile").document(FirebaseAuth.getInstance().getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                         pname = documentSnapshot.getString("username");
                         pemail = documentSnapshot.getString("useremail");
                         paddress = documentSnapshot.getString("useraddress");
                         pphone = documentSnapshot.getString("userphone");
                        profilename.setText(pname);
                        profileemail.setText(pemail);
                        profileaddress.setText(" "+paddress);
                        profilephone.setText(pphone);

                    }
                });

        return view;
    }
}