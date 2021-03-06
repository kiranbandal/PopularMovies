package com.kirangisp.fragmenthelpermodule;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.GlobalObjects;
import com.example.RunningDeviceProps;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by User on 14-Nov-15.
 */
public class MovieResponseHandler {

    private final static String MOVIE_RESPONSE_HANDLER_LOG_TAG = "Movie Response Handler";

    private Drawable mPlaceHolderDrawable = null;
    private Drawable mErrorDrawable = null;

    public void displayMovieDetails(Context appContext, HelperModuleMovieDetails movieDtls, HashMap movieDetailsFragViewIDs) {

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

            //set the Place holder image from Drawable if not set already
            if (mPlaceHolderDrawable == null) {
                setPlaceHolderDrawable(appContext);
            }

            //set the Place holder image from Drawable if not set already
            if (mErrorDrawable == null) {
                setErrorDrawable(appContext);
            }

            //to be sued in Picasso api
            String posterURL =
                    mMoviePosterBaseURL
                            + mPosterImageSize
                            + movieDtls.getMoviePosterPath();

            try {
                //load image into the movie poster image view using Picasso, resize it depending on te screen desneity
                Picasso.with(appContext).load(posterURL)
                        .placeholder(mPlaceHolderDrawable)
                        .error(mErrorDrawable)
                        .resize(
                                RunningDeviceProps.getMovieDetailsPosterResizeWidth(),
                                RunningDeviceProps.getMovieDetailsPosterResizeHeight())
                        .into(moviePosterImgView);
            }
            catch (ClassCastException ex) {
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
    public void displayMoviePosters(ArrayList<HelperModuleMovieData> moviePostersDataInList, Context context, int moviePostersGridView) {

        try {

            GridView postersGridView = (GridView) ((Activity) context).findViewById(moviePostersGridView);
            postersGridView.setAdapter(new MovieDataCustomAdapter(context, moviePostersDataInList));
        } catch (WindowManager.BadTokenException ex) {
            String errMsg = GlobalObjects.constructErrorMsg("Bad Token Exception", "displayMoviePosters()", ex.getMessage());
            Log.e(MOVIE_RESPONSE_HANDLER_LOG_TAG, errMsg);
        } catch (Exception ex) {
            String errMsg = GlobalObjects.constructErrorMsg("Generic Exception", "displayMoviePosters()", ex.getMessage());
            Log.e(MOVIE_RESPONSE_HANDLER_LOG_TAG, errMsg);
        }

    }

    private void setPlaceHolderDrawable(Context cntxt) {
        try {
            //get Place holder image from Drawable
            Bitmap placeHolderBMP = BitmapFactory.decodeResource(cntxt.getResources(), R.drawable.placeholder);
            Bitmap resizedPlaceHolderBMP = Bitmap.createScaledBitmap(placeHolderBMP, RunningDeviceProps.getMovieDetailsPosterResizeWidth(),
                    RunningDeviceProps.getMovieDetailsPosterResizeHeight(), true);
            mPlaceHolderDrawable = new BitmapDrawable(cntxt.getResources(), resizedPlaceHolderBMP);

        } catch (Exception e) {
            String errMsg = GlobalObjects.constructErrorMsg("Generic Exception", "setPlaceHolderDrawble", e.getMessage());
            Log.e(MOVIE_RESPONSE_HANDLER_LOG_TAG, errMsg);
        }
    }

    private void setErrorDrawable(Context cntxt) {
        try {
            //get Place holder image from Drawable
            Bitmap errorBMP = BitmapFactory.decodeResource(cntxt.getResources(), R.drawable.error);
            Bitmap resizedErrorBMP = Bitmap.createScaledBitmap(errorBMP, RunningDeviceProps.getMovieDetailsPosterResizeWidth(),
                    RunningDeviceProps.getMovieDetailsPosterResizeHeight(), true);
            mErrorDrawable = new BitmapDrawable(cntxt.getResources(), resizedErrorBMP);

        } catch (Exception e) {
            String errMsg = GlobalObjects.constructErrorMsg("Generic Exception", "setErrorDrawable", e.getMessage());
            Log.e(MOVIE_RESPONSE_HANDLER_LOG_TAG, errMsg);
        }
    }
}
