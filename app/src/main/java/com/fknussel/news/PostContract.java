package com.fknussel.news;

import android.provider.BaseColumns;

public class PostContract {

    // DB specific constants
    // ---------------------------
    // This is the actual SQLite file that will contain the database
    public static final String DB_NAME = "newsfeed.db";

    // Database schemas are versioned.
    public static final int DB_VERSION = 1;

    // Sort order
    public static final String DEFAULT_SORT = Column.DATE + " DESC";

    // Table name
    public static final String TABLE = "posts";

    // Table definition
    public class Column {
        // Although the ID could be any name, there’s a convention in Android
        // to name it _id, for which it provides an API-level contract as well.
        public static final String ID = BaseColumns._ID;
        public static final String TITLE = "title";
        public static final String EXCERPT = "excerpt";
        public static final String BODY = "body";
        public static final String CATEGORY = "category";
        public static final String DATE = "date";
    }
}