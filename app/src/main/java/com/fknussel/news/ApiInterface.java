package com.fknussel.news;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface ApiInterface {

    @GET("/posts/{page}")
    public void getPosts(@Path("page") int page, Callback<List<Post>> callback);

    @GET("/post/{id}")
    public void getPost(@Path("id") int id, Callback<Post> callback);
}
