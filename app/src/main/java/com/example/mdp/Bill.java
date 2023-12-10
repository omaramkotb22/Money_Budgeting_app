package com.example.mdp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Bill extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        RecyclerView recyclerView = findViewById(R.id.bill_recycler_view);
        List<BillItems> items = new ArrayList<BillItems>();
        items.add(new BillItems("John", "John@email.com", R.drawable.user));
        items.add(new BillItems("Jad", "Jad@email.com", R.drawable.user));
        items.add(new BillItems("Hani", "Hani@email.com", R.drawable.user));
        items.add(new BillItems("Kotb", "Kotb@email.com", R.drawable.user));
        items.add(new BillItems("Asdaq", "Asdaq@email.com", R.drawable.user));
        items.add(new BillItems("Desouki", "Desouki@email.com", R.drawable.user));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BillAdapter(this, items));



    }


}