package com.mahmoudelshamy.ebg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import utils.RawUtil;
import views.BaseActivity;

public class InvestmentFragment extends Fragment {
    public static final String TAG = "investment";

    private BaseActivity activity;
    private TextView textContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_investment, container, false);
        initComponents(rootView);
        return rootView;
    }

    private void initComponents(View rootView) {
        activity = (BaseActivity) getActivity();
        textContent = (TextView) rootView.findViewById(R.id.text_content);

        // customize actionbar
        activity.setActionBarTitle(getString(R.string.investment_climate));

        // set data
        String content = RawUtil.readInHTMLFormat(getActivity().getApplicationContext(), R.raw.investment_in_egypt);
        textContent.setText(Html.fromHtml(content));
    }
}
