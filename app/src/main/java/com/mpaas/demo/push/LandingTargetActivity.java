package com.mpaas.demo.push;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.alipay.pushsdk.data.MPPushMsg;
import com.mpaas.demo.R;

public class LandingTargetActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        Intent intent = getIntent();
        if (intent != null) {
            String uri = intent.getStringExtra("uri");
            MPPushMsg msg = intent.getParcelableExtra("mp_push_msg");
            ((TextView) findViewById(R.id.uri)).setText(uri);
            ((TextView) findViewById(R.id.msg)).setText(msg.toString());
        }
    }
}
