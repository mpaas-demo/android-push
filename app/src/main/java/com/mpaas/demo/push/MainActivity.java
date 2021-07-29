package com.mpaas.demo.push;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mpaas.demo.R;
import com.mpaas.mps.adapter.api.MPPush;


public class MainActivity extends Activity {

    public static final String ACTION_P_LOG = "com.mpaas.demo.plog";
    public static final String KEY_LOG = "key_log";
    public static String userId = "mpaas_push_demo";
    private TextView mTvLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        registerReceiverToPrintPushLog();

        MPPush.init(this);
    }

    private void initView() {
        mTvLog = findViewById(R.id.tv_log);
        mTvLog.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String log = mTvLog.getText().toString();
                if (!TextUtils.isEmpty(log)) {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText(null, log);
                    clipboard.setPrimaryClip(clipData);
                    Toast.makeText(MainActivity.this, "日志已复制", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
    }

    private void registerReceiverToPrintPushLog() {
        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, final Intent intent) {
                if (!ACTION_P_LOG.equals(intent.getAction())) {
                    return;
                }
                String log = intent.getStringExtra(KEY_LOG);
                String text = mTvLog.getText().toString();
                if (TextUtils.isEmpty(text)) {
                    text = log;
                } else {
                    text = text + "\n" + log;
                }
                mTvLog.setText(text);
            }
        }, new IntentFilter(ACTION_P_LOG));
    }
}
