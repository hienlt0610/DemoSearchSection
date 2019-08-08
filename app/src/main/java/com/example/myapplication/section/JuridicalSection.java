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

public class JuridicalSection extends StatelessSection {

    private String title;
    private List<String> listJuridical;

    /**
     * Create a stateless Section object based on {@link SectionParameters}.
     */
    public JuridicalSection(String title, List<String> listJuridical) {
        super(SectionParameters.builder()
                .headerResourceId(R.layout.item_header_filter)
                .itemResourceId(R.layout.item_juridical)
                .build());
        this.title = title;
        this.listJuridical = listJuridical;
    }

    @Override
    public int getContentItemsTotal() {
        return listJuridical.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemJuridicalViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderFilterViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemJuridicalViewHolder) {
            ((ItemJuridicalViewHolder) holder).bind(listJuridical.get(position));
        }
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        if (holder instanceof HeaderFilterViewHolder) {
            ((HeaderFilterViewHolder) holder).setTitle(title);
        }
    }

    public class ItemJuridicalViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public ItemJuridicalViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_juridical_name);
        }

        public void bind(String name) {
            tvName.setText(name);
        }
    }
}
