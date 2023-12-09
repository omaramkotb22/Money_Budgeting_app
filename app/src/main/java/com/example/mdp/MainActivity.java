package com.example.mdp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CardView transactionsCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        transactionsCardView = findViewById(R.id.transactions_cardView);
        transactionsCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, TransactionsActivity.class);
                        startActivity(intent);
                    }
                });


    }

    public void onCLick(View view){
        Intent intent = new Intent(MainActivity.this, Bill.class);
        startActivity(intent);
    }

}