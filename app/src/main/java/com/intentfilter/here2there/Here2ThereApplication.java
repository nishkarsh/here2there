package com.intentfilter.here2there;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

public class Here2ThereApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
    }
}
