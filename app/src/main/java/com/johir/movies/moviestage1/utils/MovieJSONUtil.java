package com.johir.movies.moviestage1.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.johir.movies.moviestage1.data.Movie;

/**
 * Created by mislam on 8/2/17.
 */

public class MovieJSONUtil {
    public static Movie [] getJSon(String json){
        Movie [] movies = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray array = jsonObject.getJSONArray("results");
            int size = array.length();
            movies = new Movie[size];
            for(int i=0; i<size;i++){
                JSONObject movieJson = (JSONObject) array.get(i);
                String title = movieJson.getString("title");
                String url = MovieDataHelpers.BASE_URL+ MovieDataHelpers.SIZE+movieJson.getString("poster_path");
                String overview = movieJson.getString("overview");
                double rating = movieJson.getDouble("vote_average");
                String release = movieJson.getString("release_date");
                Movie movie = new Movie(url,title,overview,rating,release);
                movies[i] = movie;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movies;
    }
}
