package com.intentfilter.here2there.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServiceResponse {
    @JsonProperty
    private Routes routes;
    @JsonProperty("provider_attributes")
    private Providers providers;

    public Routes getRoutes() {
        return routes;
    }

    public Providers getProviders() {
        return providers;
    }
}
