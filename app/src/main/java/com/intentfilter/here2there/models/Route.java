package com.intentfilter.here2there.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Route {
    @JsonProperty
    String type;
    @JsonProperty
    String provider;
    @JsonProperty
    Segments segments;
    @JsonProperty
    Price price;

    public String getProvider() {
        return provider;
    }

    public String getType() {
        return type;
    }

    public Segments getSegments() {
        return segments;
    }

    public int getTravelDurationInMinutes() {
        int travelDuration = 0;
        for (Segment segment : segments) {
            travelDuration += segment.getDurationInMinutes();
        }

        return travelDuration;
    }
}
