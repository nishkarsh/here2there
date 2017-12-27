package com.intentfilter.here2there.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.intentfilter.here2there.R;
import com.intentfilter.here2there.models.Route;
import com.intentfilter.here2there.models.Segment;

import org.parceler.Parcels;

import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

class RoutesAdapter extends RecyclerView.Adapter {
    private List<Route> routes;

    RoutesAdapter(List<Route> routes) {
        this.routes = routes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.route_list_item, parent, false);

        return new RouteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((RouteViewHolder) holder).setRoute(routes.get(position));
    }

    @Override
    public int getItemCount() {
        return routes.size();
    }

    class RouteViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.viewProvider)
        TextView providerView;

        @BindView(R.id.viewRouteType)
        TextView routeTypeView;

        @BindView(R.id.viewTravelDuration)
        TextView travelDurationView;

        @BindView(R.id.viewTravelModes)
        TextView travelModesView;

        RouteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick
        void selectRoute(View view) {
            Route route = routes.get(getAdapterPosition());
            Context context = view.getContext();

            Intent intent = new Intent(context, RouteDetailsActivity.class);
            intent.putExtra(RouteDetailsActivity.EXTRA_ROUTE, Parcels.wrap(route));
            context.startActivity(intent);
        }

        void setRoute(Route route) {
            Context context = itemView.getContext();
            providerView.setText(route.getProvider());
            routeTypeView.setText(route.getType());
            travelDurationView.setText(context.getString(R.string.text_display_minutes, route.getTravelDurationInMinutes()));
            travelModesView.setText(context.getString(R.string.text_travel_modes, allTravelModes(route)));
        }

        private String allTravelModes(Route route) {
            //TODO Pull this formatting logic to a SegmentViewModel
            HashSet<String> distinctTravelModes = new HashSet<>();
            List<Segment> segments = route.getSegments();
            for (Segment segment : segments) {
                distinctTravelModes.add(segment.getTravelMode());
            }
            return TextUtils.join(", ", distinctTravelModes);
        }
    }
}
