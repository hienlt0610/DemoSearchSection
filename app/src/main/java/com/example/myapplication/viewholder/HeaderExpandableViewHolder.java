package com.example.myapplication.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class HeaderExpandableViewHolder extends RecyclerView.ViewHolder {
    private TextView tvTitle;
    private ImageView imgExpandable;

    public HeaderExpandableViewHolder(@NonNull View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tv_title);
        imgExpandable = itemView.findViewById(R.id.img_expandable);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setExpandable(boolean isExpandable) {
        imgExpandable.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
    }

    public void setExpandableDisplay(boolean isExpandable) {
        imgExpandable.setScaleY(isExpandable ? -1 : 1);
    }
}
