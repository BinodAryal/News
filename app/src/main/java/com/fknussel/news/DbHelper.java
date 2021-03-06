package com.fknussel.news;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final String TAG = DbHelper.class.getSimpleName();

    public DbHelper(Context context) {
        super(context, DbContract.DB_NAME, null, DbContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Called only once first time we create the database
        // First create the posts table
        String sqlCreatePosts = String.format(
                "CREATE TABLE %s (%s int primary key, %s text, %s text, %s text, %s text, %s text)",
                PostContract.TABLE,
                PostContract.Column.ID,
                PostContract.Column.TITLE,
                PostContract.Column.EXCERPT,
                PostContract.Column.BODY,
                PostContract.Column.CATEGORY,
                PostContract.Column.DATE
        );
        db.execSQL(sqlCreatePosts);
        
        // Then create the media table (one to many relationship
        // Each post may have zero, one or multiple media items attached
        String sqlCreateMedia = String.format(
                "CREATE TABLE %s (%s int primary key, %s int, %s text, %s text)",
                MediaContract.TABLE,
                MediaContract.Column.ID,
                MediaContract.Column.POST_ID,
                MediaContract.Column.URL,
                MediaContract.Column.TYPE
        );
        db.execSQL(sqlCreateMedia);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Gets called whenever existing version != new version, i.e. schema changed.
        // This typically happens when you change the schema and release the application
        // update to users who already have older version of your app.
        // Typically you do ALTER TABLE ... but we wanna keep it simple here.
        db.execSQL("drop table if exists " + PostContract.TABLE);
        db.execSQL("drop table if exists " + MediaContract.TABLE);
        onCreate(db);
    }
}