package com.intentfilter.here2there.views;

import com.google.android.gms.maps.model.LatLng;
import com.intentfilter.here2there.models.Segment;

import java.util.List;

public interface RouteDetailsView {
    void showSegmentList(List<Segment> segments);

    void initializeMap();

    void animateCamera(LatLng latLng, int zoomLevel);

    void focusSegment(Segment segment, int zoomLevel);

    void plotOnMap(String color, List<LatLng> geoPositions);
}
