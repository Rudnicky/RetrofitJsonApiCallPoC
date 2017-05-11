package com.example.arakjel.retrofitjsonapicallpoc;

import java.util.List;
import retrofit.Callback;
import retrofit.http.GET;


public interface MoviesAPI {

    @GET("/retrofit/movies.json")
    public void getMovies(Callback<List<Movie>> response);
}
