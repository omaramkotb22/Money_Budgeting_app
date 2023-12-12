package com.example.mdp;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class BillsViewHolder extends RecyclerView.ViewHolder {
    TextView titleTextView;
    TextView amountTextView;
    TextView labelTextView;

    public BillsViewHolder(View itemView) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.titleTextView);
        amountTextView = itemView.findViewById(R.id.amountTextView);
        labelTextView = itemView.findViewById(R.id.labelTextView);
    }
}