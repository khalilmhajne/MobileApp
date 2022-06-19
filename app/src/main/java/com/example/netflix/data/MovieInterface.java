package com.example.netflix.data;

import com.example.netflix.pojo.MovieModel;
import com.example.netflix.pojo.SimpleReq;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieInterface {

    @GET("movie/{movieId}?api_key=53b04d9ae5d6fe274d5ff200a50f4240")
    public Call<MovieModel> getMovie(@Path("movieId") int id);

   @GET("movie/550?api_key=53b04d9ae5d6fe274d5ff200a50f4240")
   public Call<MovieModel> getMovie();

    @GET("movie/popular?api_key=53b04d9ae5d6fe274d5ff200a50f4240")
    public Call<JsonObject> getPopularMovies();

    @GET("search/keyword?api_key=53b04d9ae5d6fe274d5ff200a50f4240")
    public Call<JsonObject> getResults(@Query("query") String keyword);

    @GET("search/movie?api_key=53b04d9ae5d6fe274d5ff200a50f4240")
    Call<JsonObject> getMovies(@Query("query") String MovieName);
}
