package com.intentfilter.here2there.services;

import com.intentfilter.here2there.models.ServiceResponse;
import com.intentfilter.here2there.services.gateways.GatewayFactory;
import com.intentfilter.here2there.services.gateways.TransitServiceGateway;

import retrofit2.Call;
import retrofit2.Callback;

public class TransitService {

    private final TransitServiceGateway gateway;

    public TransitService(GatewayFactory gatewayFactory) {
        gateway = gatewayFactory.transitServiceGateway();
    }

    public void getRoutes(Callback<ServiceResponse> callback) {
        Call<ServiceResponse> call = gateway.getRoutes();
        call.enqueue(callback);
    }
}
