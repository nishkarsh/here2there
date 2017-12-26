package com.intentfilter.here2there.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.intentfilter.here2there.R;
import com.intentfilter.here2there.models.Routes;
import com.intentfilter.here2there.presenters.RouteSearchPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class RouteSearchActivity extends AppCompatActivity implements RouteSearchView {

    @BindView(R.id.viewRouteList)
    RecyclerView routeListView;
    @BindView(R.id.buttonSearch)
    ImageButton searchButton;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private RouteSearchPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_search);

        ButterKnife.bind(this);

        presenter = new RouteSearchPresenter(this, this);
    }

    @Override
    public void setRoutes(Routes routes) {
        routeListView.setLayoutManager(new LinearLayoutManager(this, VERTICAL, false));
        routeListView.addItemDecoration(new DividerItemDecoration(routeListView.getContext(), DividerItemDecoration.VERTICAL));
        routeListView.setAdapter(new RoutesAdapter(routes));
    }

    @Override
    public void setProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @Override
    public void setSearchButtonVisibility(int visibility) {
        searchButton.setVisibility(visibility);
    }

    @OnClick(R.id.buttonSearch)
    void searchRoutes() {
        presenter.presentRoutes();
    }
}
