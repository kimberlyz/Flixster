package com.kzai.flixster;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class MovieDetailsActivity extends YouTubeBaseActivity {

    private static final String DEVELOPER_KEY = "AIzaSyCh9Msc9OIFkvqI9B4Q5L105xgZaJcMyrI";

    ImageView ivMovieImage;
    TextView tvTitle;
    TextView tvOverview;
    RatingBar rbMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
/*
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_title); */

        ivMovieImage = (ImageView) findViewById(R.id.ivMovieImage);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvOverview = (TextView) findViewById(R.id.tvOverview);
        rbMovie = (RatingBar) findViewById(R.id.rbMovie);

        tvTitle.setText(getIntent().getStringExtra("title"));
        tvOverview.setText(getIntent().getStringExtra("overview"));

        //Picasso.with(this).load(getIntent().getStringExtra("backdrop")).transform(new RoundedCornersTransformation(10, 10)).into(ivMovieImage);
        Picasso.with(this).load(getIntent().getStringExtra("backdrop")).resize(600, 300).centerCrop().transform(new RoundedCornersTransformation(10, 10))
                .placeholder(R.drawable.movielandscape)
                .error(R.drawable.movielandscape)
                .into(ivMovieImage);

        rbMovie.setRating((float) getIntent().getDoubleExtra("voteAverage", 0) / 2);

        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.player);
        if (youTubePlayerView != null) {
            youTubePlayerView.initialize(DEVELOPER_KEY,
                    new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                            YouTubePlayer youTubePlayer, boolean b) {
                            // do any work here to cue video, play video, etc.
                            youTubePlayer.cueVideo(getIntent().getStringExtra("youtube"));
                        }
                        @Override
                        public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                            YouTubeInitializationResult youTubeInitializationResult) {

                        }
                    });
        }
    }




}
