package com.example.netflix.ui.main;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.netflix.data.MovieClient;
import com.example.netflix.pojo.ResultObj;
import com.example.netflix.pojo.MovieModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {
    MutableLiveData<MovieModel> moviemutableLiveData = new MutableLiveData<>();
    MutableLiveData<List<MovieModel>> moviesmutableLiveData = new MutableLiveData<>();
    MutableLiveData<List<ResultObj>> resultsmutableLiveData = new MutableLiveData<>();
    ArrayList<MovieModel> movies;
    ArrayList<ResultObj>resultObjs;

    //use this function to get specific movie on MovieProfile.
    public void getMovie(int id){
        MovieClient.getInstance().getmovie(id).enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                moviemutableLiveData.setValue(response.body());
            }
            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    //use this function to get popular movies on the Main Screen.
    public void getPopularMovies(){
        MovieClient.getInstance().getPopularmovies().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                //get the results as a jsonarray type
                JsonArray jsonArray = response.body().getAsJsonArray("results");
                //convert jsonarray to list of moviemdel objects
                Gson gson = new Gson();
                Type userListType = new TypeToken<ArrayList<MovieModel>>(){}.getType();
                movies = gson.fromJson(jsonArray, userListType);
                //set the moviesmutableLiveData
                moviesmutableLiveData.setValue(movies);
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    //use this function to get MoviesName options on SearchActivity.
    public void getResults(String keyword){
        MovieClient.getInstance().getResults(keyword).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                //get the results as a jsonarray type
                JsonArray jsonArray = response.body().getAsJsonArray("results");
                //convert jsonarray to list of moviemdel objects
                Gson gson = new Gson();
                Type userListType = new TypeToken<ArrayList<ResultObj>>(){}.getType();
                resultObjs = gson.fromJson(jsonArray, userListType);
                System.out.println("results object: " + resultObjs);
                //set the moviesmutableLiveData
                resultsmutableLiveData.setValue(resultObjs);
                System.out.println("live data "+resultsmutableLiveData.getValue());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    //use this function to get the movies by MovieName.
    public void getMovies(String movieName) {
        MovieClient.getInstance().getmovies(movieName).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                //get the results as a jsonarray type
                JsonArray jsonArray = response.body().getAsJsonArray("results");
                //convert jsonarray to list of moviemdel objects
                Gson gson = new Gson();
                Type userListType = new TypeToken<ArrayList<MovieModel>>(){}.getType();
                movies = gson.fromJson(jsonArray, userListType);
                //set the moviesmutableLiveData
                moviesmutableLiveData.setValue(movies);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    //this function get list of results {id,name} on SearchActivity.
    public MutableLiveData<List<ResultObj>> getResultsmutableLiveData() {
        return resultsmutableLiveData;
    }

}
