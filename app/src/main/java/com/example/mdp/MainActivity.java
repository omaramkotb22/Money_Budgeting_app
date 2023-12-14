package com.example.mdp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private CardView transactionsCardView;
    private CardView addTransactionCardView;
    private LinearLayout thisMonthNav;
    private LinearLayout billsNav;
    private LinearLayout settingsNav;
    private TextView balanceTextView;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        transactionsCardView = findViewById(R.id.transactions_cardView);
        addTransactionCardView = findViewById(R.id.add_transaction_card);
        thisMonthNav = findViewById(R.id.this_month_nav);
        billsNav = findViewById(R.id.bills_nav);
        settingsNav = findViewById(R.id.nav_item_4);
        balanceTextView = findViewById(R.id.total_balance_text);

        // Initialize ViewModel
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Observing balance changes
        mainViewModel.getBalance().observe(this, balance -> {
            if (balance != null) {
                balanceTextView.setText("MYR " + balance);
            } else {
                balanceTextView.setText("MYR -");
                Toast.makeText(this, "Failed to get amount from the database", Toast.LENGTH_SHORT).show();
            }
        });

        // Fetch balance
        mainViewModel.fetchBalance();

        // Set up onClickListeners
        setUpClickListeners();
    }

    private void setUpClickListeners() {
        transactionsCardView.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, TransactionsActivity.class);
            startActivity(intent);
        });

        addTransactionCardView.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddTransactionActivity.class);
            startActivity(intent);
        });

        thisMonthNav.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ThisMonthActivity.class);
            startActivity(intent);
        });

        billsNav.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, BillsActivity.class);
            startActivity(intent);
        });

        settingsNav.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
    }
}
