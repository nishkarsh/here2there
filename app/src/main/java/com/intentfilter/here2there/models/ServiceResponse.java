package com.intentfilter.here2there.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ServiceResponse {
    @JsonProperty
    private List<Route> routes;
}
