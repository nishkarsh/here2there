package com.intentfilter.here2there.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.intentfilter.here2there.BuildConfig;
import com.intentfilter.here2there.R;
import com.intentfilter.here2there.models.Provider;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ProviderDialogFragment extends DialogFragment {
    public static final String TAG = ProviderDialogFragment.class.getSimpleName();
    private static String EXTRA_PROVIDER = BuildConfig.APPLICATION_ID + ".provider";

    @BindView(R.id.viewProviderName)
    TextView providerNameView;
    @BindView(R.id.viewDisclaimer)
    TextView disclaimerView;
    @BindView(R.id.viewAppLink)
    TextView appLinkView;

    private Unbinder unbinder;

    public static ProviderDialogFragment newInstance(Provider provider) {
        ProviderDialogFragment fragment = new ProviderDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_PROVIDER, Parcels.wrap(provider));
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_provider_info, container, false);
        unbinder = ButterKnife.bind(this, view);

        Provider provider = Parcels.unwrap(getArguments().getParcelable(EXTRA_PROVIDER));
        providerNameView.setText(provider.getDisplayName());
        disclaimerView.setText(provider.getDisclaimer());
        appLinkView.setText(provider.getAppLink());

        return view;
    }

    @OnClick(R.id.buttonDismiss)
    void dismissDialog() {
        dismiss();
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
