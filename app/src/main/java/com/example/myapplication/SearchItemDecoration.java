package com.example.myapplication;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.section.TypeSection;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class SearchItemDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = "SearchItemDecoration";
    private SectionedRecyclerViewAdapter adapter;

    public SearchItemDecoration(SectionedRecyclerViewAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int spanCount = ((GridLayoutManager) parent.getLayoutManager()).getSpanCount();
        int position = parent.getChildAdapterPosition(view);
        Section section = adapter.getSectionForPosition(position);
        if (section instanceof TypeSection) {
            int viewType = adapter.getSectionItemViewType(position);
            if (viewType == SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED) {
                int spacing = 30;
                int positionInSection = adapter.getPositionInSection(position);
                int column = positionInSection % spanCount; // item column
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            }
        }
    }
}
