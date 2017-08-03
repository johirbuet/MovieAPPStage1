package com.johir.movies.moviestage1.data;

/**
 * Created by mislam on 8/2/17.
 */

public class MovieData {
    public static final String MOVIE_DATA = "movie_data";
    private static Movie [] movies;
    public static Movie [] getMovies(){
        return  movies;
    }
    public static void setMovies(Movie [] passedmovies){
        movies = passedmovies;
    }
}
