package com.kzai.flixster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kzai.flixster.R;
import com.kzai.flixster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by kzai on 6/15/16.
 */
public class MoviesAdapter extends ArrayAdapter<Movie> {

    // View lookup cache
    private static class ViewHolder {
        TextView title;
        TextView overview;
        ImageView image;
    }

    public MoviesAdapter(Context context, List<Movie> movies) {
        super(context, R.layout.item_movie, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // Get the data item for this position
        Movie movie = getItem(position);

        ViewHolder viewHolder; // view lookup cache stored in tag
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.overview = (TextView) convertView.findViewById(R.id.tvOverview);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.ivMovieImage);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Populate the data into the template view using the data object
        viewHolder.title.setText(movie.getOriginalTitle());
        viewHolder.overview.setText(movie.getOverview());

        boolean isLandscape = getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

            if (isLandscape) {
            //Picasso.with(getContext()).load(movie.getBackdropPath()).into(viewHolder.image);
            Picasso.with(getContext()).load(movie.getBackdropPath()).resize(600,300).centerCrop().transform(new RoundedCornersTransformation(10, 10))
                    .placeholder(R.drawable.movielandscape)
                    .error(R.drawable.movielandscape)
                    .into(viewHolder.image);
        } else {
            //Picasso.with(getContext()).load(movie.getPosterPath()).into(viewHolder.image);
            Picasso.with(getContext()).load(movie.getPosterPath()).resize(400,600).centerCrop().transform(new RoundedCornersTransformation(10, 10))
                    .placeholder(R.drawable.movieportrait)
                    .error(R.drawable.movieportrait)
                    .into(viewHolder.image);
        }

        // Return the completed view to render on screen
        return convertView;
    }
}
