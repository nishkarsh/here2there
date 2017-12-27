package com.intentfilter.here2there.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.parceler.Parcel;

import java.util.List;

@Parcel
public class Segment {
    @JsonProperty
    String name;
    @JsonProperty
    int numStops;
    @JsonProperty
    List<Stop> stops;
    @JsonProperty
    String travelMode;
    @JsonProperty
    String description;
    @JsonProperty
    String color;
    @JsonProperty
    String iconUrl;
    @JsonProperty
    String polyline;

    public String getTravelMode() {
        return travelMode;
    }

    public int getDurationInMinutes() {
        DateTime firstStopTime = stops.get(0).getDatetime();
        DateTime lastStopTime = stops.get(stops.size() - 1).getDatetime();

        return new Period(firstStopTime, lastStopTime).toStandardMinutes().getMinutes();
    }

    public String getPolyline() {
        return polyline;
    }

    public String getColor() {
        return color;
    }
}
