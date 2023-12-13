package com.example.mdp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Adapter for the bills and utilities items
public class ThisMonthBillsAdapter extends RecyclerView.Adapter<ThisMonthBillsAdapter.BillsViewHolder> {

    private List<ThisMonthBillsItem> billsItemList;

    // Constructor
    public ThisMonthBillsAdapter(List<ThisMonthBillsItem> billsItemList) {
        this.billsItemList = billsItemList;
    }

    @NonNull
    @Override
    public BillsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bills_and_utilities_items_this_month, parent, false);
        return new BillsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BillsViewHolder holder, int position) {
        ThisMonthBillsItem currentItem = billsItemList.get(position);
        holder.billsTitle.setText(currentItem.getTitle());
        holder.billsAmount.setText(currentItem.getAmount());
    }

    @Override
    public int getItemCount() {
        return billsItemList.size();
    }

    // ViewHolder for each item
    public static class BillsViewHolder extends RecyclerView.ViewHolder {
        public TextView billsTitle;
        public TextView billsAmount;

        public BillsViewHolder(@NonNull View itemView) {
            super(itemView);
            billsTitle = itemView.findViewById(R.id.bills_and_utilities_title);
            billsAmount = itemView.findViewById(R.id.bills_and_utilities_amount);
        }
    }
}
