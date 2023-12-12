package com.example.mdp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BillsAdapter extends RecyclerView.Adapter<BillsViewHolder>{
    private List<BillsItems> billsList;

    public BillsAdapter(List<BillsItems> billList) {
        this.billsList = billList;
    }

    @NonNull
    @Override
    public BillsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bills_items, parent, false);
        return new BillsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BillsViewHolder holder, int position) {
        BillsItems billItem = billsList.get(position);
        holder.titleTextView.setText(billItem.getTitle());
        holder.amountTextView.setText(billItem.getTotalBillAmount());
        holder.labelTextView.setText(billItem.getBillLabel());
    }

    @Override
    public int getItemCount() {
        return billsList.size();
    }
}
