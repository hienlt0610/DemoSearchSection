package com.example.myapplication.section;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.viewholder.HeaderExpandableViewHolder;

import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

public class SearchRecentSection extends StatelessSection {

    private String title;
    private List<String> historys;

    /**
     * Create a stateless Section object based on {@link SectionParameters}.
     */
    public SearchRecentSection(String title, List<String> historys) {
        super(SectionParameters.builder()
                .headerResourceId(R.layout.item_header_expandable)
                .itemResourceId(R.layout.item_search_history)
                .build());
        this.title = title;
        this.historys = historys;
    }

    @Override
    public int getContentItemsTotal() {
        return historys.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemSearchHistoryViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderExpandableViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemSearchHistoryViewHolder) {
            ((ItemSearchHistoryViewHolder) holder).bind(historys.get(position));
        }
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        if (holder instanceof HeaderExpandableViewHolder) {
            ((HeaderExpandableViewHolder) holder).setTitle(title);
            ((HeaderExpandableViewHolder) holder).setExpandable(false);
        }
    }

    public class ItemSearchHistoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public ItemSearchHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_history_name);
        }

        public void bind(String name) {
            tvName.setText(name);
        }
    }
}
