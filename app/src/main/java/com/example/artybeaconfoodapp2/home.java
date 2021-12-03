package com.example.artybeaconfoodapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.razorpay.PaymentResultListener;

public class home extends AppCompatActivity implements PaymentResultListener {
    BottomNavigationView bnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,new homee()).commit();

        bnv=(BottomNavigationView)findViewById(R.id.bottomnavigation);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                Fragment temp=null;

                switch (item.getItemId())
                {
                    case R.id.home : temp=new homee();
                        break;

                    case R.id.cart : temp=new cart();
                    break;
                    case R.id.profile:  temp=new profile();
                        break;
                    case R.id.share: temp = new track();
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,temp).commit();

                return true;
            }
        });

    }

        @Override
        public void onPaymentSuccess(String s) {
//            try {
//                Log.d("payid",s);
//                cart cart = new cart();
//                cart.placeorder();
//            }
//            catch (Exception e){
//                Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
    }

    @Override
    public void onPaymentError(int i, String s) {
//         try {
//             Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
//         }catch (Exception e)
//         {
//             Toast.makeText(this, "excep:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//         }
    }
}