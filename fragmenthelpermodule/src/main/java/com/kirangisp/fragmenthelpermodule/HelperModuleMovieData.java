package com.kirangisp.fragmenthelpermodule;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ArrayList of this class is used on MoviePosters fragment. Also, this class implements Parcelable
 * so that ArrayList of instances of this class can be saved into Bundle.
 */
public class HelperModuleMovieData implements Parcelable{

    public HelperModuleMovieData(String moviePosterPathInput, String movieIdInAPIInput) {
        this.moviePosterPath = moviePosterPathInput;
        this.movieIdInAPI = movieIdInAPIInput;
    }

    //Constructor needed for Parcel CREATOR
    HelperModuleMovieData(Parcel input){
        movieIdInAPI = input.readString();
        moviePosterPath = input.readString();
    }

    String moviePosterPath;
    String movieIdInAPI;

    public String getMoviePosterPath() {
        return moviePosterPath;
    }


    public String getMovieIDInAPI() {
        return movieIdInAPI;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {

        //Write movie ID and it's poster path to the parcel
        dest.writeString(movieIdInAPI);
        dest.writeString(moviePosterPath);

    }

    public static final Parcelable.Creator<HelperModuleMovieData> CREATOR
            = new Parcelable.Creator<HelperModuleMovieData>() {

        public HelperModuleMovieData createFromParcel(Parcel in) {
            return new HelperModuleMovieData(in);
        }

        public HelperModuleMovieData[] newArray(int size) {
            return new HelperModuleMovieData[size];
        }
    };


}
