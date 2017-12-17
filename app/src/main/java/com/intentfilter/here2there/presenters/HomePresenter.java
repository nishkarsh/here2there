package com.intentfilter.here2there.presenters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.intentfilter.here2there.R;
import com.intentfilter.here2there.models.ServiceResponse;
import com.intentfilter.here2there.services.TransitService;
import com.intentfilter.here2there.services.gateways.GatewayFactory;
import com.intentfilter.here2there.utils.Logger;
import com.intentfilter.here2there.utils.Toaster;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter {

    private TransitService transitService;
    private Toaster toaster;
    private Logger logger;

    public HomePresenter(Context context) {
        this(new TransitService(new GatewayFactory()), new Toaster(context), Logger.loggerFor(HomePresenter.class));
    }

    @VisibleForTesting
    HomePresenter(TransitService transitService, Toaster toaster, Logger logger) {
        this.transitService = transitService;
        this.toaster = toaster;
        this.logger = logger;
    }

    public void presentRoutes() {
        transitService.getRoutes(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(@NonNull Call<ServiceResponse> call, @NonNull Response<ServiceResponse> response) {
                logger.d(response.toString());
            }

            @Override
            public void onFailure(@NonNull Call<ServiceResponse> call, @NonNull Throwable throwable) {
                logger.e("An error occurred while fetching routes", throwable);
                toaster.toast(R.string.error_occurred);
            }
        });
    }
}
