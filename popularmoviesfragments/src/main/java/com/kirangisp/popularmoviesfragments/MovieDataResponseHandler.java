package com.kirangisp.popularmoviesfragments;

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

/**
 * This class is used to handle the formatted json objects and push the data from them to the
 * respective views in the fragments like, Movie Poster Fragment , Movie Details Info fragment
 */
public class MovieDataResponseHandler {

    private final static String JSON_RESPONSE_HANDLER_LOG_TAG = "JSON Response Handler";

    //Empty constructor
    public MovieDataResponseHandler() {

    }

    /*
    * Display te details of a single selected movie. Picasso is used to fetch thr thumbnail of a movie
    * */
    public void displayMovieDetailsNew(Context appContext, MovieDetails movieDtls) {

        try {

            //Find views and display the data from passed in movie details instance
            TextView titleTxtView = (TextView) ((Activity) appContext).findViewById(R.id.titleTextView);
            TextView releaseDateTxtView = (TextView) ((Activity) appContext).findViewById(R.id.releaseDateTextview);
            TextView voteTxtView = (TextView) ((Activity) appContext).findViewById(R.id.voteTextView);
            TextView synopsisTxtView = (TextView) ((Activity) appContext).findViewById(R.id.synopTextView);

            TextView releaseDate = (TextView) ((Activity) appContext).findViewById(R.id.releaseDateTextLiteral);
            TextView vote = (TextView) ((Activity) appContext).findViewById(R.id.voteTextLiteral);
            ImageView moviePosterImgView = (ImageView) ((Activity) appContext).findViewById(R.id.moviePosterImgView);

            titleTxtView.setText(movieDtls.getTitle());

            releaseDate.setText(R.string.release_date);
            releaseDateTxtView.setText(movieDtls.getReleaseYear());

            vote.setText(R.string.vote);
            voteTxtView.setText(movieDtls.getVote());

            synopsisTxtView.setText(movieDtls.getSynopsis());

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
                }
                else if (GlobalObjects.getDeviceDensity() == 2) {
                    //x hdpi
                    Picasso.with(appContext).load(posterURL).resize(400, 525).into(moviePosterImgView);
                }
                else if (GlobalObjects.getDeviceDensity() == 3) {
                    //xx hdpi
                    Picasso.with(appContext).load(posterURL).resize(600, 825).into(moviePosterImgView);
                }
                else if (GlobalObjects.getDeviceDensity() == 4) {
                    //xxx hdpi, higer then nexux 5
                    Picasso.with(appContext).load(posterURL).resize(800, 1000).into(moviePosterImgView);
                }
                else {
                    Picasso.with(appContext).load(posterURL).into(moviePosterImgView);
                }
            }
            catch (IllegalArgumentException e) {
                String errMsg = GlobalObjects.constructErrorMsg("Picasso IllegalArgumentException",
                        "Picasso.with()", e.getMessage());
                Log.e(JSON_RESPONSE_HANDLER_LOG_TAG, errMsg);
            }
            catch (Exception ex) {
                String errMsg = GlobalObjects.constructErrorMsg("Picasso Generic Exception", "Picasso.with()()", ex.getMessage());
                Log.e(JSON_RESPONSE_HANDLER_LOG_TAG, errMsg);
            }
        }

        catch (NullPointerException ex) {
            String errMsg = GlobalObjects.constructErrorMsg("Null Pointer Exception", "displayMovieDetailsNew()", ex.getMessage());
            Log.e(JSON_RESPONSE_HANDLER_LOG_TAG, errMsg);
        }
        catch (WindowManager.BadTokenException ex) {
            String errMsg = GlobalObjects.constructErrorMsg("Bad Token Exception", "displayMovieDetailsNew()", ex.getMessage());
            Log.e(JSON_RESPONSE_HANDLER_LOG_TAG, errMsg);
        }
        catch (Exception ex) {
            String errMsg = GlobalObjects.constructErrorMsg("Generic Exception", "displayMovieDetailsNew()", ex.getMessage());
            Log.e(JSON_RESPONSE_HANDLER_LOG_TAG, errMsg);
        }
    }

    /*
       * Display posters of favorite movies (count :twenty)
       * */
    public void displayMoviePosters(ArrayList<MovieData> moviePostersDataInList, Context context) {

        try {

            GridView postersGridView = (GridView) ((Activity) context).findViewById(R.id.moviePostersGrdView);
            postersGridView.setAdapter(new MovieDataAdapter(context, moviePostersDataInList));
        }

        catch (WindowManager.BadTokenException ex) {
            String errMsg = GlobalObjects.constructErrorMsg("Bad Token Exception", "displayMoviePosters()", ex.getMessage());
            Log.e(JSON_RESPONSE_HANDLER_LOG_TAG, errMsg);
        }
        catch (Exception ex) {
            String errMsg = GlobalObjects.constructErrorMsg("Generic Exception", "displayMoviePosters()", ex.getMessage());
            Log.e(JSON_RESPONSE_HANDLER_LOG_TAG, errMsg);
        }

    }

}
