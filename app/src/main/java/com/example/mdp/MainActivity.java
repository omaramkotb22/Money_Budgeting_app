package com.example.mdp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CardView transactionsCardView;
    CardView addTransactionCardView;

    LinearLayout thisMonthNav;

    LinearLayout billsNav;
    LinearLayout settingsNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        transactionsCardView = findViewById(R.id.transactions_cardView);
        addTransactionCardView = findViewById(R.id.add_transaction_card);
        thisMonthNav = findViewById(R.id.this_month_nav);
        billsNav = findViewById(R.id.bills_nav);
        transactionsCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, TransactionsActivity.class);
                        startActivity(intent);
                    }
                });
        addTransactionCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTransactionActivity.class);
                startActivity(intent);
            }
        });
        thisMonthNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThisMonthActivity.class);
                startActivity(intent);
            }
        });
        billsNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BillsActivity.class);
                startActivity(intent);
            }
        });

        settingsNav = findViewById(R.id.nav_item_4);
        settingsNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });






    }





}