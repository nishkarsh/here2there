package com.intentfilter.here2there.models;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RouteTest {
    @Test
    public void shouldGetProperTravelDurationInMinutes() throws Exception {
        Segment firstSegment = new SegmentBuilder()
                .withDefaults()
                .withNextStop(buildStopWithDatetime("2015-04-17T13:43:00+02:00"))
                .withNextStop(buildStopWithDatetime("2015-04-17T13:45:00+02:00"))
                .build();
        Segment secondSegment = new SegmentBuilder()
                .withDefaults()
                .withNextStop(buildStopWithDatetime("2015-04-17T13:45:00+02:00"))
                .withNextStop(buildStopWithDatetime("2015-04-17T13:49:00+02:00"))
                .build();
        Route route = new RouteBuilder()
                .withDefaults()
                .withSegment(firstSegment)
                .withSegment(secondSegment)
                .build();

        int travelDurationInMinutes = route.getTravelDurationInMinutes();

        assertThat(travelDurationInMinutes, is(6));
    }

    private Stop buildStopWithDatetime(String datetime) {
        return new StopBuilder().withDefaults().withDatetime(DateTime.parse(datetime)).build();
    }
}