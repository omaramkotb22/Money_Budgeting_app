package com.example.mdp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<Double> balance = new MutableLiveData<>();
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public LiveData<Double> getBalance() {
        return balance;
    }

    public void fetchBalance() {
        String currentUser = mAuth.getCurrentUser() != null ? mAuth.getCurrentUser().getUid() : "";
        db.collection("users").document(currentUser).get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null && task.getResult().exists()) {
                String remainderString = task.getResult().getString("remainder");
                try {
                    double remainder = Double.parseDouble(remainderString.trim());
                    balance.setValue(remainder);
                } catch (NumberFormatException e) {
                    balance.setValue(null);
                }
            } else {
                balance.setValue(null);
            }
        });
    }
}
