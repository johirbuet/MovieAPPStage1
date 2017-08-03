package com.johir.movies.moviestage1.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;


import com.johir.movies.moviestage1.R;
import com.squareup.picasso.Picasso;
import static android.R.attr.resource;
import com.johir.movies.moviestage1.data.Movie;
/**
 * Created by mislam on 8/2/17.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {
    public static final String LOG_TAG = MovieAdapter.class.getSimpleName();
    public MovieAdapter( Context context,  Movie[] objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie currMoview = getItem(position);
        String url = currMoview.getUrl();
        Log.v("URL",url);
        Context context = getContext();
        if(convertView == null ){
            convertView = LayoutInflater.from(context).inflate(R.layout.movie_list_item,parent,false);
        }
        ImageView iv = (ImageView) convertView.findViewById(R.id.iv_movie_poster);
        Picasso.with(context).load(url).resize(512,512).
                into(iv);
        return convertView;
    }

    @Override
    public void clear() {
        super.clear();
    }
}
