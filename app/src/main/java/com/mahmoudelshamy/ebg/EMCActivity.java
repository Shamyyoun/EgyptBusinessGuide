package com.mahmoudelshamy.ebg;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import views.BaseActivity;


public class EMCActivity extends BaseActivity implements View.OnClickListener {
    // tabs objects
    private TextView textVision;
    private TextView textManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emc);

        initComponents();
    }

    private void initComponents() {
        // customize actionbar
        setActionBarTitle(R.string.egypt_media_center);
        setActionbarIcon(R.drawable.ic_back_white);
        setActionBarIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // init tabs
        textVision = (TextView) findViewById(R.id.text_vision);
        textManagement = (TextView) findViewById(R.id.text_management);
        textVision.setOnClickListener(this);
        textManagement.setOnClickListener(this);

        // select first tab as default
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container_tab, new EMCVisionFragment(), EMCVisionFragment.TAG);
        ft.commit();
        selectTab(textVision);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.text_vision:
                ft.replace(R.id.container_tab, new EMCVisionFragment());
                selectTab(textVision);
                break;

            case R.id.text_management:
                ft.replace(R.id.container_tab, new ManagementFragment());
                selectTab(textManagement);
                break;
        }

        ft.commit();
    }

    private void selectTab(TextView textView) {
        // deselect all first
        textVision.setSelected(false);
        textManagement.setSelected(false);

        // select desired item
        textView.setSelected(true);
    }
}
