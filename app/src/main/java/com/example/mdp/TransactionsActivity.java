package com.example.mdp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class TransactionsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TransactionAdapter adapter;
    private List<Transaction> transactionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.transactions_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the transaction list and fill it with data
        transactionList = new ArrayList<>();
        // TODO: Fetch transactions from Firebase and populate the list

        // Initialize the adapter and attach it to the RecyclerView
        adapter = new TransactionAdapter(transactionList);
        transactionList.add(new Transaction("Halab", 100, "12/12/2020"));
        transactionList.add(new Transaction("Socar", 200, "12/12/2020"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

}