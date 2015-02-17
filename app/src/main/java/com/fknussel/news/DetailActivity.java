package com.fknussel.news;

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

            ApiClient.getApiInterface().getPost(id, new Callback<Post>() {
                @Override
                public void success(Post post, Response response) {
                    postTitle.setText(post.getTitle());
                    
                    if (!TextUtils.isEmpty(post.getExcerpt())) {
                        postExcerpt.setText(TextHelper.processHtml(post.getExcerpt()));
                    }
                    
                    if (!TextUtils.isEmpty(post.getBody())) {
                        postBody.setText(TextHelper.processHtml(post.getBody()));
                    } else {
                        // if there's no body contributed, we don't want the excerpt to be bold
                        postExcerpt.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
                        // we should also enforce the body TextView not to take up any space in the layout
                        postBody.setPadding(0, 0, 0, 0);
                    }
                    
                    postCategory.setText(post.getCategory());
                    postDate.setText(DateHelper.getHumanFriendlyDate(post.getDate()));

                    Log.d(TAG, post.getBody());
                    
                    Picasso.with(getApplicationContext())
                            .load(post.getImage())
                            .into(postImage);
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
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
