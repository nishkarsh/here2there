package com.intentfilter.here2there.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.intentfilter.here2there.R;
import com.intentfilter.here2there.models.Routes;
import com.intentfilter.here2there.presenters.RouteSearchPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class RouteSearchActivity extends AppCompatActivity implements RouteSearchView {

    @BindView(R.id.viewRouteList)
    RecyclerView routeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_search);

        ButterKnife.bind(this);

        RouteSearchPresenter presenter = new RouteSearchPresenter(this, this);
        presenter.presentRoutes();
    }

    @Override
    public void setRoutes(Routes routes) {
        routeListView.setLayoutManager(new LinearLayoutManager(this, VERTICAL, false));
        routeListView.addItemDecoration(new DividerItemDecoration(routeListView.getContext(), DividerItemDecoration.VERTICAL));
        routeListView.setAdapter(new RoutesAdapter(routes));
    }
}
