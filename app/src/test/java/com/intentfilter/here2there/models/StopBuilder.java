package com.intentfilter.here2there.models;

import org.joda.time.DateTime;

class StopBuilder {
    private Stop stop;

    StopBuilder() {
        this.stop = new Stop();
    }

    public StopBuilder withDefaults() {
        stop.datetime = new DateTime();
        stop.latitude = 52.528187;
        stop.longitude = 13.410404;
        stop.name = "U Rosa-Luxemburg-Platz";
        return this;
    }

    public StopBuilder withDatetime(DateTime datetime) {
        stop.datetime = datetime;
        return this;
    }

    public Stop build() {
        return stop;
    }
}
