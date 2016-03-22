package com.example.retrofitdemo.API;

import com.example.retrofitdemo.model.GitModel;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by tejalpar on 3/21/16.
 */
public interface GitAPI {

    @GET("/users/{user}")
    public void getFeedFromUser(@Path("user") String user, Callback<GitModel> response);
}
