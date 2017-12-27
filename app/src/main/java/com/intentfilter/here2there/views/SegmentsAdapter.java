package com.intentfilter.here2there.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.intentfilter.here2there.R;
import com.intentfilter.here2there.models.Segment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SegmentsAdapter extends RecyclerView.Adapter {
    private List<Segment> segments;

    SegmentsAdapter(List<Segment> segments) {
        this.segments = segments;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.segment_list_item, parent, false);

        return new SegmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SegmentViewHolder) holder).setSegment(segments.get(position));
    }

    @Override
    public int getItemCount() {
        return segments.size();
    }

    class SegmentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.viewSegmentMode)
        TextView segmentModeView;

        @BindView(R.id.viewSegmentTime)
        TextView segmentTimeView;

        SegmentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick
        void focusSegmentOnMap(View view) {
            ((RouteDetailsActivity) view.getContext()).focusSegment(segments.get(getAdapterPosition()), 18);
        }

        public void setSegment(final Segment segment) {
            Context context = itemView.getContext();
            segmentModeView.setText(segment.getTravelMode());
            segmentTimeView.setText(context.getResources().getQuantityString(R.plurals.text_display_minutes,
                    segment.getDurationInMinutes(), segment.getDurationInMinutes()));
        }
    }
}
