package com.intentfilter.here2there.models;

import java.util.ArrayList;

public class RouteBuilder {
    private final Route route;

    public RouteBuilder() {
        route = new Route();
    }

    public RouteBuilder withDefaults() {
        route.price = new Price("EUR", 270);
        route.provider = "UB";
        route.type = "public_transport";
        route.segments = new ArrayList<>();
        return this;
    }

    public RouteBuilder withSegment(Segment segment) {
        route.segments.add(segment);
        return this;
    }

    public Route build() {
        return route;
    }
}
