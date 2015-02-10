package com.fknussel.news;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class NewsAdapter extends ArrayAdapter<Post> {

    public static final String TAG = NewsAdapter.class.getSimpleName();
    
    public NewsAdapter(Context context) {
        super(context, R.layout.list_item_post);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Post post = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item_post, parent, false);
        }

        // Alternate the background color
        if(position % 2 == 1) {
            convertView.setBackgroundColor(Color.argb(10, 224, 224, 224));
        } else {
            convertView.setBackgroundColor(Color.argb(100, 224, 224, 224));
        }

        // Lookup view for data population
        TextView postTitle = (TextView) convertView.findViewById(R.id.post_title);
        TextView postCategory = (TextView) convertView.findViewById(R.id.post_category);
        TextView postDate = (TextView) convertView.findViewById(R.id.post_date);
        ImageView postImage = (ImageView) convertView.findViewById(R.id.post_image);

        // Populate the data into the template view using the data object
        postTitle.setText(post.getTitle());
        postCategory.setText(post.getCategory());
        postDate.setText(post.getDate());
        
        if (post.hasImage()) {
            Picasso.with(getContext())
                    .load(post.getImage())
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_error)
                    .fit()
                    .centerCrop()
                    .transform(new RoundedTransformation(100, 0))
                    .into(postImage);
        } else {
            // no image contributed
            postImage.setImageResource(R.drawable.ic_error);
        }

        // Return the completed view to render on screen
        return convertView;
    }
}
