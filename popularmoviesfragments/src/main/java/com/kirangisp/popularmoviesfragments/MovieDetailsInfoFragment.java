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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            //get the movie id that was selected and passed to the detail activity
            String selectedMovieID = getActivity().getIntent().getStringExtra(GlobalObjects.getSelectedMovieIdLiteral());

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

        } catch (NullPointerException ex) {
            String errMsg = GlobalObjects.constructErrorMsg("Null Pointer Exception", "onActivityCreated()", ex.getMessage());
            Log.e(MOVIE_DETAILS_FRAG_LOG_TAG, errMsg);
        } catch (Exception ex) {
            String errMsg = GlobalObjects.constructErrorMsg("Generic Exception", "onActivityCreated()", ex.getMessage());
            Log.e(MOVIE_DETAILS_FRAG_LOG_TAG, errMsg);
        }

    }
}
