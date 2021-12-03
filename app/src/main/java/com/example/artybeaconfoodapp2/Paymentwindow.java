package com.example.artybeaconfoodapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.artybeaconfoodapp2.Data.Showincartdata;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Paymentwindow extends AppCompatActivity implements PaymentResultListener {
Button payonline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentwindow);

        payonline = findViewById(R.id.payonline);
        String addone = getIntent().getStringExtra("address1");
        String addtwo = getIntent().getStringExtra("address2");
        String pincode = getIntent().getStringExtra("pincode");
        String  state = getIntent().getStringExtra("state");
        String city = getIntent().getStringExtra("city");
        ArrayList<Showincartdata> list = (ArrayList<Showincartdata>) getIntent().getSerializableExtra("productlist");
        Double totalamount = getIntent().getDoubleExtra("finalAmount",0.0);
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_fxsJsMixpYIpTw");

        checkout.setImage(R.drawable.accountback);

        final Activity activity = this;
        payonline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    // Model obj = new Model();
                    JSONObject options = new JSONObject();

                    options.put("name", "Prakhar Rathore");
                    options.put("description", "Reference No. #123456");
                    options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
                    // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
                    options.put("theme.color", "#3399cc");
                    options.put("currency", "INR");
                    //
                    //  int y = 740000;
                    options.put("amount", totalamount*100);//300 X 100
                    options.put("prefill.email", "prakhar.r786@gmail.com");
                    options.put("prefill.contact","7697056251");
                    checkout.open(activity, options);

                } catch(Exception e) {
                    Log.e("TAG", "Payment failed!,Try Again");
                }
            }
        });

    }




    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();

        String addone = getIntent().getStringExtra("address1");
        String addtwo = getIntent().getStringExtra("address2");
        String pincode = getIntent().getStringExtra("pincode");
        String  state = getIntent().getStringExtra("state");
        String city = getIntent().getStringExtra("city");
        Double totalamount = getIntent().getDoubleExtra("finalAmount",0.0);
        ArrayList<Showincartdata> list = (ArrayList<Showincartdata>) getIntent().getSerializableExtra("productlist");
        HashMap<String,Object> orderdata = new HashMap<>();

        orderdata.put("address1",addone);
        orderdata.put("address2",addtwo);
        orderdata.put("pincode",pincode);
        orderdata.put("total",totalamount);
        orderdata.put("state",state);
        orderdata.put("city",city);
//        for(Showincartdata showincartdata:list)
//        {
//              orderdata.put("dish",showincartdata.getDish());
//              orderdata.put("dishprice",showincartdata.getDishprice());
//              orderdata.put("dishquantity",showincartdata.getDishquantity());
//              orderdata.put("documentid",showincartdata.getDocumentid());
//        }
//        Log.d("listveda",list.toString());
        orderdata.put("orderlist",list);
        FirebaseFirestore.getInstance().collection("OrdersData").document("ODD").collection("order").add(orderdata).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentReference> task) {
                Toast.makeText(getApplicationContext(), "orderplaced", Toast.LENGTH_SHORT).show();
                Intent lastintent = new Intent(Paymentwindow.this,Reciept.class);
                lastintent.putExtra("transactionid",s);
                startActivity(lastintent);
            }
        });
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
    }
}