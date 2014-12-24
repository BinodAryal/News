package com.fknussel.news;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class DetailActivity extends ActionBarActivity {

    public final String LOG_TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (getIntent() != null) {
            int id = getIntent().getIntExtra("id", 0) - 1;
            Log.d(LOG_TAG, "Post ID = " + id);

            TextView detailPostTitle = (TextView) findViewById(R.id.detail_post_title);
            TextView detailPostDetails = (TextView) findViewById(R.id.detail_post_details);

            setTitle(Post.getDummyPosts().get(id).getTitle());

            detailPostTitle.setText(Post.getDummyPosts().get(id).getTitle());
            detailPostDetails.setText("Date - Category");
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
