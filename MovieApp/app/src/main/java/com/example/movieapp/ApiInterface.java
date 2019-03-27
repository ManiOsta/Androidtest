package com.example.movieapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("moviedb/movies.json")
    Call<MoviePojo> getTopRatedMovies();
}
