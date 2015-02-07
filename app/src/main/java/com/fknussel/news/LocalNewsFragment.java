package com.fknussel.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class LocalNewsFragment extends Fragment {

    public static LocalNewsFragment newInstance() {
        LocalNewsFragment fragment = new LocalNewsFragment();
        return fragment;
    }

    public LocalNewsFragment() {
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // inflate our fragment layout (aka show it on screen)
        View rootView = inflater.inflate(R.layout.fragment_local_news, container, false);

        getActivity().setTitle(R.string.company_name);

        // Needs to be final so that it can be accessed from the onItemClickListener
        final NewsAdapter newsAdapter = new NewsAdapter(getActivity(), Post.getDummyPosts());

        ListView listview = (ListView) rootView.findViewById(R.id.listview_news);

        listview.setAdapter(newsAdapter);

        // When the user clicks on any item, take them to the DetailView for that post
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), DetailActivity.class);
                i.putExtra("id", newsAdapter.getItem(position).getId());
                startActivity(i);
            }
        });

        return rootView;
    }

    // onAttach() gets called when the fragment gets associated to main activity

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // this is gonna allow us to call over into main activity
        // and make sure the action bar title matches our fragment title
        ((MainActivity) activity).onSectionAttached(1);
    }
}
