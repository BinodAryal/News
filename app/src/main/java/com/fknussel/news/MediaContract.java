package com.fknussel.news;

import android.provider.BaseColumns;

public class MediaContract {

    // Table name
    public static final String TABLE = "media";

    // Sort order
    public static final String DEFAULT_SORT = Column.ID + " ASC";

    // Table definition
    public class Column {
        // Although the ID could be any name, there’s a convention in Android
        // to name it _id, for which it provides an API-level contract as well.
        public static final String ID = BaseColumns._ID;
        public static final String POST_ID = "post_id";
        public static final String URL = "url";
        public static final String TYPE = "type";
    }
}