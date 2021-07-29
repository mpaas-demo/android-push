package com.mpaas.demo.push;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.mpaas.demo.MyApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PLog {

    private static final String TAG = "MyPushTest";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");

    public static void d(String msg) {
        Log.d(TAG, msg);
        sendBroadcast(msg);
    }

    public static void e(String msg) {
        Log.e(TAG, msg);
        sendBroadcast(msg);
    }

    public static void e(String msg, Throwable t) {
        Log.e(TAG, msg, t);
    }

    private static void sendBroadcast(String msg) {
        msg = sdf.format(new Date(System.currentTimeMillis())) + "  " + msg;

        Intent intent = new Intent(MainActivity.ACTION_P_LOG);
        intent.putExtra(MainActivity.KEY_LOG, msg);
        LocalBroadcastManager.getInstance(MyApplication.app).sendBroadcast(intent);
    }
}
