package com.intentfilter.here2there.utils;

import android.util.Log;

import com.intentfilter.here2there.BuildConfig;

public class Logger {
    private String LOG_TAG;

    private Logger(Class clazz) {
        this.LOG_TAG = clazz.getSimpleName();
    }

    public static Logger loggerFor(Class clazz) {
        return new Logger(clazz);
    }

    public void d(String message) {
        if (loggingEnabled()) {
            Log.d(LOG_TAG, message);
        }
    }

    public void e(String message, Throwable throwable) {
        if (loggingEnabled()) {
            Log.e(LOG_TAG, message, throwable);
        }
    }

    private static boolean loggingEnabled() {
        return BuildConfig.DEBUG;
    }
}