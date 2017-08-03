package com.johir.movies.moviestage1.NetworkUtils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static android.R.attr.format;
import static android.content.ContentValues.TAG;

/**
 * Created by mislam on 8/2/17.
 */

public class NetworkUtils {

    public static URL buildUrl(String path) {
          Uri uri =Uri.parse(path).buildUpon().build();
        URL url =null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
