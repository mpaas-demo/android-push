package com.mpaas.demo.push;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.mpaas.demo.R;

public class LauncherActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        findViewById(R.id.demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        handleIntent(getIntent());
        setIntent(null);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
        setIntent(null);
    }

    private void handleIntent(Intent intent) {
        if (intent == null) {
            return ;
        }
        Uri uri = intent.getData();
        if (uri == null) {
            return ;
        }
        String params = intent.getStringExtra("data");
        Intent targetIntent = new Intent(LauncherActivity.this, LandingTargetActivity.class);
        targetIntent.putExtra("uri", uri.toString());
        targetIntent.putExtra("data", params);
        startActivity(targetIntent);
    }
}
