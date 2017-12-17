package com.intentfilter.here2there.presenters;

import com.intentfilter.here2there.services.TransitService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import retrofit2.Callback;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class HomePresenterTest {

    @Mock
    private TransitService transitService;
    private HomePresenter homePresenter;

    @Before
    public void setUp() throws Exception {
        homePresenter = new HomePresenter(transitService);
    }

    @Test
    public void shouldGetRoutesFromTransitService() throws Exception {
        homePresenter.presentRoutes();

        verify(transitService).getRoutes(any(Callback.class));
    }
}