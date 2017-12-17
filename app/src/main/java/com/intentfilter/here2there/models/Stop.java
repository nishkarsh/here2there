package com.intentfilter.here2there.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.joda.time.DateTime;

public class Stop {
    @JsonProperty("lat")
    private double latitude;
    @JsonProperty("lng")
    private double longitude;
    @JsonProperty
    private DateTime datetime;
    @JsonProperty
    private String name;
}
