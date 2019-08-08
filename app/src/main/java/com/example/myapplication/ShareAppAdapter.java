package com.example.myapplication;

import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShareAppAdapter extends RecyclerView.Adapter<ShareAppAdapter.ViewHolder> {

    private List<ResolveInfo> apps;
    private LayoutInflater layoutInflater;

    public ShareAppAdapter(List<ResolveInfo> apps) {
        this.apps = apps;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) layoutInflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(layoutInflater.inflate(R.layout.item_app, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResolveInfo resolveInfo = apps.get(position);
        PackageManager packageManager = holder.imgIcon.getContext().getPackageManager();
        holder.tvName.setText(resolveInfo.loadLabel(packageManager));
        holder.imgIcon.setImageDrawable(resolveInfo.loadIcon(packageManager));
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgIcon;
        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.img_app_icon);
            tvName = itemView.findViewById(R.id.tv_app_name);
        }
    }
}
