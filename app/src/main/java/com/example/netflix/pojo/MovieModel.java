package com.example.netflix.pojo;

import java.util.Date;

public class MovieModel {
    private String poster_path;
    private String original_title ;
    private String release_date ;
    private String overview ;
    private String original_language;
    private int id ;
    private double popularity ;
    private double vote_average;
    private double runtime ;

    public String getOverview() {
        return overview;
    }
    public String getOriginal_language() {
        return original_language;
    }
    public String getOriginal_title() {
        return original_title;
    }
    public String getPoster_path() {
        return poster_path;
    }
    public String getRelease_date() {
        return release_date;
    }
    public String getDescription() { return overview; }
    public int getId(){ return id;}
    public double getRuntime() {
        return runtime;
    }
    public double getVote_average() {
        return vote_average;
    }
    public double getPopularity() {
        return popularity;
    }


}
