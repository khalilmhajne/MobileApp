package com.example.netflix.ui.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.netflix.R;
import com.example.netflix.pojo.MovieModel;
import com.example.netflix.pojo.ResultObj;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SearchActivity extends AppCompatActivity {
    MovieViewModel movieviewmodel;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_screan);
        ArrayList<String> src = new ArrayList<String>();

        //create autoCompleteTextView
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this , android.R.layout.simple_list_item_1,src);
        autoCompleteTextView.setAdapter(adapter);
        movieviewmodel = ViewModelProviders.of(this).get(MovieViewModel.class);

        //create OkButton and BackButton
        androidx.appcompat.widget.AppCompatButton OkButton = findViewById(R.id.Ok);
        Button BackButton = findViewById(R.id.Back);

        //listener of autoCompleteTextView
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println(s+" " +start+" " +before+" " +count);
                if(!s.toString().equals("")) {
                    movieviewmodel.getResults(s.toString());
                    List<String> results = new ArrayList<>();
                    List<ResultObj> rs = movieviewmodel.getResultsmutableLiveData().getValue();

                    System.out.println("results of keyword: " + rs);
                    if (rs != null) {
                        for (ResultObj i : rs) {
                            results.add(i.getName());
                        }
                        adapter.clear();
                        adapter.addAll(results);
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //listener of ok button
        OkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String MovieName = autoCompleteTextView.getText().toString();
                //navigation to MainActivity
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("MovieName",MovieName);
                startActivity(intent);
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

