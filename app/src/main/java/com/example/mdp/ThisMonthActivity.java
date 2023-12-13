package com.example.mdp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
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


    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_this_month);
        loadTransactionsForUser(Objects.requireNonNull(mAuth.getCurrentUser()).getUid());
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