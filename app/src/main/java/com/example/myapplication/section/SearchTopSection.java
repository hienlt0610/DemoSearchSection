package com.example.myapplication.section;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

public class SearchTopSection extends StatelessSection {

    public SearchTopSection() {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.item_search_top)
                .build());
    }

    @Override
    public int getContentItemsTotal() {
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new RecyclerView.ViewHolder(view) {
            @Override
            public String toString() {
                return super.toString();
            }
        };
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}
