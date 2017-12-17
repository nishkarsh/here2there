package com.intentfilter.here2there.services.gateways;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

class OkHttpClientFactory {
    private static OkHttpClientFactory okHttpClientFactory;
    private OkHttpClient okHttpClient;

    private OkHttpClientFactory() {
        //To avoid public instantiation of this class
    }

    static OkHttpClientFactory getInstance() {
        if (okHttpClientFactory == null) {
            okHttpClientFactory = new OkHttpClientFactory();
        }

        return okHttpClientFactory;
    }

    OkHttpClient getClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .build();
        }

        return okHttpClient;
    }
}
