package com.fknussel.news;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class DetailActivity extends ActionBarActivity {

    public final String TAG = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (getIntent() != null) {
            int id = getIntent().getIntExtra("id", 0);

            // final since they need to be accessed from within an inner class
            final TextView postTitle = (TextView) findViewById(R.id.detail_post_title);
            final TextView postExcerpt = (TextView) findViewById(R.id.detail_post_excerpt);
            final TextView postBody = (TextView) findViewById(R.id.detail_post_body);
            final TextView postCategory = (TextView) findViewById(R.id.detail_post_category);
            final TextView postDate = (TextView) findViewById(R.id.detail_post_date);
            final ImageView postImage = (ImageView) findViewById(R.id.detail_post_image);

            // query the API only if the post is not stored locally
            DbHelper dbHelper = new DbHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            // Cursor to query the database
            String postQuery = "SELECT * FROM " + PostContract.TABLE +
                    " WHERE " + PostContract.Column.ID + " = " + id;
            Cursor postCursor = db.rawQuery(postQuery, null);

            if (postCursor != null && postCursor.getCount() > 0) {
                while (postCursor.moveToNext()) {
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

                    while (mediaCursor.moveToNext()) {
                        int mediaId = mediaCursor.getInt(mediaCursor.getColumnIndex(MediaContract.Column.ID));
                        String url = mediaCursor.getString(mediaCursor.getColumnIndex(MediaContract.Column.URL));
                        String type = mediaCursor.getString(mediaCursor.getColumnIndex(MediaContract.Column.TYPE));

                        media.add(new Media(mediaId, id, type, url));
                    }

                    mediaCursor.close();

                    Post post = new Post(id, title, excerpt, body, category, date, media);
                    
                    populatePost(post, postTitle, postExcerpt, postBody, postCategory, postDate, postImage);
                    Toast.makeText(this, "data fetched from DB", Toast.LENGTH_SHORT).show();

                }

                postCursor.close();
            } else {
                ApiClient.getApiInterface().getPost(id, new Callback<Post>() {
                    @Override
                    public void success(Post post, Response response) {
                        populatePost(post, postTitle, postExcerpt, postBody, postCategory, postDate, postImage);
                        Toast.makeText(getApplicationContext(), "data fetched from API", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            }
        }
    }
    
    private void populatePost(Post post, TextView title, TextView excerpt, TextView body, 
                              TextView category, TextView date, ImageView image) {
        title.setText(post.getTitle());

        if (!TextUtils.isEmpty(post.getExcerpt())) {
            excerpt.setText(TextHelper.processHtml(post.getExcerpt()));
        }

        if (!TextUtils.isEmpty(post.getBody())) {
            body.setText(TextHelper.processHtml(post.getBody()));
        } else {
            // if there's no body contributed, we don't want the excerpt to be bold
            excerpt.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
            // we should also enforce the body TextView not to take up any space in the layout
            body.setPadding(0, 0, 0, 0);
            // finally we add a bottom padding to the excerpt textview
            // so as to have it separated from the browse image gallery button
            //postExcerpt.setPadding(R.dimen.detail_text_padding, R.dimen.detail_text_padding, R.dimen.detail_text_padding, R.dimen.detail_text_padding);
        }

        category.setText(post.getCategory());
        date.setText(DateHelper.getHumanFriendlyDate(post.getDate()));

        if (post.hasImage()) {
            Log.d(TAG, "--------------- IMAGE: " + post.getImage());
        }
        
        if (post.hasImage()) {
            Picasso.with(getApplicationContext())
                    .load(post.getImage())
                    .into(image);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
