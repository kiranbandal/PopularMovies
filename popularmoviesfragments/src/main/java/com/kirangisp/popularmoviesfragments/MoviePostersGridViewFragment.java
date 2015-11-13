package com.kirangisp.popularmoviesfragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import com.example.FetchMovieRequest;
import com.example.FetchMovieRequestType;
import com.example.GlobalObjects;
import com.kirangisp.fragmenthelpermodule.MoviePostersFragmentHelper;


/**
 * Created by User on 03-Nov-15.
 */
public class MoviePostersGridViewFragment extends Fragment {

    private final static String POSTERS_GRIDVIEW_FRAGMENT_LOg_TAG = "Posters Grid Fragment";
    private OnFragmentReady mListener; //interface instance to notify the Parent activity when this fragment is ready.
    View fragmentRootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        try{
            fragmentRootView = inflater.inflate(R.layout.layout_posters_gridview_fragment,container,false);
        }catch (android.view.InflateException ex) {
            String errMsg = GlobalObjects.constructErrorMsg("Inflate Exception", "onCreateOptionsMenu()", ex.getMessage());
            Log.e(POSTERS_GRIDVIEW_FRAGMENT_LOg_TAG, errMsg);
        }

        return fragmentRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try{

            /*set global Running Device properties like
            * Device density, orientation,
            * */
            MoviePostersFragmentHelper.setGlobalDeviceImgeViewProps(getActivity());

            //here we have to read the URL value from Global Constants
            String getPopularMoviesURL = GlobalObjects.getPopularMoviesURL() + GlobalObjects.getMovieApiKey();

            //this is the tag used while logging
            String logTagName = POSTERS_GRIDVIEW_FRAGMENT_LOg_TAG;

            //create request object, set the URL & log tag name and send it to the Fetch Movie task
            FetchMovieRequest rqst = new FetchMovieRequest(getPopularMoviesURL,
                    FetchMovieRequestType.ALL_MOVIES,
                    logTagName);

            //execute the task
            new FetchMovieDataTask(rqst, getActivity()).execute();

        }catch (NullPointerException nullExec){

            String errMsg = GlobalObjects.constructErrorMsg("Null Pointer Exception", "onActivityCreated()", nullExec.getMessage());
            Log.e(POSTERS_GRIDVIEW_FRAGMENT_LOg_TAG, errMsg);
        }
        catch (Exception ex){

            String errMsg = GlobalObjects.constructErrorMsg("Generic Exception", "onActivityCreated()", ex.getMessage());
            Log.e(POSTERS_GRIDVIEW_FRAGMENT_LOg_TAG, errMsg);
        }
    }


    //region When the Fragment becomes visible, motify the Activity.
    @Override
    public void onResume() {
        super.onResume();

        //notify the activity hosting this fragment
        mListener.onReady();
    }
    //endregion

    //region To Return reference to the Grid View to Parent Activity
    public GridView getPostersGridView(){

        try{

            GridView gv = (GridView) getActivity().findViewById(R.id.moviePostersGrdView);
            return gv;
        }catch (NoSuchFieldError err){
            Log.e("frag error", "errir is : " + err.getMessage());

            return null;
        }catch (Exception e){

            Log.e("frag error", "errir is : " + e.getMessage());
            return null;
        }

    }
    //endregion

    //region Set Interface Instance
    /*
    * This event is hooked to for just setting interface instance so that the activity hosting this fragment
    * can be notified.
    * */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity parentActivity = null;

        try {
            parentActivity = (Activity) context;
            this.mListener = (OnFragmentReady)parentActivity;
        }
        catch (final ClassCastException e) {
            throw new ClassCastException(parentActivity.toString() + " must implement OnFragmentReady");
        }
    }
    //endregion
}
