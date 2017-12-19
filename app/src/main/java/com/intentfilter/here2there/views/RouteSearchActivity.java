package com.intentfilter.here2there.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.intentfilter.here2there.R;
import com.intentfilter.here2there.presenters.RouteSearchPresenter;

public class RouteSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_search);

        RouteSearchPresenter presenter = new RouteSearchPresenter(this);
        presenter.presentRoutes();
    }
}
