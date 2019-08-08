package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.section.AdvanceSettingSection;
import com.example.myapplication.section.SearchRecentSection;
import com.example.myapplication.section.SearchTopSection;

import java.util.ArrayList;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SectionedRecyclerViewAdapter sectionedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        sectionedAdapter = new SectionedRecyclerViewAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setSpanSizeLookup(new SearchSpanSizeLookup(sectionedAdapter));


        sectionedAdapter.addSection(new SearchTopSection());
        sectionedAdapter.addSection(new SearchRecentSection("Tìm kiếm gần đây", getHistorySearch()));
        AdvanceSettingSection advanceSection = new AdvanceSettingSection("Tùy chọn nâng cao");
        advanceSection.addSection(sectionedAdapter);

        recyclerView.setAdapter(sectionedAdapter);
    }

    public List<String> getHistorySearch() {
        List<String> histories = new ArrayList<>();
        histories.add("Nhà đất Thủ đức cần bán gấp");
        histories.add("Thuê nhà Quận 9");
        histories.add("Căn hộ gần đây");
        histories.add("Biệt thự cổ");
        return histories;
    }
}
