package com.kirangisp.fragmenthelpermodule;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.GlobalObjects;
import com.example.MovieData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by User on 13-Nov-15.
 */
public class MovieDataCustomAdapter extends BaseAdapter {

    private Context mContext; //for creating new image view
    private ArrayList<MovieData> mMovieDataList; //to hold the movies data passed in in the constructor

    private String mMoviePosterBaseURL; //to get the actual url of the movies poster
    private String mPosterImageSize;

    private final static String MOVIE_ADAPATER_LOG_TAG = "Movie Custom Adapter";

    public MovieDataCustomAdapter(Context c, ArrayList<MovieData> movieDataList) {
        //set member variables
        this.mContext = c;
        this.mMovieDataList = movieDataList;
        mPosterImageSize = GlobalObjects.getMoviePosterImageSize();
        mMoviePosterBaseURL = GlobalObjects.getMoviePosterBaseURL();
    }


    @Override
    public int getCount() {
        return mMovieDataList.size();
    }


    @Override
    public Object getItem(int position) {
        return mMovieDataList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {

            ImageView moviePosterImgView;

          /*
            * the path retrieved in the json is relative to the movie poster, so we need to manipulate it,
            * we need to append a base path ahead of this relative path to build the complete url
            * so, get path value from the MovieInfo instance at the position that is passed in
            * and construct the full url to the poster
            * */
            String posterURL;
            posterURL = mMoviePosterBaseURL + mPosterImageSize + mMovieDataList.get(position).getMoviePosterPath();

            if (convertView == null) {

                // ImgaeView getting created for the first time
                moviePosterImgView = new ImageView(mContext);

                moviePosterImgView.setLayoutParams(new GridView.LayoutParams
                        (GlobalObjects.getImageviewWidth(), GlobalObjects.getImageViewHeight()));

                //read padding value from global class and set it on the ImageView
                int imgViewPadding = Integer.parseInt(GlobalObjects.getImageviewPadding());
                moviePosterImgView.setPadding(imgViewPadding,
                        imgViewPadding,
                        imgViewPadding,
                        imgViewPadding);
            } else {
                //system sent the recycled image view
                moviePosterImgView = (ImageView) convertView;
            }

            //arratch the Movie Id as a Tag, so that it can be referred in detail activity
            moviePosterImgView.setTag(mMovieDataList.get(position).getMovieIDInAPI());

            //load image into the movie poster image view using Picasso
            Picasso.with(mContext).load(posterURL)
                    .resize(GlobalObjects.getPosterResizeWidth(),
                            GlobalObjects.getPosterResizewHeight())
                    .into(moviePosterImgView);

            //moviePosterImgView.setImageResource(R.drawable.sample_0);
            return moviePosterImgView;

        }
        catch (NullPointerException e) {
            String errMsg = GlobalObjects.constructErrorMsg("Null Pointer Exception", "getView()", e.getMessage());
            Log.e(MOVIE_ADAPATER_LOG_TAG, errMsg);
            return null;
        }

        catch (Exception e) {
            String errMsg = GlobalObjects.constructErrorMsg("Generic Exception", "getView()", e.getMessage());
            Log.e(MOVIE_ADAPATER_LOG_TAG, errMsg);
            return null;
        }
    }
}
