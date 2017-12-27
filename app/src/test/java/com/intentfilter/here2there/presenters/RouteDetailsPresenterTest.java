package com.intentfilter.here2there.presenters;

import com.google.android.gms.maps.model.LatLng;
import com.intentfilter.here2there.models.Route;
import com.intentfilter.here2there.models.RouteBuilder;
import com.intentfilter.here2there.models.Segment;
import com.intentfilter.here2there.models.SegmentBuilder;
import com.intentfilter.here2there.views.RouteDetailsView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RouteDetailsPresenterTest {

    @Mock
    private RouteDetailsView view;
    @Captor
    private ArgumentCaptor<List> captor;

    private RouteDetailsPresenter presenter;
    private Segment segment;

    @Before
    public void setUp() throws Exception {
        segment = new SegmentBuilder().withDefaults().build();
        Route route = new RouteBuilder().withDefaults().withSegment(segment).build();
        presenter = new RouteDetailsPresenter(view, route);
    }

    @Test
    public void shouldPresentRouteDetails() {
        presenter.presentRouteDetails();

        verify(view).initializeMap();
        verify(view).showSegmentList(captor.capture());
        List<Segment> segments = captor.getValue();
        assertThat(segments.size(), is(1));
        assertThat(segments, hasItem(segment));
    }

    @Test
    public void shouldReturnSegments() {
        List<Segment> segments = presenter.getSegments();

        assertThat(segments.size(), is(1));
        assertThat(segments, hasItem(segment));
    }

    @Test
    public void shouldFocusSegmentOnMap() throws Exception {
        Segment segment = new SegmentBuilder().withDefaults().build();

        presenter.focusSegment(segment, 14);

        verify(view).animateCamera(any(LatLng.class), eq(14));
    }

    @Test
    public void shouldNotAttemptFocusSegmentOnMapWhenNoPolylineAvailable() throws Exception {
        Segment segment = new SegmentBuilder().withDefaults().withPolyline(null).build();

        presenter.focusSegment(segment, 14);

        verify(view, never()).animateCamera(any(LatLng.class), anyInt());
    }
}