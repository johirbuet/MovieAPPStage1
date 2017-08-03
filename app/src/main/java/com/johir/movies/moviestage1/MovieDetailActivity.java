package com.johir.movies.moviestage1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.johir.movies.moviestage1.data.Movie;
import com.johir.movies.moviestage1.data.MovieData;
import com.squareup.picasso.Picasso;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class MovieDetailActivity extends AppCompatActivity {

    TextView heading;
    TextView year;
    TextView rating;
    TextView overview;
    ImageView poster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        heading = (TextView) findViewById(R.id.tv_header);
        year = (TextView) findViewById(R.id.tv_year);
        rating = (TextView) findViewById(R.id.tv_rating);
        overview = (TextView) findViewById(R.id.tv_overview);
        poster = (ImageView) findViewById(R.id.iv_image);
        Intent intent = getIntent();
        if(intent != null && intent.hasExtra(MovieData.MOVIE_DATA)){
            Movie movie = intent.getParcelableExtra(MovieData.MOVIE_DATA);
            Log.v("MOVIE",movie.getUrl());
            Picasso.with(this).load(movie.getUrl()).resize(512,512).into(poster);
            heading.setText(movie.getTitle());
            year.setText(movie.getReleaseDate().substring(0,4));
            rating.setText(movie.getRating()+"/10");
            overview.setText(movie.getOverview());
        }
        else
        {
            Log.v("Error","Null");
        }
    }
}
