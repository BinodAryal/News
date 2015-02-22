package com.fknussel.news;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LocalNewsFragment extends Fragment {
    
    private static final String TAG = LocalNewsFragment.class.getSimpleName();

    public static LocalNewsFragment newInstance() {
        LocalNewsFragment fragment = new LocalNewsFragment();
        return fragment;
    }

    public LocalNewsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // inflate our fragment layout (aka show it on screen)
        View rootView = inflater.inflate(R.layout.fragment_local_news, container, false);

        // Needs to be final so that it can be accessed from the onItemClickListener
        final NewsAdapter newsAdapter = new NewsAdapter(getActivity());
        
        ArrayList<Post> posts = new ArrayList<>();

        // if we have cached data, do not query the API
        DbHelper dbHelper = new DbHelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Cursor to query the database
        String postQuery = "SELECT * FROM " + PostContract.TABLE +
                " ORDER BY " + PostContract.DEFAULT_SORT; 
        Cursor postCursor = db.rawQuery(postQuery, null);

        if (postCursor != null && postCursor.getCount() > 0) {
            if (postCursor.moveToFirst()) {
                do {
                    int id = postCursor.getInt(postCursor.getColumnIndex(PostContract.Column.ID));
                    String title = postCursor.getString(postCursor.getColumnIndex(PostContract.Column.TITLE));
                    String excerpt = postCursor.getString(postCursor.getColumnIndex(PostContract.Column.EXCERPT));
                    String body = postCursor.getString(postCursor.getColumnIndex(PostContract.Column.BODY));
                    String category = postCursor.getString(postCursor.getColumnIndex(PostContract.Column.CATEGORY));
                    String date = postCursor.getString(postCursor.getColumnIndex(PostContract.Column.DATE));

                    // Fetch the media items associated to the current post (if any)
                    ArrayList<Media> media = new ArrayList<>();

                    String mediaQuery = "SELECT * FROM " + MediaContract.TABLE +
                            " WHERE " + MediaContract.Column.POST_ID + " = " + id +
                            " ORDER BY " + MediaContract.DEFAULT_SORT;

                    Cursor mediaCursor = db.rawQuery(mediaQuery, null);

                    if (mediaCursor.moveToFirst()) {
                        do {
                            int mediaId = mediaCursor.getInt(mediaCursor.getColumnIndex(MediaContract.Column.ID));
                            String url = mediaCursor.getString(mediaCursor.getColumnIndex(MediaContract.Column.URL));
                            String type = mediaCursor.getString(mediaCursor.getColumnIndex(MediaContract.Column.TYPE));

                            media.add(new Media(mediaId, id, type, url));
                        } while (mediaCursor.moveToNext());

                        mediaCursor.close();
                    }

                    // Add the post to the adapter
                    Post post = new Post(id, title, excerpt, body, category, date, media);
                    posts.add(post);

                } while (postCursor.moveToNext());

                postCursor.close();
                
                newsAdapter.addAll(posts);

                Toast.makeText(getActivity(), "timeline data fetched from DB", Toast.LENGTH_SHORT).show();
                
                // here we should query the API again to fetch just
                // the latest posts and somehow merge them into
                // our adapter existing data
                // fetchPostsFromApi(newsAdapter, 1);

            }
        } else {
            // query the API
            fetchPostsFromApi(newsAdapter, 1);
        }

        // DB connection clean up
        dbHelper.close();

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

    @Override
    public void onAttach(Activity activity) {
        // onAttach() gets called when the fragment gets associated to main activity
        super.onAttach(activity);

        // this is gonna allow us to call over into main activity
        // and make sure the action bar title matches our fragment title
        ((MainActivity) activity).onSectionAttached(1);
    }
    
    private void fetchPostsFromApi(final NewsAdapter newsAdapter, int paginationIndex) {
        ApiClient.getApiInterface().getPosts(1, new Callback<List<Post>>() {
            @Override
            public void success(List<Post> posts, Response response) {
                newsAdapter.addAll(posts);
                newsAdapter.notifyDataSetChanged();

                // Cache the data
                DbHelper dbHelper = new DbHelper(getActivity());
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();

                try {
                    for (Post post : posts) {
                        // save the current post to the database
                        values.clear();

                        values.put(PostContract.Column.ID, post.getId());
                        values.put(PostContract.Column.TITLE, post.getTitle());
                        values.put(PostContract.Column.EXCERPT, post.getExcerpt());
                        values.put(PostContract.Column.BODY, post.getBody());
                        values.put(PostContract.Column.CATEGORY, post.getCategory());
                        values.put(PostContract.Column.DATE, post.getDate());

                        db.insertWithOnConflict(PostContract.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);

                        // save the associated media items
                        if (post.hasMedia()) {
                            for (Media media : post.getMedia()) {
                                values.clear();

                                values.put(MediaContract.Column.ID, new Random().nextInt((99999 - 1) + 1) + 1);
                                values.put(MediaContract.Column.POST_ID, post.getId());
                                values.put(MediaContract.Column.URL, media.getUrl());
                                values.put(MediaContract.Column.TYPE, media.getType());

                                db.insertWithOnConflict(MediaContract.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
                            }
                        }
                    }
                } catch (Exception e) {
                    Log.d(TAG, "Failed to fetch news feed");
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, error.getMessage());
            }
        });
    }
}
