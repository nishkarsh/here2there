package com.intentfilter.here2there.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Segment {
    @JsonProperty
    private String name;
    @JsonProperty
    private int numStops;
    @JsonProperty
    private List<Stop> stops;
    @JsonProperty
    private String travelMode;
    @JsonProperty
    private String description;
    @JsonProperty
    private String color;
    @JsonProperty
    private String iconUrl;
    @JsonProperty
    private String polyline;
}
