package com.example.mdp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionViewHolder> {

    private List<Transaction> transactions;

    public TransactionAdapter(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transaction transaction = transactions.get(position);
        holder.transactionName.setText(transaction.getName());
        holder.transactionAmount.setText(String.valueOf(transaction.getAmount()));
        holder.transactionDate.setText(transaction.getDate());
        holder.transactionDate.setVisibility(transaction.isExpanded() ? View.VISIBLE : View.GONE);

        holder.layout.setOnClickListener(view -> {
            boolean expanded = transaction.isExpanded();
            transaction.setExpanded(!expanded);
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }
}
