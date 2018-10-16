package com.mahmoudelshamy.ebg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import adapters.TabsAdapter;
import datamodels.Constants;
import views.BaseActivity;


public class InvestmentActivity extends BaseActivity {
    private RecyclerView recyclerTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment);

        initComponents();
    }

    private void initComponents() {
        // customize actionbar
        setActionbarIcon(R.drawable.ic_back_white);
        setActionBarIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // init recycler tabs
        recyclerTabs = (RecyclerView) findViewById(R.id.recycler_tabs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayout.HORIZONTAL);
        recyclerTabs.setLayoutManager(layoutManager);

        // --set recycler adapter--
        final String[] tabs = getResources().getStringArray(R.array.investment_tabs);
        int firstTab = 0;
        if (AppController.getInstance(getApplicationContext()).getLang().equals("ar"))
            firstTab = tabs.length - 1;
        final TabsAdapter adapter = new TabsAdapter(tabs, firstTab);
        adapter.setOnItemClickListener(new TabsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // check language to get appropriate tab position
                int selectedTab = position;
                if (AppController.getInstance(getApplicationContext()).getLang().equals("ar"))
                    selectedTab = tabs.length - 1 - position;

                // select tab
                adapter.selectTab(position);

                // goto screen
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment fragment = new InvestmentFragment();
                Bundle bundle = new Bundle();
                switch (selectedTab) {
                    case 0:
                        fragment = new InvestmentFragment();
                        break;

                    case 1:
                        fragment = new InternationalAgreementsFragment();
                        break;

                    case 2:
                        fragment = new SystemsFragment();
                        break;

                    case 3:
                        fragment = new ProjectsFragment();
                        break;
                }
                ft.replace(R.id.container_tab, fragment);
                ft.commit();
            }
        });
        recyclerTabs.setAdapter(adapter);
        recyclerTabs.scrollToPosition(firstTab);

        // select first tab as default
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container_tab, new InvestmentFragment());
        ft.commit();
    }
}
