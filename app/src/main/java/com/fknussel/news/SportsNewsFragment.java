package com.fknussel.news;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SportsNewsFragment extends Fragment {

    public static SportsNewsFragment newInstance() {
        SportsNewsFragment fragment = new SportsNewsFragment();
        return fragment;
    }

    public SportsNewsFragment() {
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // inflate our fragment layout (aka show it on screen)
        View rootView = inflater.inflate(R.layout.fragment_sports_news, container, false);
        return rootView;
    }

    // onAttach() gets called when the fragment gets associated to main activity

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // this is gonna allow us to call over into main activity
        // and make sure the action bar title matches our fragment title
        ((MainActivity) activity).onSectionAttached(2);
    }
}
