package com.example.android.popularmovie;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Phil on 1/23/2017.
 */

public final class OpenMovieJson {


    static final String M_RESULTS = "results";
    static final String M_POSTER_PATH = "poster_path";
    static final String M_OVERVIEW ="overview";
    static final String M_RELEASE = "release_date";
    static final String M_TITLE = "title";
    static final String M_POPULARITY = "popularity";
    static final String M_VOTE = "vote_average";

    private static String[] parsedMoviePoster,
            parsedMovieOverview,
            parsedMovieRelease,
            parsedMovieTitle =null;

    private static Double[] parsedMoviePopularity,
            parsedMovieVoteAverage =null;

    /**
     * Goes through the JSON putting the data in their respective parsed array
     */
    public static String[] getMovieStringsFromJson(Context context,String movieJsonStr) throws JSONException{



        JSONObject movieJson = new JSONObject(movieJsonStr);

        JSONArray movieArray = movieJson.getJSONArray(M_RESULTS);

        parsedMoviePoster = new String[movieArray.length()];
        parsedMovieOverview = new String[movieArray.length()];
        parsedMovieRelease = new String[movieArray.length()];
        parsedMovieTitle =  new String[movieArray.length()];
        parsedMoviePopularity = new Double[movieArray.length()];
        parsedMovieVoteAverage = new Double[movieArray.length()];

        for(int i = 0; i <movieArray.length(); i++){

            JSONObject movieObject = movieArray.getJSONObject(i);
            String poster = movieObject.getString(M_POSTER_PATH);
            String overview = movieObject.getString(M_OVERVIEW);
            String release_date = movieObject.getString(M_RELEASE);
            String title = movieObject.getString(M_TITLE);
            Double popularity = movieObject.getDouble(M_POPULARITY);
            Double vote_average = movieObject.getDouble(M_VOTE);

            parsedMoviePoster[i] = "http://image.tmdb.org/t/p/w342/" +poster;
            parsedMovieOverview[i] = overview;
            parsedMovieRelease[i] = release_date.substring(0,4);
            parsedMovieTitle[i] = title;
            parsedMoviePopularity[i] = popularity;
            parsedMovieVoteAverage[i] = vote_average;



        }
        return parsedMoviePoster;
    }


    /**Getter methods*/

    public static String getPoster(int i){
        return parsedMoviePoster[i];

    }

    public static String getOverview(int i){
        return parsedMovieOverview[i];

    }

    public static String getRelease(int i){
        return parsedMovieRelease[i];

    }
    public static String getTitle(int i){
        return parsedMovieTitle[i];

    }

    public static Double getPopularity(int i){
        return parsedMoviePopularity[i];

    }

    public static Double getVoteAverage(int i){
        return parsedMovieVoteAverage[i];

    }

}
