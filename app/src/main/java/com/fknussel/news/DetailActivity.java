package com.fknussel.news;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


public class DetailActivity extends ActionBarActivity {

    public final String TAG = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (getIntent() != null) {
            int id = getIntent().getIntExtra("id", 0) - 1;
            Log.d(TAG, "Post ID = " + id);

            TextView postTitle = (TextView) findViewById(R.id.detail_post_title);
//            TextView postDetails = (TextView) findViewById(R.id.detail_post_details);
            ImageView postImage = (ImageView) findViewById(R.id.detail_post_image);

            postTitle.setText(Post.getDummyPosts().get(id).getTitle());
//            postDetails.setText("Date - Category");

            DisplayMetrics displaymetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            int viewportHeight = displaymetrics.heightPixels;
            int viewportWidth = displaymetrics.widthPixels;
            
            Picasso.with(this)
                    .load("http://i.imgur.com/aX3MaJ9.jpg")
                    .resize(viewportWidth, viewportHeight/2)
                    .centerCrop()
                    .into(postImage);
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
