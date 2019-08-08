package com.example.myapplication.section;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.viewholder.HeaderExpandableViewHolder;

import java.util.ArrayList;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

public class AdvanceSettingSection extends StatelessSection {
    private String title;
    private List<Section> childSection;
    private boolean isExpand = true;
    private SectionedRecyclerViewAdapter sectionedAdapter;

    public AdvanceSettingSection(String title) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.item_header_expandable)
                .build());
        this.title = title;
        initChildSections();
    }

    private void initChildSections() {
        childSection = new ArrayList<>();
        childSection.add(new TypeSection("Loại hình", getListType()));
        childSection.add(new JuridicalSection("Pháp lý", getListJuridical()));
    }

    private List<String> getListType() {
        List<String> listType = new ArrayList<>();
        listType.add("Bất kỳ");
        listType.add("Nhà ở");
        listType.add("Đất");
        listType.add("Căn hộ");
        listType.add("Biệt thự");
        listType.add("Mặt bằng");
        listType.add("Văn phòng");
        listType.add("Đất nền dự án");
        return listType;
    }

    private List<String> getListJuridical() {
        List<String> Juridical = new ArrayList<>();
        Juridical.add("Sổ hồng");
        Juridical.add("Sổ đỏ");
        Juridical.add("Chờ ra sổ");
        Juridical.add("Hợp đồng mua bán");
        return Juridical;
    }

    public void addSection(SectionedRecyclerViewAdapter sectionedAdapter) {
        this.sectionedAdapter = sectionedAdapter;
        sectionedAdapter.addSection(this);
        if (isExpand) {
            for (Section section : childSection) {
                sectionedAdapter.addSection(section);
            }
        }
    }

    @Override
    public int getContentItemsTotal() {
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        HeaderExpandableViewHolder headerViewHolder = new HeaderExpandableViewHolder(view);
        headerViewHolder.itemView.setOnClickListener(view1 -> {
            isExpand = !isExpand;
            if (!isExpand) {
                for (Section section : childSection) {
                    sectionedAdapter.removeSection(section);
                }
            } else {
                int sectionIndex = sectionedAdapter.getSectionIndex(AdvanceSettingSection.this);
                for (int i = 0; i < childSection.size(); i++) {
                    sectionedAdapter.addSection(sectionIndex + i + 1, childSection.get(i));
                }
            }
            sectionedAdapter.notifyDataSetChanged();
        });
        return headerViewHolder;
    }


    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderExpandableViewHolder) {
            ((HeaderExpandableViewHolder) holder).setTitle(title);
            ((HeaderExpandableViewHolder) holder).setExpandable(true);
            ((HeaderExpandableViewHolder) holder).setExpandableDisplay(isExpand);
        }
    }
}
