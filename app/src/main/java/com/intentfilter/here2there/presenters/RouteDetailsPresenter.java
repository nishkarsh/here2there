package com.intentfilter.here2there.presenters;

import com.google.android.gms.maps.model.LatLng;
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

    public void focusSegment(Segment segment, int zoomLevel) {
        String polyline = segment.getPolyline();
        if (!isEmpty(polyline)) {
            view.animateCamera(decode(polyline).get(0), zoomLevel);
        }
    }

    public void onMapReady() {
        for (Segment segment : route.getSegments()) {
            String polyline = segment.getPolyline();

            if (!isEmpty(polyline)) {
                List<LatLng> geoPositions = decode(polyline);
                view.plotOnMap(segment.getColor(), geoPositions);
            }
        }

        focusSegment(route.getSegments().get(0), 12);
    }

    private boolean isEmpty(String polyline) {
        return polyline == null || polyline.isEmpty();
    }
}
