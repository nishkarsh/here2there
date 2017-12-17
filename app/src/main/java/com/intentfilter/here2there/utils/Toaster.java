package com.intentfilter.here2there.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

public class Toaster {
    private Context context;

    public Toaster(Context context) {
        this.context = context;
    }

    public void toast(@StringRes int messageResId) {
        Toast.makeText(context, context.getString(messageResId), Toast.LENGTH_SHORT).show();
    }
}