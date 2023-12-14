package com.example.mdp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddTransactionActivity extends AppCompatActivity {
    Button nextButton;
    EditText amountInput;
    EditText transactionTitleInput;
    Spinner expenseSpinner;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        // Initialize the input fields
        nextButton = findViewById(R.id.nextButton);
        amountInput = findViewById(R.id.amountInput);
        transactionTitleInput = findViewById(R.id.transactionTitleInput);
        expenseSpinner = findViewById(R.id.expenseSpinner);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the data from the input fields
                String transactionTitle = transactionTitleInput.getText().toString();
                String expenseCategory = expenseSpinner.getSelectedItem().toString();
                double amount = Double.parseDouble(amountInput.getText().toString());
                String currentUser = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                db.collection("users").document(currentUser).get().addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        DocumentSnapshot snapshot = task.getResult();
                        if (snapshot.exists()) {
                            Object balanceObject = snapshot.get("balance");
                            double balance;

                            // Convert balance to double if it's a String
                            if (balanceObject instanceof String) {
                                try {
                                    balance = Double.parseDouble((String) balanceObject);
                                } catch (NumberFormatException e) {
                                    Log.e("TAG", "Parsing balance failed", e);
                                    return; // Exit if parsing fails
                                }
                                // If it's already a Double, just use the value
                            } else if (balanceObject instanceof Double) {
                                balance = (Double) balanceObject;
                            } else {
                                Log.d("TAG", "Balance is null or not a recognized format");
                                return; // Exit if balance is null or not a recognized format
                            }

                            // Update the document with the new balance
                            DocumentReference docRef = db.collection("users").document(currentUser);
                            docRef.update("balance", balance - amount)
                                    .addOnSuccessListener(aVoid -> Log.d("TAG", "Balance successfully updated"))
                                    .addOnFailureListener(e -> Log.e("TAG", "Error updating balance", e));

                        } else {
                            Log.d("TAG", "Document does not exist");
                        }
                    } else {
                        Log.e("TAG", "Error getting documents: ", task.getException());
                    }
                });

                Map<String, Object> transaction = new HashMap<>();
                transaction.put("amount", amount);
                transaction.put("name", transactionTitle);
                transaction.put("category", expenseCategory);
                transaction.put("date", Timestamp.now());
                transaction.put("user", currentUser);
                db.collection("Transactions")
                        .add(transaction)
                        .addOnSuccessListener(new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {
                                Toast.makeText(AddTransactionActivity.this, "Transaction added successfully!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                String error = e.getMessage();
                                Toast.makeText(AddTransactionActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                            }});}});}}
