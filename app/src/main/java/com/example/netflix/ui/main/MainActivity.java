package com.example.netflix.ui.main;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.netflix.R;
import com.example.netflix.pojo.MovieModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
        MovieViewModel movieviewmodel;
        MoviesAdapter.RecyclerViewClickListner listener;
        @SuppressLint("SetTextI18n")
        @Override
        protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create object of viewmodel
        movieviewmodel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieviewmodel.getPopularMovies();

        //create recycle view and adapter
        RecyclerView recyclerView = findViewById(R.id.recycler);
        setOnClickListener();
        MoviesAdapter movieAdapter = new MoviesAdapter(listener);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(movieAdapter);
        movieviewmodel.moviesmutableLiveData.observe(this, new Observer<List<MovieModel>>() {
                @Override
                public void onChanged(List<MovieModel> movieModels) {
                        movieAdapter.SetArrayList(movieModels);
                }
        });

        //Responsible of SearchActivity's Navigation
        String MovieName ="";
        Bundle extras = getIntent().getExtras();
        if (extras!=null){
                TextView textView = findViewById(R.id.textView);
                textView.setText("Results:");
                MovieName=extras.getString("MovieName");
                movieviewmodel.getMovies(MovieName);
                movieviewmodel.moviesmutableLiveData.observe(this, new Observer<List<MovieModel>>() {
                        @Override
                        public void onChanged(List<MovieModel> movieModels) {
                                movieAdapter.SetArrayList(movieModels);
                        }
                });

                }

        //Create search button in the main page ...
         Button SearchButton = findViewById(R.id.search_button);
         SearchButton.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                         Intent intent2 = new Intent(getApplicationContext(),SearchActivity.class);
                         startActivity(intent2);
                 }
         });
        }

        //Create listener of recyclerview (click on one movie).
        private void setOnClickListener() {
                listener = new MoviesAdapter.RecyclerViewClickListner() {
                        @Override
                        public void onClick(View view, int Position) {
                                Intent intent = new Intent(getApplicationContext(),MovieProfileActivity.class);
                                System.out.println("extre: "+movieviewmodel.movies.get(Position).getId());
                                intent.putExtra("ID",movieviewmodel.movies.get(Position).getId()+"");
                                startActivity(intent);
                        }
                };
        }

}