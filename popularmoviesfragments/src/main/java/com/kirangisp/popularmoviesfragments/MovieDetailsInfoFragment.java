package com.kirangisp.popularmoviesfragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.FetchMovieRequest;
import com.example.FetchMovieRequestType;
import com.example.GlobalObjects;
import com.kirangisp.fragmenthelpermodule.CommonFunctions;
import com.kirangisp.fragmenthelpermodule.HelperModuleMovieDetails;
import com.kirangisp.fragmenthelpermodule.MovieResponseHandler;

import java.util.HashMap;

/**
 * Created by User on 04-Nov-15.
 */
public class MovieDetailsInfoFragment extends Fragment {

    private final static String MOVIE_DETAILS_FRAG_LOG_TAG = "Movie Details Fragment";
    View mFragmenRoot;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        try {
            mFragmenRoot = inflater.inflate(R.layout.layout_movie_details_fragment, container, false);
        } catch (InflateException ex) {
            String errMsg = GlobalObjects.constructErrorMsg("Inflate Exception", "onCreateOptionsMenu()", ex.getMessage());
            Log.e(MOVIE_DETAILS_FRAG_LOG_TAG, errMsg);
        }

        return mFragmenRoot;
    }

    //save the data which is being displayed here, so that it can be used when in onActivityCreated event.
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //save the movie details which are currently being displayed in the Bundle
        //so that they can be accesses in onActivityCreated when the orientation is chaned
        try {
            outState.putParcelable(CommonFunctions.getMovieDetailsKey(),
                    CommonFunctions.getMovieDetailsInfo());
        } catch (Exception e) {
            String errMsg = GlobalObjects.constructErrorMsg("Generic Exception", "onSaveInstanceState()", e.getMessage());
            Log.e(MOVIE_DETAILS_FRAG_LOG_TAG, errMsg);
        }
    }

    public static HashMap getMovieDetailFragViews() {
        try {


            //create HashMap and push the IDs of required views to it
            HashMap viewIDsHashMap = new HashMap();
            viewIDsHashMap.put(GlobalObjects.getTitleTextViewKey(), R.id.titleTextView);
            viewIDsHashMap.put(GlobalObjects.getReleaseDateTextViewKey(), R.id.releaseDateTextview);
            viewIDsHashMap.put(GlobalObjects.getVoteTextViewKey(), R.id.voteTextView);
            viewIDsHashMap.put(GlobalObjects.getSynopsisTextViewKey(), R.id.synopTextView);
            viewIDsHashMap.put(GlobalObjects.getReleaseDateLiteralTextViewKey(), R.id.releaseDateTextLiteral);
            viewIDsHashMap.put(GlobalObjects.getVoteLiteralTextViewKey(), R.id.voteTextLiteral);
            viewIDsHashMap.put(GlobalObjects.getPosterImageViewKey(), R.id.moviePosterImgView);

            return viewIDsHashMap;
        } catch (Exception e) {

            return null;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            //get the movie id that was selected and passed to the detail activity
            String selectedMovieID = getActivity().getIntent().getStringExtra(GlobalObjects.getSelectedMovieIdLiteral());

            //meaning, the fragment is being created due to change in the device orientation
            if (savedInstanceState != null && savedInstanceState.getParcelable(CommonFunctions.getMovieDetailsKey()) != null) {

                //get the instance of MovieDetailsInfo from the bundle
                HelperModuleMovieDetails movieDetals =
                        savedInstanceState.getParcelable(CommonFunctions.getMovieDetailsKey());

                //instance of Response handelr class
                MovieResponseHandler responseHandler = new MovieResponseHandler();
                responseHandler.displayMovieDetails(getActivity(),
                        movieDetals,
                        getMovieDetailFragViews());
                return;
            }

            //region Get Movie detail using netwrok call since fragment is being created for the first time
            //construct the URL by reading values from the Global Constants class and using the movie id
            String getSelectedMovieDetailsURL =
                    GlobalObjects.getMovieDetailsURLPart1()
                            + selectedMovieID
                            + GlobalObjects.getMovieDetailsURLPart2()
                            + GlobalObjects.getMovieApiKey();

            //create request object, set the URL & Context and send it to the Fetch Movie task
            FetchMovieRequest rqst = new FetchMovieRequest(getSelectedMovieDetailsURL,
                    FetchMovieRequestType.DETAIL_OF_SINGLE_MOVIE,
                    this.getClass().getSimpleName());

            //execute the task
            new FetchMovieDataTask(rqst, getActivity()).execute();
            //endregion

        } catch (NullPointerException ex) {
            String errMsg = GlobalObjects.constructErrorMsg("Null Pointer Exception", "onActivityCreated()", ex.getMessage());
            Log.e(MOVIE_DETAILS_FRAG_LOG_TAG, errMsg);
        } catch (Exception ex) {
            String errMsg = GlobalObjects.constructErrorMsg("Generic Exception", "onActivityCreated()", ex.getMessage());
            Log.e(MOVIE_DETAILS_FRAG_LOG_TAG, errMsg);
        }

    }
}
