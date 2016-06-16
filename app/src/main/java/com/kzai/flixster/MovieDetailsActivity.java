package com.kzai.flixster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    ImageView ivMovieImage;
    TextView tvTitle;
    TextView tvOverview;
    RatingBar rbMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ivMovieImage = (ImageView) findViewById(R.id.ivMovieImage);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvOverview = (TextView) findViewById(R.id.tvOverview);
        rbMovie = (RatingBar) findViewById(R.id.rbMovie);

        tvTitle.setText(getIntent().getStringExtra("title"));
        tvOverview.setText(getIntent().getStringExtra("overview"));
        Picasso.with(this).load(getIntent().getStringExtra("backdrop")).into(ivMovieImage);
        //rbMovie.setNumStars(getIntent().getDoubleExtra("voteAverage", 0));
        rbMovie.setRating((float) getIntent().getDoubleExtra("voteAverage", 0) / 2);
        double h = getIntent().getDoubleExtra("voteAverage", 0) / 2;
        //Log.d("DEBUG", Double.toString(h));
        //rbMovie.setNumStars(Math.round(rbMovie.getRating() / 2));
        //Picasso.with(this).load(getIntent().getStringExtra("backdrop")).resize(600,300).centerCrop().into(ivMovieImage);
    }




}
