package com.xinran.testannotation;

import android.app.Application;

import com.growingio.android.sdk.collection.Configuration;
import com.growingio.android.sdk.collection.GrowingIO;

/**
 * Created by qixinh on 16/4/15.
 */
public class QxApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        GrowingIO.startWithConfiguration(this, new Configuration("995cc8458663d20e")
                .setURLScheme("growing.24504fb53f0ef73d")
                .useID()
                .setChannel("小米应用商店")
                .trackAllFragments());
    }
}
