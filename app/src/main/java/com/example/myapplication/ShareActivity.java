package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ShareActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        findViewById(R.id.btn_share).setOnClickListener(view -> {
//            Intent shareIntent = new Intent(Intent.ACTION_SEND);
//            shareIntent.setType("text/plain");
//            shareIntent.putExtra(Intent.EXTRA_TEXT, "http://play.google.com/store/apps/details?id=pk.nimgade.Bostan.Train.Schedule");
//            List<ResolveInfo> listGel = getPackageManager().queryIntentActivities(shareIntent, 0);
//            ActivityInfo activityInfo = listGel.get(9).activityInfo;
//            shareIntent.setClassName(activityInfo.applicationInfo.packageName,
//                    activityInfo.name);
//            startActivity(shareIntent);
            ShareDialogFragment.show(getSupportFragmentManager());
        });
    }
}
