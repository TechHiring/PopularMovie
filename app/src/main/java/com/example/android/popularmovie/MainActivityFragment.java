package com.example.android.popularmovie;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.GridView;

import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.Arrays;

import static android.R.attr.id;
import static android.R.attr.name;

/**
 * Created by Phil on 1/21/2017.
 */

public class MainActivityFragment extends Fragment {

    private MovieAdapter movieAdapter;          //Adapter


    private String top_rated = "top_rated";     //Strings how the movies are loaded
    private String popular = "popular";          // by most popular or top rated

    GridView gridView;          //Gridview for the movies

    public MainActivityFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false); //Inflate

        gridView = (GridView) rootView.findViewById(R.id.movies_grid);

        gridView.setOnItemClickListener (new AdapterView.OnItemClickListener() {


            /**
             * Store the data of the movie clicked by user and store it in intents
             * for the Movie details page
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {


                String poster = OpenMovieJson.getPoster(position);
                String overview = OpenMovieJson.getOverview(position);
                String release = OpenMovieJson.getRelease(position);
                String title = OpenMovieJson.getTitle(position);
                Double popularity = OpenMovieJson.getPopularity(position);
                Double vote_average = OpenMovieJson.getVoteAverage(position);


                Class destinationClass = MovieDetailActivity.class;
                Intent intentToStartDetailActivity = new Intent(getActivity(),destinationClass);

                intentToStartDetailActivity.putExtra("POSTER", poster);
                intentToStartDetailActivity.putExtra("OVERVIEW", overview);
                intentToStartDetailActivity.putExtra("RELEASE", release);
                intentToStartDetailActivity.putExtra("TITLE", title);
                intentToStartDetailActivity.putExtra("POPULARITY", popularity);
                intentToStartDetailActivity.putExtra("VOTE_AVERAGE", vote_average);

                startActivity(intentToStartDetailActivity);


            }});

        loadMovies(popular);

        return rootView;
    }

    /**
     *  Load the movies onto the grid either by most popular or top rated
    */
    public void loadMovies(String orderBy) {
        String sortBy = orderBy;
        new FetchMovieTask().execute(sortBy);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        /**
         * Action bar options
         */
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_popular) {
            loadMovies(popular);
            return true;
        }
        if (id == R.id.action_top_rated) {

            loadMovies(top_rated);
            return true;
        }

        return true;
    }







    public class FetchMovieTask extends AsyncTask<String, Void, String[]>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();

        }

        @Override
        protected String[] doInBackground(String...params) {
            if (params.length == 0) {
                return null;
            }

            String q = params[0];
            URL movieRequestUrl = MovieNetwork.buildUrl(q);

            try {
                String jsonMovieResponse = MovieNetwork
                        .getResponseFromHttpUrl(movieRequestUrl);

                String[] simpleJsonMovieData = OpenMovieJson
                      .getMovieStringsFromJson(getActivity(), jsonMovieResponse);




                return simpleJsonMovieData;


            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String[] movieData) {
            if (movieData != null) {

                movieAdapter = new MovieAdapter(getActivity(), Arrays.asList(movieData));
                gridView.setAdapter(movieAdapter);


            }

        }
    }

}
