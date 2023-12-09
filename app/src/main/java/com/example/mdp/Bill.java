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
        items.add(new BillItems("John", "John@email.com", R.drawable.plus));
        items.add(new BillItems("John", "John@email.com", R.drawable.plus));
        items.add(new BillItems("John", "John@email.com", R.drawable.plus));
        items.add(new BillItems("John", "John@email.com", R.drawable.plus));
        items.add(new BillItems("John", "John@email.com", R.drawable.plus));
        items.add(new BillItems("John", "John@email.com", R.drawable.plus));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BillAdapter(this, items));



    }


}