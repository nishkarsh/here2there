package com.intentfilter.here2there.presenters;

import android.support.annotation.VisibleForTesting;

import com.intentfilter.here2there.models.ServiceResponse;
import com.intentfilter.here2there.services.TransitService;
import com.intentfilter.here2there.services.gateways.GatewayFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter {

    private TransitService transitService;

    public HomePresenter() {
        this(new TransitService(new GatewayFactory()));
    }

    @VisibleForTesting
    HomePresenter(TransitService transitService) {
        this.transitService = transitService;
    }

    public void presentRoutes() {
        transitService.getRoutes(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                //TODO Dump the routes at some place
            }

            @Override
            public void onFailure(Call<ServiceResponse> call, Throwable throwable) {
                //TODO Show a toast
            }
        });
    }
}
