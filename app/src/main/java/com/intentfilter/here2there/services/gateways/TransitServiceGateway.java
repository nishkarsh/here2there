package com.intentfilter.here2there.services.gateways;

import com.intentfilter.here2there.models.Route;

import java.util.List;

import retrofit2.http.GET;

public interface TransitServiceGateway {
    @GET("/transit-app-task/master/data.json")
    List<Route> getRoutes();
}
