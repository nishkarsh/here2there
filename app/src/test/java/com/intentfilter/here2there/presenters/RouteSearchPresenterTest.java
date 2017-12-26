package com.intentfilter.here2there.presenters;

import android.view.View;

import com.intentfilter.here2there.R;
import com.intentfilter.here2there.models.Routes;
import com.intentfilter.here2there.models.ServiceResponse;
import com.intentfilter.here2there.services.TransitService;
import com.intentfilter.here2there.utils.Logger;
import com.intentfilter.here2there.utils.Toaster;
import com.intentfilter.here2there.views.RouteSearchView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RouteSearchPresenterTest {

    @Mock
    private RouteSearchView view;
    @Mock
    private TransitService transitService;
    @Mock
    private Toaster toaster;
    @Mock
    private Logger logger;
    @Mock
    private Call call;
    @Captor
    private ArgumentCaptor<Callback> callbackCaptor;

    private RouteSearchPresenter routeSearchPresenter;

    @Before
    public void setUp() throws Exception {
        routeSearchPresenter = new RouteSearchPresenter(view, transitService, toaster, logger);
    }

    @Test
    public void shouldGetRoutesFromTransitService() throws Exception {
        routeSearchPresenter.presentRoutes();

        verify(transitService).getRoutes(any(Callback.class));
    }

    @Test
    public void shouldShowProgressBarWhileFetchingRoutes() throws Exception {
        routeSearchPresenter.presentRoutes();

        InOrder inOrder = inOrder(view, transitService);
        inOrder.verify(view).setProgressBarVisibility(View.VISIBLE);
        inOrder.verify(view).setSearchButtonVisibility(View.GONE);
        inOrder.verify(transitService).getRoutes(any(Callback.class));
    }

    @Test
    public void shouldShowToastOnFailureInFetchingRoutes() throws Exception {
        UnknownHostException exception = new UnknownHostException("Unknown Host");
        routeSearchPresenter.presentRoutes();

        verify(transitService).getRoutes(callbackCaptor.capture());

        Callback callback = callbackCaptor.getValue();
        callback.onFailure(call, exception);

        verify(toaster).toast(R.string.error_occurred);
        verify(logger).e("An error occurred while fetching routes", exception);
    }

    @Test
    public void shouldRevertProgressBarStatusOnResponse() throws Exception {
        Response<ServiceResponse> response = Response.success(null);
        routeSearchPresenter.presentRoutes();

        verify(transitService).getRoutes(callbackCaptor.capture());

        Callback callback = callbackCaptor.getValue();
        callback.onResponse(call, response);

        InOrder inOrder = inOrder(view);
        inOrder.verify(view).setProgressBarVisibility(View.GONE);
        inOrder.verify(view).setSearchButtonVisibility(View.VISIBLE);
    }

    @Test
    public void shouldRevertProgressBarStatusOnFailure() throws Exception {
        UnknownHostException exception = new UnknownHostException("Unknown Host");
        routeSearchPresenter.presentRoutes();

        verify(transitService).getRoutes(callbackCaptor.capture());

        Callback callback = callbackCaptor.getValue();
        callback.onFailure(call, exception);

        InOrder inOrder = inOrder(view);
        inOrder.verify(view).setProgressBarVisibility(View.GONE);
        inOrder.verify(view).setSearchButtonVisibility(View.VISIBLE);
    }

    @Test
    public void shouldPresentFetchedRoutes() throws Exception {
        ServiceResponse serviceResponse = new ServiceResponse();
        Response<ServiceResponse> response = Response.success(serviceResponse);
        routeSearchPresenter.presentRoutes();

        verify(transitService).getRoutes(callbackCaptor.capture());

        Callback callback = callbackCaptor.getValue();
        callback.onResponse(call, response);

        verify(view).setRoutes(serviceResponse.getRoutes());
    }

    @Test
    public void shouldHandleEmptyResponseFromServer() throws Exception {
        Response<ServiceResponse> response = Response.success(null);
        routeSearchPresenter.presentRoutes();

        verify(transitService).getRoutes(callbackCaptor.capture());

        Callback callback = callbackCaptor.getValue();
        callback.onResponse(call, response);

        verify(view, never()).setRoutes(any(Routes.class));
    }
}