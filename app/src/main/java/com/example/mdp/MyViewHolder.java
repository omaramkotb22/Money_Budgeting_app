package com.example.mdp;

import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;


import androidx.annotation.NonNull;

public class MyViewHolder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView name, email;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.userImageView);
        name = itemView.findViewById(R.id.userNameTextView);
        email = itemView.findViewById(R.id.userEmailTextView);
    }


}
