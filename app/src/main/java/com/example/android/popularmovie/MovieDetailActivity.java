package com.example.android.popularmovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static android.os.Build.VERSION.RELEASE;

/**
 * Created by Phil on 1/21/2017.
 */

public class MovieDetailActivity extends AppCompatActivity {

    private String mForecast;
    private ImageView posterDisplay;
    private TextView titleView;
    private TextView releaseView;
    private TextView voteAvgView;
    private TextView overviewView;

    /**
     * Store the data from the intents into their respective views
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        posterDisplay = (ImageView) findViewById(R.id.test_image);
        titleView = (TextView) findViewById(R.id.title);
        releaseView = (TextView) findViewById(R.id.release_date);
        voteAvgView = (TextView) findViewById(R.id.vote_average);
        overviewView = (TextView) findViewById(R.id.overview);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
                String posterString = extras.getString("POSTER");
                String overviewString = extras.getString("OVERVIEW");
                String titleString =  extras.getString("TITLE");
                String releaseString = extras.getString("RELEASE");
                Double voteAverageString = extras.getDouble("VOTE_AVERAGE");

                Picasso.with(this).load(posterString).into(posterDisplay);
                titleView.setText(titleString);
                releaseView.setText("("+releaseString+")");
                voteAvgView.setText(Double.toString(voteAverageString)+"/10");
                overviewView.setText(overviewString);



        }

    }
}
