package com.intentfilter.here2there.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;
import com.google.android.gms.maps.model.SquareCap;
import com.intentfilter.here2there.BuildConfig;
import com.intentfilter.here2there.R;
import com.intentfilter.here2there.models.Route;
import com.intentfilter.here2there.models.Segment;
import com.intentfilter.here2there.presenters.RouteDetailsPresenter;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.graphics.Color.parseColor;
import static android.support.v7.widget.LinearLayoutManager.VERTICAL;
import static android.text.TextUtils.isEmpty;
import static com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom;
import static com.google.maps.android.PolyUtil.decode;

public class RouteDetailsActivity extends AppCompatActivity implements RouteDetailsView, OnMapReadyCallback {
    final static String EXTRA_ROUTE = BuildConfig.APPLICATION_ID + ".route";

    @BindView(R.id.viewSegmentList)
    RecyclerView segmentListView;

    private GoogleMap googleMap;
    private RouteDetailsPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_details);

        ButterKnife.bind(this);

        Route route = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_ROUTE));
        presenter = new RouteDetailsPresenter(this, route);
        presenter.presentRouteDetails();
    }

    @Override
    public void showSegmentList(List<Segment> segments) {
        segmentListView.setLayoutManager(new LinearLayoutManager(this, VERTICAL, false));
        segmentListView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        segmentListView.setAdapter(new SegmentsAdapter(segments));
    }

    @Override
    public void initializeMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        List<Segment> segments = presenter.getSegments();

        for (Segment segment : segments) {
            String polyline = segment.getPolyline();
            if (isEmpty(polyline)) {
                continue;
            }

            List<LatLng> geoPositions = decode(polyline);
            plotOnMap(googleMap, segment.getColor(), geoPositions);
        }

        focusSegment(segments.get(0), 12);
    }

    @Override
    public void animateCamera(LatLng latLng, int zoomLevel) {
        googleMap.animateCamera(newLatLngZoom(latLng, zoomLevel));
    }

    public void focusSegment(Segment segment, int zoomLevel) {
        presenter.focusSegment(segment, zoomLevel);
    }

    private void plotOnMap(GoogleMap googleMap, String color, List<LatLng> geoPositions) {
        PolylineOptions polylineOptions = new PolylineOptions()
                .startCap(new RoundCap())
                .endCap(new SquareCap())
                .color(parseColor(color))
                .addAll(geoPositions);

        googleMap.addPolyline(polylineOptions);
    }
}
