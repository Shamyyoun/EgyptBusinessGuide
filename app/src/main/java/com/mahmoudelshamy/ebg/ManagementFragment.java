package com.mahmoudelshamy.ebg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import adapters.ManagementAdapter;
import datamodels.ManagementPerson;
import utils.DrawableUtil;
import utils.RawUtil;
import views.BaseActivity;
import views.SlideExpandableListView;

public class ManagementFragment extends Fragment {
    public static final String TAG = "management";

    // fragment objects
    private BaseActivity activity;
    private SlideExpandableListView listManagement;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_management, container, false);
        initComponents(rootView);
        return rootView;
    }

    private void initComponents(final View rootView) {
        activity = (BaseActivity) getActivity();
        listManagement = (SlideExpandableListView) rootView.findViewById(R.id.list_management);

        final ManagementPerson[] persons = getManagementPersons();
        final ManagementAdapter adapter = new ManagementAdapter(activity, R.layout.list_management_item,
                R.layout.list_management_sub_item, persons);
        listManagement.setAdapter(adapter);

        // add listeners
        listManagement.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                boolean expand = !persons[groupPosition].isExpanded();

                // expand or collapse group view
                if (expand) {
                    listManagement.expandGroupWithAnimation(groupPosition);
                } else {
                    listManagement.collapseGroupWithAnimation(groupPosition);
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
    private ManagementPerson[] getManagementPersons() {
        String[] names = activity.getResources().getStringArray(R.array.management_persons);
        ManagementPerson[] persons = new ManagementPerson[names.length];

        for (int i = 0; i < persons.length; i++) {
            String name = names[i];
            String description = RawUtil.readInHTMLFormat(activity.getApplicationContext(), "m_person" + (i + 1));
            // get email
            int emailResId = activity.getResources().getIdentifier("m_person" + (i + 1) + "_mail", "string", activity.getPackageName());
            String email = null;
            if (emailResId != 0)
                email = getString(emailResId);
            int imageResourceId = DrawableUtil.getDrawableId(activity.getApplicationContext(), "m_person" + (i + 1));
            ManagementPerson person = new ManagementPerson(name, imageResourceId, description, email);

            persons[i] = person;
        }

        return persons;
    }
}
