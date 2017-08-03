package com.johir.movies.moviestage1;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.johir.movies.moviestage1.NetworkUtils.NetworkUtils;
import com.johir.movies.moviestage1.adapters.MovieAdapter;
import com.johir.movies.moviestage1.data.Movie;
import com.johir.movies.moviestage1.data.MovieData;
import com.johir.movies.moviestage1.utils.MovieDataHelpers;
import com.johir.movies.moviestage1.utils.MovieJSONUtil;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    MovieAdapter adapter;
    GridView gv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null || !savedInstanceState.containsKey("movies")){
            String path = MovieDataHelpers.API_BASE_URL+ MovieDataHelpers.POPULAR_MOVIES_END_POINT +
                    MovieDataHelpers.KEY_PREFIX + MovieDataHelpers.API_KEY;
            loadMoviedata(path);
        }
        else
        {
            MovieData.setMovies((Movie [])savedInstanceState.getParcelableArray("movies"));
        }
        setContentView(R.layout.activity_main);

        gv = (GridView) findViewById(R.id.gridview);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movie movie = (Movie)adapterView.getItemAtPosition(i);
                Intent intent = new Intent(MainActivity.this,MovieDetailActivity.class);
                intent.putExtra(MovieData.MOVIE_DATA,movie);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putParcelableArray("movies",MovieData.getMovies());
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_top_rated){
            String path = MovieDataHelpers.API_BASE_URL+ MovieDataHelpers.TOP_RATED_MOVIES_END_POINT +
                    MovieDataHelpers.KEY_PREFIX + MovieDataHelpers.API_KEY;
            loadMoviedata(path);
            return true;
        }
        if(id == R.id.action_popular){
            String path = MovieDataHelpers.API_BASE_URL+ MovieDataHelpers.POPULAR_MOVIES_END_POINT +
                    MovieDataHelpers.KEY_PREFIX + MovieDataHelpers.API_KEY;
            loadMoviedata(path);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadMoviedata(String path){
        URL url = NetworkUtils.buildUrl(path);
        new MovieAsyncTask().execute(url);
    }

    public class MovieAsyncTask extends AsyncTask<URL, Void, Movie[]> {

        @Override
        protected Movie [] doInBackground(URL... urls) {
            URL url = urls[0];
            String result = null;
            try {
                Log.v("URL",url.toString());
                result = NetworkUtils.getResponseFromHttpUrl(url);
                Movie [] movies = MovieJSONUtil.getJSon(result);
                Log.v("SIZE",movies.length +"");
                return  movies;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Movie [] data) {
            MovieData.setMovies(data);
            adapter = new MovieAdapter(MainActivity.this,MovieData.getMovies());
            gv.setAdapter(adapter);
        }
    }


}
