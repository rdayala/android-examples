package com.rdayala.example.githubreporetrofitbasicexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by rdayala on 7/22/2016.
 */
public interface GitHubService {

    @GET("users/{user}/repos")
    Call<List<GitHubRepo>> reposForUser(@Path("user") String username);
}
