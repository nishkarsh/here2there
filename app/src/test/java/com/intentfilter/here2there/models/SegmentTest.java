package com.intentfilter.here2there.models;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SegmentTest {

    @Test
    public void shouldGetProperDuration() {
        Stop firstStop = new StopBuilder()
                .withDefaults()
                .withDatetime(DateTime.parse("2015-04-17T13:43:00+02:00"))
                .build();
        Stop lastStop = new StopBuilder()
                .withDefaults()
                .withDatetime(DateTime.parse("2015-04-17T13:45:00+02:00"))
                .build();
        Segment segment = new SegmentBuilder()
                .withDefaults()
                .withNextStop(firstStop)
                .withNextStop(lastStop)
                .build();

        int duration = segment.getDurationInMinutes();

        assertThat(duration, is(2));
    }
}