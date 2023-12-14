package com.example.mdp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

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

    LinearLayout mainActivity;

    LinearLayout thisMonthActivity;

    LinearLayout billsActivity;

    LinearLayout settingsActivity;
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

        mainActivity = findViewById(R.id.nav_item_1);
        mainActivity.setOnClickListener(view -> {
            Intent intent = new Intent(BillsActivity.this, MainActivity.class);
            startActivity(intent);
        });

        thisMonthActivity = findViewById(R.id.this_month_nav);
        thisMonthActivity.setOnClickListener(view -> {
            Intent intent = new Intent(BillsActivity.this, ThisMonthActivity.class);
            startActivity(intent);
        });

        billsActivity = findViewById(R.id.bills_nav);
        billsActivity.setOnClickListener(view -> {
            Intent intent = new Intent(BillsActivity.this, BillsActivity.class);
            startActivity(intent);
        });

        settingsActivity = findViewById(R.id.nav_item_4);
        settingsActivity.setOnClickListener(view -> {
            Intent intent = new Intent(BillsActivity.this, SettingsActivity.class);
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