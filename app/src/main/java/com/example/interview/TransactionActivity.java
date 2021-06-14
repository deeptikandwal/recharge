package com.example.interview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class TransactionActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView toolbar_title;
    ImageView img;
    Transaction transaction;
    TextView tv_txn,tv_phone,tv_amount,tv_type,tv_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        tv_txn=findViewById(R.id.tv_txn);
        tv_phone=findViewById(R.id.tv_phone);
        tv_amount=findViewById(R.id.tv_amount);
        tv_type=findViewById(R.id.tv_type);
        tv_message=findViewById(R.id.tv_message);
        toolbar_title=findViewById(R.id.toolbar_title);
        img = findViewById(R.id.back_arrow);

        toolbar_title.setText("Transaction Receipt");

        img.setOnClickListener((v)->{
            onBackPressed();
        });
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            toolbar.setTitle("Recharge");
        }
        if(getIntent()!=null){
           transaction=(Transaction) getIntent().getSerializableExtra("transaction") ;

            tv_txn.setText(transaction.getTxnid());
            tv_phone.setText(transaction.getMobile());
            tv_amount.setText(transaction.getAmount());
            tv_type.setText("MOBILE RECHARGE");
            tv_message.setText(transaction.getMessage());
        }
    }
}