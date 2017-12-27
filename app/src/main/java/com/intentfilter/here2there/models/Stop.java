package com.intentfilter.here2there.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.joda.time.DateTime;
import org.parceler.Parcel;

@Parcel
public class Stop {
    @JsonProperty("lat")
    double latitude;
    @JsonProperty("lng")
    double longitude;
    @JsonProperty
    DateTime datetime;
    @JsonProperty
    String name;

    public DateTime getDatetime() {
        return datetime;
    }
}
