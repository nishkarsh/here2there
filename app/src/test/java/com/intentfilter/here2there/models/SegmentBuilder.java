package com.intentfilter.here2there.models;

import java.util.ArrayList;

class SegmentBuilder {
    private final Segment segment;

    public SegmentBuilder() {
        this.segment = new Segment();
    }

    public SegmentBuilder withDefaults() {
        segment.name = null;
        segment.numStops = 0;
        segment.stops = new ArrayList<>();
        segment.travelMode = "walking";
        segment.description = null;
        segment.color = "b1becc";
        segment.iconUrl = "https://d3m2tfu2xpiope.cloudfront.net/vehicles/walking.svg";
        segment.polyline = "uvr_I{yxpABuAFcAp@yHvAwNr@iGPwAh@a@jAg@";
        return this;
    }

    public SegmentBuilder withNextStop(Stop stop) {
        segment.stops.add(stop);
        return this;
    }

    public Segment build() {
        return segment;
    }
}
