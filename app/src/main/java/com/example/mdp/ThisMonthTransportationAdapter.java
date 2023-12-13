package com.example.mdp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ThisMonthTransportationAdapter extends RecyclerView.Adapter<ThisMonthTransportationAdapter.ThisMonthTransportationViewHolder> {


    private List<ThisMonthTransportationItem> transportationItemList;
    public ThisMonthTransportationAdapter(List<ThisMonthTransportationItem> transportationItemList) {
        this.transportationItemList = transportationItemList;
    }

    @NonNull
    @Override
    public ThisMonthTransportationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.transportation_items_this_month, parent, false);
        return new ThisMonthTransportationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ThisMonthTransportationViewHolder holder, int position) {
        ThisMonthTransportationItem currentItem = transportationItemList.get(position);
        holder.transportationTitle.setText(currentItem.getTitle());
        holder.transportationAmount.setText(currentItem.getAmount());
    }

    @Override
    public int getItemCount() {
        return transportationItemList.size();
    }
    public static class ThisMonthTransportationViewHolder extends RecyclerView.ViewHolder {
        public android.widget.TextView transportationTitle;
        public android.widget.TextView transportationAmount;

        public ThisMonthTransportationViewHolder(@NonNull View itemView) {
            super(itemView);
            transportationTitle = itemView.findViewById(R.id.transport_title);
            transportationAmount = itemView.findViewById(R.id.transport_amount);
        }
    }

}
