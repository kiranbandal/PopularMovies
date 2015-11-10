package com.kirangisp.popularmoviesui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.FetchMovieRequest;
import com.example.FetchMovieRequestType;
import com.example.GlobalObjects;
import com.kirangisp.popularmoviesfragments.FetchMovieDataTask;
import com.kirangisp.popularmoviesfragments.MoviePostersGridViewFragment;
import com.kirangisp.popularmoviesfragments.OnFragmentReady;

public class MoviePostersActivity extends AppCompatActivity implements OnFragmentReady {

    private final String POSTER_ACTIVITY_LOG_TAG = "Movie Posters Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_movie_posters_activity);

        //set action bar from the support library for the activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //if the device is not connected to the internet, then exit
        if (MoviePostersActivityHelper.isPhoneConnectedToInternet(this) == false) {

            String noInternetMsg = "Please make sure that the device is connected to the Internet!!";
            Toast.makeText(MoviePostersActivity.this, noInternetMsg, Toast.LENGTH_LONG).show();
            return;
        }

        // Check that the activity has the container to hold the fragment
        if (findViewById(R.id.container_for_movie_posters_fragment) == null) {
            return;
        }

        // if the activity is not getting created for the first time, then do not do anything
        if (savedInstanceState != null) {
            return;
        }


        //set the global objects like, device desnity; Poster ImageView size and padding, Poster Image Resize dimensions
        MoviePostersActivityHelper.setImageViewAndPosterResizeDimensions(this);

        try {

            //create fragment instance from the library and add it to the activity
            MoviePostersGridViewFragment postersGridViewFragment = new MoviePostersGridViewFragment();

            getSupportFragmentManager().beginTransaction().
                    add(R.id.container_for_movie_posters_fragment, postersGridViewFragment, GlobalObjects.getGridViewFragmentTag()).commit();
        } catch (Fragment.InstantiationException ex) {

            String errMsg = GlobalObjects.constructErrorMsg("Fragment Instantiation Exception", "onCreate()", ex.getMessage());
            Log.e(POSTER_ACTIVITY_LOG_TAG, errMsg);

        } catch (Exception ex) {
            String errMsg = GlobalObjects.constructErrorMsg("Generic Exception", "onCreate()", ex.getMessage());
            Log.e(POSTER_ACTIVITY_LOG_TAG, errMsg);
        }

    }


    //region Menu Code
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater mnuInflater = getMenuInflater();

        try {
            mnuInflater.inflate(R.menu.menu_movie_posters_actvity, menu);
        } catch (android.view.InflateException ex) {
            String errMsg = GlobalObjects.constructErrorMsg("Inflate Exception", "onCreateOptionsMenu()", ex.getMessage());
            Log.e(POSTER_ACTIVITY_LOG_TAG, errMsg);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //get Id of the clicked menu item
        int id = item.getItemId();

        if (id == R.id.action_sort_most_popular) {

            /*query the movie db api to get the movie data sorted on most popular
            * Construct the url by reading global constants file
            * Sample query : http://api.themoviedb.org/3/discover/movie?&api_key=5254a84a6c18f9e3cbec007290ea297c&sort_by=popularity.desc
            * */

            String sortMoviesOnPopularityURL = GlobalObjects.getPopularMoviesURL()
                    + GlobalObjects.getMovieApiKey()
                    + GlobalObjects.getSortKeyWord()
                    + GlobalObjects.getPopularityFieldName();

            //Request Object to be passed in to the Task
            FetchMovieRequest sortRequest = new FetchMovieRequest(sortMoviesOnPopularityURL,
                    FetchMovieRequestType.SORT_ON_POPULARITY,
                    this.getClass().getSimpleName());

            new FetchMovieDataTask(sortRequest, MoviePostersActivity.this).execute();
            return true;


        } else {


            //query the movie db api to get the movie data sorted on highest rating
            String sortMoviesOnHighestVotingUrl = GlobalObjects.getPopularMoviesURL()
                    + GlobalObjects.getMovieApiKey()
                    + GlobalObjects.getSortKeyWord()
                    + GlobalObjects.getHighestRatingFieldName();

            //Request Object to be passed in to the Task
            FetchMovieRequest sortRequest = new FetchMovieRequest(sortMoviesOnHighestVotingUrl,
                    FetchMovieRequestType.SORT_ON_HIGHTEST_RATING,
                    this.getClass().getSimpleName());

            new FetchMovieDataTask(sortRequest, MoviePostersActivity.this).execute();
            return true;

        }

    }
    //endregion

    //region Fragment Ready callback
    /*
    * Will be called when the fragment is ready and displayed in this activity
    * */
    @Override
    public void onReady() {

        MoviePostersGridViewFragment postersFrag = null;

        try {
            postersFrag = (MoviePostersGridViewFragment)
                    getSupportFragmentManager().findFragmentByTag(GlobalObjects.getGridViewFragmentTag());
        }
        catch (Fragment.InstantiationException ex) {
            String errMsg = GlobalObjects.constructErrorMsg("Fragment Instantiation Exception", "onReady()", ex.getMessage());
            Log.e(POSTER_ACTIVITY_LOG_TAG, errMsg);
        }

        if (postersFrag == null) return;

        //find the Movie Posters Grid view from the Fragment and
        // hook to it's onclick event in order to start the detail activity
        GridView moviePostersGrdView = postersFrag.getPostersGridView();

        if (moviePostersGrdView == null) return;

        moviePostersGrdView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //get the image view that was clicked by the user
                ImageView clickedPoster = (ImageView) view;
                String clickedMovieId = clickedPoster.getTag().toString();

                if (clickedMovieId == null) return;

                //create instance of Detail Info Activity and star it
                Intent detailActivityIntent = new Intent(MoviePostersActivity.this,
                        MovieDetailsInfoActivity.class);

                try {

                    detailActivityIntent.putExtra(GlobalObjects.getSelectedMovieIdLiteral(), clickedMovieId);
                    startActivity(detailActivityIntent);
                } catch (ActivityNotFoundException ex) {
                    String errMsg = GlobalObjects.constructErrorMsg("Activity Not Found Exception",
                            "moviePostersGrdView.setOnItemClickListener()", ex.getMessage());
                    Log.e(POSTER_ACTIVITY_LOG_TAG, errMsg);
                }
                catch (Exception ex) {
                    String errMsg = GlobalObjects.constructErrorMsg("Generic Exception",
                            "moviePostersGrdView.setOnItemClickListener()", ex.getMessage());
                    Log.e(POSTER_ACTIVITY_LOG_TAG, errMsg);
                }


            }
        }); //setOnItemClickListener ends...

    }
    //endregion
}
