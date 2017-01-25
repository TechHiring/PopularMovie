package com.example.android.popularmovie;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Phil on 1/22/2017.
 */

public class MovieNetwork {


    /******************************************
     *         *************************
     *
     *         ADD API KEY BETWEEN THE "  "
     *      GET API KEY FROM SIGNING UP AT
     *      https://www.themoviedb.org/
     *
     *
     *
     *      ******************************
     * ************************************
     */
    private static final String api_key = "";



    private static final String BASE_MOVIE_URL = "http://api.themoviedb.org/3/movie/";
    final static String API_KEY_PARAM = "api_key";

    /**
     * Build the url
     */
    public static URL buildUrl(String movieQuery) {
        Uri builtUri = Uri.parse(BASE_MOVIE_URL).buildUpon()
                .appendPath(movieQuery)
                .appendQueryParameter(API_KEY_PARAM, api_key)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
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




