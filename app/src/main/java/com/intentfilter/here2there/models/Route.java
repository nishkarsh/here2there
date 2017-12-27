package com.intentfilter.here2there.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class Route {
    @JsonProperty
    String type;
    @JsonProperty
    String provider;
    @JsonProperty
    List<Segment> segments;
    @JsonProperty
    Price price;

    public String getProvider() {
        return provider;
    }

    public String getType() {
        return type;
    }

    public List<Segment> getSegments() {
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
