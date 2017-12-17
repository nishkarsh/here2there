package com.intentfilter.here2there.services.gateways;

import com.intentfilter.here2there.BuildConfig;
import com.intentfilter.here2there.utils.JsonUtil;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class GatewayFactory {
    private final OkHttpClientFactory okHttpClientFactory;

    public GatewayFactory() {
        this.okHttpClientFactory = OkHttpClientFactory.getInstance();
    }

    public TransitServiceGateway transitServiceGateway() {
        return retrofit().create(TransitServiceGateway.class);
    }

    private Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(JsonUtil.objectMapper()))
                .client(okHttpClientFactory.getClient())
                .build();
    }
}
