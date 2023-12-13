package com.example.mdp;

import androidx.recyclerview.widget.RecyclerView;

public class ThisMonthTransportationViewHolder extends RecyclerView.ViewHolder{

    public android.widget.TextView transportationTitle;
    public android.widget.TextView transportationAmount;

    public ThisMonthTransportationViewHolder(android.view.View itemView) {
        super(itemView);
        transportationTitle = itemView.findViewById(R.id.transport_title);
        transportationAmount = itemView.findViewById(R.id.transport_amount);
    }
}
