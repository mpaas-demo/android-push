package com.mpaas.demo;

import android.app.Application;
import android.content.Context;

import com.mpaas.demo.push.LauncherActivity;
import com.mpaas.mps.adapter.api.MPPush;


public class MyApplication extends Application {

    public static Application app;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        app = this;
        MPPush.setup(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MPPush.setBadgeActivityClassName(this, LauncherActivity.class.getName());
        MPPush.setBadgeAutoClearEnabled(this, true);
        MPPush.clearBadges(this);
    }
}
