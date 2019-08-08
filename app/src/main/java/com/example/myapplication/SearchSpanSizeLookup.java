package com.example.myapplication;

import androidx.recyclerview.widget.GridLayoutManager;

import com.example.myapplication.section.TypeSection;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class SearchSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
    private static final int SPAN_COUNT = 3;
    private SectionedRecyclerViewAdapter sectionedAdapter;

    public SearchSpanSizeLookup(SectionedRecyclerViewAdapter sectionedAdapter) {
        this.sectionedAdapter = sectionedAdapter;
    }

    @Override
    public int getSpanSize(int position) {
        int sectionItemViewType = sectionedAdapter.getSectionItemViewType(position);
        boolean isHeader = sectionItemViewType
                == SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER;
        boolean isFooter = sectionItemViewType
                == SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER;
        if (isHeader || isFooter) {
            return SPAN_COUNT;
        }

        Section section = sectionedAdapter.getSectionForPosition(position);
        if (section instanceof TypeSection) {
            return 1;
        }
        return SPAN_COUNT;
    }
}
