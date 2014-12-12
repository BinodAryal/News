package com.fknussel.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainFragment extends Fragment {

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

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


}