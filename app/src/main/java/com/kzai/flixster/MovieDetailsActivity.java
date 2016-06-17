package com.kzai.flixster;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class MovieDetailsActivity extends AppCompatActivity {

    ImageView ivMovieImage;
    TextView tvTitle;
    TextView tvOverview;
    RatingBar rbMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_title);

        ivMovieImage = (ImageView) findViewById(R.id.ivMovieImage);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvOverview = (TextView) findViewById(R.id.tvOverview);
        rbMovie = (RatingBar) findViewById(R.id.rbMovie);

        tvTitle.setText(getIntent().getStringExtra("title"));
        tvOverview.setText(getIntent().getStringExtra("overview"));
        Picasso.with(this).load(getIntent().getStringExtra("backdrop")).transform(new RoundedCornersTransformation(10, 10)).into(ivMovieImage);

        rbMovie.setRating((float) getIntent().getDoubleExtra("voteAverage", 0) / 2);
    }




}
