package com.example.myapplication.section;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.viewholder.HeaderFilterViewHolder;

import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

public class TypeSection extends StatelessSection {

    private String title;
    private List<String> listType;

    /**
     * Create a stateless Section object based on {@link SectionParameters}.
     */
    public TypeSection(String title, List<String> listType) {
        super(SectionParameters.builder()
                .headerResourceId(R.layout.item_header_filter)
                .itemResourceId(R.layout.item_type)
                .build());
        this.title = title;
        this.listType = listType;
    }

    @Override
    public int getContentItemsTotal() {
        return listType.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemTypeViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderFilterViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemTypeViewHolder) {
            ((ItemTypeViewHolder) holder).bind(listType.get(position));
        }
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        if (holder instanceof HeaderFilterViewHolder) {
            ((HeaderFilterViewHolder) holder).setTitle(title);
        }
    }

    public class ItemTypeViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public ItemTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_type_name);
        }

        public void bind(String name) {
            tvName.setText(name);
        }
    }
}
