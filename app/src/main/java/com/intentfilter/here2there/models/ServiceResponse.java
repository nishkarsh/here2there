package com.intentfilter.here2there.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServiceResponse {
    @JsonProperty
    private Routes routes;

    public Routes getRoutes() {
        return routes;
    }
}
