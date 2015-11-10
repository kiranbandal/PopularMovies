package com.kirangisp.popularmoviesui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.GlobalObjects;
import com.kirangisp.popularmoviesfragments.MovieDetailsInfoFragment;

public class MovieDetailsInfoActivity extends AppCompatActivity {

    private final String DETAIL_ACTIVITY_LOG_TAG = "Movie Details Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_movie_details_info_activity);

        //set up the action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //setup "Up" button on the activity action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addDetailsFragment(savedInstanceState);

    }

    private void addDetailsFragment(Bundle bndl) {

        // Check that the activity has the container to hold the movie detail fragment
        if (findViewById(R.id.MovieDetailFragContainer) == null) {
            return;
        }

        // if the activity is not getting created for the first time, then do not do anything
        if (bndl != null) {
            return;
        }

        // Create instance of our Movie Details fragment to add to the activity FrameLayout
        MovieDetailsInfoFragment movieDetailsFragment = new MovieDetailsInfoFragment();

        try {
            // Add the fragment to it's destined FrameLayout and commit changes
            getSupportFragmentManager().beginTransaction().
                    add(R.id.MovieDetailFragContainer, movieDetailsFragment, "DETAIL FRAGMENT").commit();
        } catch (Fragment.InstantiationException ex) {
            String errMsg = GlobalObjects.constructErrorMsg("Fragment Instantiation Exception", "addDetailsFragment()", ex.getMessage());
            Log.e(DETAIL_ACTIVITY_LOG_TAG, errMsg);
        }
    }
}
