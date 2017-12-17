package com.intentfilter.here2there.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Route {
    @JsonProperty
    private String type;
    @JsonProperty
    private String provider;
    @JsonProperty
    private List<Segment> segments;
    @JsonProperty
    private Price price;
}
