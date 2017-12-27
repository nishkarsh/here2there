package com.intentfilter.here2there.presenters;

import com.intentfilter.here2there.models.Route;
import com.intentfilter.here2there.models.Segment;
import com.intentfilter.here2there.views.RouteDetailsView;

import java.util.List;

import static com.google.maps.android.PolyUtil.decode;

public class RouteDetailsPresenter {
    private RouteDetailsView view;
    private Route route;

    public RouteDetailsPresenter(RouteDetailsView view, Route route) {
        this.view = view;
        this.route = route;
    }

    public void presentRouteDetails() {
        view.initializeMap();
        view.showSegmentList(route.getSegments());
    }

    public List<Segment> getSegments() {
        return route.getSegments();
    }

    public void focusSegment(Segment segment, int zoomLevel) {
        String polyline = segment.getPolyline();
        if (!isEmpty(polyline)) {
            view.animateCamera(decode(polyline).get(0), zoomLevel);
        }
    }

    private boolean isEmpty(String polyline) {
        return polyline == null || polyline.isEmpty();
    }
}
