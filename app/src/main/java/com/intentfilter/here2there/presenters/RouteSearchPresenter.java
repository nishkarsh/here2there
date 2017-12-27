package com.intentfilter.here2there.presenters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.intentfilter.here2there.R;
import com.intentfilter.here2there.models.Providers;
import com.intentfilter.here2there.models.ServiceResponse;
import com.intentfilter.here2there.services.TransitService;
import com.intentfilter.here2there.services.gateways.GatewayFactory;
import com.intentfilter.here2there.utils.Logger;
import com.intentfilter.here2there.utils.Toaster;
import com.intentfilter.here2there.views.RouteSearchView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class RouteSearchPresenter {
    private RouteSearchView view;
    private TransitService transitService;
    private Toaster toaster;
    private Logger logger;
    private Providers providers;

    public RouteSearchPresenter(Context context, RouteSearchView view) {
        this(view, new TransitService(new GatewayFactory()), new Toaster(context), Logger.loggerFor(RouteSearchPresenter.class));
    }

    @VisibleForTesting
    RouteSearchPresenter(RouteSearchView view, TransitService transitService, Toaster toaster, Logger logger) {
        this.view = view;
        this.transitService = transitService;
        this.toaster = toaster;
        this.logger = logger;
    }

    public void presentRoutes() {
        setLoaderIndication(true);

        transitService.getRoutes(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(@NonNull Call<ServiceResponse> call, @NonNull Response<ServiceResponse> response) {
                setLoaderIndication(false);

                ServiceResponse responseBody = response.body();
                if (responseBody != null) {
                    view.setRoutes(responseBody.getRoutes());
                    providers = responseBody.getProviders();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ServiceResponse> call, @NonNull Throwable throwable) {
                setLoaderIndication(false);

                logger.e("An error occurred while fetching routes", throwable);
                toaster.toast(R.string.error_occurred);
            }
        });
    }

    public void showProviderInfo(String providerName) {
        view.showProviderDialogFragment(providers.get(providerName));
    }

    private void setLoaderIndication(boolean shouldShowLoader) {
        view.setProgressBarVisibility(shouldShowLoader ? VISIBLE : GONE);
        view.setSearchButtonVisibility(shouldShowLoader ? GONE : VISIBLE);
    }
}
