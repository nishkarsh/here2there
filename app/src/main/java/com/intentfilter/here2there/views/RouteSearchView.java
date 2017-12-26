package com.intentfilter.here2there.views;

import com.intentfilter.here2there.models.Routes;

public interface RouteSearchView {
    void setRoutes(Routes routes);

    void setProgressBarVisibility(int visibility);

    void setSearchButtonVisibility(int visibility);
}
