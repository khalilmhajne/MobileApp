package com.example.netflix.pojo;

import com.example.netflix.pojo.MovieModel;

import java.util.List;

public class SimpleReq {
    String page;
    List<MovieModel> results =null;
    public String getPage() {
        return page;
    }
    public void setPage(String page) {
        this.page = page;
    }
    public List<MovieModel> getResults() {
        return results;
    }
    public void setResults(List<MovieModel> results) {
        this.results = results;
    }
}
