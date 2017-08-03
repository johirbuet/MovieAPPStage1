package com.johir.movies.moviestage1.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mislam on 8/2/17.
 */

public class Movie implements Parcelable {
    private String url;
    private String title;
    private String overview;
    private double rating;
    private String releaseDate;

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public double getRating() {
        return rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Movie(String url, String title, String overview, double rating, String releaseDate) {

        this.url = url;
        this.title = title;
        this.overview = overview;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeDouble(this.rating);
        dest.writeString(this.releaseDate);
    }

    protected Movie(Parcel in) {
        this.url = in.readString();
        this.title = in.readString();
        this.overview = in.readString();
        this.rating = in.readDouble();
        this.releaseDate = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
