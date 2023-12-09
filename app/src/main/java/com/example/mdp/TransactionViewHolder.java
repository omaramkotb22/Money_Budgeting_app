package com.example.mdp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class TransactionViewHolder extends RecyclerView.ViewHolder {

    public ImageView icon;
    public TextView transactionName;
    public TextView transactionAmount;
    public TextView transactionDate;
    public View layout;

    public TransactionViewHolder(View itemView) {
        super(itemView);
        icon = itemView.findViewById(R.id.TransactionIcon); // Corrected the ID
        transactionName = itemView.findViewById(R.id.tvTransactionName);
        transactionAmount = itemView.findViewById(R.id.tvTransactionAmount);
        transactionDate = itemView.findViewById(R.id.tvTransactionDate);
        layout = itemView.findViewById(R.id.llTransactionItem); // The layout for the click listener
    }
}