package com.example.artybeaconfoodapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Reciept extends AppCompatActivity {
Button continueeating;
TextView transactionid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciept);
        transactionid = findViewById(R.id.transactionid);
        transactionid.setText("Transaction ID : "+getIntent().getStringExtra("transactionid"));
        continueeating = findViewById(R.id.continueeating);
        continueeating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),home.class));
                finish();
            }
        });
    }
}