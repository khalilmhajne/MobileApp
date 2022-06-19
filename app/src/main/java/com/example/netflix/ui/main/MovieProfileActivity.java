package com.example.netflix.ui.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netflix.R;
import com.example.netflix.pojo.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class MovieProfileActivity extends AppCompatActivity {
    MovieViewModel movieviewmodel;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_profile);
        TextView name = findViewById(R.id.Name);
        TextView date = findViewById(R.id.MovieDate);
        TextView description = findViewById(R.id.Description);
        TextView popularity = findViewById(R.id.Popularity);
        TextView runtime = findViewById(R.id.runtime);
        TextView vote = findViewById(R.id.vote);
        TextView language = findViewById(R.id.language);
        ImageView imageView = findViewById(R.id.imageView);
        Button BackButton = findViewById(R.id.button_back);


        //Responsible for Navigation from MainActivity
        String ID ="";
        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            ID=extras.getString("ID");
            System.out.println(ID);
        }

        //create ViewModel's object and adapter
        movieviewmodel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieviewmodel.getMovie(Integer.parseInt(ID));
        MoviesAdapter movieAdapter = new MoviesAdapter();
        movieviewmodel.moviemutableLiveData.observe(this, new Observer<MovieModel>() {
            @Override
            public void onChanged(MovieModel movieModel) {
                movieAdapter.SetMovie(movieModel);
                String imageUri = "https://image.tmdb.org/t/p/w500"+movieviewmodel.moviemutableLiveData.getValue().getPoster_path();
                Picasso.get().load(imageUri).into(imageView);
                name.setText(movieviewmodel.moviemutableLiveData.getValue().getOriginal_title());
                date.setText("Realse Date: "+movieviewmodel.moviemutableLiveData.getValue().getRelease_date()+"");
                description.setText("Description\n\n"+movieviewmodel.moviemutableLiveData.getValue().getDescription());
                popularity.setText("popularity: "+movieviewmodel.moviemutableLiveData.getValue().getPopularity()+"");
                runtime.setText(movieviewmodel.moviemutableLiveData.getValue().getRuntime()+" min");
                language.setText("language: "+movieviewmodel.moviemutableLiveData.getValue().getOriginal_language()+"");
                vote.setText(movieviewmodel.moviemutableLiveData.getValue().getVote_average()+"");
            }
        });

        //listener of back button
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });



    }
}

