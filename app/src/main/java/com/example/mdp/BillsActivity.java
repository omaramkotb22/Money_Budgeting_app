package com.example.mdp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

public class BillsActivity extends AppCompatActivity {
    CardView addBillCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills);
        addBillCardView = findViewById(R.id.add_bill_card);
        addBillCardView.setOnClickListener(view -> {
            Intent intent = new Intent(BillsActivity.this, AddBillActivity.class);
            startActivity(intent);
        });


    }
}