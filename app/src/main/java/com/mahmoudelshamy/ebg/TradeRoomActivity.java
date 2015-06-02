package com.mahmoudelshamy.ebg;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import views.BaseActivity;


public class TradeRoomActivity extends BaseActivity implements View.OnClickListener {
    // tabs objects
    private TextView textAboutRoom;
    private TextView textServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_room);

        initComponents();
    }

    private void initComponents() {
        // customize actionbar
        setActionBarTitle(R.string.trade_room);
        setActionbarIcon(R.drawable.ic_back_white);
        setActionBarIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // init tabs
        textAboutRoom = (TextView) findViewById(R.id.text_aboutRoom);
        textServices = (TextView) findViewById(R.id.text_services);
        textAboutRoom.setOnClickListener(this);
        textServices.setOnClickListener(this);

        // select first tab as default
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container_tab, new AboutRoomFragment(), AboutRoomFragment.TAG);
        ft.commit();
        selectTab(textAboutRoom);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.text_aboutRoom:
                ft.replace(R.id.container_tab, new AboutRoomFragment());
                selectTab(textAboutRoom);
                break;

            case R.id.text_services:
                ft.replace(R.id.container_tab, new RoomServicesFragment());
                selectTab(textServices);
                break;
        }

        ft.commit();
    }

    private void selectTab(TextView textView) {
        // deselect all first
        textAboutRoom.setSelected(false);
        textServices.setSelected(false);

        // select desired item
        textView.setSelected(true);
    }
}
