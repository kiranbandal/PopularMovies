package com.kirangisp.fragmenthelpermodule;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.GlobalObjects;
import com.example.MovieData;
import com.example.MovieDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by User on 14-Nov-15.
 */
public class MovieResponseHandler {

    private final static String MOVIE_RESPONSE_HANDLER_LOG_TAG = "Movie Response Handler";

    public void displayMovieDetails(Context appContext, MovieDetails movieDtls, HashMap movieDetailsFragViewIDs) {

        try {

            //Find views and display the data from passed in movie details instance
            TextView titleTxtView = (TextView) ((Activity) appContext).findViewById((Integer)
                    movieDetailsFragViewIDs.get(GlobalObjects.getTitleTextViewKey()));

            TextView releaseDateTxtView = (TextView) ((Activity) appContext).findViewById((Integer)
                    movieDetailsFragViewIDs.get(GlobalObjects.getReleaseDateTextViewKey()));

            TextView voteTxtView = (TextView) ((Activity) appContext).findViewById((Integer)
                    movieDetailsFragViewIDs.get(GlobalObjects.getVoteTextViewKey()));

            TextView synopsisTxtView = (TextView) ((Activity) appContext).findViewById((Integer)
                    movieDetailsFragViewIDs.get(GlobalObjects.getSynopsisTextViewKey()));

            //TextViews for Literal Texts
            TextView releaseDate = (TextView) ((Activity) appContext).findViewById((Integer)
                    movieDetailsFragViewIDs.get(GlobalObjects.getReleaseDateLiteralTextViewKey()));

            TextView vote = (TextView) ((Activity) appContext).findViewById((Integer)
                    movieDetailsFragViewIDs.get(GlobalObjects.getVoteLiteralTextViewKey()));

            //find image View
            ImageView moviePosterImgView = (ImageView) ((Activity) appContext).findViewById((Integer)
                    movieDetailsFragViewIDs.get(GlobalObjects.getPosterImageViewKey()));

            titleTxtView.setText(movieDtls.getTitle());

            releaseDate.setText("Release Date:");
            releaseDateTxtView.setText(movieDtls.getReleaseYear());

            vote.setText("Average Vote:");
            voteTxtView.setText(movieDtls.getVote());

            //If the sysnopsis returned is null, then
            if (movieDtls.getSynopsis().toUpperCase().equals("NULL")) {
                synopsisTxtView.setText("No Synopsis Found!!");
            } else {
                synopsisTxtView.setText(movieDtls.getSynopsis());
            }

            //construct the url to getthe the movie thumbnail using Picasso
            String mMoviePosterBaseURL = GlobalObjects.getMoviePosterBaseURL();
            String mPosterImageSize = GlobalObjects.getMoviePosterImageSize();

            //to be sued in Picasso api
            String posterURL =
                    mMoviePosterBaseURL
                            + mPosterImageSize
                            + movieDtls.getMoviePosterPath();

            try {
                //load image into the movie poster image view using Picasso, resize it depending on te screen desneity
                if (GlobalObjects.getDeviceDensity() == 1.5) {
                    //hdpi
                    Picasso.with(appContext).load(posterURL).resize(250, 325).into(moviePosterImgView);
                } else if (GlobalObjects.getDeviceDensity() == 2) {
                    //x hdpi
                    Picasso.with(appContext).load(posterURL).resize(400, 525).into(moviePosterImgView);
                } else if (GlobalObjects.getDeviceDensity() == 3) {
                    //xx hdpi
                    Picasso.with(appContext).load(posterURL).resize(600, 825).into(moviePosterImgView);
                } else if (GlobalObjects.getDeviceDensity() == 4) {
                    //xxx hdpi, higer then nexux 5
                    Picasso.with(appContext).load(posterURL).resize(800, 1000).into(moviePosterImgView);
                } else {
                    Picasso.with(appContext).load(posterURL).into(moviePosterImgView);
                }
            }

            catch (ClassCastException ex){
                String errMsg = GlobalObjects.constructErrorMsg("Class Cast Exception",
                        "displayMovieDetails()", ex.getMessage());
                Log.e(MOVIE_RESPONSE_HANDLER_LOG_TAG, errMsg);
            }
            catch (IllegalArgumentException e) {
                String errMsg = GlobalObjects.constructErrorMsg("Picasso IllegalArgumentException",
                        "Picasso.with()", e.getMessage());
                Log.e(MOVIE_RESPONSE_HANDLER_LOG_TAG, errMsg);
            }

            catch (Exception ex) {
                String errMsg = GlobalObjects.constructErrorMsg("Picasso Generic Exception", "Picasso.with()()", ex.getMessage());
                Log.e(MOVIE_RESPONSE_HANDLER_LOG_TAG, errMsg);
            }
        }

        catch (NullPointerException ex) {
            String errMsg = GlobalObjects.constructErrorMsg("Null Pointer Exception", "displayMovieDetailsNew()", ex.getMessage());
            Log.e(MOVIE_RESPONSE_HANDLER_LOG_TAG, errMsg);
        }

        catch (WindowManager.BadTokenException ex) {
            String errMsg = GlobalObjects.constructErrorMsg("Bad Token Exception", "displayMovieDetailsNew()", ex.getMessage());
            Log.e(MOVIE_RESPONSE_HANDLER_LOG_TAG, errMsg);
        }

        catch (Exception ex) {
            String errMsg = GlobalObjects.constructErrorMsg("Generic Exception", "displayMovieDetailsNew()", ex.getMessage());
            Log.e(MOVIE_RESPONSE_HANDLER_LOG_TAG, errMsg);
        }
    }

    /*
      * Display posters of favorite movies (count :twenty by design of movie db api)
      * */
    public void displayMoviePosters(ArrayList<MovieData> moviePostersDataInList, Context context, int moviePostersGridView) {

        try {

            GridView postersGridView = (GridView) ((Activity) context).findViewById(moviePostersGridView);
            postersGridView.setAdapter(new MovieDataCustomAdapter(context, moviePostersDataInList));
        }

        catch (WindowManager.BadTokenException ex) {
            String errMsg = GlobalObjects.constructErrorMsg("Bad Token Exception", "displayMoviePosters()", ex.getMessage());
            Log.e(MOVIE_RESPONSE_HANDLER_LOG_TAG, errMsg);
        }
        catch (Exception ex) {
            String errMsg = GlobalObjects.constructErrorMsg("Generic Exception", "displayMoviePosters()", ex.getMessage());
            Log.e(MOVIE_RESPONSE_HANDLER_LOG_TAG, errMsg);
        }

    }
}
