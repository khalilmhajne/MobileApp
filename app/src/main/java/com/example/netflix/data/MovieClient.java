package com.example.netflix.data;

import com.example.netflix.pojo.MovieModel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieClient {
    private static final  String BASE_URL = "https://api.themoviedb.org/3/";
    private MovieInterface movieInterface ;
    private static MovieClient Instance;

    public MovieClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        movieInterface = retrofit.create(MovieInterface.class);
    }

    public static MovieClient getInstance() {
        if(Instance == null){
            Instance = new MovieClient();
        }
        return Instance;
    }

    public Call<MovieModel> getmovie(int id){
        return movieInterface.getMovie(id);
    }

    public Call<JsonObject> getPopularmovies() {
        return movieInterface.getPopularMovies();
    }
    public Call<JsonObject> getResults(String keyword) {
        return movieInterface.getResults(keyword);
    }

    public Call<JsonObject> getmovies(String movieID) {
        return movieInterface.getMovies(movieID);
    }
}
