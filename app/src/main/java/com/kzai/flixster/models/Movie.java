package com.kzai.flixster.models;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by kzai on 6/15/16.
 */
public class Movie {


    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w780/%s", backdropPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public Double getVoteAverage() { return voteAverage; }

    public String getYoutubePath() { return youtubePath; }

    String posterPath;
    String backdropPath;
    String originalTitle;
    String overview;
    Double voteAverage;
    String youtubePath;
    Integer id;
    Boolean hasVideo;

    String trailerPath;

    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString("poster_path");
        this.backdropPath = jsonObject.getString("backdrop_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
        this.voteAverage = jsonObject.getDouble("vote_average");
        this.id = jsonObject.getInt("id");

        String jsonResult = getJSONTrailerResults();
        if (jsonResult == null) {
            this.hasVideo = false;
            this.youtubePath = "";
        } else {
            this.hasVideo = true;
            this.youtubePath = getJSONTrailerResults();
        }
        Log.d("DEBUG", this.youtubePath);
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray array) {
        ArrayList<Movie> results = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            try {
                results.add(new Movie(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return results;
    }

    public String getJSONTrailerResults() {
        String url = "https://api.themoviedb.org/3/movie/" + this.id.toString() + "/trailers?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieTrailerJsonArray = null;

                try {
                    movieTrailerJsonArray = response.getJSONArray("youtube");
                    trailerPath = null;
                    trailerPath = movieTrailerJsonArray.getJSONObject(0).getString("source");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

        return trailerPath;
    }

}
