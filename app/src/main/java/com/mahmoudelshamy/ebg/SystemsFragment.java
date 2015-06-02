package com.mahmoudelshamy.ebg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import adapters.SystemsAdapter;
import datamodels.InvestmentSystem;
import utils.DrawableUtil;
import utils.RawUtil;
import views.BaseActivity;
import views.SlideExpandableListView;

public class SystemsFragment extends Fragment {
    public static final String TAG = "systems";

    // fragment objects
    private BaseActivity activity;
    private SlideExpandableListView listSystems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_systems, container, false);
        initComponents(rootView);
        return rootView;
    }

    private void initComponents(final View rootView) {
        activity = (BaseActivity) getActivity();
        listSystems = (SlideExpandableListView) rootView.findViewById(R.id.list_systems);

        // customize actionbar
        activity.setActionBarTitle(getString(R.string.investment_systems));

        final InvestmentSystem[] systems = getInvestmentSystems();
        final SystemsAdapter adapter = new SystemsAdapter(activity, R.layout.list_systems_item,
                R.layout.list_systems_sub_item, systems);
        listSystems.setAdapter(adapter);

        // add listeners
        listSystems.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                boolean expand = !systems[groupPosition].isExpanded();

                // expand or collapse group view
                if (expand) {
                    listSystems.expandGroupWithAnimation(groupPosition);
                } else {
                    listSystems.collapseGroupWithAnimation(groupPosition);
                }

                // update in adapter
                adapter.expandItem(groupPosition, expand);

                return true;
            }
        });
    }

    /**
     * method, used to return array of management persons
     */
    private InvestmentSystem[] getInvestmentSystems() {
        String[] names = activity.getResources().getStringArray(R.array.investment_systems);
        InvestmentSystem[] systems = new InvestmentSystem[names.length];

        for (int i = 0; i < systems.length; i++) {
            String name = names[i];
            String description = RawUtil.readInHTMLFormat(activity.getApplicationContext(), "inv_system" + (i + 1));
            int imageResourceId = DrawableUtil.getDrawableId(activity.getApplicationContext(), "inv_system" + (i + 1));
            InvestmentSystem system = new InvestmentSystem(name, imageResourceId, description);

            systems[i] = system;
        }

        return systems;
    }
}
