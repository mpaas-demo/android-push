package com.mpaas.demo;

import android.app.Application;
import android.content.Context;

import com.mpaas.mps.adapter.api.MPPush;


public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MPPush.setup(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
