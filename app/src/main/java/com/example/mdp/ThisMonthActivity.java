package com.example.mdp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class ThisMonthActivity extends AppCompatActivity {

    private RecyclerView foodrecyclerView;
    private RecyclerView billsrecyclerView;
    private FoodAdapter foodAdapter;
    private ThisMonthBillsAdapter billsAdapter;
    private List<FoodItem> foodItemList;
    private List<ThisMonthBillsItem> billsItemList;

    private RecyclerView transportationRecyclerView;
    private ThisMonthTransportationAdapter transportationAdapter;
    private List<ThisMonthTransportationItem> transportationItemList;

    LinearLayout mainActivity;
    LinearLayout thisMonthActivity;
    LinearLayout billsActivity;
    LinearLayout settingsActivity;


    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView remainder_text;
    TextView dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_this_month);
        remainder_text = findViewById(R.id.remainder);
        String currentUser = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        db.collection("users").document(currentUser).get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null && task.getResult().exists()) {
                String remainderString = task.getResult().getString("remainder");
                String totalString = task.getResult().getString("monthly_budget");

                if (remainderString != null && totalString != null) {
                    try {
                        double remainder = Double.parseDouble(remainderString.trim());
                        double total = Double.parseDouble(totalString.trim());
                        remainder_text.setText("MYR " + (total - remainder));
                    } catch (NumberFormatException e) {
                        Log.e("TAG", "Parsing error", e);
                        remainder_text.setText("MYR -");
                        Toast.makeText(this, "Failed to get amount from the database", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Handle the case where either "remainder" or "total" is null
                    Log.w("TAG", "One of the required fields is missing");
                    remainder_text.setText("MYR -");
                    Toast.makeText(this, "Failed to get amount from the database", Toast.LENGTH_SHORT).show();
                }
            } else {
                Log.w("TAG", "Error getting user document: ", task.getException());
                remainder_text.setText("MYR -");
                Toast.makeText(this, "Failed to get amount from the database", Toast.LENGTH_SHORT).show();
            }
        });
        dateText = findViewById(R.id.date);
        String todayDate = new SimpleDateFormat("dd, MMMM yyyy", Locale.getDefault()).format(new Date());
        dateText.setText(todayDate);
        loadTransactionsForUser(currentUser);
        foodrecyclerView = findViewById(R.id.food_recycler_view);
        foodrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        foodItemList = new ArrayList<>();

        foodAdapter = new FoodAdapter(foodItemList);
        foodrecyclerView.setAdapter(foodAdapter);

        billsrecyclerView = findViewById(R.id.bills_and_utilities_recycler_view);
        billsrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        billsItemList = new ArrayList<>();

        billsAdapter = new ThisMonthBillsAdapter(billsItemList);
        billsrecyclerView.setAdapter(billsAdapter);


        transportationRecyclerView = findViewById(R.id.transportation_recycler_view);
        transportationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        transportationItemList = new ArrayList<>();
        transportationAdapter = new ThisMonthTransportationAdapter(transportationItemList);
        transportationRecyclerView.setAdapter(transportationAdapter);


        mainActivity = findViewById(R.id.nav_item_1);
        mainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThisMonthActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        thisMonthActivity = findViewById(R.id.this_month_nav);
        thisMonthActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThisMonthActivity.this, ThisMonthActivity.class);
                startActivity(intent);
            }
        });
        billsActivity = findViewById(R.id.bills_nav);
        billsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThisMonthActivity.this, BillsActivity.class);
                startActivity(intent);
            }
        });
        settingsActivity = findViewById(R.id.nav_item_4);
        settingsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThisMonthActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });


    }

    private void loadTransactionsForUser(String userId) {

        db.collection("Transactions")
                .whereEqualTo("user", userId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String name = document.getString("name");
                            double amount = document.getDouble("amount");
                            String category = document.getString("category");
                            switch (Objects.requireNonNull(category)) {
                                case "Food and Restaurants":

                                    foodItemList.add(new FoodItem(name, "MYR " + amount));
                                    break;
                                case "Bills & Utilities":
                                    billsItemList.add(new ThisMonthBillsItem(name, "MYR " + amount));

                                    break;
                                case "Transportation":
                                    transportationItemList.add(new ThisMonthTransportationItem(name, "MYR " + amount));
                                    break;
                            }
                        }

                        foodAdapter.notifyDataSetChanged();
                        billsAdapter.notifyDataSetChanged();
                        transportationAdapter.notifyDataSetChanged();
                    } else {
                        Log.w("TAG", "Error getting documents: ", task.getException());
                    }
                });
    }

}