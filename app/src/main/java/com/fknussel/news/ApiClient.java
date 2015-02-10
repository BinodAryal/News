package com.fknussel.news;

import retrofit.RestAdapter;

public class ApiClient {

    private static final String API_URL = "http://api.freyre.com.ar";

    public static ApiInterface getApiInterface() {
        // Create a very simple REST adapter which points to the API endpoint
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        // Create an instance of our API interface
        ApiInterface api = restAdapter.create(ApiInterface.class);
        return api;
    }
}
