package com.fknussel.news;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class NewsAdapter extends ArrayAdapter<Post> {

    public NewsAdapter(Context context, ArrayList<Post> posts) {
        super(context, 0, posts);
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
        ImageView postImage = (ImageView) convertView.findViewById(R.id.post_image);

        // Populate the data into the template view using the data object
        postTitle.setText(post.getTitle());

        Picasso.with(getContext())
                .load(post.getImage())
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .resize(200, 200)
                .centerCrop()
                .transform(new RoundedTransformation(100, 0))
                .into(postImage);

        // Return the completed view to render on screen
        return convertView;
    }
}
