package com.intentfilter.here2there.services;

import com.intentfilter.here2there.services.gateways.GatewayFactory;
import com.intentfilter.here2there.services.gateways.TransitServiceGateway;

public class TransitService {

    private final TransitServiceGateway gateway;

    public TransitService(GatewayFactory gatewayFactory) {
        gateway = gatewayFactory.transitServiceGateway();
    }
}
