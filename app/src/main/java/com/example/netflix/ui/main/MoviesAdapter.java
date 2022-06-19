package com.example.netflix.ui.main;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.netflix.R;
import com.example.netflix.pojo.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private List<MovieModel> movies = new ArrayList<>();
    private RecyclerViewClickListner listener;
    public MoviesAdapter(RecyclerViewClickListner recyclerViewClickListner) {
        listener = recyclerViewClickListner;
    }
    public MoviesAdapter() {

    }

    @NonNull
    @Override
    //create a new RecyclerView.ViewHolder and initializes some private fields to be used by RecyclerView.
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false));
    }


    @SuppressLint("SetTextI18n")
    @Override
    // Called by RecyclerView to display the data at the specified position
    public void onBindViewHolder(@NonNull MoviesAdapter.MovieViewHolder holder, int position) {
        String imageUri = "https://image.tmdb.org/t/p/w500"+movies.get(position).getPoster_path();
        Picasso.get().load(imageUri).into(holder.MovieCover);
        holder.MovieName.setText(movies.get(position).getOriginal_title());
        holder.MovieDate.setText(movies.get(position).getRelease_date()+"");
        holder.MovieId.setText(movies.get(position).getId()+"");
        holder.Avg_vote.setText(movies.get(position).getVote_average()+"");
    }

    @Override
    //Returns The itemId represented by this ViewHolder.
    public int getItemCount() {
        return movies.size();
    }
    public void SetMovie(MovieModel movie){
        this.movies = new ArrayList<>();
        this.movies.add(movie);
        notifyDataSetChanged();
    }
    public void SetArrayList(List <MovieModel> all){
        this.movies =  all;
        notifyDataSetChanged();
    }

    //the data of movieDetails will change when binded to new viewholder
    //describes an item view and metadata about its place within the RecyclerView
    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        ImageView MovieCover;
        TextView MovieName ;
        TextView MovieDate ;
        TextView MovieId;
        TextView Avg_vote;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            MovieCover = itemView.findViewById(R.id.movie_cover);
            MovieName = itemView.findViewById(R.id.MovieName);
            MovieDate  = itemView.findViewById(R.id.MovieDate);
            MovieId = itemView.findViewById(R.id.MovieId);
            Avg_vote = itemView.findViewById(R.id.Avg_vote);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view,getAdapterPosition());
        }
    }
    public interface RecyclerViewClickListner{
        void onClick(View view , int Position);
    }
}
