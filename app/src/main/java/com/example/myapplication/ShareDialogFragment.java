package com.example.myapplication;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class ShareDialogFragment extends BottomSheetDialogFragment {

    private RecyclerView rcvApp;
    private ShareAppAdapter shareAppAdapter;

    public static void show(FragmentManager fragmentManager) {
        ShareDialogFragment shareDialogFragment = new ShareDialogFragment();
        shareDialogFragment.show(fragmentManager, "ShareDialogFragment");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_share_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvApp = view.findViewById(R.id.rcv_app);
        rcvApp.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        shareAppAdapter = new ShareAppAdapter(getResolveInfo());
        rcvApp.setAdapter(shareAppAdapter);
        GravitySnapHelper.apply(rcvApp, Gravity.START);
    }

    private List<ResolveInfo> getResolveInfo() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "http://play.google.com/store/apps/details?id=pk.nimgade.Bostan.Train.Schedule");
        return getActivity().getPackageManager().queryIntentActivities(shareIntent, 0);
    }
}
