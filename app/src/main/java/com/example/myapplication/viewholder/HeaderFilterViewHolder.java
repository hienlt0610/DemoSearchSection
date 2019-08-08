package com.example.myapplication.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class HeaderFilterViewHolder extends RecyclerView.ViewHolder {
    private TextView tvTitle;

    public HeaderFilterViewHolder(@NonNull View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tv_title);
    }

    public void setTitle(String title) {
        tvTitle = itemView.findViewById(R.id.tv_title);
    }
}
