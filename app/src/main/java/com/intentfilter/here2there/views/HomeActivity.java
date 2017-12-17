package com.intentfilter.here2there.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.intentfilter.here2there.presenters.HomePresenter;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HomePresenter presenter = new HomePresenter(this);
        presenter.presentRoutes();
    }
}
