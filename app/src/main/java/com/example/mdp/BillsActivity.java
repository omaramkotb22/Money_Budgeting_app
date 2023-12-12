package com.example.mdp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BillsActivity extends AppCompatActivity {
    CardView addBillCardView;
    RecyclerView recyclerViewBills;
    BillsAdapter billsAdapter;
    List<BillsItems> billsItemsList;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills);
        recyclerViewBills = findViewById(R.id.recycler_view_bills);
        recyclerViewBills.setLayoutManager(new LinearLayoutManager(this));

        billsItemsList = new ArrayList<>();
        billsAdapter = new BillsAdapter(billsItemsList);
        recyclerViewBills.setAdapter(billsAdapter);

        db = FirebaseFirestore.getInstance();
        fetchBills();
        addBillCardView = findViewById(R.id.add_bill_card);
        addBillCardView.setOnClickListener(view -> {
            Intent intent = new Intent(BillsActivity.this, AddBillActivity.class);
            startActivity(intent);
        });


    }
    private void fetchBills() {
        String currentUserId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        db.collection("Bills")
                .whereEqualTo("bill_creator", currentUserId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String title = document.getString("title");
                            String totalBillAmount = document.getString("total_bill_amount");
                            String billLabel = document.getString("bill_label");
                            BillsItems billItem = new BillsItems(title, totalBillAmount, billLabel);
                            billsItemsList.add(billItem);
                        }
                        billsAdapter.notifyDataSetChanged();
                    } else {
                        Log.e("BillsActivity", "Error getting documents: ", task.getException());
                    }
                });
    }
}