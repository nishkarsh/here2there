package com.intentfilter.here2there.presenters;

import com.intentfilter.here2there.R;
import com.intentfilter.here2there.models.ServiceResponse;
import com.intentfilter.here2there.services.TransitService;
import com.intentfilter.here2there.utils.Logger;
import com.intentfilter.here2there.utils.Toaster;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RouteSearchPresenterTest {

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
        routeSearchPresenter = new RouteSearchPresenter(transitService, toaster, logger);
    }

    @Test
    public void shouldGetRoutesFromTransitService() throws Exception {
        routeSearchPresenter.presentRoutes();

        verify(transitService).getRoutes(any(Callback.class));
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
    public void shouldLogFetchedResponse() throws Exception {
        Response<ServiceResponse> response = Response.success(new ServiceResponse());
        routeSearchPresenter.presentRoutes();

        verify(transitService).getRoutes(callbackCaptor.capture());

        Callback callback = callbackCaptor.getValue();
        callback.onResponse(call, response);

        verify(logger).d(response.toString());
        verify(toaster).toast(R.string.text_success);
    }
}