package com.example.mdp;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class ThisMonthBillsViewHolder extends RecyclerView.ViewHolder {
    public TextView billsTitle;
    public TextView billsAmount;

    public ThisMonthBillsViewHolder(View itemView) {
        super(itemView);
        billsTitle = itemView.findViewById(R.id.bills_and_utilities_title);
        billsAmount = itemView.findViewById(R.id.bills_and_utilities_amount);
    }
}