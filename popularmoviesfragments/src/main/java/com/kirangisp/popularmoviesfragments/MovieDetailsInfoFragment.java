package com.kirangisp.popularmoviesfragments;

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

        try{
            mFragmenRoot = inflater.inflate(R.layout.layout_movie_details_fragment, container, false);
        }
        catch (InflateException ex){
            String errMsg = GlobalObjects.constructErrorMsg("Inflate Exception", "onCreateOptionsMenu()", ex.getMessage());
            Log.e(MOVIE_DETAILS_FRAG_LOG_TAG, errMsg);
        }

        return mFragmenRoot;
    }

    /*
    * When the movie details info is being rendered, the textual info like, Release date ,Vote etc.
    * was initially being placed at the top of the fragment, because Image rendering happens after a bit.
    * To fix this, the Movie Poster Image is being given the minimum size depending on the device density.
    *
    * */
    private void setImageViewDimension() {
        try {

            //handle the size of the movie poster image view here
            ImageView posterView = (ImageView) mFragmenRoot.findViewById(R.id.moviePosterImgView);

            //load image into the movie poster image view using Picasso, resize it depending on te screen desneity
            if (GlobalObjects.getDeviceDensity() == 1.5) {
                //hdpi
                posterView.setMinimumWidth(250);
                posterView.setMinimumHeight(325);

            } else if (GlobalObjects.getDeviceDensity() == 2) {
                //x hdpi
                posterView.setMinimumWidth(400);
                posterView.setMinimumHeight(525);
            } else if (GlobalObjects.getDeviceDensity() == 3) {
                //xx hdpi
                posterView.setMinimumWidth(600);
                posterView.setMinimumHeight(825);

            } else if (GlobalObjects.getDeviceDensity() == 4) {
                //xxx hdpi, higer then nexux 5
                posterView.setMinimumWidth(800);
                posterView.setMinimumHeight(1000);

            }
        } catch (NullPointerException e) {
            Log.e("movieDetailsFragLogTag","Null Reference Error occured in the method,setImageViewDimension(), error : " + e.getMessage());
        }
        catch (Exception e){
            Log.e("movieDetailsFragLogTag","Error occured in the method,setImageViewDimension(), error : " + e.getMessage());
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {

            setImageViewDimension();

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

        }
        catch (NullPointerException ex){
            String errMsg = GlobalObjects.constructErrorMsg("Null Pointer Exception", "onActivityCreated()", ex.getMessage());
            Log.e(MOVIE_DETAILS_FRAG_LOG_TAG, errMsg);
        }

        catch (Exception ex) {
            String errMsg = GlobalObjects.constructErrorMsg("Generic Exception", "onActivityCreated()", ex.getMessage());
            Log.e(MOVIE_DETAILS_FRAG_LOG_TAG, errMsg);
        }

    }
}
